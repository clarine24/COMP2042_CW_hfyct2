package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class GameBoardController {
    GameBoardModel model;

    @FXML private Rectangle player;
    @FXML private Rectangle wall;

    @FXML
    public void initialize() {
        //Get model
        model = new GameBoardModel(wall, player);

        //Link Model with View
        player.xProperty().bind(model.getWallModel().getPlayer().getPlayerFace().xProperty());
    }

    @FXML
    private void playerMove(KeyEvent event) {
        switch(event.getCode()) {
            case A -> {
                model.getWallModel().getPlayer().moveLeft();
                break;
            }
            case D -> {
                model.getWallModel().getPlayer().moveRight();
                break;
            }
        }
    }
}
