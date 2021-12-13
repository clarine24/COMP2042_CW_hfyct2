package com.game.BrickDestroy.Model;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The GameTimer class is the animation timer of the game.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GameTimer extends AnimationTimer {
    private WallModel wall;
    
    private BooleanProperty running;
    private BooleanProperty gameOver;
    private BooleanProperty displayAddBallText;

    private int timer;

    /**
     * Creates an instance of GameTimer with the given wallModel.
     * @param wall the wallModel
     */
    public GameTimer(WallModel wall) {
        this.wall = wall;
        running = new SimpleBooleanProperty(false);
        gameOver = new SimpleBooleanProperty(false);

        displayAddBallText = new SimpleBooleanProperty(false);
        timer = 0;
    }

    /**
     * Trigger the events for every timeframe.
     */
    @Override
    public void handle(long l) {
        wall.move();
        wall.findImpacts();

        if(getDisplayAddBallText().get()) {
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

    /**
     * Start the animation timer.
     */
    @Override
    public void start() {
        super.start();
        running.setValue(true);
    }

    /**
     * Stop the animation timer.
     */
    @Override
    public void stop() {
        super.stop();
        running.setValue(false);
    }

    /**
     * Gets the boolean property of running.
     * @return the boolean property of running
     */
    public BooleanProperty isRunning() {
        return running;
    }

    /**
     * Gets the boolean property of GameOver.
     * @return the boolean property of game over
     */
    public BooleanProperty isGameOver() {
        return gameOver;
    }

    /**
     * Sets the boolean value of GameOver.
     * @param gameOver the new boolean value of game over
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver.set(gameOver);
    }

    /**
     * Gets the boolean property of displayAddBallText.
     * @return the boolean property of the whether the add ball text is display
     */
    public BooleanProperty getDisplayAddBallText() {
        return displayAddBallText;
    }

    /**
     * Sets the boolean value of displayAddBallText
     * @param displayAddBallText the new boolean value of whether the add ball text is display
     */
    public void setDisplayAddBallText(boolean displayAddBallText) {
        this.displayAddBallText.set(displayAddBallText);
    }
}
