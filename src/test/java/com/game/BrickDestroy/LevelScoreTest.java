package com.game.BrickDestroy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelScoreTest {
    int level = 1;
    LevelScore levelScore = new LevelScore(level);

    @Test
    void getInstance() {
    }

    @Test
    void calculateTotalScore_ballScoreIs0_brickScoreIs0_totalScoreIs0() {
        //levelScore.calculateTotalScore();
        //assertEquals(levelScore.getTotalScore().get(), 0);
    }

    @Test
    void clayBrickScore_defaultScoreAdded_scoreAdd100() {
        levelScore.clayBrickScore();
        assertEquals(levelScore.getTotalScore().get(), 100);
    }

    @Test
    void cementBrickScore_defaultScoreAdded_scoreAdd200() {
        levelScore.cementBrickScore();
        assertEquals(levelScore.getTotalScore().get(), 200);
    }

    @Test
    void steelBrickScore_defaultScoreAdded_scoreAdd150() {
        levelScore.steelBrickScore();
        assertEquals(levelScore.getTotalScore().get(), 150);
    }

    @Test
    void resetScore_totalScoreIs0() {
        levelScore.resetScore();
        assertEquals(levelScore.getTotalScore().get(), 0);
    }

    @Test
    void getTotalScore() {
        //return 0;
    }

    @Test
    void getHighScores() {
    }
}