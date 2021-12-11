package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The HomeMenuController class is the controller for home menu view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class HomeMenuController {
    /**
     * Show the game stage.
     * @throws IOException
     */
    @FXML
    private void startButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.gameStage();
    }

    /**
     * Switch scene to info menu view.
     * @throws IOException
     */
    @FXML
    private void infoButtonClicked() throws IOException {
        Stages.setRoot("InfoMenuView");
    }

    /**
     * Exit the program.
     */
    @FXML
    private void exitButtonClicked() {
        System.out.println("Goodbye " + System.getProperty("user.name"));
        System.exit(0);
    }
}
