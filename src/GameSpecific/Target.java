package GameSpecific;

import Structure.ActorBox;
import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Target extends ActorBox {

    private boolean isHit;
    private LevelWL level;

    public Target(double width, double height, double depth) {
        super(width, height, depth);
        isHit = false;
        level = (LevelWL) getParent();
        setMaterial(new PhongMaterial(Color.RED));
    }

    public Target(double width, double height, double depth, LevelWL level) {
        super(width, height, depth);
        isHit = false;
        this.level = level;
        setMaterial(new PhongMaterial(Color.RED));
    }

    boolean isHit() {
        return isHit;
    }

    void setHit(boolean hit) {
        isHit = hit;
        // switch model
    }

    @Override
    public void act() {
        if (level != null && getIntersectedObject(level) != null && getIntersectedObject(level) instanceof Bullet) {

            setHit(true);
            setMaterial(new PhongMaterial(Color.GREEN));
        } else if (level != null && getIntersectedObject(level) != null) {
            System.out.println(getIntersectedObject(level).getClass());
        }
    }


    private boolean isIntersectingObjectInParent(Parent p) {
        for (int i = 0; i < p.getChildrenUnmodifiable().size(); i++) {
            if (p.getChildrenUnmodifiable().get(i) instanceof Parent && !(p.getChildrenUnmodifiable().get(i) instanceof Model) && !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL)) {
                if (isIntersectingObjectInParent((Parent) (p.getChildrenUnmodifiable().get(i)))) {
                    return true;
                }
            } else {
                if (getBoundsInParent().intersects(p.getChildrenUnmodifiable().get(i).getBoundsInParent()) &&
                        !p.getChildrenUnmodifiable().get(i).equals(this) &&
                        !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Node getIntersectedObject(Parent p) {
        for (int i = 0; i < p.getChildrenUnmodifiable().size(); i++) {
            if (p.getChildrenUnmodifiable().get(i) instanceof Parent && !(p.getChildrenUnmodifiable().get(i) instanceof Model) && !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL)) {
                if (getIntersectedObject((Parent) (p.getChildrenUnmodifiable().get(i))) != null) {
                    return getIntersectedObject((Parent) (p.getChildrenUnmodifiable().get(i)));
                }
            } else {
                if (getBoundsInParent().intersects(p.getChildrenUnmodifiable().get(i).getBoundsInParent()) &&
                        !p.getChildrenUnmodifiable().get(i).equals(this) &&
                        !(p.getChildrenUnmodifiable().get(i) instanceof PlayerWL) &&
                        !(p.getChildrenUnmodifiable().get(i) instanceof PerspectiveCamera)) {
                    return p.getChildrenUnmodifiable().get(i);
                }
            }
        }
        return null;
    }
}
