package com.game.BrickDestroy;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Stages {
    private static final String DEF_TITLE = "Brick Destroy";

    private Stage homeStage;
    private Stage gameStage;

    private static Scene scene;

    private static Stages instance;

    public Stages(Stage stage) {
        homeStage = stage;
        gameStage = new Stage();

        instance = this;
    }

    public static Stages getInstance() {
        return instance;
    }

    public void initialize(Stage stage) {
        stage.setTitle(DEF_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        autoLocate(stage);
    }

    public void homeStage() throws IOException {
        scene = new Scene(loadFXML("HomeMenuView"));
        homeStage.initStyle(StageStyle.UNDECORATED); //remove window decoration
        initialize(homeStage);
    }

    public void gameStage() throws IOException {
        homeStage.hide();
        Parent root = loadFXML("GameBoardView");
        scene = new Scene(root);
        gameStage.initStyle(StageStyle.DECORATED);
        initialize(gameStage);
        root.requestFocus();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Stages.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void autoLocate(Stage stage) {
        Rectangle2D primaryScreen = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreen.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreen.getHeight() - stage.getHeight()) / 2);
    }
}
