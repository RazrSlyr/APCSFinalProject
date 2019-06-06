package GameSpecific;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class TestWL extends Application {

    private final int width = 1920;
    private final int height = 1080;

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        Group cameraGroup = new Group();

        LevelWL testLevel = new Main();
        testLevel.setCameraGroup(cameraGroup);
        PlayerWL player = new PlayerWL(0, -230, 1337, 1337, testLevel);
        testLevel.setPlayer(player);

        cameraGroup = buildCameraGroup(player);
        cameraGroup.setTranslateZ(250);
        testLevel.setCameraGroup(cameraGroup);
        player.setCameraGroup(cameraGroup);
        testLevel.addCameraGroupToWorld();

        Group group = setupSubscene(testLevel);
        Scene scene = setupScene(group, testLevel);

        addCrosshairs(group);

        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(testLevel.getChildren().size());

        testLevel.start();
    }

    private Scene setupScene(Group group, LevelWL l) {
        Scene scene = new Scene(group, width, height, true);
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
        SubScene subScene = new SubScene(l, width, height, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.SKYBLUE);
        subScene.setCamera(l.getPlayer().getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        l.start();

        return group;
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

    private PointLight buildPointlight(double translateX, double translateY, double translateZ) {
        PointLight p = new PointLight();
        p.setColor(Color.LIGHTGOLDENRODYELLOW);

        p.setTranslateX(translateX);
        p.setTranslateY(translateY);
        p.setTranslateZ(translateZ);

        return p;
    }

    private Group buildCameraGroup(PlayerWL p) {
        PerspectiveCamera worldCamera = p.getCamera();
        worldCamera.setFarClip(5000.0);
        worldCamera.setNearClip(0.01);

        worldCamera.setRotationAxis(new Point3D(0, 1, 0));
        Group cameraGroup = new Group(buildGun(), worldCamera);
        cameraGroup.getChildren().add(buildPointlight(worldCamera.getTranslateX(), worldCamera.getTranslateY(), worldCamera.getTranslateY()));

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
        cross.setTranslateX(width / 2 - (cross.getBoundsInParent().getWidth() / 2));
        cross.setTranslateY(height / 2 - (cross.getBoundsInParent().getHeight() / 2));
    }
}