package com.game.BrickDestroy.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The ScoreBoardModel class is the model for score board view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class ScoreBoardModel {
    private static ScoreBoardModel instance;

    private LevelScore[] allScore;
    private int level;

    private BooleanProperty open;

    /**
     * Creates a new instance of ScoreBoardModel.
     */
    public ScoreBoardModel() {
        open = new SimpleBooleanProperty(false);
        createLevelScores();

        instance = this;
    }

    /**
     * Gets the created score board model instance.
     * @return the ScoreBoardModel instance
     */
    public static ScoreBoardModel getInstance() {
        return instance;
    }

    /**
     * Creates an array of scores.
     */
    private void createLevelScores() {
        allScore = new LevelScore[WallModel.LEVELS_COUNT];

        for(int i=0; i<WallModel.LEVELS_COUNT; i++) {
            allScore[i] = new LevelScore(i + 1);
        }
    }

    /**
     * Sets the level of the game.
     * @param level the current game level
     */
    public void setLevel(IntegerProperty level) {
        this.level = level.get();
    }

    /**
     * Gets the LevelScore model.
     * @return the LevelScore model
     */
    public LevelScore getScore() {
        return allScore[level];
    }

    /**
     * Close all external txt files.
     */
    public void closeAllFiles() {
        for (LevelScore levelScore : allScore) {
            levelScore.closeFile();
        }
    }

    /**
     * Gets the boolean property of open.
     * @return the boolean property of open
     */
    public BooleanProperty isOpen() {
        return open;
    }

    /**
     * Sets the boolean value of open.
     * @param open the new boolean value of open
     */
    public void setOpen(boolean open) {
        this.open.set(open);
    }
}
