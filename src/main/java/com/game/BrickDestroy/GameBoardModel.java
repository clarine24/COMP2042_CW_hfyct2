package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBoardModel {
    private WallModel wallModel;
    private PauseMenuModel pauseMenuModel;

    private GameTimer gameTimer;

    public GameBoardModel(Rectangle wall, Rectangle player, Circle ball) {
        wallModel = new WallModel(wall, player, ball);
        pauseMenuModel = new PauseMenuModel();

        gameTimer = new GameTimer(wallModel);
    }

    public WallModel getWallModel() {
        return wallModel;
    }

    public PauseMenuModel getPauseMenuModel() {
        return pauseMenuModel;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}
