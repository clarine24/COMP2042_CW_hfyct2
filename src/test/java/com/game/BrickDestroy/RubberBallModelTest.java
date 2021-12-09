package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RubberBallModelTest {
    Circle ballTest = new Circle(300, 425, 10);
    Rectangle wallTest = new Rectangle(0, 50, 600, 450);
    RubberBallModel rubberBallModel = new RubberBallModel(ballTest, wallTest);

    @Test
    void makeBall_ballFaceSameAsBallPassedInFromParameter() {
        rubberBallModel.makeBall(ballTest);
        assertEquals(ballTest.getRadius(), rubberBallModel.getBallFace().getRadius());
        assertEquals(ballTest.getCenterX(), rubberBallModel.getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), rubberBallModel.getBallFace().getCenterY());
    }
}