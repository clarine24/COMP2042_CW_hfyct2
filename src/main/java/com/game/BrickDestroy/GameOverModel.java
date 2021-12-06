package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameOverModel {
    private static GameOverModel instance;
    private Score score;

    private BooleanProperty restart;
    private BooleanProperty nextLevel;

    public GameOverModel() {
        restart = new SimpleBooleanProperty(false);
        nextLevel = new SimpleBooleanProperty(false);

        score = new Score();

        instance = this;
    }

    public static GameOverModel getInstance() {
        return instance;
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

    public BooleanProperty isNextLevel() { return nextLevel; }

    public Score getScore() {
        return score;
    }
}
