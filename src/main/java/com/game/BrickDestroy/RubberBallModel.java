package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RubberBallModel extends BallModel {
    private static final String NAME = "rubberBall";

    public RubberBallModel(Circle ball, Rectangle wall) {
        super(NAME, ball, wall);
    }
}
