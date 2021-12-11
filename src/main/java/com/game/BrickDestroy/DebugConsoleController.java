package com.game.BrickDestroy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

/**
 * The DebugConsoleController class is the controller for the debug console view.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class DebugConsoleController {
    private GameBoardModel model;

    @FXML private Button nextLevel;
    @FXML private Slider ballSpeed;

    /**
     * Initialise the debug console controller.
     * Links the models and view.
     */
    @FXML
    private void initialize() {
        model = GameBoardModel.getInstance();

        linkWallModel();
        linkBallModel();
    }

    /**
     * Links the wall model and the debug console view.
     */
    private void linkWallModel() {
        nextLevel.visibleProperty().bind(model.getWallModel().hasNextLevel());
    }

    /**
     * Links the ball model and the debug console view.
     */
    private void linkBallModel() {
        ballSpeed.valueProperty().bindBidirectional(model.getWallModel().getBall().getSpeed());
    }

    /**
     * Sets the nextLevel boolean to true.
     */
    @FXML
    private void nextLevelButtonClicked() {
        model.getGameOverModel().setNextLevel(true);
    }

    /**
     * Reset the ball count.
     */
    @FXML
    private void resetBallCountButtonClicked() {
        model.getWallModel().resetBallCount();
    }
}
