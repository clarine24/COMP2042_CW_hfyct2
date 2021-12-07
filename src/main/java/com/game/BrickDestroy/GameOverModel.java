package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameOverModel {
    private static GameOverModel instance;
    private Score[] allScore;

    private BooleanProperty restart;
    private BooleanProperty nextLevel;

    private int level;

    public GameOverModel() {
        restart = new SimpleBooleanProperty(false);
        nextLevel = new SimpleBooleanProperty(false);

        createScore();

        instance = this;
    }

    public static GameOverModel getInstance() {
        return instance;
    }

    private void createScore() {
        allScore = new Score[WallModel.LEVELS_COUNT];

        for(int i=0; i<WallModel.LEVELS_COUNT; i++) {
            allScore[i] = new Score(i + 1);
        }
    }

    public void setRestart(boolean restart) {
        this.restart.set(restart);
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel.set(nextLevel);
    }

    public BooleanProperty isRestart() {
        return restart;
    }

    public BooleanProperty isNextLevel() {
        return nextLevel;
    }

    public void setLevel(IntegerProperty level) {
        this.level = level.get();
    }

    public Score getScore() {
        return allScore[level];
    }
}
