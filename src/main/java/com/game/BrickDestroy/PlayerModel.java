package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class PlayerModel {
    private Rectangle playerFace;
    private double startX;
    private double startY;

    private static final int DEF_MOVE_AMOUNT = 5;
    private int moveAmount;

    private double min;
    private double max;

    public PlayerModel(Rectangle player, Rectangle wall) {
        startX = player.getX();
        startY = player.getY();

        playerFace = new Rectangle(startX, startY, player.getWidth(), player.getHeight());

        moveAmount = 0;

        min = wall.getX();
        max = min + wall.getWidth() - playerFace.getWidth();
    }

    public void move() {
        double x = playerFace.getX() + moveAmount;
        if (x < min || x > max) {
            return;
        }
        playerFace.setX(x);
    }

    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop() {
        moveAmount = 0;
    }

    public void moveToStart() {
        playerFace.setX(startX);
        playerFace.setY(startY);
    }

    public Rectangle getPlayerFace() {
        return playerFace;
    }
}