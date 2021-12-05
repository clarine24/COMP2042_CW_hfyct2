package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RubberBallModel extends BallModel {
    public RubberBallModel(Circle ball, Rectangle wall) {
        super(ball, wall);
    }

    @Override
    protected Circle makeBall(Circle ball) {
        return new Circle(ball.getCenterX(), ball.getCenterY(), ball.getRadius());
    }
}
