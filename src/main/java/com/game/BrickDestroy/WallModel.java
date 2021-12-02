package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
        if(impactSideBorder()) {
            ball.reverseX();
        }
        else if(impactTopBorder()) {
            ball.reverseY();
        }
        else if (ball.getBallFace().intersects(player.getPlayerFace().getBoundsInLocal())) {
            ball.reverseY();
        }
    }

    private boolean impactSideBorder() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        if(intersect.getBoundsInLocal().getWidth() < ball.getBallFace().getRadius() * 2)  {
            return true;
        }
        return false;
    }

    private boolean impactTopBorder() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        if(intersect.getBoundsInLocal().getHeight() < ball.getBallFace().getRadius() * 2) { //hit top or bottom of wall
            if(intersect.getBoundsInLocal().getMinY() == 0) { //hit top of wall
                return true;
            }
            return false;
        }
        return false;
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
