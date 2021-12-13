package com.game.BrickDestroy.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Rectangle;

/**
 * The BrickModel class is the brick model.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class BrickModel {
    private Rectangle brickFace;
    private final StringProperty name;

    private final int fullStrength;
    private int currentStrength;

    private BooleanProperty broken;

    private boolean additionalBall;

    /**
     * Creates a new instance of BrickModel with the given brick's shape, name and full strength.
     * @param brick the shape of the brick
     * @param name the name of the brick
     * @param fullStrength the full strength of the brick
     */
    public BrickModel(Rectangle brick, String name, int fullStrength) {
        brickFace = makeBrickFace(brick);
        this.name = new SimpleStringProperty(name);
        this.fullStrength = currentStrength = fullStrength;
        broken = new SimpleBooleanProperty(false);

        setAdditionalBall(false);
    }

    /**
     * Creates the brick shape based on the given brick shape.
     * @param brick the shape of the brick
     * @return the created brick shape
     */
    public Rectangle makeBrickFace(Rectangle brick) {
        return new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

    /**
     * Gets the shape of the brick.
     * The brick shape contains information about the brick's coordinates and size.
     * @return the brick shape
     */
    public Rectangle getBrickFace() {
        return brickFace;
    }

    /**
     * Sets the impact on the brick.
     * Check if the brick is broken.
     * @return the boolean value of the property broken
     */
    public boolean setImpact() {
        impact();
        return isBroken().get();
    }

    /**
     * Sets the effect of the impact of the brick.
     * Reduces the strength of the brick and sets the value of the boolean broken.
     */
    public void impact() {
        currentStrength--;
        broken.set(currentStrength == 0);
    }

    /**
     * Resets the state of the brick.
     * Restores the full strength of the brick.
     * Set the boolean broken to false.
     */
    public void repair() {
        broken.set(false);
        currentStrength = fullStrength;
    }

    /**
     * Gets the string property of the brick's name.
     * @return the string property of name
     */
    public StringProperty getName() {
        return name;
    }

    /**
     * Gets the boolean property of broken.
     * @return the value of boolean property broken
     */
    public BooleanProperty isBroken() {
        return broken;
    }

    /**
     * Gets the boolean value of additionalBall.
     * Checks if the brick contains an additional ball.
     * @return the boolean value of additional ball
     */
    public boolean isAdditionalBall() {
        return additionalBall;
    }

    /**
     * Sets the value of the boolean additionalBall.
     * @param additionalBall the new value of the boolean additional ball
     */
    public void setAdditionalBall(boolean additionalBall) {
        this.additionalBall = additionalBall;
    }
}
