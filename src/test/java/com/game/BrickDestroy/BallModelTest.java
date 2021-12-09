package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallModelTest {
    Circle ballTest = new Circle(300, 425, 10);
    Rectangle wallTest = new Rectangle(0, 50, 600, 450);
    BallModel ballModel = new BallModel("ballTest", ballTest, wallTest);

    @Test
    void getBallFace_createdBallFaceSameAsBallPassedInParameter() {
        assertEquals(ballTest.getCenterX(), ballModel.getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), ballModel.getBallFace().getCenterY());
        assertEquals(ballTest.getRadius(), ballModel.getBallFace().getRadius());
    }

    @Test
    void move() {
    }

    @Test
    void moveToStart_ballPositionBackToStartPosition() {
        ballModel.move(); //Move ball away from start position
        ballModel.moveToStart(); //Move ball back to start position

        assertEquals(ballTest.getCenterX(), ballModel.getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), ballModel.getBallFace().getCenterY());
    }

    @Test
    void setMoveDirection() {
    }

    @Test
    void reverseX() {
    }

    @Test
    void reverseY() {
    }

    @Test
    void getSpeed_defaultSpeed_1() {
        assertEquals(1, ballModel.getSpeed().get());
    }

    @Test
    void getName() {
        assertEquals("ballTest", ballModel.getName().get());
    }
}