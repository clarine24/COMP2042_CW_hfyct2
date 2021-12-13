package com.game.BrickDestroy.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuModelTest {
    PauseMenuModel pauseMenuModel = new PauseMenuModel();

    @Test
    void setRestart_true() {
        pauseMenuModel.setRestart(true);
        assertTrue(pauseMenuModel.isRestart().get());
    }

    @Test
    void setResume_true() {
        pauseMenuModel.setResume(true);
        assertTrue(pauseMenuModel.isResume().get());
    }

    @Test
    void isResume_defaultValue_false() {
        assertFalse(pauseMenuModel.isResume().get());
    }

    @Test
    void isRestart_defaultValue_false() {
        assertFalse(pauseMenuModel.isRestart().get());
    }
}