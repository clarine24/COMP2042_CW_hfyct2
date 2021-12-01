package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class InfoMenuController {
    @FXML private Label title;
    @FXML private Label content1;
    @FXML private Label content2;
    @FXML private Label content3;
    @FXML private Label content4;
    @FXML private Label content5;
    @FXML private Label content6;
    @FXML private Label content7;

    @FXML
    private void initialize() {
        title.setText("How to Play");
        content1.setText("GOAL: Destroy all Bricks!");
        content2.setText("Press Space to Start/Pause Game");
        content3.setText("Press A Key to Move Left");
        content4.setText("Press D Key to Move Right");
        content5.setText("Press ESC Key to Enter/Exit Pause Menu");
        content6.setText("Press ALT+SHIFT+F1 Key to Open Console");
        content7.setText("Game automatically pause when window\nloses focus");
    }

    @FXML
    private void closeButtonClicked() throws IOException {
        Stages.setRoot("HomeMenuView");
    }
}
