package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;

/**
 * The PlayerModel class is the player model.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class PlayerModel {
    private Rectangle playerFace;
    private final double startX;
    private final double startY;

    private static final int DEF_MOVE_AMOUNT = 7;
    private int moveAmount;

    private final double min;
    private final double max;

    /**
     * Creates a new instance of PlayerModel.
     * @param player the player shape to be created
     * @param wall the wall in which the player is in
     */
    public PlayerModel(Rectangle player, Rectangle wall) {
        startX = player.getX();
        startY = player.getY();

        playerFace = new Rectangle(startX, startY, player.getWidth(), player.getHeight());

        moveAmount = 0;

        min = wall.getX();
        max = min + wall.getWidth() - playerFace.getWidth();
    }

    /**
     * Moves the player paddle.
     */
    public void move() {
        double x = playerFace.getX() + moveAmount;
        if (x < min) {
            playerFace.setX(min);
            return;
        }
        else if (x > max) {
            playerFace.setX(max);
            return;
        }
        playerFace.setX(x);
    }

    /**
     * Sets the player move amount to the left.
     */
    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Sets the player move amount to the right.
     */
    public void moveRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Sets the player move amount to stop.
     */
    public void stop() {
        moveAmount = 0;
    }

    /**
     * Moves the player paddle to its starting position.
     */
    public void moveToStart() {
        playerFace.setX(startX);
        playerFace.setY(startY);
    }

    /**
     * Gets the player shape.
     * @return the player shape
     */
    public Rectangle getPlayerFace() {
        return playerFace;
    }
}