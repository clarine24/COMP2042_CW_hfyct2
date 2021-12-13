package com.game.BrickDestroy.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Rectangle;

/**
 * The BrickCrackModel class is the model for bricks with cracks.
 * The class extends the BrickModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class BrickCrackModel extends BrickModel {
    private BooleanProperty crack;

    /**
     * Creates a new instance of BrickCrackModel with the given brick's shape, name and full strength.
     * @param brick the shape of the brick
     * @param name the name of the brick
     * @param fullStrength the full strength of the brick
     */
    public BrickCrackModel(Rectangle brick, String name, int fullStrength) {
        super(brick, name, fullStrength);
        this.crack = new SimpleBooleanProperty(false);
    }

    /**
     * Sets the boolean value of crack.
     * @param crack the new value of the boolean crack
     */
    public void setCrack(boolean crack) {
        this.crack.set(crack);
    }

    /**
     * Sets the effect of the impact on the brick.
     * @return the boolean value of broken
     */
    @Override
    public boolean setImpact() {
        impact();
        if(!super.isBroken().get()) {
            setCrack(true);
            return false;
        }
        setCrack(false);
        return true;
    }

    /**
     * Resets the state of the brick.
     * Restores the full strength of the brick and remove cracks on the brick.
     */
    @Override
    public void repair() {
        super.repair();
        crack.set(false);
    }

    /**
     * Gets the boolean property of crack.
     * @return the boolean property of crack
     */
    public BooleanProperty isCrack() {
        return crack;
    }
}
