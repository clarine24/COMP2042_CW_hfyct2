package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;

/**
 * The CementBrickModel class is the cement brick model.
 * This class extends the BrickCrackModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class CementBrickModel extends BrickCrackModel {
    private static final String NAME = "cementBrick";
    private static final int FULL_STRENGTH = 2;

    /**
     * Creates a new instance of CementBrickModel.
     * @param brick the brick to be created
     */
    public CementBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
    }
}
