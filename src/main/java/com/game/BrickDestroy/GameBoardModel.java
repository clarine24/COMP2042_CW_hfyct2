package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class GameBoardModel {
    private WallModel wallModel;

    public GameBoardModel(Rectangle wall, Rectangle player) {
        wallModel = new WallModel(wall, player);
    }

    public WallModel getWallModel() {
        return wallModel;
    }
}
