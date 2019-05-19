import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
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

        GameWorld world = new GameWorld(1500, 1500);

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

        AmbientLight light = new AmbientLight();
        light.setColor(Color.LIGHTGOLDENRODYELLOW);

       // world.add(box2);
        world.addAll(floor, box2, box);
        world.getChildren().add(light);


        SubScene subScene = new SubScene(world, 1500,1500, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(world.getCamera());
        Group group = new Group();
        group.getChildren().add(subScene);
        world.start();

        Scene scene = new Scene(group, 1500, 1500, true);
        scene.setOnKeyPressed(event -> world.setKeyDown(event.getCode()));
        scene.setOnKeyReleased(event -> world.setKeyUp(event.getCode()));

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
