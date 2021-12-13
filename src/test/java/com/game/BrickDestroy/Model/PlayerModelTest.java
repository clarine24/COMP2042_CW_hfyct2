package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {
    Rectangle playerTest;
    Rectangle wallTest;
    PlayerModel playerModel;

    @BeforeEach
    void setUp() {
        playerTest = new Rectangle(100, 250, 150, 50);
        wallTest = new Rectangle(0, 50, 600, 450);
        playerModel = new PlayerModel(playerTest, wallTest);
    }

    @AfterEach
    void tearDown() {
        playerTest = null;
        wallTest = null;
        playerModel = null;
    }

    @Test
    void getPlayerFace_playerFaceSameAsPlayerPassedInFromParameter() {
        assertEquals(playerTest.getX(), playerModel.getPlayerFace().getX());
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
        assertEquals(playerTest.getWidth(), playerModel.getPlayerFace().getWidth());
        assertEquals(playerTest.getHeight(), playerModel.getPlayerFace().getHeight());
    }

    @Test
    void move_defaultMoveAmount_noChangeInCoordinates() {
        playerModel.move();
        assertEquals(playerTest.getX(), playerModel.getPlayerFace().getX());
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }

    @Test
    void move_playerMoveOutOfWallAtLeftSide_playerStopAtWallBoundary() {
        for(int i=0; i<100000; i++) {
            playerModel.moveLeft();
            playerModel.move();
        }

        assertEquals(wallTest.getX(), playerModel.getPlayerFace().getX());
    }

    @Test
    void move_playerMoveOutOfWallAtRightSide_playerStopAtWallBoundary() {
        for(int i=0; i<100000; i++) {
            playerModel.moveRight();
            playerModel.move();
        }

        assertEquals(wallTest.getX() + wallTest.getWidth() - playerTest.getWidth(), playerModel.getPlayerFace().getX());
    }

    @Test
    void moveLeft_playerMoveLeft1Step_xCoordinateReduceBy7() {
        playerModel.moveLeft();
        playerModel.move();
        assertEquals(playerTest.getX()-7, playerModel.getPlayerFace().getX());
    }

    @Test
    void moveLeft_playerMoveLeft1Step_noChangeInYCoordinate() {
        playerModel.moveLeft();
        playerModel.move();
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }

    @Test
    void moveRight_playerMoveRight1Step_xCoordinateIncreaseBy7() {
        playerModel.moveRight();
        playerModel.move();
        assertEquals(playerTest.getX()+7, playerModel.getPlayerFace().getX());
    }

    @Test
    void moveRight_playerMoveRight1Step_noChangeInYCoordinate() {
        playerModel.moveRight();
        playerModel.move();
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }

    @Test
    void stop_playerStopAtStartPosition_noChangeInCoordinates() {
        playerModel.stop();
        playerModel.move();
        assertEquals(playerTest.getX(), playerModel.getPlayerFace().getX());
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }

    @Test
    void stop_playerMoveRight1StepThenStop_noChangeInCoordinatesWhenStop() {
        //Player move right 1 step
        playerModel.moveRight();
        playerModel.move();
        playerTest.setX(playerTest.getX() + 7);

        //Player stop
        playerModel.stop();
        playerModel.move();

        assertEquals(playerTest.getX(), playerModel.getPlayerFace().getX());
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }

    @Test
    void moveToStart_movePlayerToStartPosition() {
        //Player moves away from start position
        playerModel.moveRight();
        playerModel.move();

        //Player moves to start position
        playerModel.moveToStart();
        assertEquals(playerTest.getX(), playerModel.getPlayerFace().getX());
        assertEquals(playerTest.getY(), playerModel.getPlayerFace().getY());
    }
}