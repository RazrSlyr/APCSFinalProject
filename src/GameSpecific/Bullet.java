package GameSpecific;

import Structure.ActorSphere;
import Structure.World;
import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec3d;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class Bullet extends ActorSphere {

    private double posX;
    private double posY;
    private double posZ;

    private double angleX;
    private double angleY;

    private double speed;
    private double time;
    private boolean hit;

    private Color explosionStart;
    private Color explosionEnd;
    private Color currColor;


    public Bullet(double[] position, double[] angle, double speed) {
        super(0.01);
        posX = position[0];
        posY = position[1];
        posZ = position[2];

        explosionStart = Color.YELLOW;
        explosionEnd = Color.RED;
        currColor = explosionStart;

        setTranslateX(posX);
        setTranslateY(posY);
        setTranslateZ(posZ);
        hit = false;

        angleX = angle[0];
        angleY = angle[1];

        this.speed = speed;

        time = 0;

        setMaterial(new PhongMaterial(Color.BLACK));

    }

    public World getWorld() {
        return (World)(getParent());
    }

    @Override
    public void act() {
        World w = getWorld();
        if(!hit) {

            time += w.deltaTime();

            posX += getSpeedX();
            posY += getSpeedY();
            posZ += getSpeedZ();

            setTranslateX(posX);
            setTranslateY(posY);
            setTranslateZ(posZ);
        }

        if(time > 5000) {
            w.remove(this);
        }

        if(isIntersectingObjectInParent(getTopParent()) && !hit) {
            setRadius(7);
            hit = true;
            setMaterial(new PhongMaterial(explosionStart));
        }

        if(hit) {
            setRadius(getRadius() - 0.5);
            double deltaRed = (explosionEnd.getRed() - explosionStart.getRed()) / 14.0;
            double deltaGreen = (explosionEnd.getGreen() - explosionStart.getGreen()) / 14.0;
            double deltaBlue = (explosionEnd.getBlue() - explosionStart.getBlue()) / 14.0;

            double newRed = Math.min(1, Math.max(0, currColor.getRed() + deltaRed));
            double newGreen = Math.min(1, Math.max(0, currColor.getGreen() + deltaGreen));
            double newBlue = Math.min(1, Math.max(0, currColor.getBlue() + deltaBlue));


            currColor = new Color(newRed, newGreen, newBlue, 1);

            setMaterial(new PhongMaterial(currColor));

            if(getRadius() <= 0) {
                w.remove(this);
            }
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
            if(p.getChildrenUnmodifiable().get(i) instanceof Parent && !(p.getChildrenUnmodifiable().get(i) instanceof Model)) {
                if(isIntersectingObjectInParent((Parent)(p.getChildrenUnmodifiable().get(i)))) {
                    return true;
                }
            } else {
                if(!(p.getChildrenUnmodifiable().get(i) instanceof Bullet) &&
                        getBoundsInParent().intersects(p.getChildrenUnmodifiable().get(i).getBoundsInParent()) &&
                        !p.getChildrenUnmodifiable().get(i).equals(this)) {
                    return true;
                }
            }
        }
        return false;
    }
}
