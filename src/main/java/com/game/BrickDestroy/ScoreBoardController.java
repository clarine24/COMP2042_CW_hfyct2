package com.game.BrickDestroy;

import javafx.fxml.FXML;

public class ScoreBoardController {
    private ScoreBoardModel model;

    @FXML
    private void initialize() {
        model = new ScoreBoardModel();
    }

    @FXML
    private void closeButtonClicked() {
        model.setOpen(false);
    }
}
