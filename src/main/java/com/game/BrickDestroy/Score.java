package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.File;
import java.io.IOException;

public class Score {
    private IntegerProperty totalScore;
    private File scoreBoard;

    private final int CLAY_BRICK_SCORE = 100;
    private final int CEMENT_BRICK_SCORE = 200;
    private final int STEEL_BRICK_SCORE = 150;

    public Score() {
        totalScore = new SimpleIntegerProperty(0);

        scoreBoard = new File("src/main/resources/com/game/BrickDestroy/scoreBoard.txt");
        try {
            if(scoreBoard.createNewFile()) {
                System.out.print("File created: " + scoreBoard.getName());
            }
            else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void calculateTotalScore() {
        int totalScore = this.totalScore.get();
        totalScore += ballScore();
        System.out.println(totalScore);
    }

    private int ballScore() {
        int remainingBallCount = GameBoardModel.getInstance().getWallModel().getBallCount().get();
        return remainingBallCount * 50; //each ball left is 50 points
    }

    private void totalBrickScore(int addScore) {
        totalScore.set(totalScore.get() + addScore);
    }

    public void clayBrickScore() {
        totalBrickScore(CLAY_BRICK_SCORE);
    }

    public void cementBrickScore() {
        totalBrickScore(CEMENT_BRICK_SCORE);
    }

    public void steelBrickScore() {
        totalBrickScore(STEEL_BRICK_SCORE);
    }

    public void resetScore() {
        totalScore.set(0);
    }
}
