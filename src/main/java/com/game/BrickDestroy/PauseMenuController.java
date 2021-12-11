package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The PauseMenuController is the controller of pause menu view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class PauseMenuController {
    private PauseMenuModel model;

    /**
     * Initialise the pause menu controller.
     */
    @FXML
    public void initialize() {
        //Get model
        model = new PauseMenuModel();
    }

    /**
     * Set the boolean value of resume to true.
     */
    @FXML
    private void resumeButtonClicked() {
        model.setResume(true);
    }

    /**
     * Set the boolean value of restart to true.
     */
    @FXML
    private void restartButtonClicked() {
        model.setRestart(true);
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
}
