package GameSpecific;

import Structure.ActorBox;

public class Target extends ActorBox {
    private boolean isHit;

    public Target(double width, double height, double depth) {
        super(width, height, depth);
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
    }
}
