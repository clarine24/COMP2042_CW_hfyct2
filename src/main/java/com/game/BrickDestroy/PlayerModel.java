package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class PlayerModel {
    private Rectangle playerFace;
    private double centerX;
    private double centerY;

    private static final int DEF_MOVE_AMOUNT = 5;
    private int moveAmount;

    private double min;
    private double max;

    public PlayerModel(Rectangle player, Rectangle wall) {
        centerX = player.getX();
        centerY = player.getY();

        playerFace = new Rectangle(centerX, centerY, player.getWidth(), player.getHeight());

        moveAmount = 0;

        min = wall.getX();
        max = min + wall.getWidth() - playerFace.getWidth();
    }

    public Rectangle getPlayerFace() {
        return playerFace;
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

    public void moveTo() {
        playerFace.setX(centerX);
        playerFace.setY(centerY);
    }
}