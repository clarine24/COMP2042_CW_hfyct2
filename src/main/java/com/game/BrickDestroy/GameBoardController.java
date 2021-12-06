package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    @FXML private Pane bricksPane;
    @FXML private Pane cracksPane;
    @FXML private Label levelNumber;
    @FXML private Label brickCountNumber;
    @FXML private Label ballCountNumber;

    private Rectangle[] bricks;

    @FXML
    public void initialize() throws IOException {
        loadFXML("PauseMenuView", pauseMenu);
        loadFXML("GameOverView", gameOverMenu);

        //initialize arrays
        initializeBricks();
        initializeCracks();

        //Get model
        model = new GameBoardModel(wall, player, rubberBall, bricks);

        //Link Model with View
        linkModelView();

        playButton.setVisible(true);
        pauseMenu.setVisible(false);
        gameOverMenu.setVisible(false);
    }

    private void loadFXML(String fxml, Pane pane) throws IOException {
        AnchorPane view = FXMLLoader.load(GameBoardController.class.getResource(fxml + ".fxml"));
        pane.getChildren().add(view);
    }

    private void initializeBricks() {
        int brickCount = bricksPane.getChildren().size();
        bricks = new Rectangle[brickCount];

        int i = 0;
        for(Node node: bricksPane.getChildren()) {
            bricks[i] = (Rectangle) node;
            i++;
        }
    }

    private void initializeCracks() {
        for(Node node: cracksPane.getChildren()) {
            node.setVisible(false);
        }
    }

    private void linkModelView() {
        linkPlayerModel();
        linkRubberBallModel();
        linkGameTimer();
        linkPauseMenuModel();
        linkGameOverMenuModel();
        linkBrickModel();
        linkWallModel();
    }

    private void linkPlayerModel() {
        player.xProperty().bind(model.getWallModel().getPlayer().getPlayerFace().xProperty());
    }

    private void linkRubberBallModel() {
        rubberBall.centerXProperty().bind(model.getWallModel().getBall().getBallFace().centerXProperty());
        rubberBall.centerYProperty().bind(model.getWallModel().getBall().getBallFace().centerYProperty());
    }

    private void linkGameTimer() {
        model.getGameTimer().isRunning().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue) {
                stop();
            }
        });

        model.getGameTimer().isGameOver().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                stop();
                gameOverMenu.setVisible(true);
                model.getScore().calculateTotalScore();
                model.getGameTimer().setGameOver(false);
            }
        });
    }

    private void linkPauseMenuModel() {
        model.getPauseMenuModel().isResume().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                pauseMenu.setVisible(false);
                model.getPauseMenuModel().setResume(false);
                Stages.getInstance().setFocus();
            }
        });

        model.getPauseMenuModel().isRestart().addListener((observableValue, oldValue, newValue) -> {
            if(newValue){
                pauseMenu.setVisible(false);
                model.getPauseMenuModel().setRestart(false);
                Stages.getInstance().setFocus();
                model.getWallModel().wallReset();
                model.getScore().resetScore();
            }
        });
    }

    private void linkGameOverMenuModel() {
        model.getGameOverModel().isRestart().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                gameOverMenu.setVisible(false);
                model.getGameOverModel().setRestart(false);
                model.getWallModel().wallReset();
                model.getScore().resetScore();
                Stages.getInstance().setFocus();
            }
        });

        model.getGameOverModel().isNextLevel().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                gameOverMenu.setVisible(false);
                model.getGameOverModel().setNextLevel(false);
                model.getWallModel().wallReset();
                model.getScore().resetScore();
                model.getWallModel().nextLevel();
                linkBrickModel();
                Stages.getInstance().setFocus();
            }
        });
    }

    private void linkBrickModel() {
        int i = 0;
        for(Node node: bricksPane.getChildren()) {
            BrickModel brick = model.getWallModel().getBricks()[i];

            node.idProperty().bind(brick.getName());

            brick.isBroken().addListener((observableValue, oldValue, newValue) -> {
                node.setVisible(!newValue);
                switch(node.getId()) {
                    case "clayBrick":
                        model.getScore().clayBrickScore();
                        break;
                    case "cementBrick":
                        model.getScore().cementBrickScore();
                        break;
                    case "steelBrick":
                        model.getScore().steelBrickScore();
                        break;
                }
            });

            if(brick.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("BrickCrackModel")) {
                Node crackNode = cracksPane.getChildren().get(i);
                ((BrickCrackModel)brick).isCrack().addListener((observableValue, oldValue, newValue) -> crackNode.setVisible(newValue));
            }

            i++;
        }
    }

    private void linkWallModel() {
        levelNumber.textProperty().bind(model.getWallModel().getLevel().asString());

        model.getWallModel().hasNextLevel().addListener((observableValue, oldValue, newValue) -> {
            if(! newValue) {
                gameOverMenu.lookup("#nextLevelButton").setVisible(false);
            }
        });

        brickCountNumber.textProperty().bind(model.getWallModel().getBrickCount().asString());

        ballCountNumber.textProperty().bind(model.getWallModel().getBallCount().asString());
    }

    @FXML
    private void keyPressed(KeyEvent event) throws IOException {
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
        else if (key == KeyCode.F1) {
            if(event.isAltDown() && event.isShiftDown()) {
                Stages stages = Stages.getInstance();
                stages.debugConsole();
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
