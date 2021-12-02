package com.game.BrickDestroy;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    private WallModel wall;

    public GameTimer(WallModel wall) {
        this.wall = wall;
    }

    @Override
    public void handle(long l) {
        wall.move();
        //wall.findImpacts();
    }
}
