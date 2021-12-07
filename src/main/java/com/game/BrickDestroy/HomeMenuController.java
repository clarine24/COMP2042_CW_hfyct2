package com.game.BrickDestroy;

import javafx.fxml.FXML;

import java.io.IOException;

public class HomeMenuController {
    @FXML
    private void startButtonClicked() throws IOException {
        Stages stages = Stages.getInstance();
        stages.gameStage();
    }

    @FXML
    private void infoButtonClicked() throws IOException {
        Stages.setRoot("InfoMenuView");
    }

    @FXML
    private void exitButtonClicked() {
        GameBoardModel.getInstance().getGameOverModel().getScore().closeFile();
        System.out.println("Goodbye " + System.getProperty("user.name"));
        System.exit(0);
    }
}
