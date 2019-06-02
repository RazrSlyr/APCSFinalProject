package GameSpecific;

import Structure.ActorBox;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class TestWL extends Application {

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

        Group cameraGroup = new Group();

        LevelWL testLevel = new LevelWL();
        testLevel.setCameraGroup(cameraGroup);

        PlayerWL player = new PlayerWL(1337, 1337, testLevel); // replace this with a level object, and have the player be a group that implements actor

        testLevel.setPlayer(player);

        cameraGroup = buildCameraGroup(player);
        testLevel.setCameraGroup(cameraGroup);
        player.setCameraGroup(cameraGroup);
        testLevel.addCameraGroupToWorld();

        testLevel.getChildren().add(buildPointlight(0, -20, 0));

        testLevel.getChildren().add(buildAmbientLight());
        Model tree = buildTree();
        tree.setTranslateX(5);
        testLevel.getChildren().add(tree);

        Model lightHouse = buildHouse();
        lightHouse.setTranslateX(-20);
        testLevel.getChildren().add(lightHouse);
        System.out.println(lightHouse.getBoundsInParent());

        testLevel.getChildren().add(floor);

        Group group = setupSubscene(testLevel);
        Scene scene = setupScene(group, testLevel);

        addCrosshairs(group);


        primaryStage.setScene(scene);
        primaryStage.show();

        testLevel.start();
    }

    private Scene setupScene(Group group, LevelWL l) {
        Scene scene = new Scene(group, 800, 600, true);
        scene.setFill(Color.SKYBLUE);
        scene.setOnKeyPressed(event -> l.setKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> l.setKeyUp(event.getCode()));
        scene.setOnMousePressed(event -> l.setMouseClicked(true));
        scene.setOnMouseReleased(event -> l.setMouseClicked(false));
        scene.setOnMouseExited(event -> l.getPlayer().moveMouse(1500, 1000));
        scene.setCursor(Cursor.NONE);

        return scene;

    }

    private Group setupSubscene(LevelWL l) {
        SubScene subScene = new SubScene(l, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.SKYBLUE);
        subScene.setCamera(l.getPlayer().getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        l.start();

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
        ActorBox floor = new Floor(100, 1, 100) {
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

    public ActorBox buildExtraEpic() {
        ActorBox box = new ActorBox(5, 5, 5) {
            private int rotate = 0;

            @Override
            public void act() {
                setRotate(rotate + 1);
                rotate++;
            }
        };

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("../extraEpic.jpg")));
        box.setMaterial(material);
        box.setTranslateX(10);
        box.setTranslateY(-10);

        return box;
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

        return gun;
    }

    private Group buildCameraGroup(PlayerWL p) {
        PerspectiveCamera worldCamera = p.getCamera();
        worldCamera.setFarClip(5000.0);
        worldCamera.setNearClip(0.01);

//        PointLight pLight = new PointLight();
//        pLight.translateXProperty().bind(worldCamera.translateXProperty());
//        pLight.translateYProperty().bind(worldCamera.translateYProperty());
//        pLight.translateZProperty().bind(worldCamera.translateZProperty());

        worldCamera.setRotationAxis(new Point3D(0, 1, 0));
        Group cameraGroup = new Group(buildGun(), worldCamera);
        cameraGroup.setRotationAxis(Rotate.Y_AXIS);

        return cameraGroup;
    }

    private void addCrosshairs(Group g) {
        Image crossImg = new Image(getClass().getResourceAsStream("../crosshairs.png"));
        ImageView cross = new ImageView(crossImg);
        cross.setPreserveRatio(true);
        cross.setFitWidth(50);
        cross.setFitHeight(50);

        g.getChildren().add(cross);
        cross.setTranslateX(800 / 2 - (cross.getBoundsInParent().getWidth() / 2));
        cross.setTranslateY(600 / 2 - (cross.getBoundsInParent().getHeight() / 2));
    }

}