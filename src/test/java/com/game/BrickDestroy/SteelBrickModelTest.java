package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickModelTest {
    Rectangle brickTest = new Rectangle(50, 10, 50, 25);
    SteelBrickModel steelBrickModel = new SteelBrickModel(brickTest);

    @Test
    void makeBrickFace_brickFaceSameAsBrickPassedInFromParameter() {
        steelBrickModel.makeBrickFace(brickTest);
        assertEquals(brickTest.getX(), steelBrickModel.getBrickFace().getX());
        assertEquals(brickTest.getY(), steelBrickModel.getBrickFace().getY());
        assertEquals(brickTest.getWidth(), steelBrickModel.getBrickFace().getWidth());
        assertEquals(brickTest.getHeight(), steelBrickModel.getBrickFace().getHeight());
    }

    @Test
    void impact() {
    }
}