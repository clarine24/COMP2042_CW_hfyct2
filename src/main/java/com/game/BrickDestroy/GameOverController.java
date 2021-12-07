package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameOverController {
    @FXML private Label score;
    @FXML private Pane gameOver;
    @FXML private Pane scoreBoard;

    private GameOverModel model;

    @FXML
    public void initialize() {
        //Get model
        model = new GameOverModel();

        linkScoreBoard();

        model.isNextLevel().addListener((observableValue, aBoolean, t1) -> linkScoreBoard());

        gameOver.setVisible(true);
        scoreBoard.setVisible(false);
    }

    private void linkScoreBoard() {
        score.textProperty().bind(model.getScore().getTotalScore().asString());
    }

    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
    }

    @FXML
    private void nextLevelButtonClicked() {
        model.setNextLevel(true);
    }

    @FXML
    private void exitButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.homeStage();
    }

    @FXML
    private void scoreBoardButtonClicked() {
        gameOver.setVisible(false);
        scoreBoard.setVisible(true);
    }

    @FXML
    private void closeScoreBoardButtonClicked() {
        gameOver.setVisible(true);
        scoreBoard.setVisible(false);
    }
}
