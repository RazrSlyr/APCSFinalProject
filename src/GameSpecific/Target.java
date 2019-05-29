package GameSpecific;

import Structure.ActorBox;

public class Target extends ActorBox {
    private boolean isHit;

    boolean isHit() {
        return isHit;
    }

    void setHit(boolean hit) {
        isHit = hit;
        // switch model
    }

    public Target(double width, double height, double depth) {
        super(width, height, depth);
    }

    @Override
    public void act() {
    }
}
