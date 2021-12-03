package com.game.BrickDestroy;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameTimer extends AnimationTimer {
    private WallModel wall;
    
    private BooleanProperty running;
    private BooleanProperty gameOver;

    public GameTimer(WallModel wall) {
        this.wall = wall;
        running = new SimpleBooleanProperty(false);
        gameOver = new SimpleBooleanProperty(false);
    }

    @Override
    public void handle(long l) {
        wall.move();
        wall.findImpacts();

        if(wall.isBallLost()) {
            if(wall.ballEnd()) {
                gameOver.setValue(true);
            }
            stop();
            wall.ballPlayerReset();
        }
    }

    @Override
    public void start() {
        super.start();
        running.setValue(true);
    }

    @Override
    public void stop() {
        super.stop();
        running.setValue(false);
    }

    public BooleanProperty isRunning() {
        return running;
    }

    public BooleanProperty isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver.set(gameOver);
    }
}
