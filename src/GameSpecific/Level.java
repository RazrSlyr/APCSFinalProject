package GameSpecific;

import Structure.Actor;
import Structure.World;
import javafx.scene.Node;

import java.util.ArrayList;

/**
 * Holds all the necessary information for creating a level
 */
public abstract class Level extends World {
    /*Need for level
    platforms // store as an array list
        platform objects
            location
            lighting
            dimensions

            // get children.add
    targets
        objects
            location
            lighting
            dimension
        number of targets
            number hit (flags)
            number remaining
        time
            how much time they take
            (maybe later) time limit
    add the levels and player to overarching group
     */

    // Timer
    private long startTime = System.currentTimeMillis();
    private long timeElapsed;

    {
        if (getRemaining() == 0) {
            long endTime = System.currentTimeMillis();
            timeElapsed = Math.abs(endTime - startTime);
        }
    }

    // Platforms
    public ArrayList<Platform> platforms = new ArrayList<>();

    public long getTimeElapsed() {
        return timeElapsed;
    }

    // Targets
    private int numHit;
    private ArrayList<Target> targets = new ArrayList<>();

    public <A extends Node & Actor> void add(Platform platform) {
        super.add(platform);
    }

    public int getNumHit() {
        int count = 0;
        for (Target target : targets) {
            if (target.isHit()) {
                count++;
            }
        }
        numHit = count;
        return count;
    }

    public <A extends Node & Actor> void add(Target target) {
        super.add(target);
        targets.add(target);
    }

    private int getRemaining() {
        return targets.size() - numHit;
    }

    public abstract void act();
}
