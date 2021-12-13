package com.game.BrickDestroy.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The PauseMenuModel class is the model for the pause menu view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class PauseMenuModel {
    private static PauseMenuModel instance;

    private BooleanProperty resume;
    private BooleanProperty restart;

    /**
     * Creates a new instance of PauseMenuModel.
     */
    public PauseMenuModel() {
        resume = new SimpleBooleanProperty(false);
        restart = new SimpleBooleanProperty(false);

        instance = this;
    }

    /**
     * Gets the created instance of PauseMenuModel.
     * @return the PauseMenuModel instance
     */
    public static PauseMenuModel getInstance() {
        return instance;
    }

    /**
     * Sets the boolean value of restart.
     * @param restart the new boolean value of restart
     */
    public void setRestart(boolean restart) {
        this.restart.set(restart);
    }

    /**
     * Sets the boolean value of resume.
     * @param resume the new boolean value of resume
     */
    public void setResume(boolean resume) {
        this.resume.set(resume);
    }

    /**
     * Gets the boolean property of resume.
     * @return the boolean property of resume
     */
    public BooleanProperty isResume() {
        return resume;
    }

    /**
     * Gets the boolean property of restart.
     * @return the boolean property of restart
     */
    public BooleanProperty isRestart() {
        return restart;
    }
}
