package GameSpecific;

import Structure.ActorBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Target extends ActorBox {
    private boolean isHit;
    private LevelWL currLevel;

    public Target(double width, double height, double depth) {
        super(width, height, depth);
    }

    boolean isHit() {
        return isHit;
    }

    void setHit(boolean hit) {
        isHit = hit;
    }

    @Override
    public void act() {
        if (isHit()) {
            this.setMaterial(new PhongMaterial(Color.RED));
        }
    }
}
