package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PauseMenuController {
    private PauseMenuModel model;

    @FXML
    public void initialize() {
        //Get model
        model = new PauseMenuModel();
    }

    @FXML
    private void resumeButtonClicked() {
        model.setResume(true);
        System.out.println("resume button clicked");
    }

    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
        System.out.println("restart button clicked");
    }

    @FXML
    private void exitButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.homeStage();
    }
}
