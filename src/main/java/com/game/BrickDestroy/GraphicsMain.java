package com.game.BrickDestroy;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The GraphicsMain class contains the main method to run the entire program.
 * This class links to the Stages class.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class GraphicsMain extends Application {
    /**
     * The main entry point for the application.
     * Calls the homeStage method in Stages class.
     * @param stage the primary stage (homeStage) of the application
     * @throws IOException
     * @see IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Stages stages = new Stages(stage);
        stages.homeStage();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        launch();
    }
}