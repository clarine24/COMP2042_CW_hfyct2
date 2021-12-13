package com.game.BrickDestroy.Controller;

import com.game.BrickDestroy.Model.BrickCrackModel;
import com.game.BrickDestroy.Model.BrickModel;
import com.game.BrickDestroy.Model.GameBoardModel;
import com.game.BrickDestroy.Stages;
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

/**
 * The GameBoardController class is the controller of game board view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GameBoardController {
    private GameBoardModel model;

    @FXML private Rectangle wall;
    @FXML private Rectangle player;
    @FXML private Circle ball;
    @FXML private Pane playButton;
    @FXML private Pane pauseMenu;
    @FXML private Pane gameOverMenu;
    @FXML private Pane bricksPane;
    @FXML private Pane cracksPane;
    @FXML private Label levelNumber;
    @FXML private Label brickCountNumber;
    @FXML private Label ballCountNumber;
    @FXML private Label addBall;

    private Rectangle[] bricks;

    /**
     * Initialise the game board controller.
     * Load the pause menu and game over view.
     * Link the model and view.
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        loadFXML("PauseMenuView", pauseMenu);
        loadFXML("GameOverView", gameOverMenu);

        //initialize arrays
        initializeBricks();
        initializeCracks();

        //Get model
        model = new GameBoardModel(wall, player, ball, bricks);

        //Link Model with View
        linkModelView();

        playButton.setVisible(true);
        pauseMenu.setVisible(false);
        gameOverMenu.setVisible(false);
        addBall.setVisible(false);
    }

    /**
     * Load a fxml file.
     * @param fxml the fxml name without the file extension
     * @param pane the pane the fxml is added into
     * @throws IOException
     */
    private void loadFXML(String fxml, Pane pane) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("/com/game/BrickDestroy/View/" + fxml + ".fxml"));
        pane.getChildren().add(view);
    }

    /**
     * Get array of bricks from the bricks pane.
     */
    private void initializeBricks() {
        int brickCount = bricksPane.getChildren().size();
        bricks = new Rectangle[brickCount];

        int i = 0;
        for(Node node: bricksPane.getChildren()) {
            bricks[i] = (Rectangle) node;
            i++;
        }
    }

    /**
     * Set all cracks to be invisible.
     */
    private void initializeCracks() {
        for(Node node: cracksPane.getChildren()) {
            node.setVisible(false);
        }
    }

    /**
     * Link the models and the game board view.
     */
    private void linkModelView() {
        linkPlayerModel();
        linkBallModel();
        linkGameTimer();
        linkPauseMenuModel();
        linkGameOverMenuModel();
        linkBrickModel();
        linkWallModel();
    }

    /**
     * Link the player model and the game board view.
     */
    private void linkPlayerModel() {
        player.xProperty().bind(model.getWallModel().getPlayer().getPlayerFace().xProperty());
    }

    /**
     * Link the ball model and the game board view.
     */
    private void linkBallModel() {
        ball.idProperty().bind(model.getWallModel().getBall().getName());

        ball.centerXProperty().bind(model.getWallModel().getBall().getBallFace().centerXProperty());
        ball.centerYProperty().bind(model.getWallModel().getBall().getBallFace().centerYProperty());
    }

    /**
     * Link the game timer and the game board view.
     */
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
                model.getGameOverModel().getScoreBoardModel().getScore().calculateTotalScore();
                model.getGameTimer().setGameOver(false);
            }
        });

        model.getGameTimer().getDisplayAddBallText().addListener((observableValue, oldValue, newValue) -> addBall.setVisible(newValue));
    }

    /**
     * Link the pause menu model and the game board view.
     */
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
                model.getGameOverModel().getScoreBoardModel().getScore().resetScore();
            }
        });
    }

    /**
     * Link the game over model and the game board view.
     */
    private void linkGameOverMenuModel() {
        model.getGameOverModel().isRestart().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                gameOverMenu.setVisible(false);
                model.getGameOverModel().setRestart(false);
                model.getWallModel().wallReset();
                model.getGameOverModel().getScoreBoardModel().getScore().resetScore();
                Stages.getInstance().setFocus();
            }
        });

        model.getGameOverModel().isNextLevel().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                gameOverMenu.setVisible(false);
                model.getWallModel().wallReset();
                model.getGameOverModel().getScoreBoardModel().getScore().resetScore();
                model.getWallModel().nextLevel();
                model.getGameOverModel().setNextLevel(false);
                linkBrickModel();
                Stages.getInstance().setFocus();
            }
        });

        gameOverMenu.lookup("#nextLevelButton").visibleProperty().bind(model.getWallModel().hasNextLevel());
    }

    /**
     * Link the brick model and the game board view.
     */
    private void linkBrickModel() {
        int i = 0;
        for(Node node: bricksPane.getChildren()) {
            BrickModel brick = model.getWallModel().getBricks()[i];

            node.idProperty().bind(brick.getName());

            brick.isBroken().addListener((observableValue, oldValue, newValue) -> {
                node.setVisible(!newValue);
                switch (node.getId()) {
                    case "clayBrick" -> model.getGameOverModel().getScoreBoardModel().getScore().clayBrickScore();
                    case "cementBrick" -> model.getGameOverModel().getScoreBoardModel().getScore().cementBrickScore();
                    case "steelBrick" -> model.getGameOverModel().getScoreBoardModel().getScore().steelBrickScore();
                    case "blueBrick" -> model.getGameOverModel().getScoreBoardModel().getScore().blueBrickScore();
                }
            });

            if(brick.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("BrickCrackModel")) {
                Node crackNode = cracksPane.getChildren().get(i);
                ((BrickCrackModel)brick).isCrack().addListener((observableValue, oldValue, newValue) -> crackNode.setVisible(newValue));
            }

            i++;
        }
    }

    /**
     * Link the wall model and the game board view.
     */
    private void linkWallModel() {
        levelNumber.textProperty().bind(model.getWallModel().getLevel().asString());

        brickCountNumber.textProperty().bind(model.getWallModel().getBrickCount().asString());

        ballCountNumber.textProperty().bind(model.getWallModel().getBallCount().asString());

        model.getWallModel().isAddAdditionalBall().addListener((observableValue, oldValue, newValue) -> {
            if(newValue) {
                model.getGameTimer().setDisplayAddBallText(true);
            }
        });
    }

    /**
     * Trigger key pressed events.
     * @param event the key pressed
     * @throws IOException
     */
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

    /**
     * Move player paddle.
     * @param key the key pressed
     */
    private void playerMove(KeyCode key) {
        if (key == KeyCode.A) {
            model.getWallModel().getPlayer().moveLeft();
        }
        else if (key == KeyCode.D) {
            model.getWallModel().getPlayer().moveRight();
        }
    }

    /**
     * Stop player paddle.
     */
    @FXML
    private void playerStop() {
        model.getWallModel().getPlayer().stop();
    }

    /**
     * Start the game.
     */
    @FXML
    private void play() {
        playButton.setVisible(false);
        model.getGameTimer().start();
    }

    /**
     * Pause/stop the game.
     */
    private void stop() {
        model.getGameTimer().stop();
        playButton.setVisible(true);
    }
}
