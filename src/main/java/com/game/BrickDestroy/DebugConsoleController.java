package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class DebugConsoleController {
    private GameBoardModel model;

    @FXML private Button nextLevel;
    @FXML private Slider ballSpeed;

    @FXML
    private void initialize() {
        model = GameBoardModel.getInstance();

        linkWallModel();
        linkBallModel();
    }

    private void linkWallModel() {
        nextLevel.visibleProperty().bind(model.getWallModel().hasNextLevel());
    }

    private void linkBallModel() {
        ballSpeed.valueProperty().bindBidirectional(model.getWallModel().getBall().getSpeed());
    }

    @FXML
    private void nextLevelButtonClicked() {
        model.getGameOverModel().setNextLevel(true);
    }

    @FXML
    private void resetBallCountButtonClicked() {
        model.getWallModel().resetBallCount();
    }
}
