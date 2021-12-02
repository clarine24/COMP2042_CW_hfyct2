package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class WallModel {
    private PlayerModel player;

    private Rectangle area;

    public WallModel(Rectangle wall, Rectangle player) {
        area = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, area);
    }

    public Rectangle getArea() {
        return area;
    }

    public PlayerModel getPlayer() {
        return player;
    }
}
