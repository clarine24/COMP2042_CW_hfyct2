package com.game.BrickDestroy;

import javafx.scene.shape.Circle;

public class RubberBallModel extends BallModel {
    public RubberBallModel(Circle ball) {
        super(ball);
    }

    @Override
    protected Circle makeBall(Circle ball) {
        return new Circle(ball.getCenterX(), ball.getCenterY(), ball.getRadius());
    }
}
