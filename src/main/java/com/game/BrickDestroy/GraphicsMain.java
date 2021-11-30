package com.game.BrickDestroy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicsMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsMain.class.getResource("HomeMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Brick Destroy");
        stage.setScene(scene);
        //stage.init;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}