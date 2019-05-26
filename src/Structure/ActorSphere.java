package Structure;

import javafx.scene.shape.Sphere;

public abstract class ActorSphere extends Sphere implements Actor {

    public ActorSphere(double r) {
        setRadius(r);
    }
}
