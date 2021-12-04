package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class ClayBrickModel extends BrickModel {
    private static final String NAME = "clayBrick";
    private static final int FULL_STRENGTH = 1;

    public ClayBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
    }

    @Override
    protected Rectangle makeBrickFace(Rectangle brick) {
        return new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

    @Override
    public Rectangle getBrickFace() {
        return super.brickFace;
    }
}
