package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;

    private Rectangle wall;

    public WallModel(Rectangle wall, Rectangle player, Circle ball) {
        this.wall = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, this.wall);
        this.ball = new RubberBallModel(ball);
    }

    public void move() {
        ball.move();
        player.move();
    }

    public void findImpacts() {
        //if(ball.getBallFace().intersects(wall))
    }

    public Rectangle getWall() {
        return wall;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public BallModel getBall() {
        return ball;
    }
}
