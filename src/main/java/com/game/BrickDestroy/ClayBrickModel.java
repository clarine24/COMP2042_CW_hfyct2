package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class ClayBrickModel extends BrickModel {
    private static final String NAME = "clayBrick";
    private static final int FULL_STRENGTH = 1;

    public ClayBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
    }
}
