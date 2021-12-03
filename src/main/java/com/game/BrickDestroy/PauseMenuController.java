package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

public class PauseMenuController {
    private PauseMenuModel model;

    @FXML
    public void initialize() {
        //Get model
        model = PauseMenuModel.getInstance();
    }

    @FXML
    private void resumeButtonClicked() {
        model.setResume(true);
    }

    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
    }

    @FXML
    private void exitButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.homeStage();
    }
}
