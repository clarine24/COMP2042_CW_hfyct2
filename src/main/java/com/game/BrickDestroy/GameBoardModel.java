package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class GameBoardModel {
    private WallModel wallModel;
    private PauseMenuModel pauseMenuModel;
    private GameOverModel gameOverModel;

    private GameTimer gameTimer;

    private static GameBoardModel instance;

    public GameBoardModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks) {
        wallModel = new WallModel(wall, player, ball, bricks);
        pauseMenuModel = PauseMenuModel.getInstance();
        gameOverModel = GameOverModel.getInstance();

        gameTimer = new GameTimer(wallModel);

        instance = this;

        wallModel.nextLevel();
    }

    public static GameBoardModel getInstance() {
        return instance;
    }

    public WallModel getWallModel() {
        return wallModel;
    }

    public PauseMenuModel getPauseMenuModel() {
        return pauseMenuModel;
    }

    public GameOverModel getGameOverModel() {
        return gameOverModel;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void onLostFocus() {
        gameTimer.stop();
    }
}
