package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Rectangle;

public abstract class BrickModel {
    private Rectangle brickFace;
    private StringProperty name;

    private int fullStrength;
    private int currentStrength;

    private BooleanProperty broken;

    public BrickModel(Rectangle brick, String name, int fullStrength) {
        brickFace = makeBrickFace(brick);
        this.name = new SimpleStringProperty(name);
        this.fullStrength = currentStrength = fullStrength;
        broken = new SimpleBooleanProperty(false);
    }

    protected abstract Rectangle makeBrickFace(Rectangle brick);

    public Rectangle getBrickFace() {
        return brickFace;
    }

    public boolean setImpact() {
        impact();
        return isBroken().get();
    }

    public void impact() {
        currentStrength--;
        broken.set(currentStrength == 0);
    }

    public void repair() {
        broken.set(false);
        currentStrength = fullStrength;
    }

    public StringProperty getName() {
        return name;
    }

    public BooleanProperty isBroken() {
        return broken;
    }
}
