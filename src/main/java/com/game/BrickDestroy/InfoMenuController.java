package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


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
