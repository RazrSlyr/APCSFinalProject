import java.util.ArrayList;

/**
 *
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

    public long startTime;

    public ArrayList<Platform> platforms = new ArrayList<>();

    public abstract void act();
}