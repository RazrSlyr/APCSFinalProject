package GameSpecific;

import Structure.Actor;
import Structure.ActorBox;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
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
    public ArrayList<Platform> platforms = new ArrayList<>();
    // Timer
    private long startTime = System.currentTimeMillis();
    private long timeElapsed;
    // Targets
    private int numHit;
    private ArrayList<Target> targets = new ArrayList<>();
    private PlayerWL p;
    private Group cameraGroup;

    public Main(){

        ActorBox floor = buildFloor();
        getChildren().add(buildPointlight(0, -20, 0));

        getChildren().add(buildAmbientLight());

        getChildren().add(floor);

        ActorBox w1 = buildWall(0,0,0, 4, 20, 20);
        w1.setTranslateZ(-200);
        w1.setTranslateX(10);
        getChildren().add(w1);



        ActorBox w2 = buildWall(0,0,0, 4, 20, 20);
        w2.setTranslateZ(-200);
        w2.setTranslateX(-10);
        getChildren().add(w2);

        ActorBox w3 = buildWall(0,0,0, 4, 20, 20);
        ActorBox w4 = buildWall(0,0,0, 4, 20, 20);

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

        ActorBox w5 = buildWall(0,0,0, 4, 20, 20);
        ActorBox w6 = buildWall(0,0,0, 4, 20, 20);

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

    private ActorBox buildFloor() {
        ActorBox floor = new Floor(100, 1, 500) {
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

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void add(Platform platform) {
        super.add(platform);
    }

    public void addAll(Platform... platform) {
        super.addAll(platform);
    }

    public int getNumHit() {
        int count = 0;
        for (Target target : targets) {
            if (target.isHit()) {
                count++;
            }
        }
        numHit = count;
        return count;
    }

    public void add(Target target) {
        super.add(target);
        targets.add(target);
    }

    public void add(double x, double y, double z) {
        Target target = new Target(5, 5, 5);

        System.out.println("im in");
        target.setTranslateX(x);
        target.setTranslateY(y);
        target.setTranslateZ(z);
        add(target);

    }


    public void addAll(Target... target) {
        super.addAll(target);
        targets.addAll(Arrays.asList(target));
    }

    private int getRemaining() {
        return targets.size() - numHit;
    }

    public void act() {
        if (getRemaining() != 0) {
            timeElapsed += deltaTime();
        }

    }

    public void setPlayer(PlayerWL p) {
        this.p = p;
        getChildren().add(p);
    }

    public PlayerWL getPlayer() {
        return p;
    }


    public void setCameraGroup(Group g) {
        cameraGroup = g;
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS));

        cameraGroup.setTranslateZ(-10);
        cameraGroup.setTranslateX(-10);
    }

    public void addCameraGroupToWorld() {
        getChildren().add(cameraGroup);
    }

    public Group getCameraGroup() {
        return cameraGroup;
    }


}