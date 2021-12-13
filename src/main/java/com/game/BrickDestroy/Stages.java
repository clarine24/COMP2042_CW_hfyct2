package com.game.BrickDestroy;

import com.game.BrickDestroy.Model.GameBoardModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The Stages class contains all the stages of the application.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class Stages {
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String DEBUG_TITLE = "Debug Console";

    private Stage homeStage;
    private Stage gameStage;

    private Stage debugStage;

    private static Scene scene;

    private static Stages instance;

    private Parent root;
    private Parent gameRoot;

    /**
     * Creates an instance of Stages with the primary stage being the homeStage.
     * Creates empty stage for all stages in the application.
     * Set the style and modality for all stages.
     * @param stage the primary stage
     */
    public Stages(Stage stage) {
        homeStage = stage;
        homeStage.initStyle(StageStyle.UNDECORATED); //remove window decoration

        gameStage = new Stage();
        gameStage.initStyle(StageStyle.DECORATED);

        debugStage = new Stage();
        debugStage.initStyle(StageStyle.DECORATED);
        debugStage.initModality(Modality.APPLICATION_MODAL);

        instance = this;
    }

    /**
     * Get the created instance of Stages
     * @return the Stages instance
     */
    public static Stages getInstance() {
        return instance;
    }

    /**
     * Initialise the stage.
     * Set the scene on the stage and show the stage.
     * Set the focus to be on the stage.
     * @param stage the stage name
     */
    private void initialize(Stage stage) {
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setFocus();
    }

    /**
     * Display the home menu.
     * Hides the game board.
     * @throws IOException
     */
    public void homeStage() throws IOException {
        gameStage.hide();
        root = loadFXML("HomeMenuView");
        homeStage.setTitle(GAME_TITLE);
        initialize(homeStage);
        setLocation(homeStage);
        homeStage.setOnCloseRequest(e -> onCloseGame());
    }

    /**
     * Display the game board.
     * Hides the home menu.
     * Informs the game board model when the stage loses focus.
     * @throws IOException
     */
    public void gameStage() throws IOException {
        homeStage.hide();
        gameRoot = loadFXML("GameBoardView");
        root = gameRoot;
        gameStage.setTitle(GAME_TITLE);
        initialize(gameStage);
        setLocation(gameStage);
        gameStage.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (! newValue) {
                GameBoardModel.getInstance().onLostFocus();
            }
        });
        gameStage.setOnCloseRequest(e -> onCloseGame());
    }

    /**
     * Display the debug panel.
     * @throws IOException
     */
    public void debugConsole() throws IOException {
        root = loadFXML("DebugConsoleView");
        debugStage.setTitle(DEBUG_TITLE);
        initialize(debugStage);
        setDebugLocation();
        debugStage.setOnCloseRequest(event -> root = gameRoot);
    }

    /**
     * Switch scene.
     * @param fxml the fxml file name without the file extension
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load a fxml file.
     * @param fxml the fxml file name without the file extension
     * @return the loaded file
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Stages.class.getResource("View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Set the location of stage (homeStage or gameStage).
     * @param stage the stage name
     */
    private void setLocation(Stage stage) {
        Rectangle2D primaryScreen = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreen.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreen.getHeight() - stage.getHeight()) / 2);
    }

    /**
     * Set the location of debug console.
     * Debug panel will be placed at the center of the game stage window.
     */
    private void setDebugLocation() {
        double x = (gameStage.getWidth() - debugStage.getWidth()) / 2 + gameStage.getX();
        double y = (gameStage.getHeight() - debugStage.getHeight()) / 2 + gameStage.getY();

        debugStage.setX(x);
        debugStage.setY(y);
    }

    /**
     * Set focus on stage.
     */
    public void setFocus() {
        root.requestFocus();
    }

    /**
     * Closes open external txt files when game window is closed.
     */
    public void onCloseGame() {
        GameBoardModel.getInstance().getGameOverModel().getScoreBoardModel().closeAllFiles();
    }
}
