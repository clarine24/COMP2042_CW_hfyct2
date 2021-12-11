package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

/**
 * The WallModel class is the wall model.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
public class WallModel {
    private final PlayerModel player;
    private final BallModel ball;
    private BrickModel[] bricks;

    private final Rectangle wall;

    static final int LEVELS_COUNT = 5;
    private final BrickModel[][] allLevels;
    private final IntegerProperty level;
    private final BooleanProperty nextLevel;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private final Random rnd;
    private final IntegerProperty ballCount;
    private final int[] maxBallCount;
    private boolean ballLost;
    private final BooleanProperty addAdditionalBall;

    private final IntegerProperty brickCount;

    /**
     * Creates a new instance of WallModel with the given wall shape, player shape, ball shape and bricks shapes.
     * @param wall the shape of the wall
     * @param player the shape of the player
     * @param ball the shape of the ball
     * @param bricks the shapes of all the bricks
     */
    public WallModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks) {
        rnd = new Random();

        this.wall = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, this.wall);
        this.ball = new RubberBallModel(ball, this.wall);

        maxBallCount = new int[LEVELS_COUNT];
        ballCount = new SimpleIntegerProperty();
        ballLost = false;
        addAdditionalBall = new SimpleBooleanProperty(false);

        brickCount = new SimpleIntegerProperty(bricks.length);

        level = new SimpleIntegerProperty(0);
        allLevels = makeLevels(bricks);
        nextLevel = new SimpleBooleanProperty(true);
    }

    /**
     * Creates new BrickModel instances for all levels.
     * @param bricks the shapes of the bricks
     * @return the array of all the levels' bricks
     */
    private BrickModel[][] makeLevels(Rectangle[] bricks) {
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
        BrickModel[] tmp = new BrickModel[brickCount.get()];

        for(int i=0; i<tmp.length; i++) {
            tmp[i] = makeBrick(bricks[i], WallModel.CLAY);
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
        BrickModel[] tmp = new BrickModel[brickCount.get()];

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
        BrickModel[] tmp = new BrickModel[brickCount.get()];

        int ballLocation = additionalBallLocation(tmp.length);

        for(int i=0; i<tmp.length; i++) {
            if (i % 3 == 0) {
                tmp[i] = makeBrick(bricks[i], WallModel.CEMENT);
            } else if (i % 3 == 1) {
                tmp[i] = makeBrick(bricks[i], WallModel.CLAY);
            } else {
                tmp[i] = makeBrick(bricks[i], WallModel.STEEL);
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
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

    /**
     * Gets the position of the additional ball in the bricks.
     * @param totalBrickCount the total number of bricks
     * @return the location of the additional ball
     */
    private int additionalBallLocation(int totalBrickCount) {
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

    /**
     * Moves the ball and player.
     */
    public void move() {
        ball.move();
        player.move();
    }

    /**
     * Finds and sets the effects of the impact of the ball.
     */
    public void findImpacts() {
        if(impactBottomWall()) {
            ballCount.set(ballCount.get() - 1);
            ballLost = true;
        }
        else if(impactTopWall()) {
            ball.reverseY();
        }
        else if(impactLeftRightWall()) {
            ball.reverseX();
        }
        else if (ballHitTopPlayer()) {
            ball.reverseY();

            if(ball.getBallFace().getBoundsInLocal().getMaxY() > player.getPlayerFace().getY()) { //move ball on paddle if ball is in paddle
                ball.getBallFace().setCenterY(player.getPlayerFace().getY() - ball.getBallFace().getRadius());
            }
        }
        else if (impactBrick()) {
            brickCount.set(brickCount.get() - 1);
        }
    }

    /**
     * Determines if the ball hits the left or right sides of the wall.
     * @return the boolean value of whether the ball hits the sides of the wall
     */
    private boolean impactLeftRightWall() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());

        boolean left = intersect.getBoundsInLocal().getMinX() == wall.getBoundsInLocal().getMinX();
        boolean right = intersect.getBoundsInLocal().getMaxX() == wall.getBoundsInLocal().getMaxX();

        return left || right;
    }

    /**
     * Determines if the ball hits the top of the wall.
     * @return the boolean value of whether the ball hits the top of the wall
     */
    private boolean impactTopWall() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        return intersect.getBoundsInLocal().getMinY() == wall.getY();
    }

    /**
     * Determines if the ball moves out of the wall boundary from the bottom.
     * @return the boolean value of whether the ball moves out of wall from the bottom
     */
    private boolean impactBottomWall() {
        return !wall.intersects(ball.getBallFace().getBoundsInLocal());
    }

    /**
     * Determines if the ball hits the player paddle.
     * @return the boolean value of whether the ball hits the player paddle
     */
    private boolean ballHitTopPlayer() {
        Shape intersect = Shape.intersect(ball.getBallFace(), player.getPlayerFace());
        return intersect.getBoundsInLocal().getMinY() == player.getPlayerFace().getY();
    }

    /**
     * Determines if the ball hits the brick.
     * Sets the impact on the brick.
     * @return the boolean value of whether the brick breaks
     */
    private boolean impactBrick() {
        for(BrickModel b : bricks) {
            if(b.isBroken().get()) {
                continue;
            }

            Shape intersect = Shape.intersect(b.getBrickFace(), ball.getBallFace());
            if(intersect.getBoundsInLocal().getMaxX() != -1) { //ball hits brick
                if(intersect.getBoundsInLocal().getHeight() < intersect.getBoundsInLocal().getWidth()){
                    ball.reverseY();

                    //move ball next to brick, so that ball is not inside brick
                    double y = intersect.getBoundsInLocal().getHeight();
                    ball.getBallFace().setCenterY(ball.getBallFace().getCenterY() + y);
                }
                else {
                    ball.reverseX();

                    //move ball next to brick, so that ball is not inside brick
                    double x = intersect.getBoundsInLocal().getWidth();
                    ball.getBallFace().setCenterX(ball.getBallFace().getCenterX() + x);
                }


                if(b.setImpact()) {
                    if(b.isAdditionalBall()) {
                        ballCount.set(ballCount.get() + 1);
                        b.setAdditionalBall(false);
                        setAddAdditionalBall(true);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Resets the entire level.
     * Resets all the bricks.
     * Resets the ball and player position.
     * Resets the ball count.
     */
    public void wallReset() {
        int ballLocation = additionalBallLocation(bricks.length);

        for(int i=0; i< bricks.length; i++) {
            bricks[i].repair();
            bricks[i].setAdditionalBall(false);

            if(level.get() >= 4) {
                if(ballLocation == i) {
                    bricks[i].setAdditionalBall(true);
                }
            }
        }
        brickCount.set(bricks.length);

        resetBallCount();
        ballPlayerReset();

        setAddAdditionalBall(false);
    }

    /**
     * Resets the ball and player.
     * Resets the position of the ball and player.
     */
    public void ballPlayerReset() {
        player.moveToStart();
        ball.moveToStart();
        ball.setMoveDirection();
        ballLost = false;
    }

    /**
     * Gets the created playerModel instance.
     * @return the playerModel instance
     */
    public PlayerModel getPlayer() {
        return player;
    }

    /**
     * Gets the created ballModel instance.
     * @return the ballModel instance
     */
    public BallModel getBall() {
        return ball;
    }

    /**
     * Gets the integer property of ballCount
     * @return the integer property of ball count
     */
    public IntegerProperty getBallCount() {
        return ballCount;
    }

    /**
     * Resets the ball count.
     */
    public void resetBallCount() {
        ballCount.set(maxBallCount[level.get() - 1]);
    }

    /**
     * Determines if it is the last ball.
     * @return the boolean value whether if it is the last ball
     */
    public boolean ballEnd(){
        return ballCount.get() == 0;
    }

    /**
     * Determines if the ball is lost.
     * @return the boolean value of ball lost
     */
    public boolean isBallLost() {
        return ballLost;
    }

    /**
     * Gets the array of BrickModel of the level.
     * @return the array of bricks.
     */
    public BrickModel[] getBricks() {
        return bricks;
    }

    /**
     * Gets the integer property of brickCount.
     * @return the integer property of brick count
     */
    public IntegerProperty getBrickCount() {
        return brickCount;
    }

    /**
     * Determines if all the bricks are broken.
     * @return the boolean value of whether all the bricks are broken.
     */
    public boolean isDone() {
        return brickCount.get() == 0;
    }

    /**
     * Gets the integer property of level.
     * @return the integer property of level
     */
    public IntegerProperty getLevel() {
        return level;
    }

    /**
     * Determines if there is a next level.
     * @return the boolean property of nextLevel
     */
    public BooleanProperty hasNextLevel() {
        return nextLevel;
    }

    /**
     * Go to the next level.
     */
    public void nextLevel() {
        GameOverModel.getInstance().getScoreBoardModel().setLevel(level);

        ballCount.set(maxBallCount[level.get()]);
        bricks = allLevels[level.get()];
        level.set(level.get() + 1);
        this.brickCount.set(bricks.length);
        int x = level.get();
        nextLevel.set(x < allLevels.length);
    }

    /**
     * Gets the boolean property of addAdditionalBall.
     * @return the boolean property of addAdditionalBall
     */
    public BooleanProperty isAddAdditionalBall() {
        return addAdditionalBall;
    }

    /**
     * Sets the boolean addAdditionalBall.
     * @param addAdditionalBall the new boolean value of addAdditionalBall
     */
    public void setAddAdditionalBall(boolean addAdditionalBall) {
        this.addAdditionalBall.set(addAdditionalBall);
    }
}
