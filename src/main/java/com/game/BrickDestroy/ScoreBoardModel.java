package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ScoreBoardModel {
    private static ScoreBoardModel instance;

    private LevelScore[] allScore;
    private int level;

    private BooleanProperty open;

    public ScoreBoardModel() {
        open = new SimpleBooleanProperty(false);
        createLevelScores();

        instance = this;
    }

    public static ScoreBoardModel getInstance() {
        return instance;
    }

    private void createLevelScores() {
        allScore = new LevelScore[WallModel.LEVELS_COUNT];

        for(int i=0; i<WallModel.LEVELS_COUNT; i++) {
            allScore[i] = new LevelScore(i + 1);
        }
    }

    public void setLevel(IntegerProperty level) {
        this.level = level.get();
    }

    public LevelScore getScore() {
        return allScore[level];
    }

    public void closeAllFiles() {
        for(int i=0; i<allScore.length; i++) {
            allScore[i].closeFile();
        }
    }

    public BooleanProperty isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open.set(open);
    }
}
