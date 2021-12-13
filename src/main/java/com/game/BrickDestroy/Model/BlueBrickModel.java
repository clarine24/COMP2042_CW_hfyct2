package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * The BlueBrickModel class is the blue brick model.
 * This class extends the BrickCrackModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class BlueBrickModel extends BrickCrackModel {
    private static final String NAME = "blueBrick";
    private static final int FULL_STRENGTH = 2;

    private static final double BREAK_PROBABILITY = 0.5;
    private boolean hit;
    private Random rnd;

    /**
     * Creates a new instance of BlueBrickModel.
     * @param brick the brick to be created
     */
    public BlueBrickModel(Rectangle brick) {
        super(brick, NAME, FULL_STRENGTH);
        rnd = new Random();
        hit = false;
    }

    @Override
    public boolean setImpact() {
        impact();
        if(hit && !super.isBroken().get()) {
            hit = false;
            setCrack(true);
            return false;
        }
        setCrack(false);
        return true;
    }

    /**
     * Set the effect of an impact on the blue brick.
     */
    @Override
    public void impact() {
        double x = rnd.nextDouble();
        System.out.println(x);
        if (x < BREAK_PROBABILITY) {
            hit = true;
            super.impact();
        }
    }
}
