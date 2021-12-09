package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallModelTest {
    static Rectangle playerTest;
    static Circle ballTest;
    static Rectangle[] bricksTest;
    static WallModel wallModel;

    @BeforeAll
    static void setUp() {
        Rectangle wallTest = new Rectangle(10,20,600,500);
        playerTest = new Rectangle(550, 200,50,10);
        ballTest = new Circle(400,250,10);

        bricksTest = new Rectangle[2];
        for(int i=0; i<bricksTest.length; i++) {
            bricksTest[i] = new Rectangle(15,30,60,20);
        }

        wallModel = new WallModel(wallTest, playerTest, ballTest, bricksTest);
    }

    @Test
    void move_ballMovesFromStartPosition() {
        wallModel.move();

        assertNotEquals(ballTest.getCenterX(), wallModel.getBall().getBallFace().getCenterX());
        assertNotEquals(ballTest.getCenterY(), wallModel.getBall().getBallFace().getCenterY());
    }

    @Test
    void ballPlayerReset_ballMovesToStartPosition() {
        wallModel.move();
        wallModel.ballPlayerReset();

        assertEquals(ballTest.getCenterX(), wallModel.getBall().getBallFace().getCenterX());
        assertEquals(ballTest.getCenterY(), wallModel.getBall().getBallFace().getCenterY());
    }

    @Test
    void ballPlayerReset_playerMovesToStartPosition() {
        wallModel.getPlayer().moveRight();
        wallModel.ballPlayerReset();

        assertEquals(playerTest.getX(), wallModel.getPlayer().getPlayerFace().getX());
        assertEquals(playerTest.getY(), wallModel.getPlayer().getPlayerFace().getY());
    }

    @Test
    void ballPlayerReset_ballNotLost() {
        wallModel.ballPlayerReset();
        assertFalse(wallModel.isBallLost());
    }

    @Test
    void isBallLost_defaultValue_false() {
        assertFalse(wallModel.isBallLost());
    }

    @Test
    void getBrickCount_defaultValue_numberOfBricksPassedInParameter() {
        assertEquals(bricksTest.length, wallModel.getBrickCount().get());
    }

    @Test
    void isDone_defaultValue_false() {
        assertFalse(wallModel.isDone());
    }

    @Test
    void getLevel_defaultValue_0() {
        assertEquals(0, wallModel.getLevel().get());
    }

    @Test
    void hasNextLevel_defaultValue() {
        assertTrue(wallModel.hasNextLevel().get());
    }
}