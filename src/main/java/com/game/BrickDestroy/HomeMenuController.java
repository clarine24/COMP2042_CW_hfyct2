package com.game.BrickDestroy;

import javafx.fxml.FXML;

public class HomeMenuController {
    @FXML
    private void initialize() {}

    @FXML
    private void startButtonClicked() {
        System.out.println("button start test pass");
    }

    @FXML
    private void infoButtonClicked() {
        System.out.println("button info test pass");
    }

    @FXML
    private void exitButtonClicked() {
        System.out.println("Goodbye " + System.getProperty("user.name"));
        System.exit(0);
    }
}
