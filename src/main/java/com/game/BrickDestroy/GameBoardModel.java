package com.game.BrickDestroy;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBoardModel {
    private WallModel wallModel;

    private AnimationTimer gameTimer;

    public GameBoardModel(Rectangle wall, Rectangle player, Circle ball) {
        wallModel = new WallModel(wall, player, ball);

        gameTimer = new GameTimer(wallModel);
    }

    public WallModel getWallModel() {
        return wallModel;
    }

    public AnimationTimer getGameTimer() {
        return gameTimer;
    }
}
