package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * The SteelBrickModel class is the steel brick model.
 * This class extends the BrickModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class SteelBrickModel extends BrickModel {
    private static final String NAME = "steelBrick";

    private static final int FULL_STRENGTH = 1;
    private static final double BREAK_PROBABILITY = 0.4;

    private Random rnd;

    /**
     * Creates a new instance of SteelBrickModel.
     * @param brick the brick to be created
     */
    public SteelBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
        rnd = new Random();
    }

    /**
     * Set the effect of an impact on the steel brick.
     */
    @Override
    public void impact() {
        double x = rnd.nextDouble();
        if (x < BREAK_PROBABILITY) {
            super.impact();
        }
    }
}
