import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Test extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);

        ActorBox floor = new ActorBox(100, 1, 100) {
            @Override
            public void act() {

            }
        };

        floor.setMaterial(new PhongMaterial(Color.RED));
        floor.setTranslateY(3);

        ActorBox box = new ActorBox(5, 5, 5) {
            private int rotate = 0;

            @Override
            public void act() {
                setRotate(rotate + 1);
                rotate++;
            }
        };

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("extraEpic.jpg")));
        box.setMaterial(material);

        Player world = new Player(500, 500);

        box.setTranslateX(6);

        ActorBox box2 = new ActorBox(5, 5, 5) {
            @Override
            public void act() {
            }
        };

        box2.setTranslateX(-6);
        box2.setMaterial(new PhongMaterial());
        box2.setDrawMode(DrawMode.FILL);
        box.setDrawMode(DrawMode.FILL);

        ActorBox gun = new ActorBox(0.5,0.5,5) {
            @Override
            public void act() {

            }
        };

        gun.setTranslateY(0.15);
        gun.setTranslateX(0.5);
        gun.setTranslateZ(6);
        gun.setMaterial(new PhongMaterial(Color.RED));
        gun.setRotationAxis(Rotate.Y_AXIS);
        gun.setRotate(-5);

        PerspectiveCamera worldCamera = world.getCamera();

        Group cameraGroup = new Group(gun, worldCamera);
        cameraGroup.setRotationAxis(Rotate.Y_AXIS);

        world.addAll(floor, box2, box);

        SubScene subScene = new SubScene(world, 750, 750, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(world.getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        world.start();

        Scene scene = new Scene(group, 750, 750, true);
        scene.setOnKeyPressed(event -> world.setKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> world.setKeyUp(event.getCode()));

        world.setCameraGroup(cameraGroup);
        world.addCameraGroupToWorld();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
