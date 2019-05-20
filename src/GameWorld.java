import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class GameWorld extends World {

    private PerspectiveCamera camera;
    private double rotate;
    private double positionZ;
    private double positionX;
    private Group cameraGroup;

    public GameWorld() {
        super();
        rotate = 0;
        positionZ = 0;
        positionX = 0;
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(-10, Rotate.X_AXIS),
                new Translate(0, 0, 0));
        cameraGroup = new Group(camera);
        camera.setRotationAxis(new Point3D(0, 1, 0));
    }

    public GameWorld(double width, double height) {
        super(width, height);
        rotate = 0;
        positionZ = 0;
        positionX = 0;
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(0, Rotate.X_AXIS),
                new Translate(0, 0, 0));
        cameraGroup = new Group(camera);
        camera.setRotationAxis(new Point3D(0, 1, 0));
    }

    public void setCameraGroup(Group g){
        cameraGroup = g;
    }

    public void addCameraGroupToWorld(){
        getChildren().add(cameraGroup);
    }

    @Override
    public void act() {
        if (isKeyDown(KeyCode.A)) {
            rotate -= 1;
            if(rotate < 0) {
                rotate += 360;
            }
            cameraGroup.setRotate(rotate);

        }

        if (isKeyDown(KeyCode.D)) {
            rotate = (rotate + 1) % 360;
            cameraGroup.setRotate(rotate);
        }

        if (isKeyDown(KeyCode.W)) {
            positionZ += .5 * Math.cos(rotate / 180 * Math.PI);
            positionX += .5 * Math.sin(rotate / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);
        }

        if (isKeyDown(KeyCode.S)) {
            positionZ -= .5 * Math.cos(rotate / 180 * Math.PI);
            positionX -= .5 * Math.sin(rotate / 180 * Math.PI);
            cameraGroup.setTranslateZ(positionZ);
            cameraGroup.setTranslateX(positionX);
        }

      /*  Rotate r = (Rotate)camera.getTransforms().get(0);
        camera.getTransforms().set(0, new Rotate(r.getAngle() - 10, Rotate.Y_AXIS));*/
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
