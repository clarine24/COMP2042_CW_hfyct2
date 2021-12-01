package com.game.BrickDestroy;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphicsMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Stages stages = new Stages(stage);
        stages.homeStage();
    }

    public static void main(String[] args) {
        launch();
    }
}