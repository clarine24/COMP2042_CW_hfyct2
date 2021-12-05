package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class DebugConsoleController {
    private GameBoardModel model;

    @FXML private Button nextLevel;
    @FXML private Button resetBallCount;
    @FXML private Slider ballSpeed;

    @FXML
    private void initialize() {
        model = GameBoardModel.getInstance();

        //Link with wall model
        model.getWallModel().hasNextLevel().addListener((observableValue, oldValue, newValue) -> {
            if(! newValue) {
                nextLevel.setVisible(false);
            }
        });
    }

    @FXML
    private void nextLevelButtonClicked() {
        model.getGameOverModel().setNextLevel(true);
    }

    @FXML
    private void resetBallCountButtonClicked() {}
}
