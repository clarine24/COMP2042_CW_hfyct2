package com.game.BrickDestroy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Stages {
    private static final String DEF_TITLE = "Brick Destroy";
    private static final String DEBUG_TITLE = "Debug Console";

    private Stage homeStage;
    private Stage gameStage;
    private Stage debugStage;

    private static Scene scene;

    private static Stages instance;

    private Parent root;

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

    public static Stages getInstance() {
        return instance;
    }

    public void initialize(Stage stage) {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setFocus();
    }

    public void homeStage() throws IOException {
        gameStage.hide();
        root = loadFXML("HomeMenuView");
        scene = new Scene(root);
        homeStage.setTitle(DEF_TITLE);
        initialize(homeStage);
        setLocation(homeStage);
    }

    public void gameStage() throws IOException {
        homeStage.hide();
        root = loadFXML("GameBoardView");
        scene = new Scene(root);
        gameStage.setTitle(DEF_TITLE);
        initialize(gameStage);
        setLocation(gameStage);
        gameStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (! newValue) {
                    GameBoardModel.getInstance().onLostFocus();
                }
            }
        });
    }

    public void debugConsole() throws IOException {
        root = loadFXML("DebugConsoleView");
        scene = new Scene(root);
        debugStage.setTitle(DEBUG_TITLE);
        initialize(debugStage);
        setDebugLocation();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Stages.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void setLocation(Stage stage) {
        Rectangle2D primaryScreen = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreen.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreen.getHeight() - stage.getHeight()) / 2);
    }

    private void setDebugLocation() {
        double x = (gameStage.getWidth() - debugStage.getWidth()) / 2 + gameStage.getX();
        double y = (gameStage.getHeight() - debugStage.getHeight()) / 2 + gameStage.getY();

        debugStage.setX(x);
        debugStage.setY(y);
    }

    public void setFocus() {
        root.requestFocus();
    }
}
