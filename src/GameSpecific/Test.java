package GameSpecific;

import Structure.ActorAgent;
import Structure.ActorBox;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test extends Application {

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        ActorBox floor = buildFloor();

        Level testLevel = new Level();

        Player world = new Player(2097152, 10100080); // replace this with a level object, and have the player be a group that implements actor
        Group cameraGroup = buildCameraGroup(world); // Gun is built into camera group
        world.getChildren().add(buildPointlight(0, -20, 0));

        world.getChildren().add(buildAmbientLight());
        Model tree = buildTree();
        tree.setTranslateX(5);
        world.getChildren().add(tree);

        Model lightHouse = buildHouse();
        lightHouse.setTranslateX(-20);
        world.getChildren().add(lightHouse);
        System.out.println(lightHouse.getBoundsInParent());

        world.getChildren().add(floor);

        Group group = setupSubscene(world);
        Scene scene = setupScene(group, world);

        world.setCameraGroup(cameraGroup);
        world.addCameraGroupToWorld();

        addCrosshairs(group);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCrosshairs(Group g){
        Image crossImg = new Image(getClass().getResourceAsStream("../crosshairs.png"));
        ImageView cross = new ImageView(crossImg);
        cross.setPreserveRatio(true);
        cross.setFitWidth(50);
        cross.setFitHeight(50);

        g.getChildren().add(cross);
        cross.setTranslateX(1920/2 - (cross.getBoundsInParent().getWidth()/2));
        cross.setTranslateY(1080/2 - (cross.getBoundsInParent().getHeight()/2));
    }

    private Scene setupScene(Group group, Player world) {
        Scene scene = new Scene(group, 1920, 1080, true);
        scene.setFill(Color.SKYBLUE);
        scene.setOnKeyPressed(event -> world.setKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> world.setKeyUp(event.getCode()));
        scene.setOnMousePressed(event -> world.setMouseClicked(true));
        scene.setOnMouseReleased(event -> world.setMouseClicked(false));
        scene.setCursor(Cursor.NONE);

        return scene;
    }

    private Group setupSubscene(Player p) {
        SubScene subScene = new SubScene(p, 1920, 1080, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.SKYBLUE);
        subScene.setCamera(p.getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        p.start();

        return group;
    }

    private AmbientLight buildAmbientLight() {
        AmbientLight a = new AmbientLight();
        a.setColor(Color.LIGHTGOLDENRODYELLOW);

        return a;
    }

    private PointLight buildPointlight(int translateX, int translateY, int translateZ) {
        PointLight p = new PointLight();
        p.setColor(Color.LIGHTGOLDENRODYELLOW);

        p.setTranslateX(translateX);
        p.setTranslateY(translateY);
        p.setTranslateZ(translateZ);

        return p;
    }

    private ActorBox buildFloor() {
        ActorBox floor = new Floor(500, 1, 500) {
            @Override
            public void act() {

            }
        };

        floor.setMaterial(new PhongMaterial(Color.SANDYBROWN));
        floor.setTranslateY(3);

        System.out.println(floor.getBoundsInParent());

        return floor;
    }

    private Model buildTree() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Model tree = new Model();
        try {
            objImporter.read(getClass().getResource("../tree.obj"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MeshView[] treeMeshViews = objImporter.getImport();
        for (MeshView treePart : treeMeshViews) {
            treePart.setScaleX(3);
            treePart.setScaleY(3);
            treePart.setScaleZ(3);
        }
        treeMeshViews[0].setTranslateY(2);

        System.out.println(treeMeshViews.length);
        tree.getChildren().addAll(treeMeshViews);
        tree.setTranslateX(0);

        return tree;
    }

    private Model buildHouse() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Model house = new Model();
        try {
            objImporter.read(getClass().getResource("../house.obj"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MeshView[] houseMeshViews = objImporter.getImport();
        house.getChildren().addAll(houseMeshViews);
        house.setTranslateY(3);

        return house;
    }

    private Group buildGun() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Model gun = new Model();
        try {
            objImporter.read(getClass().getResource("../KSR-29 sniper rifle new_obj.obj"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MeshView[] gunMeshViews = objImporter.getImport();
        System.out.println(gunMeshViews.length);
        for (MeshView m : gunMeshViews) {
            m.setScaleX(0.5);
            m.setScaleY(0.5);
            m.setScaleZ(0.5);
        }

        // 0 is scope
        gunMeshViews[0].setTranslateY(1.6);
        gunMeshViews[0].setTranslateX(0.3);

        // 2 is bottom of magazine
        gunMeshViews[2].setTranslateY(-1.08);
        gunMeshViews[2].setTranslateX(0.54);

        MeshView[] newGunMeshViews = new MeshView[]{gunMeshViews[0], gunMeshViews[3], gunMeshViews[2]};
        gun.getChildren().addAll(newGunMeshViews);

        gun.setTranslateY(-1.5);
        gun.setTranslateX(0.8);
        gun.setTranslateZ(4);
        gun.setRotationAxis(Rotate.Y_AXIS);
        gun.setRotate(-95);

        gun.getTransforms().add(new Rotate(-2, Rotate.Z_AXIS));

        return gun;
    }

    private Group buildCameraGroup(Player p) {
        PerspectiveCamera worldCamera = p.getCamera();
        worldCamera.setFarClip(5000.0);
        worldCamera.setNearClip(0.01);

        Group cameraGroup = new Group(buildGun(), worldCamera);
        cameraGroup.setRotationAxis(Rotate.Y_AXIS);

        return cameraGroup;
    }
}