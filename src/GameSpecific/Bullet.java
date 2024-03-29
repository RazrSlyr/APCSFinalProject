package GameSpecific;

import Structure.ActorSphere;
import Structure.World;
import javafx.geometry.BoundingBox;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

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

    private LevelWL currLevel;


    public Bullet(double[] position, double[] angle, double speed, LevelWL l) {
        super(0.00);
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

        currLevel = l;
    }

    private World getWorld() {
        Parent p = getParent();
        while(!(p instanceof World)) {
            p = p.getParent();
        }

        return (World)p;
    }

    @Override
    public void act() {
        World w = getWorld();
        if (!hit) {

            time += w.deltaTime();

            posX += getSpeedX();
            posY += getSpeedY();
            posZ += getSpeedZ();
//
            setTranslateX(posX);
            setTranslateY(posY);
            setTranslateZ(posZ);
        }

        if (time > 5000) {
            w.remove(this);
        }

        if (isIntersectingObjectInParent(currLevel) && !hit) {
            setRadius(7);
            hit = true;
            setMaterial(new PhongMaterial(explosionStart));
        }

        if (hit) {
            setRadius(getRadius() - 0.5);
            double deltaRed = (explosionEnd.getRed() - explosionStart.getRed()) / 14.0;
            double deltaGreen = (explosionEnd.getGreen() - explosionStart.getGreen()) / 14.0;
            double deltaBlue = (explosionEnd.getBlue() - explosionStart.getBlue()) / 14.0;

            double newRed = Math.min(1, Math.max(0, currColor.getRed() + deltaRed));
            double newGreen = Math.min(1, Math.max(0, currColor.getGreen() + deltaGreen));
            double newBlue = Math.min(1, Math.max(0, currColor.getBlue() + deltaBlue));


            currColor = new Color(newRed, newGreen, newBlue, 1);

            setMaterial(new PhongMaterial(currColor));

            if (getRadius() <= 0) {
                w.remove(this);
            }
        }
    }

    private double getSpeedX() {
        return Math.sin(angleY / 180 * Math.PI) * speed;
    }

    private double getSpeedY() {
        return -Math.sin(angleX / 180 * Math.PI) * speed;
    }

    private double getSpeedZ() {
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

        BoundingBox b = new BoundingBox(posX - 0.05,
                posY - 0.05,
                posZ - 0.05,
                .1,
                .1,
                .1);

        for (int i = 0; i < p.getChildrenUnmodifiable().size(); i++) {
            if (p.getChildrenUnmodifiable().get(i) instanceof Parent && !(p.getChildrenUnmodifiable().get(i) instanceof Model) && !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL)) {
                if (isIntersectingObjectInParent((Parent) (p.getChildrenUnmodifiable().get(i)))) {
                    return true;
                }
            } else {
                if (!(p.getChildrenUnmodifiable().get(i) instanceof Bullet) && b.intersects(p.getChildrenUnmodifiable().get(i).getBoundsInParent()) &&
                        !p.getChildrenUnmodifiable().get(i).equals(this) &&
                        !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL)) {
                    return true;
                }
            }
        }
        return false;
    }
}
