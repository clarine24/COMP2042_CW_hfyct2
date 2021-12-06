package com.game.BrickDestroy;

import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class CementBrickModel extends BrickCrackModel {
    private static final String NAME = "cementBrick";
    private static final int FULL_STRENGTH = 2;

    public CementBrickModel(Rectangle brick, Path crack) {
        super(brick, NAME, FULL_STRENGTH, crack);
    }

    @Override
    protected Rectangle makeBrickFace(Rectangle brick) {
        return new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }
}
