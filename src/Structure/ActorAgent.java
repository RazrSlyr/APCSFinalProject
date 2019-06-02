package Structure;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import sun.management.Agent;

public abstract class ActorAgent extends Group implements Actor {
    public ActorAgent(MeshView[] objs){
        this.getChildren().addAll(objs);
    }
}
