package com.game.BrickDestroy.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The GameOverModel class is the model class for the game over view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GameOverModel {
    private static GameOverModel instance;
    private ScoreBoardModel scoreBoardModel;

    private BooleanProperty restart;
    private BooleanProperty nextLevel;


    /**
     * Creates a new instance of GameOverModel.
     */
    public GameOverModel() {
        restart = new SimpleBooleanProperty(false);
        nextLevel = new SimpleBooleanProperty(false);

        scoreBoardModel = ScoreBoardModel.getInstance();

        instance = this;
    }

    /**
     * Gets the created instance of GameOverModel.
     * @return the GameOverModel instance
     */
    public static GameOverModel getInstance() {
        return instance;
    }

    /**
     * Gets the created scoreBoardModel instance.
     * @return the ScoreBoardModel instance
     */
    public ScoreBoardModel getScoreBoardModel() {
        return scoreBoardModel;
    }

    /**
     * Gets the boolean property value of restart.
     * @return the boolean property value of restart
     */
    public BooleanProperty isRestart() {
        return restart;
    }

    /**
     * Sets the boolean value of restart.
     * @param restart the new boolean value of restart
     */
    public void setRestart(boolean restart) {
        this.restart.set(restart);
    }

    /**
     * Gets the boolean property of nextLevel.
     * @return the boolean property of nextLevel
     */
    public BooleanProperty isNextLevel() {
        return nextLevel;
    }

    /**
     * Sets the boolean value of nextLevel.
     * @param nextLevel the new boolean value of nextLevel
     */
    public void setNextLevel(boolean nextLevel) {
        this.nextLevel.set(nextLevel);
    }
}
