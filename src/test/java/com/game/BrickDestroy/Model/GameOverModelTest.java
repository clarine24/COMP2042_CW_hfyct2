package com.game.BrickDestroy.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverModelTest {
    GameOverModel gameOverModel = new GameOverModel();

    @Test
    void setRestart_true() {
        gameOverModel.setRestart(true);
        assertTrue(gameOverModel.isRestart().get());
    }

    @Test
    void setNextLevel_true() {
        gameOverModel.setNextLevel(true);
        assertTrue(gameOverModel.isNextLevel().get());
    }

    @Test
    void isRestart_defaultValue_false() {
        assertFalse(gameOverModel.isRestart().get());
    }

    @Test
    void isNextLevel_defaultValue_false() {
        assertFalse(gameOverModel.isNextLevel().get());
    }
}