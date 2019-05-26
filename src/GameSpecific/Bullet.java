package GameSpecific;

import Structure.ActorSphere;
import Structure.World;
import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec3d;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.shape.Shape3D;

public class Bullet extends ActorSphere {

    private double posX;
    private double posY;
    private double posZ;

    private double angleX;
    private double angleY;

    private double speed;
    private double time;




    public Bullet(Vec3d position, Vec2d angle, double speed) {
        super(0.1);
        posX = position.x;
        posY = position.y;
        posZ = position.z;
        setTranslateX(posX);
        setTranslateY(posY);
        setTranslateZ(posZ);

        angleX = angle.x;
        angleY = angle.y;

        this.speed = speed;

        time = 0;

    }

    public World getWorld() {
        return (World)(getParent());
    }

    @Override
    public void act() {
        World w = getWorld();

        time += w.deltaTime();

        posX += getSpeedX();
        posY += getSpeedY();
        posZ += getSpeedZ();

        setTranslateX(posX);
        setTranslateY(posY);
        setTranslateZ(posZ);

        if(time > 5000) {
            w.remove(this);
        }
    }

    public double getSpeedX() {
        return Math.sin(angleY / 180 * Math.PI) * speed;
    }

    public double getSpeedY() {
        return -Math.sin(angleX / 180 * Math.PI) * speed;
    }

    public double getSpeedZ() {
        return  Math.cos(angleY / 180 * Math.PI) * speed;
    }

    private Parent getTopParent() {
        Parent p = getParent();
        while (p.getParent() != null) {
            p = p.getParent();
        }

        return p;
    }

    private boolean isIntersectingObjectInParent(Parent p) {
        for (int i = 0; i < p.getChildrenUnmodifiable().size(); i++) {
            if(p.getChildrenUnmodifiable().get(i) instanceof Parent) {
                if(isIntersectingObjectInParent((Parent)(p.getChildrenUnmodifiable().get(i)))) {
                    return true;
                }
            } else {

            }
        }
        return false;
    }
}
