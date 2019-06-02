package GameSpecific;

import Structure.World;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds all the necessary information for creating a level
 */
public class LevelWL extends World {
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
    private PlayerWL p;
    private Group cameraGroup;


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

    public void setPlayer(PlayerWL p) {
        this.p = p;
        getChildren().add(p);
    }

    public PlayerWL getPlayer() {
        return p;
    }


    public void setCameraGroup(Group g) {
        cameraGroup = g;
        cameraGroup.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS));

        cameraGroup.setTranslateZ(-10);
        cameraGroup.setTranslateX(-10);
    }

    public void addCameraGroupToWorld() {
        getChildren().add(cameraGroup);
    }

    public Group getCameraGroup() {
        return cameraGroup;
    }


}