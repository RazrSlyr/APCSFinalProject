import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.awt.*;

public class GameWorld extends World {

    private PerspectiveCamera camera;
    private double rotateYAxis;
    private double rotateXAxis;
    private double positionZ;
    private double positionX;
    private Group cameraGroup;
    private AmbientLight light;
    private int[] bounds;
    private double mouseX;
    private double mouseY;
    private Robot r;

    public GameWorld() {
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

    public GameWorld(double width, double height) {
        super(width, height);
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
        camera.setRotationAxis(new Point3D(0, 1, 0));
    }

    public void setCameraGroup(Group g) {
        cameraGroup = g;
    }

    public void addCameraGroupToWorld() {
        getChildren().add(cameraGroup);
    }

    @Override
    public void act() {

        double newMouseX = MouseInfo.getPointerInfo().getLocation().getX();
        double newMouseY = MouseInfo.getPointerInfo().getLocation().getY();

        if (isKeyDown(KeyCode.A)) {
 /*           rotateYAxis -= 1;
            if (rotateYAxis < 0) {
                rotateYAxis += 360;
            }
            cameraGroup.setRotate(rotateYAxis);*/
            positionZ += .5 * Math.cos((rotateYAxis - 90)/ 180 * Math.PI);
            positionX += .5 * Math.sin((rotateYAxis - 90)/ 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);

        }

        if (isKeyDown(KeyCode.D)) {
/*            rotateYAxis = (rotateYAxis + 1) % 360;
            cameraGroup.setRotate(rotateYAxis);*/
            positionZ += .5 * Math.cos((rotateYAxis + 90)/ 180 * Math.PI);
            positionX += .5 * Math.sin((rotateYAxis + 90)/ 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);
        }

        if (isKeyDown(KeyCode.W)) {
            positionZ += .5 * Math.cos(rotateYAxis / 180 * Math.PI);
            positionX += .5 * Math.sin(rotateYAxis / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);
        }

        if (isKeyDown(KeyCode.S)) {
            positionZ -= .5 * Math.cos(rotateYAxis / 180 * Math.PI);
            positionX -= .5 * Math.sin(rotateYAxis / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);
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
            camera.getTransforms().set(1, new Rotate(rotateXAxis, Rotate.X_AXIS));
            mouseY = newMouseY;
        }

        System.out.println(rotateXAxis);

        if(mouseX >= bounds[0] * 0.9 || mouseX <= bounds[0] * 0.1 || mouseY >= bounds[1] * 0.9 || mouseY <= bounds[1] * 0.1) {
            moveMouse(bounds[0] / 2, bounds[1] / 2);
            mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        }



      /*  Rotate r = (Rotate)camera.getTransforms().get(0);
        camera.getTransforms().set(0, new Rotate(r.getAngle() - 10, Rotate.Y_AXIS));*/
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
