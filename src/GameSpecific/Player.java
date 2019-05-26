package GameSpecific;

import Structure.ActorBox;
import Structure.World;
import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.awt.*;

public class Player extends World {

    private PerspectiveCamera camera;
    private double rotateYAxis;
    private double rotateXAxis;
    private double positionZ;
    private double positionX;
    private double positionY;
    private Group cameraGroup; //HAVE TO MOVE FULl group gun + player
    private AmbientLight light;
    private int[] bounds;
    private double mouseX;
    private double mouseY;
    private Robot r;


    private boolean inAir = false;
    private double speedX, speedZ;
    private double oldX;
    private double oldZ;
    private boolean upReleased;

    private ActorBox gun;

    private boolean wasInAir = false;
    private int counter = 0;
    private  double originalY = 0;

    private boolean firstTime = true;
    private int counterSpace = 0;


    //variable that keeps track of if you're grounded (boolean)
    //model gravity equation with acceleration(final) and velocityY
    //collide with ground will set boolean to true
    //keep track of velocity in x and y if not grounded keep velocity x and velocity y, rotateY has angle
    // rotateY angle
    //use a variable to keep track of time to know how long since the last acrt was called
    //x component: Xc(positionX) - Xo
    //z component: Zc(positionZ) - Zo
    //x speed: x component / actTime
    //z speed: z component / actTime
    //when you're in the air, ignore the keyboard inputs, movement in x z is x SPeed and z Speed you were before

    public Player() {
        super();
        rotateYAxis = 0;
        positionZ = 0;
        positionX = 0;
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(-10, Rotate.X_AXIS),
                new Translate(0, 0, 0));
        cameraGroup = new Group(camera);
        camera.setRotationAxis(new Point3D(0, 1, 0));
    }

    public Player(double width, double height) {
        super(width, height);
        //light = new AmbientLight(Color.LIGHTGOLDENRODYELLOW);
        bounds = getBounds();
        r = null;
        try {
            r = new Robot(MouseInfo.getPointerInfo().getDevice());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        moveMouse(bounds[0] / 2, bounds[1] / 2);

        upReleased = true;

        mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        rotateXAxis = -10;
        rotateYAxis = 0;
        positionZ = 0;
        positionX = 0;
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(0, Rotate.X_AXIS),
                new Translate(0, -2 , 0));
        cameraGroup = new Group(camera);
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS)
        );
        camera.setRotationAxis(new Point3D(0, 1, 0));
        //getChildren().add(light);

        gun = new ActorBox(0.5,0.5,5) {
            @Override
            public void act() {

            }
        };

        gun.setTranslateY(-2);
        gun.setTranslateX(0.5);
        gun.setTranslateZ(6);
        gun.setMaterial(new PhongMaterial(Color.RED));
        gun.setRotationAxis(Rotate.Y_AXIS);
        gun.setRotate(-5);

        cameraGroup.getChildren().add(gun);

        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);


    }

    public void setCameraGroup(Group g) {
        cameraGroup = g;
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS)
        );
        camera.setRotationAxis(new Point3D(0, 1, 0));
    }

    public void addCameraGroupToWorld() {
        getChildren().add(cameraGroup);
    }

    @Override
    public void act() {

        double newMouseX = MouseInfo.getPointerInfo().getLocation().getX();
        double newMouseY = MouseInfo.getPointerInfo().getLocation().getY();


        System.out.println(speedX);

        inAir = isKeyDown(KeyCode.SPACE);

        if (!inAir) {

            if(wasInAir) {
                if(originalY > positionY) {
                    positionY += 0.75;
                    cameraGroup.setTranslateY(positionY);
                    System.out.println("Position Y aFTer jump = " + positionY);
                    //wasInAir = true;
                    counter ++;
                } else {
                    wasInAir = false;
                    counter = 0;
                }
            }
            if (isKeyDown(KeyCode.A)) {
                positionZ += .5 * Math.cos((rotateYAxis - 90) / 180 * Math.PI);
                positionX += .5 * Math.sin((rotateYAxis - 90) / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                if(isColliding()){
                    positionZ -= .5 * Math.cos((rotateYAxis - 90) / 180 * Math.PI);
                    positionX -= .5 * Math.sin((rotateYAxis - 90) / 180 * Math.PI);
                    cameraGroup.setTranslateZ(positionZ);
                    cameraGroup.setTranslateX(positionX);
                }

            }

            if (isKeyDown(KeyCode.D)) {
                positionZ += .5 * Math.cos((rotateYAxis + 90) / 180 * Math.PI);
                positionX += .5 * Math.sin((rotateYAxis + 90) / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                if (isColliding()) {
                    positionZ -= .5 * Math.cos((rotateYAxis + 90) / 180 * Math.PI);
                    positionX -= .5 * Math.sin((rotateYAxis + 90) / 180 * Math.PI);
                    cameraGroup.setTranslateZ(positionZ);
                    cameraGroup.setTranslateX(positionX);
                }
            }

            if (isKeyDown(KeyCode.W)) {
                positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                if(isColliding()){
                    positionZ -= .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                    positionX -= .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                    cameraGroup.setTranslateZ(positionZ);
                    cameraGroup.setTranslateX(positionX);
                }
            }

            if (isKeyDown(KeyCode.S)) {
                positionZ -= .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                positionX -= .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                if(isColliding()){
                    positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                    positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                    cameraGroup.setTranslateZ(positionZ);
                    cameraGroup.setTranslateX(positionX);
                }
            }

        } else {
            //in air, can't move keys, but can look

            double currentY = positionY;

            if(firstTime) {
                firstTime = false;

                originalY = positionY;
            }


            positionY -= 5;

            positionX += speedX;
            positionZ += speedZ;

            System.out.println("Position Y in Jump = " + positionY);

            cameraGroup.setTranslateY(positionY);
            cameraGroup.setTranslateX(positionX);
            cameraGroup.setTranslateZ(positionZ);
            wasInAir = true;
            inAir = false;
        }
        if (newMouseX != mouseX) {
            rotateYAxis += ((newMouseX - mouseX) / 5);
            rotateYAxis = rotateYAxis % 360;
            if (rotateYAxis < 0) {
                rotateYAxis += 360;
            }
            cameraGroup.setRotationAxis(Rotate.Y_AXIS);
            cameraGroup.setRotate(rotateYAxis);
            mouseX = newMouseX;
        }

        if (newMouseY != mouseY) {
            rotateXAxis -= (newMouseY - mouseY) / 10;
            if (Math.abs(rotateXAxis) > 90) {
                rotateXAxis = 90 * Math.signum(rotateXAxis);
            }

            cameraGroup.getTransforms().set(0, new Rotate(rotateXAxis, Rotate.X_AXIS));
            mouseY = newMouseY;
        }

        if(mouseX >= bounds[0] * 0.99 || mouseX <= bounds[0] * 0.01 || mouseY >= bounds[1] * 0.99 || mouseY <= bounds[1] * 0.01) {
            moveMouse(bounds[0] / 2, bounds[1] / 2);
            mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        }

        speedX = (positionX - oldX) / deltaTime();
        speedZ = (positionZ - oldZ) / deltaTime();

        oldX = positionX;
        oldZ = positionZ;

        if(isKeyDown(KeyCode.UP)) {
            if(upReleased) {
                Bullet b = new Bullet(new Vec3d(positionX, positionY, positionZ), new Vec2d(rotateXAxis, rotateYAxis), 1);
                b.setMaterial(new PhongMaterial(Color.LIGHTCORAL));
                getChildren().add(b);
                System.out.printf("Position: %f x, %f y, %f z\n", positionX, positionY, positionZ);
                System.out.printf("Angle: %f x, %f y\n", rotateXAxis, rotateYAxis);
                System.out.printf("Bullet Speed: %f x, %f y, %f z\n", b.getSpeedX(), b.getSpeedY(), b.getSpeedZ());
                upReleased = false;
            }
        } else {
            upReleased = true;
        }
    }



    public boolean isColliding(){
        for(Node n : getChildren()){
            if(!(n == cameraGroup) && !cameraGroup.getChildren().contains(n)){
                if(n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent()) && !(n instanceof Floor)){
                    return true;
                }
            }
        }

        return false;
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void moveMouse(int x, int y) {
        r.mouseMove(x, y);
    }

    public void moveMouse(Point p) {
        r.mouseMove(p.x, p.y);
    }


    public int[] getBounds() {
        Rectangle r = MouseInfo.getPointerInfo().getDevice().getConfigurations()[0].getBounds();
        return new int[]{r.width, r.height};
    }
}
