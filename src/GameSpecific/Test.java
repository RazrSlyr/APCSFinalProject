package GameSpecific;

import Structure.ActorBox;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Test extends Application {

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);

        ActorBox floor = buildFloor();

        Player world = new Player(010000000, 10100080); // replace this with a level object, and have the player be a group that implements actor
        Group cameraGroup = buildCameraGroup(world); // Gun is built into camera group
        world.getChildren().add(buildPointlight(0, -20, 0));

        world.getChildren().add(buildAmbientLight());
        Group tree = buildTree();
        tree.setTranslateX(5);
        world.getChildren().add(tree);

        Group lightHouse = buildHouse();
        lightHouse.setTranslateX(-20);
        world.getChildren().add(lightHouse);

        world.getChildren().add(floor);

        Group group = setupSubscene(world);
        Scene scene = setupScene(group, world);

        world.setCameraGroup(cameraGroup);
        world.addCameraGroupToWorld();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene setupScene(Group group, Player world) {
        Scene scene = new Scene(group, 800, 600, true);
        scene.setFill(Color.SKYBLUE);
        scene.setOnKeyPressed(event -> world.setKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> world.setKeyUp(event.getCode()));
        scene.setCursor(Cursor.NONE);

        return scene;
    }

    public Group setupSubscene(Player p) {
        SubScene subScene = new SubScene(p, 800, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.SKYBLUE);
        subScene.setCamera(p.getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        p.start();

        return group;
    }

    public AmbientLight buildAmbientLight() {
        AmbientLight a = new AmbientLight();
        a.setColor(Color.LIGHTGOLDENRODYELLOW);

        return a;
    }

    public PointLight buildPointlight(int translateX, int translateY, int translateZ) {
        PointLight p = new PointLight();
        p.setColor(Color.LIGHTGOLDENRODYELLOW);

        p.setTranslateX(translateX);
        p.setTranslateY(translateY);
        p.setTranslateZ(translateZ);

        return p;
    }

    public ActorBox buildFloor() {
        ActorBox floor = new Floor(100, 1, 100) {
            @Override
            public void act() {

            }
        };

        floor.setMaterial(new PhongMaterial(Color.SANDYBROWN));
        floor.setTranslateY(3);

        return floor;
    }

    public Group buildTree() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Group tree = new Group();
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

        return tree;
    }

    public Group buildHouse() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Group house = new Group();
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
        box.setTranslateX(6);

        return box;
    }

    public Group buildGun() {
        ObjModelImporter objImporter = new ObjModelImporter();

        Group gun = new Group();
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

    public Group buildCameraGroup(Player p) {
        PerspectiveCamera worldCamera = p.getCamera();
        worldCamera.setFarClip(5000.0);
        worldCamera.setNearClip(0.01);

//        PointLight pLight = new PointLight();
//        pLight.translateXProperty().bind(worldCamera.translateXProperty());
//        pLight.translateYProperty().bind(worldCamera.translateYProperty());
//        pLight.translateZProperty().bind(worldCamera.translateZProperty());

        Group cameraGroup = new Group(buildGun(), worldCamera);
        cameraGroup.setRotationAxis(Rotate.Y_AXIS);

        return cameraGroup;
    }
}