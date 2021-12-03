package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PauseMenuModel {
    private static PauseMenuModel instance;

    private BooleanProperty resume;
    private BooleanProperty restart;

    private PauseMenuModel() {
        resume = new SimpleBooleanProperty(false);
        restart = new SimpleBooleanProperty(false);
    }

    public static PauseMenuModel getInstance() {
        if(instance == null) {
            instance = new PauseMenuModel();
        }
        return instance;
    }

    public void setRestart(boolean restart) {
        this.restart.set(restart);
    }

    public void setResume(boolean resume) {
        this.resume.set(resume);
    }

    public BooleanProperty isResume() {
        return resume;
    }

    public BooleanProperty isRestart() {
        return restart;
    }
}
