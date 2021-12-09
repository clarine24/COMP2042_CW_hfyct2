package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickCrackModelTest {
    Rectangle brickTest = new Rectangle(0, 0, 100, 25);
    int fullStrength = 3;
    BrickCrackModel brickCrackModel = new BrickCrackModel(brickTest, "brickTest", fullStrength);

    @Test
    void setCrack_true() {
        brickCrackModel.setCrack(true);
        assertTrue(brickCrackModel.isCrack().get());
    }

    @Test
    void setImpact_BrickNotBroken_BrickCracks() {
        brickCrackModel.setImpact();
        assertTrue(brickCrackModel.isCrack().get());
    }

    @Test
    void setImpact_BrickBroken_NoCrack() {
        for(int i=0; i<fullStrength; i++) {
            brickCrackModel.setImpact();
        }
        assertFalse(brickCrackModel.isCrack().get());
    }

    @Test
    void repair_BrickNoCrack() {
        brickCrackModel.repair();
        assertFalse(brickCrackModel.isCrack().get());
    }

    @Test
    void isCrack_defaultCrack_NoCrack() {
        assertFalse(brickCrackModel.isCrack().get());
    }
}