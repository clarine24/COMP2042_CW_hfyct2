package com.game.BrickDestroy.Controller;

import com.game.BrickDestroy.Stages;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The InfoMenuController class is the controller class of the info menu view.
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
