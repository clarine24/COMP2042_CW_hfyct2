package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class DebugConsoleController {
    private DebugConsoleModel model;

    @FXML private Button nextLevel;
    @FXML private Button resetBallCount;
    @FXML private Slider ballSpeed;

    @FXML
    private void initialize() {
        model = new DebugConsoleModel();
    }

    @FXML
    private void nextLevelButtonClicked() {
    }
}
