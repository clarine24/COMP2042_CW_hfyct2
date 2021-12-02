package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;

    private Rectangle area;

    public WallModel(Rectangle wall, Rectangle player, Circle ball) {
        area = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, area);
        this.ball = new RubberBallModel(ball);
    }

    public void move() {
        //ball.move();
        player.move();
    }

    public Rectangle getArea() {
        return area;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public BallModel getBall() {
        return ball;
    }
}
