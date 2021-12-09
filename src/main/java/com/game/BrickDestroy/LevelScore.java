package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;

public class LevelScore {
    private static LevelScore instance;

    private IntegerProperty totalScore;
    private File scoreBoardFile;
    private RandomAccessFile file;

    private static final String FILE_PATH = "src/main/resources/com/game/BrickDestroy/scoreBoard";

    private final int CLAY_BRICK_SCORE = 100;
    private final int CEMENT_BRICK_SCORE = 200;
    private final int STEEL_BRICK_SCORE = 150;

    private IntegerProperty[] highScores;
    private final int TOP_SCORES= 5;

    public LevelScore(int level) {
        totalScore = new SimpleIntegerProperty(0);

        createFile(level);

        calculateTopScores();

        instance = this;
    }

    public static LevelScore getInstance() {
        return instance;
    }

    private void createFile(int level) {
        scoreBoardFile = new File(FILE_PATH + "Level" + level + ".txt");
        try {
            if(scoreBoardFile.createNewFile()) {
                System.out.println("File created: " + scoreBoardFile.getName());
            }
            else {
                System.out.println("File already exists: " + scoreBoardFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
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
            highScores = new SimpleIntegerProperty[scoreCount];
        }
        else {
            highScores = new SimpleIntegerProperty[TOP_SCORES];
        }

        for(int i=0; i<highScores.length; i++) {
            highScores[i] = new SimpleIntegerProperty();
        }
    }

    private long[] getTopScoresPointer() {
        long[] tmp = new long[highScores.length];
        long previousPointer;
        long currentPointer;
        int score;
        int nextScore;

        try {
            for(int i=0; i<highScores.length; i++) {
                file.seek(0); //read from start of file

                int x=0;
                while(x<i){
                    for (int j = 0; j < i; j++) {
                        if (tmp[j] == file.getFilePointer()) {
                            file.readInt();
                            break;
                        }
                    }
                    x++;
                }

                previousPointer = file.getFilePointer();
                tmp[i] = previousPointer;
                score = file.readInt();
                currentPointer = file.getFilePointer();

                while (true) {
                    try {
                        boolean skip = false;

                        nextScore = file.readInt();
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
                highScores[i].set(file.readInt());
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

    public IntegerProperty[] getHighScores() {
        return highScores;
    }

    public void closeFile() {
        try {
            file.close();
            System.out.println("File closed: " + scoreBoardFile.getName());
        } catch (IOException e) {
            System.out.println("File cannot be closed: " + scoreBoardFile.getName());
            e.printStackTrace();
        }
    }
}
