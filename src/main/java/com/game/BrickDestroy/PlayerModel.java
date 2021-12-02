package com.game.BrickDestroy;

import javafx.scene.shape.Rectangle;

public class PlayerModel {
    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private double centerX;
    private double centerY;

    private double min;
    private double max;

    public PlayerModel(Rectangle player, Rectangle wall) {
        centerX = player.getX();
        centerY = player.getY();

        playerFace = new Rectangle(centerX, centerY, player.getWidth(), player.getHeight());

        min = wall.getX();
        max = min + wall.getWidth() - playerFace.getWidth();
    }

    public Rectangle getPlayerFace() {
        return playerFace;
    }

    private void move(int moveAmount) {
        double x = playerFace.getX() + moveAmount;
        if (x < min || x > max) {
            System.out.println("player stopped");
            return;
        }
        playerFace.setX(x);
    }

    public void moveLeft() {
        int moveAmount = -DEF_MOVE_AMOUNT;
        move(moveAmount);
    }

    public void moveRight() {
        int moveAmount = DEF_MOVE_AMOUNT;
        move(moveAmount);
    }
}