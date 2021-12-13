package com.game.BrickDestroy.Controller;

import com.game.BrickDestroy.Model.ScoreBoardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * The ScoreBoardController class is the controller class for score board view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class ScoreBoardController {
    @FXML private Label top1Score;
    @FXML private Label top2Score;
    @FXML private Label top3Score;
    @FXML private Label top4Score;
    @FXML private Label top5Score;

    private ScoreBoardModel model;
    private Label[] topScores;

    /**
     * Initialise the score board controller.
     * Links the model and the view.
     */
    @FXML
    private void initialize() {
        model = new ScoreBoardModel();

        initializeTopScores();
        linkScoreBoardModel();

        model.isOpen().addListener((observableValue, oldValue, newValue) -> linkScoreBoardModel());
    }

    /**
     * Creates an array of top scores labels.
     */
    private void initializeTopScores() {
        topScores = new Label[5];
        topScores[0] = top1Score;
        topScores[1] = top2Score;
        topScores[2] = top3Score;
        topScores[3] = top4Score;
        topScores[4] = top5Score;
    }

    /**
     * Links the score board model and the score board view.
     */
    private void linkScoreBoardModel() {
        for (Label topScore : topScores) {
            topScore.textProperty().unbind();
            topScore.textProperty().set("-");
        }

        int i=0;
        for(; i<model.getScore().getHighScores().length; i++) {
            topScores[i].textProperty().bind(model.getScore().getHighScores()[i].asString());
        }
    }

    /**
     * Sets the boolean Open to false.
     * Closes the score board.
     */
    @FXML
    private void closeButtonClicked() {
        model.setOpen(false);
    }
}
