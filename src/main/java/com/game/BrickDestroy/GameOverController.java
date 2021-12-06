package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameOverController {
    @FXML private Label score;

    private GameOverModel model;

    @FXML
    public void initialize() {
        //Get model
        model = new GameOverModel();

        score.textProperty().bind(model.getInstance().getScore().getTotalScore().asString());
    }

    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
    }

    @FXML
    private void nextLevelButtonClicked() { model.setNextLevel(true); }

    @FXML
    private void exitButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.homeStage();
    }

    @FXML
    private void scoreBoardButtonClicked() {}
}
