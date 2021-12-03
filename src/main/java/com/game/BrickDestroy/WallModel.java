package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;

    private Rectangle wall;

    private int ballCount;
    private boolean ballLost;

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
        if(impactBottomBorder()) {
            ballCount--;
            ballLost = true;
        }
        else if(impactTopBorder()) {
            ball.reverseY();
        }
        else if(impactLeftRightBorder()) {
            ball.reverseX();
        }
        else if (ballHitPlayer()) {
            ball.reverseY();
        }
    }

    private boolean impactLeftRightBorder() {
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
        }
        return false;
    }

    private boolean impactBottomBorder() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        if(intersect.getBoundsInLocal().getHeight() != -1) {
            return false;
        }
        return true;
    }

    private boolean ballHitPlayer() {
        return ball.getBallFace().intersects(player.getPlayerFace().getBoundsInLocal());
    }

    public void ballPlayerReset() {
        player.moveToStart();
        ball.moveToStart();
        ball.setMoveDirection();
        ballLost = false;
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

    public boolean isBallLost() {
        return ballLost;
    }
}
