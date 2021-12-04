package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public abstract class BrickModel {
    Rectangle brickFace;
    private String name;

    private int fullStrength;
    private int currentStrength;

    private boolean broken;

    public BrickModel(Rectangle brick, String name, int fullStrength) {
        brickFace = makeBrickFace(brick);
        this.name = name;
        this.fullStrength = currentStrength = fullStrength;
        broken = false;
    }

    protected abstract Rectangle makeBrickFace(Rectangle brick);

    public abstract Rectangle getBrickFace();

    public String getName() {
        return name;
    }

    public boolean isBroken() {
        return broken;
    }
}
