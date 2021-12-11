package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The GameBoardModel is the model of game board view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GameBoardModel {
    private WallModel wallModel;
    private PauseMenuModel pauseMenuModel;
    private GameOverModel gameOverModel;

    private GameTimer gameTimer;

    private static GameBoardModel instance;

    /**
     * Creates an instance of GameBoardModel with the given wall, player, ball and bricks.
     * @param wall the wall drawn in game board view
     * @param player the player drawn in game board view
     * @param ball the ball drawn in game board view
     * @param bricks the array of bricks drawn in game board view
     */
    public GameBoardModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks) {
        wallModel = new WallModel(wall, player, ball, bricks);
        pauseMenuModel = PauseMenuModel.getInstance();
        gameOverModel = GameOverModel.getInstance();

        gameTimer = new GameTimer(wallModel);

        instance = this;

        wallModel.nextLevel();
    }

    /**
     * Get the created GameBoardModel instance.
     * @return the GameBoardModel instance
     */
    public static GameBoardModel getInstance() {
        return instance;
    }

    /**
     * Get the created WalLModel instance.
     * @return the WalLModel instance
     */
    public WallModel getWallModel() {
        return wallModel;
    }

    /**
     * Get the created PauseMenuModel instance.
     * @return the PauseMenuModel instance
     */
    public PauseMenuModel getPauseMenuModel() {
        return pauseMenuModel;
    }

    /**
     * Get the created GameOverModel instance.
     * @return the GameOverModel instance
     */
    public GameOverModel getGameOverModel() {
        return gameOverModel;
    }

    /**
     * Get the created GameTimer instance.
     * @return the GameTimer instance
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * Stop the game timer.
     */
    public void onLostFocus() {
        gameTimer.stop();
    }
}
