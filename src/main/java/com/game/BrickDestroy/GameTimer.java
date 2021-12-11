package com.game.BrickDestroy;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameTimer extends AnimationTimer {
    private WallModel wall;
    
    private BooleanProperty running;
    private BooleanProperty gameOver;
    private BooleanProperty displayAddBallText;

    int timer;

    public GameTimer(WallModel wall) {
        this.wall = wall;
        running = new SimpleBooleanProperty(false);
        gameOver = new SimpleBooleanProperty(false);

        displayAddBallText = new SimpleBooleanProperty(false);
        timer = 0;
    }

    @Override
    public void handle(long l) {
        wall.move();
        wall.findImpacts();

        if(displayAddBallTextProperty().get()) {
            timer++;
            if(timer > 50) {
                setDisplayAddBallText(false);
                timer = 0;
            }
        }

        if(wall.isBallLost()) {
            if(wall.ballEnd()) {
                gameOver.setValue(true);
            }
            stop();
            wall.ballPlayerReset();
        }
        else if(wall.isDone()) {
            stop();
            gameOver.setValue(true);
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

    public BooleanProperty displayAddBallTextProperty() {
        return displayAddBallText;
    }

    public void setDisplayAddBallText(boolean displayAddBallText) {
        this.displayAddBallText.set(displayAddBallText);
    }
}
