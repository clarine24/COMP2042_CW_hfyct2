package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;

/**
 * The ClayBrickModel class is the clay brick model.
 * This class extends the BrickModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class ClayBrickModel extends BrickModel {
    private static final String NAME = "clayBrick";
    private static final int FULL_STRENGTH = 1;

    /**
     * Creates a new instance of ClayBrickModel.
     * @param brick the brick to be created
     */
    public ClayBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
    }
}
