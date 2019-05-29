package Structure;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public abstract class World extends Group {

    private HashMap<Class, ArrayList> actors;
    private HashSet<KeyCode> keyCodes;
    private AnimationTimer actTimer;
    private double currentTime;
    private double oldTime;

    public World() {
        actors = new HashMap<>();
        keyCodes = new HashSet<>();

        actTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (oldTime == currentTime) {
                    currentTime = System.currentTimeMillis();
                } else {
                    double temp = currentTime;
                    currentTime = System.currentTimeMillis();
                    oldTime = temp;
                }

                act();
                for (Node n : getChildren()) {
                    if (n instanceof Actor) {
                        ((Actor) n).act();
                    }
                }
            }
        };
        actTimer.stop();

        setOnKeyPressed(event -> {
            if (!keyCodes.contains(event.getCode())) {
                keyCodes.add(event.getCode());
                System.out.println("EEEEEE");
            }
        });

        setOnKeyReleased(event -> {
            keyCodes.remove(event.getCode());
        });
    }

    public World(double width, double height) {
        this();
        minHeight(height);
        minWidth(width);
        prefWidth(width);
        prefHeight(height);
        maxHeight(height);
        maxWidth(width);
    }


    protected <A extends Node & Actor> void add(A a) {
        Class c = a.getClass();
        if (!actors.containsKey(c)) {
            ArrayList<A> arr = new ArrayList<>();
            arr.add(a);
            actors.put(c, arr);
        } else {
            actors.get(c).add(a);
        }

        getChildren().add(a);


    }

    @SafeVarargs
    protected final <A extends Node & Actor> void addAll(A... a) {
        Class c = a[0].getClass();
        if (!actors.containsKey(c)) {
            ArrayList<A> arr = new ArrayList<>(Arrays.asList(a));

        } else {
            actors.get(c).add(a);
        }

        getChildren().addAll(Arrays.asList(a));
    }

    public <A extends Node & Actor> void remove(A a) {
        if (getChildren().contains(a)) {
            getChildren().remove(a);
            actors.get(a.getClass()).remove(a);
        }
    }

    public double deltaTime() {
        return currentTime - oldTime;
    }

    public abstract void act();

    public void start() {
        actTimer.start();
        currentTime = System.currentTimeMillis();
        oldTime = currentTime;
    }

    public void stop() {
        actTimer.stop();
    }

    protected boolean isKeyDown(KeyCode c) {
        return keyCodes.contains(c);
    }

    public void setKeyDown(KeyCode c) {
        keyCodes.add(c);
    }

    public void setKeyUp(KeyCode c) {
        keyCodes.remove(c);
    }


}
