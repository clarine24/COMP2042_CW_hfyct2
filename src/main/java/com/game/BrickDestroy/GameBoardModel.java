package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBoardModel {
    private WallModel wallModel;

    private GameTimer gameTimer;

    public GameBoardModel(Rectangle wall, Rectangle player, Circle ball) {
        wallModel = new WallModel(wall, player, ball);

        gameTimer = new GameTimer(wallModel);
    }

    public WallModel getWallModel() {
        return wallModel;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}
