import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
                new Translate(0, 0, 0));
        cameraGroup = new Group(camera);
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS)
        );
        camera.setRotationAxis(new Point3D(0, 1, 0));
        //getChildren().add(light);
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

        if(!inAir) {

            if (isKeyDown(KeyCode.A)) {
     /*           rotateYAxis -= 1;
                if (rotateYAxis < 0) {
                    rotateYAxis += 360;
                }
                cameraGroup.setRotate(rotateYAxis);*/
                positionZ += .5 * Math.cos((rotateYAxis - 90) / 180 * Math.PI);
                positionX += .5 * Math.sin((rotateYAxis - 90) / 180 * Math.PI);
                cameraGroup.setTranslateZ(positionZ);
                cameraGroup.setTranslateX(positionX);

                for(Node n : getChildren()){
                    if(!(n == cameraGroup) && !cameraGroup.getChildren().contains(n)){
                        if(n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent())){
                            System.out.println("here");

                            positionZ -= .5 * Math.cos((rotateYAxis - 90)/ 180 * Math.PI);
                            positionX -= .5 * Math.sin((rotateYAxis - 90)/ 180 * Math.PI);

                            cameraGroup.setTranslateZ(positionZ);
                            cameraGroup.setTranslateX(positionX);
                        }
                    }
                }

            }

        if (isKeyDown(KeyCode.D)) {
/*            rotateYAxis = (rotateYAxis + 1) % 360;
            cameraGroup.setRotate(rotateYAxis);*/
            positionZ += .5 * Math.cos((rotateYAxis + 90)/ 180 * Math.PI);
            positionX += .5 * Math.sin((rotateYAxis + 90)/ 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);

            for(Node n : getChildren()){
                if(!(n == cameraGroup) && !cameraGroup.getChildren().contains(n)){
                    if(n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent())){
                        System.out.println("here");

                        positionZ -= .5 * Math.cos((rotateYAxis - 90)/ 180 * Math.PI);
                        positionX -= .5 * Math.sin((rotateYAxis - 90)/ 180 * Math.PI);

                        cameraGroup.setTranslateZ(positionZ);
                        cameraGroup.setTranslateX(positionX);
                    }
                }
            }
        }

        if (isKeyDown(KeyCode.W)) {
            positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
            positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);

            for(Node n : getChildren()){
                if(!(n == cameraGroup) && !cameraGroup.getChildren().contains(n)){
                    if(n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent())){
//                        System.out.println("here");
                        positionZ -= .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                        positionX -= .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                        cameraGroup.setTranslateZ(positionZ);
                        cameraGroup.setTranslateX(positionX);
                    }
                }
            }
        }

        if (isKeyDown(KeyCode.S)) {
            positionZ -= .5 * Math.cos(rotateYAxis / 180 * Math.PI);
            positionX -= .5 * Math.sin(rotateYAxis / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);

            for(Node n : getChildren()){
                if(!(n == cameraGroup) && !cameraGroup.getChildren().contains(n)){
                    if(n.getBoundsInParent().intersects(cameraGroup.getBoundsInParent())){
                        System.out.println("here");

                        positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
                        positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
                        cameraGroup.setTranslateZ(positionZ);
                        cameraGroup.setTranslateX(positionX);
                    }
                }
            }

        } else {
            //in air, can't move keys, but can look
            double currentY = positionY;
            positionY -= 0.4;
            cameraGroup.setTranslateY(positionY);


        }
        if (newMouseX != mouseX) {
            rotateYAxis += (newMouseX - mouseX) / 5;
            if (rotateYAxis < 0) {
                rotateYAxis += 360;
            }
            cameraGroup.setRotationAxis(Rotate.Y_AXIS);
            cameraGroup.setRotate(rotateYAxis);
            mouseX = newMouseX;
        }

        if(newMouseY != mouseY) {
            rotateXAxis -= (newMouseY - mouseY) / 10;
            if(Math.abs(rotateXAxis) > 90) {
                rotateXAxis = 90 * Math.signum(rotateXAxis);
            }

            cameraGroup.getTransforms().set(0, new Rotate(rotateXAxis, Rotate.X_AXIS));
            mouseY = newMouseY;
        }

        if(mouseX >= bounds[0] * 0.9 || mouseX <= bounds[0] * 0.1 || mouseY >= bounds[1] * 0.9 || mouseY <= bounds[1] * 0.1) {
            moveMouse(bounds[0] / 2, bounds[1] / 2);
            mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        }

        speedX = (positionX - oldX) / deltaTime();

        speedZ = (positionZ - oldZ) / deltaTime();

        oldX = positionX;

        oldZ = positionZ;

        System.out.println(speedX);
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
