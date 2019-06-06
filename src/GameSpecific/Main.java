package GameSpecific;

import Structure.ActorBox;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends LevelWL {
   /*Need for level
    platforms // store as an array list
        platform objects
            location
            lighting
            dimensions

            // get children.add
    targets
        objects
            location
            lighting
            dimension
        number of targets
            number hit (flags)
            number remaining
        time
            how much time they take
            (maybe later) time limit
    add the levels and player to overarching group
     */

    // Platforms

    // Timer

    // Targets

    private Text timeElapsedText;


    public Main(Text text){
        timeElapsedText = text;

        ActorBox floor = buildFloor();
        getChildren().add(buildPointlight(0, -20, 0));

        getChildren().add(buildAmbientLight());

        getChildren().add(floor);

        // Walls
        ActorBox w1 = buildWall(0, 0, 0, 4, 20, 20);
        w1.setTranslateZ(-200);
        w1.setTranslateX(10);
        getChildren().add(w1);

        ActorBox w2 = buildWall(0, 0, 0, 4, 20, 20);
        w2.setTranslateZ(-200);
        w2.setTranslateX(-10);
        getChildren().add(w2);

        ActorBox w3 = buildWall(0, 0, 0, 4, 20, 20);
        ActorBox w4 = buildWall(0, 0, 0, 4, 20, 20);

        w3.setRotationAxis(Rotate.Y_AXIS);
        w3.setRotate(90);

        w4.setRotationAxis(Rotate.Y_AXIS);
        w4.setRotate(90);

        getChildren().add(w3);
        w3.setTranslateX(20);
        w3.setTranslateZ(-180);

        getChildren().add(w4);
        w4.setTranslateX(20);
        w4.setTranslateZ(-160);

        ActorBox w5 = buildWall(0, 0, 0, 4, 20, 20);
        ActorBox w6 = buildWall(0, 0, 0, 4, 20, 20);

        w5.setRotationAxis(Rotate.Y_AXIS);
        w5.setRotate(90);

        w6.setRotationAxis(Rotate.Y_AXIS);
        w6.setRotate(90);

        getChildren().add(w5);
        w5.setTranslateX(-20);
        w5.setTranslateZ(-180);

        getChildren().add(w6);
        w6.setTranslateX(-20);
        w6.setTranslateZ(-160);

        ActorBox w7 = buildWall(0,0,0, 4, 20, 20);
        w7.setRotationAxis(Rotate.Y_AXIS);
        w7.setRotate(90);
        getChildren().add(w7);
        w7.setTranslateX(0);
        w7.setTranslateZ(-140);

        ActorBox w10 = buildWall(0,0,0, 4, 20, 20);
        w10.setTranslateZ(-120);
        w10.setTranslateX(30);
        getChildren().add(w10);

        ActorBox w11 = buildWall(0,0,0, 4, 20, 20);
        w11.setTranslateZ(-120);
        w11.setTranslateX(20);
        getChildren().add(w11);

        ActorBox w13 = buildWall(0,0,0, 4, 20, 20);
        w13.setTranslateZ(-120);
        w13.setTranslateX(-20);
        getChildren().add(w13);

        ActorBox w14 = buildWall(0, 0, 0, 4, 20, 20);
        w14.setRotationAxis(Rotate.Y_AXIS);
        w14.setRotate(90);
        w14.setTranslateZ(220 -210);
        w14.setTranslateX(-20);
        getChildren().add(w14);

        ActorBox w15 = buildWall(0, 0, 0, 4, 20, 20);
        w15.setRotationAxis(Rotate.Y_AXIS);
        w15.setRotate(90);
        w15.setTranslateZ(205 - 210);
        w15.setTranslateX(0);
        getChildren().add(w15);

        ActorBox w16 = buildWall(0, 0, 0, 4, 20, 20);
        w16.setRotationAxis(Rotate.Y_AXIS);
        w16.setRotate(90);
        w16.setTranslateZ(190 - 210);
        w16.setTranslateX(0);
        getChildren().add(w16);

        ActorBox w17 = buildWall(0, 0, 0, 4, 20, 15);
        w17.setRotationAxis(Rotate.Y_AXIS);
        w17.setRotate(90);
        w17.setTranslateZ(210 - 210);
        w17.setTranslateX(35);
        getChildren().add(w17);

        ActorBox w18 = buildWall(0, 0, 0, 4, 20, 15);
        w18.setTranslateZ(210 - 210);
        w18.setTranslateX(20);
        getChildren().add(w18);

        ActorBox w19 = buildWall(0, 0, 0, 4, 20, 20);
        w19.setRotationAxis(Rotate.Y_AXIS);
        w19.setRotate(90);
        w19.setTranslateZ(180 - 210);
        w19.setTranslateX(30);
        getChildren().add(w19);

        ActorBox w20 = buildWall(0, 0, 0, 4, 20, 20);
        w20.setRotationAxis(Rotate.Y_AXIS);
        w20.setRotate(90);
        w20.setTranslateZ(170 - 210);
        w20.setTranslateX(30);
        getChildren().add(w20);

        ActorBox w21 = buildWall(0, 0, 0, 4, 20, 20);
        w21.setTranslateZ(170 - 210);
        w21.setTranslateX(5);
        getChildren().add(w21);

        ActorBox w22 = buildWall(0, 0, 0, 4, 20, 20);
        w22.setTranslateZ(170 - 210);
        w22.setTranslateX(-5);
        getChildren().add(w22);

        ActorBox w23 = buildWall(0, 0, 0, 4, 20, 20);
        w23.setRotationAxis(Rotate.Y_AXIS);
        w23.setRotate(90);
        w23.setTranslateZ(150 - 210);
        w23.setTranslateX(5);
        getChildren().add(w23);

        ActorBox w24 = buildWall(0, 0, 0, 4, 20, 20);
        w24.setTranslateZ(150 - 210);
        w24.setTranslateX(20);
        getChildren().add(w24);

        ActorBox w25 = buildWall(0, 0, 0, 4, 20, 20);
        w25.setTranslateZ(190 - 210);
        w25.setTranslateX(-20);
        getChildren().add(w25);

        ActorBox w26 = buildWall(0, 0, 0, 4, 20, 20);
        w26.setRotationAxis(Rotate.Y_AXIS);
        w26.setRotate(90);
        w26.setTranslateZ(150 - 210);
        w26.setTranslateX(-20);
        getChildren().add(w26);

        ActorBox w27 = buildWall(0, 0, 0, 4, 20, 20);
        w27.setRotationAxis(Rotate.Y_AXIS);
        w27.setRotate(90);
        w27.setTranslateZ(130 - 210);
        w27.setTranslateX(-20);
        getChildren().add(w27);

        ActorBox w28 = buildWall(0, 0, 0, 4, 20, 20);
        w28.setTranslateZ(110 - 210);
        w28.setTranslateX(-20);
        getChildren().add(w28);

        ActorBox w29 = buildWall(0, 0, 0, 4, 20, 20);
        w29.setTranslateZ(110 - 210);
        w29.setTranslateX(-10);
        getChildren().add(w29);

        ActorBox w30 = buildWall(0, 0, 0, 4, 20, 20);
        w30.setRotationAxis(Rotate.Y_AXIS);
        w30.setRotate(90);
        w30.setTranslateZ(110 - 210);
        w30.setTranslateX(10);
        getChildren().add(w30);

        ActorBox w31 = buildWall(0, 0, 0, 4, 20, 20);
        w31.setRotationAxis(Rotate.Y_AXIS);
        w31.setRotate(90);
        w31.setTranslateZ(120 - 210);
        w31.setTranslateX(10);
        getChildren().add(w31);

        ActorBox w32 = buildWall(0, 0, 0, 4, 20, 20);
        w32.setTranslateZ(150 - 210);
        w32.setTranslateX(30);
        getChildren().add(w32);

        ActorBox w33 = buildWall(0, 0, 0, 4, 20, 10);
        w33.setTranslateZ(136 - 210);
        w33.setTranslateX(0);
        getChildren().add(w33);

        ActorBox w34 = buildWall(0, 0, 0, 4, 20, 15);
        w34.setRotationAxis(Rotate.Y_AXIS);
        w34.setRotate(90);
        w34.setTranslateZ(130 - 210);
        w34.setTranslateX(25);
        getChildren().add(w34);

        copyMaze();
    }

    public void setTimeElapsedText(Text t){
        timeElapsedText = t;
    }

    public void copyMaze(){
        ActorBox w1 = buildWall(0,0,0, 4, 20, 20);
        w1.setTranslateZ(-200 + 210);
        w1.setTranslateX(10);
        getChildren().add(w1);

        ActorBox w3 = buildWall(0,0,0, 4, 20, 20);
        ActorBox w4 = buildWall(0,0,0, 4, 20, 20);

        w3.setRotationAxis(Rotate.Y_AXIS);
        w3.setRotate(90);

        w4.setRotationAxis(Rotate.Y_AXIS);
        w4.setRotate(90);

        getChildren().add(w3);
        w3.setTranslateX(20);
        w3.setTranslateZ(-180 + 210);

        getChildren().add(w4);
        w4.setTranslateX(20);
        w4.setTranslateZ(-160 + 210);

        ActorBox w5 = buildWall(0,0,0, 4, 20, 20);
        ActorBox w6 = buildWall(0,0,0, 4, 20, 20);

        w5.setRotationAxis(Rotate.Y_AXIS);
        w5.setRotate(90);

        w6.setRotationAxis(Rotate.Y_AXIS);
        w6.setRotate(90);

        getChildren().add(w5);
        w5.setTranslateX(-20);
        w5.setTranslateZ(-180 + 210);

        getChildren().add(w6);
        w6.setTranslateX(-20);
        w6.setTranslateZ(-160 + 210);

        ActorBox w7 = buildWall(0,0,0, 4, 20, 20);
        w7.setRotationAxis(Rotate.Y_AXIS);
        w7.setRotate(90);
        getChildren().add(w7);
        w7.setTranslateX(0);
        w7.setTranslateZ(-140 + 210);

        ActorBox w10 = buildWall(0,0,0, 4, 20, 20);
        w10.setTranslateZ(-120 + 210);
        w10.setTranslateX(30);
        getChildren().add(w10);

        ActorBox w11 = buildWall(0,0,0, 4, 20, 20);
        w11.setTranslateZ(-120 + 210);
        w11.setTranslateX(20);
        getChildren().add(w11);

        ActorBox w13 = buildWall(0,0,0, 4, 20, 20);
        w13.setTranslateZ(-120 + 210);
        w13.setTranslateX(-20);
        getChildren().add(w13);

        ActorBox w14 = buildWall(0,0,0, 4, 20, 20);
        w14.setRotationAxis(Rotate.Y_AXIS);
        w14.setRotate(90);
        w14.setTranslateZ(220);
        w14.setTranslateX(-20);
        getChildren().add(w14);

        ActorBox w15 = buildWall(0,0,0, 4, 20, 20);
        w15.setRotationAxis(Rotate.Y_AXIS);
        w15.setRotate(90);
        w15.setTranslateZ(205);
        w15.setTranslateX(0);
        getChildren().add(w15);

        ActorBox w16 = buildWall(0,0,0, 4, 20, 20);
        w16.setRotationAxis(Rotate.Y_AXIS);
        w16.setRotate(90);
        w16.setTranslateZ(190);
        w16.setTranslateX(0);
        getChildren().add(w16);

        ActorBox w17 = buildWall(0,0,0, 4, 20, 15);
        w17.setRotationAxis(Rotate.Y_AXIS);
        w17.setRotate(90);
        w17.setTranslateZ(210);
        w17.setTranslateX(35);
        getChildren().add(w17);

        ActorBox w18 = buildWall(0,0,0, 4, 20, 15);
        w18.setTranslateZ(210);
        w18.setTranslateX(20);
        getChildren().add(w18);

        ActorBox w19 = buildWall(0,0,0, 4, 20, 20);
        w19.setRotationAxis(Rotate.Y_AXIS);
        w19.setRotate(90);
        w19.setTranslateZ(180);
        w19.setTranslateX(30);
        getChildren().add(w19);

        ActorBox w20 = buildWall(0,0,0, 4, 20, 20);
        w20.setRotationAxis(Rotate.Y_AXIS);
        w20.setRotate(90);
        w20.setTranslateZ(170);
        w20.setTranslateX(30);
        getChildren().add(w20);

        ActorBox w21 = buildWall(0,0,0, 4, 20, 20);
        w21.setTranslateZ(170);
        w21.setTranslateX(5);
        getChildren().add(w21);

        ActorBox w22 = buildWall(0,0,0, 4, 20, 20);
        w22.setTranslateZ(170);
        w22.setTranslateX(-5);
        getChildren().add(w22);

        ActorBox w23 = buildWall(0,0,0, 4, 20, 20);
        w23.setRotationAxis(Rotate.Y_AXIS);
        w23.setRotate(90);
        w23.setTranslateZ(150);
        w23.setTranslateX(5);
        getChildren().add(w23);

        ActorBox w24 = buildWall(0,0,0, 4, 20, 20);
        w24.setTranslateZ(150);
        w24.setTranslateX(20);
        getChildren().add(w24);

        ActorBox w25 = buildWall(0,0,0, 4, 20, 20);
        w25.setTranslateZ(190);
        w25.setTranslateX(-20);
        getChildren().add(w25);

        ActorBox w26 = buildWall(0,0,0, 4, 20, 20);
        w26.setRotationAxis(Rotate.Y_AXIS);
        w26.setRotate(90);
        w26.setTranslateZ(150);
        w26.setTranslateX(-20);
        getChildren().add(w26);

        ActorBox w27 = buildWall(0,0,0, 4, 20, 20);
        w27.setRotationAxis(Rotate.Y_AXIS);
        w27.setRotate(90);
        w27.setTranslateZ(130);
        w27.setTranslateX(-20);
        getChildren().add(w27);

        ActorBox w28 = buildWall(0,0,0, 4, 20, 20);
        w28.setTranslateZ(110);
        w28.setTranslateX(-20);
        getChildren().add(w28);

        ActorBox w29 = buildWall(0,0,0, 4, 20, 20);
        w29.setTranslateZ(110);
        w29.setTranslateX(-10);
        getChildren().add(w29);

        ActorBox w30 = buildWall(0,0,0, 4, 20, 20);
        w30.setRotationAxis(Rotate.Y_AXIS);
        w30.setRotate(90);
        w30.setTranslateZ(110);
        w30.setTranslateX(10);
        getChildren().add(w30);

        ActorBox w31 = buildWall(0,0,0, 4, 20, 20);
        w31.setRotationAxis(Rotate.Y_AXIS);
        w31.setRotate(90);
        w31.setTranslateZ(120);
        w31.setTranslateX(10);
        getChildren().add(w31);

        ActorBox w32 = buildWall(0,0,0, 4, 20, 20);
        w32.setTranslateZ(150);
        w32.setTranslateX(30);
        getChildren().add(w32);

        ActorBox w33 = buildWall(0,0,0, 4, 20, 10);
        w33.setTranslateZ(136);
        w33.setTranslateX(0);
        getChildren().add(w33);

        ActorBox w34 = buildWall(0,0,0, 4, 20, 15);
        w34.setRotationAxis(Rotate.Y_AXIS);
        w34.setRotate(90);
        w34.setTranslateZ(130);
        w34.setTranslateX(25);
        getChildren().add(w34);

        // Targets
        add(-10, -5, -150);
        add(30, -1, -100);
        add(0, -2, 0);
        add(10, 0, 22);
        add(-5, -4, 50);
        add(30, -3, 110);
    }


    public void act() {
        if (getRemaining() != 0) {
            timeElapsed += deltaTime();
        }
        timeElapsedText.setText("Time elapsed: " + (timeElapsed/1000) + "\n" + "Number of targets remaining: " + getRemaining());
//        timeElapsed += deltaTime();
//        timeElapsedText.setText("Time elapsed: " + (timeElapsed/1000));
    }

    public ActorBox buildWall(double x, double y, double z, double width, double height, double depth) {
        ActorBox box = new ActorBox(width, height, depth) {
            @Override
            public void act() {
            }
        };

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("../extraEpic.jpg")));
        box.setMaterial(material);
        box.setTranslateX(x);
        box.setTranslateX(y);
        box.setTranslateX(z);

        return box;
    }

    private ActorBox buildFloor() {
        ActorBox floor = new Floor(70, 1, 500) {
            @Override
            public void act() {
            }
        };

        floor.setMaterial(new PhongMaterial(Color.SANDYBROWN));
        floor.setTranslateY(3);

        System.out.println(floor.getBoundsInParent());

        return floor;
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


    public void add(double x, double y, double z) {
        Target target = new Target(3, 3, 3, this);
        target.setMaterial(new PhongMaterial(Color.BLUE));
        target.setTranslateX(x);
        target.setTranslateY(y);
        target.setTranslateZ(z);
        add(target);
    }



}