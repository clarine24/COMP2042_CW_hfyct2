package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallModelTest {
    Circle ballTest = new Circle(300, 425, 10);
    Rectangle wallTest = new Rectangle(0, 0, 600, 450);
    BallModel ballModel = new BallModel("ballTest", ballTest, wallTest);

    @Test
    void getBallFace_createdBallFaceSameAsBallPassedInParameter() {
        assertEquals(ballTest.getCenterX(), ballModel.getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), ballModel.getBallFace().getCenterY());
        assertEquals(ballTest.getRadius(), ballModel.getBallFace().getRadius());
    }

    @Test
    void move_defaultMove_xCoordinateIncreaseOrDecreaseBy4() {
        ballModel.move();

        assertTrue((ballTest.getCenterX() + 4 == ballModel.getBallFace().getCenterX()) ||
                (ballTest.getCenterX() - 4 == ballModel.getBallFace().getCenterX()));
    }

    @Test
    void move_defaultMove_yCoordinateReduceBy4() {
        ballModel.move();

        assertEquals(ballTest.getCenterY() - 4, ballModel.getBallFace().getCenterY());
    }

    @Test
    void move_ballMoveOutOfWallAtLeftRightSide_ballStopAtWallBoundary() {
        for(int i=0; i<1000000; i++) {
            ballModel.move();
        }

        assertTrue((wallTest.getX() + ballTest.getRadius() == ballModel.getBallFace().getCenterX()) ||
                (wallTest.getX() + wallTest.getWidth() - ballTest.getRadius() == ballModel.getBallFace().getCenterX()));
    }

    @Test
    void move_ballMoveOutOfWallAtTop_ballStopAtWallBoundary() {
        for(int i=0; i<1000000; i++) {
            ballModel.move();
        }

        assertTrue(ballModel.getBallFace().getCenterY() == wallTest.getY() + ballTest.getRadius());
    }

    @Test
    void moveToStart_ballPositionBackToStartPosition() {
        ballModel.move(); //Move ball away from start position
        ballModel.moveToStart(); //Move ball back to start position

        assertEquals(ballTest.getCenterX(), ballModel.getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), ballModel.getBallFace().getCenterY());
    }

    @Test
    void setMoveDirection_defaultMoveDirection_xCoordinateIncreaseOrReduceBy4() {
        ballModel.setMoveDirection();
        ballModel.move();

        assertTrue((ballTest.getCenterX() + 4 == ballModel.getBallFace().getCenterX()) ||
                (ballTest.getCenterX() - 4 == ballModel.getBallFace().getCenterX()));
    }

    @Test
    void setMoveDirection_defaultMoveDirection_yCoordinateReduceBy4() {
        ballModel.setMoveDirection();
        ballModel.move();

        assertEquals(ballTest.getCenterY() - 4, ballModel.getBallFace().getCenterY());
    }

    @Test
    void reverseX_ballMoveOnceThenChangeDirection_ballReturnToStartPosition() {
        ballModel.move(); //ball move once

        ballModel.reverseX(); //change move direction
        ballModel.move(); //move ball again

        assertEquals(ballTest.getCenterX(), ballModel.getBallFace().getCenterX());

    }

    @Test
    void reverseY_changeDefaultMoveDirection_yCoordinateIncreaseBy4() {
        ballModel.reverseY();
        ballModel.move();

        assertEquals(ballTest.getCenterY() + 4, ballModel.getBallFace().getCenterY());
    }

    @Test
    void getSpeed_defaultSpeed_1() {
        assertEquals(1, ballModel.getSpeed().get());
    }

    @Test
    void getName_true() {
        assertTrue("ballTest".equals(ballModel.getName().get()));
    }
}