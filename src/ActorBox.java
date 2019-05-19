import javafx.scene.shape.Box;

import java.util.HashMap;

public abstract class ActorBox extends Box implements Actor {

    public ActorBox(double width, double height, double depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }

}
