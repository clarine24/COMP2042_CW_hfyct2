package com.game.BrickDestroy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {
    GameTimer gameTimer;

    @BeforeEach
    void setUp() {
        Rectangle[] bricks = new Rectangle[3];
        for(int i=0; i< bricks.length; i++) {
            bricks[i] = new Rectangle();
        }

        WallModel wallModel = new WallModel(new Rectangle(), new Rectangle(), new Circle(), bricks);
        gameTimer = new GameTimer(wallModel);
    }

    @AfterEach
    void tearDown() {
        gameTimer = null;
    }

    @Test
    void isRunning_defaultValue_false() {
        assertFalse(gameTimer.isRunning().get());
    }

    @Test
    void isGameOver_defaultValue_false() {
        assertFalse(gameTimer.isGameOver().get());
    }

    @Test
    void setGameOver_true() {
        gameTimer.setGameOver(true);
        assertTrue(gameTimer.isGameOver().get());
    }

    @Test
    void displayAddBallTextProperty_defaultValue_false() {
        assertFalse(gameTimer.displayAddBallTextProperty().get());
    }

    @Test
    void setDisplayAddBallText_true() {
        gameTimer.setDisplayAddBallText(true);
        assertTrue(gameTimer.displayAddBallTextProperty().get());
    }
}