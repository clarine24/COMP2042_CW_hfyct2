package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Rectangle;

public class BrickModel {
    private Rectangle brickFace;
    private final StringProperty name;

    private final int fullStrength;
    private int currentStrength;

    private BooleanProperty broken;

    private boolean additionalBall;

    public BrickModel(Rectangle brick, String name, int fullStrength) {
        brickFace = makeBrickFace(brick);
        this.name = new SimpleStringProperty(name);
        this.fullStrength = currentStrength = fullStrength;
        broken = new SimpleBooleanProperty(false);

        setAdditionalBall(false);
    }

    protected Rectangle makeBrickFace(Rectangle brick) {
        return new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

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

    public boolean isAdditionalBall() {
        return additionalBall;
    }

    public void setAdditionalBall(boolean additionalBall) {
        this.additionalBall = additionalBall;
    }
}
