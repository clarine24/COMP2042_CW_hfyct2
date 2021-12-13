package com.game.BrickDestroy.Model;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Levels {
    private static int LEVELS_COUNT;
    private static int brickCount;
    private int[] maxBallCount;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int BLUE = 4;

    private final Random rnd;

    public Levels(int levelCount, int brickCount) {
        rnd = new Random();

        LEVELS_COUNT = levelCount;
        this.brickCount = brickCount;
        maxBallCount = new int[LEVELS_COUNT];
    }

    /**
     * Creates new BrickModel instances for all levels.
     * @param bricks the shapes of the bricks
     * @return the array of all the levels' bricks
     */
    public BrickModel[][] makeLevels(Rectangle[] bricks) {
        BrickModel[][] tmp = new BrickModel[LEVELS_COUNT][];

        tmp[0] = makeSingleTypeLevel(bricks);
        tmp[1] = makeChessboardLevel(bricks, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(bricks, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(bricks, STEEL, CEMENT);
        tmp[4] = makeTripleTypeLevel(bricks);

        setMaxBallCount();

        return tmp;
    }

    /**
     * Creates new BrickModel instances of levels with a single type brick.
     * @param bricks the shapes of the bricks
     * @return the array of BrickModel instances
     */
    private BrickModel[] makeSingleTypeLevel(Rectangle[] bricks) {
        BrickModel[] tmp = new BrickModel[brickCount];

        for(int i=0; i<tmp.length; i++) {
            tmp[i] = makeBrick(bricks[i], CLAY);
        }
        return tmp;
    }

    /**
     * Creates new BrickModel instances of levels with two types of bricks.
     * @param bricks the shapes of the bricks
     * @param typeA the first type of brick
     * @param typeB the second type of brick
     * @return the array of BrickModel instances
     */
    private BrickModel[] makeChessboardLevel(Rectangle[] bricks, int typeA, int typeB) {
        BrickModel[] tmp = new BrickModel[brickCount];

        for(int i=0; i<tmp.length; i++) {
            if(i % 2 == 0) {
                tmp[i] = makeBrick(bricks[i], typeA);
            }
            else {
                tmp[i] = makeBrick(bricks[i], typeB);
            }
        }
        return tmp;
    }

    /**
     * Creates new BrickModel instances of levels with three types bricks.
     * @param bricks the shapes of the bricks
     * @return the array of BrickModel instances
     */
    private BrickModel[] makeTripleTypeLevel(Rectangle[] bricks) {
        BrickModel[] tmp = new BrickModel[brickCount];

        int ballLocation = additionalBallLocation(tmp.length);

        for(int i=0; i<tmp.length; i++) {
            if (i % 3 == 0) {
                tmp[i] = makeBrick(bricks[i], CEMENT);
            } else if (i % 3 == 1) {
                tmp[i] = makeBrick(bricks[i], CLAY);
            } else {
                tmp[i] = makeBrick(bricks[i], BLUE);
            }

            if(ballLocation == i) {
                tmp[i].setAdditionalBall(true);
            }
        }
        return tmp;
    }

    /**
     * Creates a new BrickModel instance based on the given brick shape and brick type.
     * @param brick the shape of the brick
     * @param type the type of brick
     * @return the BrickModel instance
     */
    private BrickModel makeBrick(Rectangle brick, int type) {
        return switch (type) {
            case CLAY -> new ClayBrickModel(brick);
            case STEEL -> new SteelBrickModel(brick);
            case CEMENT -> new CementBrickModel(brick);
            case BLUE -> new BlueBrickModel(brick);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

    /**
     * Gets the position of the additional ball in the bricks.
     * @param totalBrickCount the total number of bricks
     * @return the location of the additional ball
     */
    public int additionalBallLocation(int totalBrickCount) {
        return rnd.nextInt(totalBrickCount);
    }

    /**
     * Sets the maximum ball count for each level.
     */
    private void setMaxBallCount() {
        maxBallCount[0] = 3;
        maxBallCount[1] = 3;
        maxBallCount[2] = 3;
        maxBallCount[3] = 2;
        maxBallCount[4] = 2;
    }

    public int getMaxBallCount(int level) {
        return maxBallCount[level];
    }
}