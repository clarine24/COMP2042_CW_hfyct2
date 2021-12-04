package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class SteelBrickModel extends BrickModel {
    private Rectangle brickFace;
    private static final String NAME = "steelBrick";

    private static final int FULL_STRENGTH = 1;
    private static final double BREAK_PROBABILITY = 0.4;

    public SteelBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
    }

    @Override
    protected Rectangle makeBrickFace(Rectangle brick) {
        return new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

    @Override
    public Rectangle getBrickFace() {
        return brickFace;
    }
}
