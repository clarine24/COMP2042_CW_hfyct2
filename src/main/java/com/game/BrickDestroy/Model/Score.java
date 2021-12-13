package com.game.BrickDestroy.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;

/**
 * The Score calculates and stores all the scores in external txt file.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class Score {
    private static Score instance;

    private IntegerProperty totalScore;
    private File scoreBoardFile;
    private RandomAccessFile file;

    private static final String FILE_PATH = "src/main/resources/com/game/BrickDestroy/ScoreFiles/";

    private final int CLAY_BRICK_SCORE = 100;
    private final int CEMENT_BRICK_SCORE = 200;
    private final int STEEL_BRICK_SCORE = 150;
    private final int BLUE_BRICK_SCORE = 250;

    private IntegerProperty[] highScores;
    private final int TOP_SCORES= 5;

    /**
     * Creates a new instance of LevelScore for the given level.
     * @param level the current level
     */
    public Score(int level) {
        totalScore = new SimpleIntegerProperty(0);

        createFile(level);

        calculateTopScores();

        instance = this;
    }

    /**
     * Gets the created LevelScore instance.
     * @return the LevelScore instance
     */
    public static Score getInstance() {
        return instance;
    }

    /**
     * Creates a new txt file, if it does not exist, to store the scores for the given level.
     * @param level the level
     */
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

    /**
     * Calculates the total score and stores the score in the txt file.
     * Calculates the high scores.
     */
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

    /**
     * Saves the given score in the txt file.
     * @param score the score
     */
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

    /**
     * Calculates the top 5 scores.
     */
    private void calculateTopScores() {
        int scoreCount = numberOfScoreInFile();
        createHighScoreArray(scoreCount);
        long[] tmp = getTopScoresPointer();
        saveHighScoresInArray(tmp);
    }

    /**
     * Gets the number of scores saved in the file.
     * @return the number of scores saved in the file
     */
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

    /**
     * Creates an empty array to save the top 5 scores.
     * The size of the empty array depends on the number of scores saved in the txt file.
     * If there is at least 5 saved scores, the size of the array will be 5.
     * Otherwise, the size of array will be the same as the number of saved scores.
     * @param scoreCount the number of scores
     */
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

    /**
     * Gets the pointers to the top 5 scores in the txt file.
     * @return the pointers to the top scores in the txt file
     */
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

    /**
     * Saves the top 5 scores in the array of high scores with the given array of pointer.
     * @param tmp the pointers to the top 5 scores in the txt file
     */
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

    /**
     * Gets the score obtained from the remaining ball count.
     * @return the score obtained from the ball count
     */
    private int ballScore() {
        int remainingBallCount = GameBoardModel.getInstance().getWallModel().getBallCount().get();
        return remainingBallCount * 50; //each ball left is 50 points
    }

    /**
     * Calculates the total score obtained from the bricks by adding the given brick score.
     * @param addScore the score to be added
     */
    private void totalBrickScore(int addScore) {
        totalScore.set(totalScore.get() + addScore);
    }

    /**
     * Adds the clay brick score to the total brick score.
     */
    public void clayBrickScore() {
        totalBrickScore(CLAY_BRICK_SCORE);
    }

    /**
     * Adds the cement brick score to the total brick score.
     */
    public void cementBrickScore() {
        totalBrickScore(CEMENT_BRICK_SCORE);
    }

    /**
     * Adds the steel brick score to the total brick score.
     */
    public void steelBrickScore() {
        totalBrickScore(STEEL_BRICK_SCORE);
    }

    /**
     * Adds the steel brick score to the total brick score.
     */
    public void blueBrickScore() {
        totalBrickScore(BLUE_BRICK_SCORE);
    }

    /**
     * Resets the total score to 0.
     */
    public void resetScore() {
        totalScore.set(0);
    }

    /**
     * Gets the integer property of totalScore.
     * @return the integer property of the total score
     */
    public IntegerProperty getTotalScore() {
        return totalScore;
    }

    /**
     * Gets the integer property of the array of highScores.
     * @return the array of integer property of high scores
     */
    public IntegerProperty[] getHighScores() {
        return highScores;
    }

    /**
     * Close the txt file.
     */
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
