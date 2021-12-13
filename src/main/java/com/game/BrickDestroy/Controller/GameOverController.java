package com.game.BrickDestroy.Controller;

import com.game.BrickDestroy.Model.GameOverModel;
import com.game.BrickDestroy.Stages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * The GameOverController class is the controller class of the game over view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GameOverController {
    @FXML private Label score;
    @FXML private Pane scoreBoard;

    private GameOverModel model;

    /**
     * Initialise the game over controller.
     * Load the score board view.
     * Links the model and view.
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("/com/game/BrickDestroy/View/ScoreBoardView.fxml"));
        scoreBoard.getChildren().add(view);

        //Get model
        model = new GameOverModel();

        linkScoreBoard();

        model.isNextLevel().addListener((observableValue, oldValue, newValue) -> linkScoreBoard());

        scoreBoard.setVisible(false);
    }

    /**
     * Links the score board model and the game over view.
     */
    private void linkScoreBoard() {
        score.textProperty().bind(model.getScoreBoardModel().getScore().getTotalScore().asString());

        model.getScoreBoardModel().isOpen().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue) {
                scoreBoard.setVisible(false);
            }
        });
    }

    /**
     * Sets restart boolean to true.
     */
    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
    }

    /**
     * Sets nextLevel boolean to true.
     */
    @FXML
    private void nextLevelButtonClicked() {
        model.setNextLevel(true);
    }

    /**
     * Shows the home menu.
     * @throws IOException
     */
    @FXML
    private void exitButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.homeStage();
    }

    /**
     * Shows the score board.
     */
    @FXML
    private void scoreBoardButtonClicked() {
        scoreBoard.setVisible(true);
        model.getScoreBoardModel().setOpen(true);
    }
}
