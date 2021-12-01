package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

public class HomeMenuController {
    @FXML
    private void startButtonClicked() {
        System.out.println("button start test pass");
    }

    @FXML
    private void infoButtonClicked() throws IOException {
        Stages.setRoot("InfoMenuView");
    }

    @FXML
    private void exitButtonClicked() {
        System.out.println("Goodbye " + System.getProperty("user.name"));
        System.exit(0);
    }
}
