package GameSpecific;

import Structure.ActorSphere;
import Structure.World;
import javafx.scene.Parent;

public class Bullet extends ActorSphere {

    private double posX;
    private double posY;
    private double posZ;

    private double angleX;
    private double angleY;

    private double speed;
    private double time;


    public Bullet(double[] position, double[] angle, double speed) {
        super(0.1);
        posX = position[0];
        posY = position[1];
        posZ = position[2];
        setTranslateX(posX);
        setTranslateY(posY);
        setTranslateZ(posZ);

        angleX = angle[0];
        angleY = angle[1];

        this.speed = speed;

        time = 0;

    }

    private World getWorld() {
        return (World) (getParent());
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

        if (time > 5000) {
            w.remove(this);
        }
    }

    double getSpeedX() {
        return Math.sin(angleY / 180 * Math.PI) * speed;
    }

    double getSpeedY() {
        return -Math.sin(angleX / 180 * Math.PI) * speed;
    }

    double getSpeedZ() {
        return Math.cos(angleY / 180 * Math.PI) * speed;
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
            if (p.getChildrenUnmodifiable().get(i) instanceof Parent) {
                if (isIntersectingObjectInParent((Parent) (p.getChildrenUnmodifiable().get(i)))) {
                    return true;
                }
            }
        }
        return false;
    }
}
