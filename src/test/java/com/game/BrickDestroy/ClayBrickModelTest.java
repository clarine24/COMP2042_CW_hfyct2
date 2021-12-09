package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickModelTest {
    Rectangle brickTest = new Rectangle(50, 10, 40, 20);
    ClayBrickModel clayBrickModel = new ClayBrickModel(brickTest);

    @Test
    void makeBrickFace_brickFaceSameAsBrickPassedInFromParameter() {
        clayBrickModel.makeBrickFace(brickTest);
        assertEquals(brickTest.getX(), clayBrickModel.getBrickFace().getX());
        assertEquals(brickTest.getY(), clayBrickModel.getBrickFace().getY());
        assertEquals(brickTest.getWidth(), clayBrickModel.getBrickFace().getWidth());
        assertEquals(brickTest.getHeight(), clayBrickModel.getBrickFace().getHeight());
    }
}