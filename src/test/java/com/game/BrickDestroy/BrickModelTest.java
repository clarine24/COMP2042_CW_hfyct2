package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickModelTest {
    Rectangle brickTest = new Rectangle(70, 0, 40, 20);
    BrickModel brickModel = new BrickModel(brickTest, "brickTest", 1);

    @Test
    void makeBrickFace_brickFaceSameAsBrickPassedInFromParameter() {
        brickModel.makeBrickFace(brickTest);
        assertEquals(brickTest.getX(), brickModel.getBrickFace().getX());
        assertEquals(brickTest.getY(), brickModel.getBrickFace().getY());
        assertEquals(brickTest.getWidth(), brickModel.getBrickFace().getWidth());
        assertEquals(brickTest.getHeight(), brickModel.getBrickFace().getHeight());
    }

    @Test
    void setImpact_fullStrengthIs1_BrickBroken() {
        assertTrue(brickModel.setImpact());
    }

    @Test
    void setImpact_setImpactOnce_fullStrengthIs5_BrickNotBroken() {
        brickModel = new BrickModel(brickTest, "brickTest", 5);
        assertFalse(brickModel.setImpact());
    }

    @Test
    void repair_BrickNotBroken() {
        brickModel.repair();
        assertFalse(brickModel.isBroken().get());
    }

    @Test
    void getName_true() {
        assertEquals("brickTest", brickModel.getName().get());
    }

    @Test
    void isBroken_defaultBrokenValue_false() {
        assertFalse(brickModel.isBroken().get());
    }
}