package com.game.BrickDestroy;

import javafx.scene.shape.Circle;

public abstract class BallModel {
    private Circle ballFace;
    private double centerX;
    private double centerY;

    private double speedX;
    private double speedY;

    public BallModel(Circle ball) {
        centerX = ball.getCenterX();
        centerY = ball.getCenterY();

        ballFace = makeBall(ball);
    }

    protected abstract Circle makeBall(Circle ball);

    public Circle getBallFace() {
        return ballFace;
    }

    public double getSpeedX(){
        return speedX;
    }

    public double getSpeedY(){
        return speedY;
    }
}
