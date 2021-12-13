package com.game.BrickDestroy.Controller;

import com.game.BrickDestroy.Model.PauseMenuModel;
import com.game.BrickDestroy.Stages;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The PauseMenuController class is the controller class of the pause menu view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class PauseMenuController {
    private PauseMenuModel model;

    /**
     * Initialise the pause menu controller.
     * Creates a new instance of the pause menu model.
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
