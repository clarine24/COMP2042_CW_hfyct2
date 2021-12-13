package com.game.BrickDestroy.Model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The RubberBallModel class is the rubber ball model.
 * This class extends the BallModel class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class RubberBallModel extends BallModel {
    private static final String NAME = "rubberBall";

    /**
     * Creates a new instance of RubberBallModel.
     * @param ball the ball to be created
     * @param wall the wall in which the ball is in
     */
    public RubberBallModel(Circle ball, Rectangle wall) {
        super(NAME, ball, wall);
    }
}
