package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;

public class Score {
    private IntegerProperty totalScore;
    private File scoreBoardFile;
    private RandomAccessFile file;

    private static final String FILE_PATH = "src/main/resources/com/game/BrickDestroy/scoreBoard";

    private final int CLAY_BRICK_SCORE = 100;
    private final int CEMENT_BRICK_SCORE = 200;
    private final int STEEL_BRICK_SCORE = 150;

    private int[] highScores;

    public Score(int level) {
        totalScore = new SimpleIntegerProperty(0);

        scoreBoardFile = new File(FILE_PATH + "Level" + level + ".txt");
        try {
            if(scoreBoardFile.createNewFile()) {
                System.out.println("File created: " + scoreBoardFile.getName());
            }
            else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        calculateTopScores();
    }

    public void calculateTotalScore() {
        totalScore.set(totalScore.get() + ballScore());

        try {
            file = new RandomAccessFile(scoreBoardFile, "rw");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        writeToFile(totalScore.get());
        calculateTopScores();
    }

    private void writeToFile(int score) {
        try {
            long lastLine = file.length();
            file.seek(lastLine); //save new score in last line
            file.writeInt(score);
            System.out.println("Successfully wrote to file");
        } catch (IOException e) {
            System.out.println("Error occurred during write to file");
            e.printStackTrace();
        }
    }

    private void calculateTopScores() {
        int scoreCount = numberOfScoreInFile();
        createHighScoreArray(scoreCount);
        long[] tmp = getTopScoresPointer();
        saveHighScoresInArray(tmp);
        for (int highScore : highScores) {
            System.out.println(highScore);
        }
    }

    private int numberOfScoreInFile() {
        int count = 0;

        try {
            file = new RandomAccessFile(scoreBoardFile, "rw");
            file.seek(0);
            while(true) {
                try {
                    file.readInt();
                    count++;
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred during calculation of number of scores saved in file");
            e.printStackTrace();
        }
        return count;
    }

    private void createHighScoreArray(int scoreCount) {
        if(scoreCount < 5) {
            highScores = new int[scoreCount];
        }
        else {
            highScores = new int[5];
        }
    }

    private long[] getTopScoresPointer() {
        long[] tmp = new long[highScores.length];
        long previousPointer;
        long currentPointer;

        try {
            for(int i=0; i<highScores.length; i++) {
                file.seek(0); //read from start of file

                previousPointer = file.getFilePointer();
                tmp[i] = previousPointer;

                int score = file.readInt();
                currentPointer = file.getFilePointer();

                while (true) {
                    try {
                        boolean skip = false;

                        int nextScore = file.readInt();
                        previousPointer = currentPointer;
                        currentPointer = file.getFilePointer();

                        for (int j = 0; j < i; j++) {
                            if (tmp[j] == previousPointer) {
                                skip = true;
                                break;
                            }
                        }
                        if (skip) {
                            continue;
                        }

                        if (score < nextScore) {
                            score = nextScore;
                            tmp[i] = previousPointer;
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred during finding top scores pointer");
            e.printStackTrace();
        }

        return tmp;
    }

    private void saveHighScoresInArray(long[] tmp) {
        for(int i=0; i<highScores.length; i++) {
            try {
                file.seek(tmp[i]);
                highScores[i] = file.readInt();
            } catch (IOException e) {
                System.out.println("Error occurred during saving high scores in array");
                e.printStackTrace();
            }
        }
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

    public IntegerProperty getTotalScore() {
        return totalScore;
    }

    public void closeFile() {
        try {
            file.close();
            System.out.println("File is closed");
        } catch (IOException e) {
            System.out.println("File cannot be closed");
            e.printStackTrace();
        }
    }
}
