package Structure;

import Structure.Actor;
import javafx.scene.shape.Box;

public abstract class ActorBox extends Box implements Actor {

    public ActorBox(double width, double height, double depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }
}
