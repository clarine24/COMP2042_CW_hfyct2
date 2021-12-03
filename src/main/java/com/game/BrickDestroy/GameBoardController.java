package com.game.BrickDestroy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class GameBoardController {
    private GameBoardModel model;

    @FXML private Rectangle wall;
    @FXML private Rectangle player;
    @FXML private Circle rubberBall;
    @FXML private Pane playButton;
    @FXML private Pane pauseMenu;
    @FXML private Pane gameOverMenu;

    @FXML
    public void initialize() throws IOException {
        loadFXML("PauseMenuView", pauseMenu);
        loadFXML("GameOverView", gameOverMenu);

        //Get model
        model = new GameBoardModel(wall, player, rubberBall);

        //Link Model with View
        player.xProperty().bind(model.getWallModel().getPlayer().getPlayerFace().xProperty());

        rubberBall.centerXProperty().bind(model.getWallModel().getBall().getBallFace().centerXProperty());
        rubberBall.centerYProperty().bind(model.getWallModel().getBall().getBallFace().centerYProperty());

        model.getGameTimer().isRunning().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    stop();
                }
            }
        });

        model.getGameTimer().isGameOver().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    stop();
                    gameOverMenu.setVisible(true);
                    model.getGameTimer().setGameOver(false);
                }
            }
        });
        
        model.getPauseMenuModel().isResume().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    pauseMenu.setVisible(false);
                    model.getPauseMenuModel().setResume(false);
                    Stages.getInstance().setFocus();
                }
            }
        });

        model.getPauseMenuModel().isRestart().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    pauseMenu.setVisible(false);
                    model.getPauseMenuModel().setRestart(false);
                    Stages.getInstance().setFocus();
                }
            }
        });

        model.getGameOverModel().isRestart().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    gameOverMenu.setVisible(false);
                    model.getGameOverModel().setRestart(false);
                    Stages.getInstance().setFocus();
                }
            }
        });

        playButton.setVisible(true);
        pauseMenu.setVisible(false);
        gameOverMenu.setVisible(false);
    }

    private void loadFXML(String fxml, Pane pane) throws IOException {
        AnchorPane view = FXMLLoader.load(GameBoardController.class.getResource(fxml + ".fxml"));
        pane.getChildren().add(view);
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == KeyCode.SPACE) {
            if(!playButton.isVisible()) {
                stop();
            }
            else if(!pauseMenu.isVisible() && !gameOverMenu.isVisible()) {
                play();
            }
        }
        else if (key == KeyCode.A || key == KeyCode.D) {
            playerMove(key);
        }
        else if(key == KeyCode.ESCAPE) {
            if(pauseMenu.isVisible()) {
                pauseMenu.setVisible(false);
            }
            else if (!gameOverMenu.isVisible()){
                stop();
                pauseMenu.setVisible(true);
            }
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
        model.getGameTimer().stop();
        playButton.setVisible(true);
    }
}
