package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardModelTest {
    ScoreBoardModel scoreBoardModel = new ScoreBoardModel();

    @Test
    void isOpen_defaultValue_false() {
        assertFalse(scoreBoardModel.isOpen().get());
    }

    @Test
    void setOpen_true() {
        scoreBoardModel.setOpen(true);
        assertTrue(scoreBoardModel.isOpen().get());
    }
}