package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickModelTest {
    Rectangle brickTest = new Rectangle(10, 20, 60, 30);
    CementBrickModel cementBrickModel = new CementBrickModel(brickTest);

    @Test
    void makeBrickFace_brickFaceSameAsBrickPassedInFromParameter() {
        cementBrickModel.makeBrickFace(brickTest);
        assertEquals(brickTest.getX(), cementBrickModel.getBrickFace().getX());
        assertEquals(brickTest.getY(), cementBrickModel.getBrickFace().getY());
        assertEquals(brickTest.getWidth(), cementBrickModel.getBrickFace().getWidth());
        assertEquals(brickTest.getHeight(), cementBrickModel.getBrickFace().getHeight());
    }
}