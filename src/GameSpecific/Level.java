package GameSpecific;

import Structure.World;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds all the necessary information for creating a level
 */
public class Level extends World {
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

    // Platforms
    public ArrayList<Platform> platforms = new ArrayList<>();
    // Timer
    private long startTime = System.currentTimeMillis();
    private long timeElapsed;
    // Targets
    private int numHit;
    private ArrayList<Target> targets = new ArrayList<>();


    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void add(Platform platform) {
        super.add(platform);
    }

    public void addAll(Platform... platform) {
        super.addAll(platform);
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

    public void add(Target target) {
        super.add(target);
        targets.add(target);
    }

    public void addAll(Target... target) {
        super.addAll(target);
        targets.addAll(Arrays.asList(target));
    }

    private int getRemaining() {
        return targets.size() - numHit;
    }

    public void act() {
        if (getRemaining() != 0) {
            timeElapsed += deltaTime();
        }

    }
}
