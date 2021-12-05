package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Rectangle;

public abstract class BrickModel {
    Rectangle brickFace;
    private String name;

    private int fullStrength;
    private int currentStrength;

    private BooleanProperty broken;

    public BrickModel(Rectangle brick, String name, int fullStrength) {
        brickFace = makeBrickFace(brick);
        this.name = name;
        this.fullStrength = currentStrength = fullStrength;
        broken = new SimpleBooleanProperty(false);
    }

    protected abstract Rectangle makeBrickFace(Rectangle brick);

    public abstract Rectangle getBrickFace();

    public boolean setImpact() {
        impact();
        return isBroken().get();
    }

    public void impact() {
        currentStrength--;
        broken.set(currentStrength == 0);
    }

    public String getName() {
        return name;
    }

    public BooleanProperty isBroken() {
        return broken;
    }
}
