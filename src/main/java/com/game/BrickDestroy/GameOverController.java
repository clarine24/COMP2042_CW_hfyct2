package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameOverController {
    @FXML private Label score;
    @FXML private Pane scoreBoard;

    private GameOverModel model;

    @FXML
    public void initialize() throws IOException {
        AnchorPane view = FXMLLoader.load(GameBoardController.class.getResource("ScoreBoardView.fxml"));
        scoreBoard.getChildren().add(view);

        //Get model
        model = new GameOverModel();

        linkScoreBoard();

        model.isNextLevel().addListener((observableValue, oldValue, newValue) -> linkScoreBoard());

        scoreBoard.setVisible(false);
    }

    private void linkScoreBoard() {
        score.textProperty().bind(model.getScoreBoardModel().getScore().getTotalScore().asString());

        model.getScoreBoardModel().isOpen().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue) {
                scoreBoard.setVisible(false);
            }
        });
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
        scoreBoard.setVisible(true);
        model.getScoreBoardModel().setOpen(true);
    }
}
