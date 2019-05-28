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
    public long startTime;

    // Platforms
    public ArrayList<Platform> platforms = new ArrayList<>();

    // Targets
    private int numHit;
    private ArrayList<Target> targets = new ArrayList<>();

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

    public int getRemaining() {
        return targets.size() - numHit;
    }

    public abstract void act();
}