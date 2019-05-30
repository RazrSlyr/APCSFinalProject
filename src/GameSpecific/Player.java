package GameSpecific;

import Structure.Actor;
import Structure.ActorBox;
import Structure.World;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
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

    private Box groundCheck;


    private double speedX, speedZ, speedY;
    private final double GRAVITY = 25;

    private boolean upReleased;

    private boolean wasInAir = false;
    private int counter = 0;
    private double originalY = 0;

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
//        super(width, height);
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
                new Translate(0, -2, 0));
        cameraGroup = new Group(camera);
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS)
        );
        camera.setRotationAxis(new Point3D(0, 1, 0));
        //getChildren().add(light);

        ActorBox gun = new ActorBox(0.5, 0.5, 5) {
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
        groundCheck = new Box(1, 1, 1);

        cameraGroup.getChildren().add(gun);
        cameraGroup.getChildren().add(groundCheck);
        cameraGroup.setTranslateZ(-10);
        cameraGroup.setTranslateX(-10);
        positionZ = -10;
        positionX = -10;
        groundCheck.setMaterial(new PhongMaterial(Color.WHITE));
        groundCheck.setTranslateY(0.5);

        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);


    }

    void setCameraGroup(Group g) {
        cameraGroup = g;
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS)
        );
        camera.setRotationAxis(new Point3D(0, 1, 0));
        cameraGroup.getChildren().add(groundCheck);
        groundCheck.setTranslateY(3);
        cameraGroup.setTranslateZ(-10);
        cameraGroup.setTranslateX(-10);
        positionZ = -5;
        positionX = -5;
    }

    void addCameraGroupToWorld() {
        getChildren().add(cameraGroup);
    }

    @Override
    public void act() {

        double newMouseX = MouseInfo.getPointerInfo().getLocation().getX();
        double newMouseY = MouseInfo.getPointerInfo().getLocation().getY();

        double oldX = positionX;
        double oldZ = positionZ;


        // System.out.println(speedX);

        //boolean inAir = isKeyDown(KeyCode.SPACE);

        if (isGrounded()) {

            if(speedY > 0) {
                speedY = 0;
            } else if(isKeyDown(KeyCode.SPACE)) {
                speedY = -25;
            }

/*            if (wasInAir) {
                if (originalY > positionY) {
                    positionY += 0.75;
                    cameraGroup.setTranslateY(positionY);
                    //     System.out.println("Position Y aFTer jump = " + positionY);
                    //wasInAir = true;
                    counter++;
                } else {
                    wasInAir = false;
                    counter = 0;
                }
            }*/
/*            if (isKeyDown(KeyCode.A)) {
                positionZ += .5 * Math.cos((rotateYAxis - 90) / 180 * Math.PI);
                positionX += .5 * Math.sin((rotateYAxis - 90) / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                if (isColliding()) {
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

                if (isColliding()) {
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

                if (isColliding()) {
                    positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                    positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                    cameraGroup.setTranslateZ(positionZ);
                    cameraGroup.setTranslateX(positionX);
                }
            }

            speedX = (positionX - oldX) / deltaTime();
            speedZ = (positionZ - oldZ) / deltaTime();*/

        } else {
            //in air, can't move keys, but can look

            /*double currentY = positionY;

            if (firstTime) {
                firstTime = false;

                originalY = positionY;
            }


            positionY -= 5;

            positionX += speedX;
            positionZ += speedZ;

            // System.out.println("Position Y in Jump = " + positionY);

            cameraGroup.setTranslateY(positionY);
            cameraGroup.setTranslateX(positionX);
            cameraGroup.setTranslateZ(positionZ);
            wasInAir = true;
//            inAir = false;*/
/*
            positionX += speedX * deltaTime();
            positionZ += speedZ * deltaTime();



            cameraGroup.setTranslateX(positionX);
            cameraGroup.setTranslateZ(positionZ);*/

            speedY += GRAVITY * deltaTime() / 1000;

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

        if (isKeyDown(KeyCode.A)) {
            positionZ += .5 * Math.cos((rotateYAxis - 90) / 180 * Math.PI);
            positionX += .5 * Math.sin((rotateYAxis - 90) / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);

            if (isColliding()) {
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

            if (isColliding()) {
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

            if (isColliding()) {
                positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);
            }
        }

        speedX = (positionX - oldX) / deltaTime();
        speedZ = (positionZ - oldZ) / deltaTime();

        if (mouseX >= bounds[0] * 0.99 || mouseX <= bounds[0] * 0.01 || mouseY >= bounds[1] * 0.99 || mouseY <= bounds[1] * 0.01) {
            moveMouse(bounds[0] / 2, bounds[1] / 2);
            mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        }




        if (isKeyDown(KeyCode.UP)) {
            if (upReleased) {
                Bullet b = new Bullet(new double[]{positionX, positionY + 2, positionZ}, new double[]{rotateXAxis, rotateYAxis}, 5);
                b.setMaterial(new PhongMaterial(Color.BLACK));

                getChildren().add(b);
//                System.out.printf("Position: %f x, %f y, %f z\n", positionX, positionY, positionZ);
//                System.out.printf("Angle: %f x, %f y\n", rotateXAxis, rotateYAxis);
//                System.out.printf("Bullet Speed: %f x, %f y, %f z\n", b.getSpeedX(), b.getSpeedY(), b.getSpeedZ());
                upReleased = false;
            }
        } else {
            upReleased = true;
        }
        positionY += speedY * deltaTime() / 1000;
        cameraGroup.setTranslateY(positionY);


        if(positionY > 10) {
            positionY = -5;
            positionX = -5;
            positionZ = -5;
            cameraGroup.setTranslateX(positionX);
            cameraGroup.setTranslateY(positionY);
            cameraGroup.setTranslateZ(positionZ);

        }
    }

    public boolean isGrounded() {
        BoundingBox b = new BoundingBox(groundCheck.getBoundsInParent().getMinX() + positionX,
                groundCheck.getBoundsInParent().getMinY() + positionY,
                groundCheck.getBoundsInParent().getMinZ() + positionZ,
                groundCheck.getWidth(),
                groundCheck.getHeight(),
                groundCheck.getDepth());

        for (Node n : getChildren()) {
            if (n != groundCheck && !(n == cameraGroup) && !cameraGroup.getChildren().contains(n)) {
                if (b.intersects(n.getBoundsInParent())) {
                    speedY = 0;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isColliding() {
        for (Node n : getChildren()) {
            if (n != groundCheck && !(n == cameraGroup) && !cameraGroup.getChildren().contains(n)) {
                if (n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent()) && !(n instanceof Floor)) {
                    return true;
                }
            }
        }

        return false;
    }

    PerspectiveCamera getCamera() {
        return camera;
    }

    private void moveMouse(int x, int y) {
        r.mouseMove(x, y);
    }

    public void moveMouse(Point p) {
        r.mouseMove(p.x, p.y);
    }


    private int[] getBounds() {
        Rectangle r = MouseInfo.getPointerInfo().getDevice().getConfigurations()[0].getBounds();
        return new int[]{r.width, r.height};
    }
}
