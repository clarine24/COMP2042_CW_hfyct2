package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBoardController {
    private GameBoardModel model;

    @FXML private Rectangle wall;
    @FXML private Rectangle player;
    @FXML private Circle rubberBall;
    @FXML private Pane playButton;

    @FXML
    public void initialize() {
        //Get model
        model = new GameBoardModel(wall, player, rubberBall);

        //Link Model with View
        player.xProperty().bind(model.getWallModel().getPlayer().getPlayerFace().xProperty());
        rubberBall.centerXProperty().bind(model.getWallModel().getBall().getBallFace().centerXProperty());
        rubberBall.centerYProperty().bind(model.getWallModel().getBall().getBallFace().centerYProperty());

        playButton.setVisible(true);
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == KeyCode.SPACE) {
            if(playButton.isVisible()) {
                play();
            }
            else {
                stop();
            }
        }
        else if (key == KeyCode.A || key == KeyCode.D) {
            playerMove(key);
        }
    }

    private void playerMove(KeyCode key) {
        if (key == KeyCode.A) {
            model.getWallModel().getPlayer().moveLeft();
        }
        else if (key == KeyCode.D) {
            model.getWallModel().getPlayer().moveRight();
        }
    }

    @FXML
    private void playerStop() {
        model.getWallModel().getPlayer().stop();
    }

    @FXML
    private void play() {
        playButton.setVisible(false);
        model.getGameTimer().start();
    }

    private void stop() {
        playButton.setVisible(true);
        model.getGameTimer().stop();
    }
}
