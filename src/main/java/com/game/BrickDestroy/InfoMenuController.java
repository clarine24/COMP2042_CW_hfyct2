package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The InfoMenuController is the controller for info menu view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class InfoMenuController {
    /**
     * Switch scene to home menu.
     * @throws IOException
     */
    @FXML
    private void closeButtonClicked() throws IOException {
        Stages.setRoot("HomeMenuView");
    }
}
