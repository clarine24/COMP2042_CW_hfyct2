package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;
    private BrickModel[] bricks;

    private Rectangle wall;

    private static final int LEVELS_COUNT = 5;
    private BrickModel[][] allLevels;
    private IntegerProperty level;
    private BooleanProperty nextLevel;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private IntegerProperty ballCount;
    private int[] maxBallCount;
    private boolean ballLost;

    private IntegerProperty brickCount;

    public WallModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks, Path[] cracks) {
        this.wall = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, this.wall);
        this.ball = new RubberBallModel(ball, this.wall);

        maxBallCount = new int[LEVELS_COUNT];
        ballCount = new SimpleIntegerProperty();
        ballLost = false;

        brickCount = new SimpleIntegerProperty(bricks.length);

        level = new SimpleIntegerProperty(0);
        allLevels = makeLevels(bricks, cracks);
        nextLevel = new SimpleBooleanProperty(true);
    }

    private BrickModel[][] makeLevels(Rectangle[] bricks, Path[] cracks) {
        BrickModel[][] tmp = new BrickModel[LEVELS_COUNT][];

        tmp[0] = makeSingleTypeLevel(bricks, cracks);
        tmp[1] = makeChessboardLevel(bricks, CLAY, CEMENT, cracks);
        tmp[2] = makeChessboardLevel(bricks, CLAY, STEEL, cracks);
        tmp[3] = makeChessboardLevel(bricks, STEEL, CEMENT, cracks);
        tmp[4] = makeTripleTypeLevel(bricks, cracks);

        setMaxBallCount();

        return tmp;
    }

    private BrickModel[] makeSingleTypeLevel(Rectangle[] bricks, Path[] cracks) {
        BrickModel[] tmp = new BrickModel[brickCount.get()];

        for(int i=0; i<tmp.length; i++) {
            tmp[i] = makeBrick(bricks[i], WallModel.CLAY, cracks[i]);
        }
        return tmp;
    }

    private BrickModel[] makeChessboardLevel(Rectangle[] bricks, int typeA, int typeB, Path[] cracks) {
        BrickModel[] tmp = new BrickModel[brickCount.get()];

        for(int i=0; i<tmp.length; i++) {
            if(i % 2 == 0) {
                tmp[i] = makeBrick(bricks[i], typeA, cracks[i]);
            }
            else {
                tmp[i] = makeBrick(bricks[i], typeB, cracks[i]);
            }
        }
        return tmp;
    }

    private BrickModel[] makeTripleTypeLevel(Rectangle[] bricks, Path[] cracks) {
        BrickModel[] tmp = new BrickModel[brickCount.get()];

        for(int i=0; i<tmp.length; i++) {
            if(i % 3 == 0) {
                tmp[i] = makeBrick(bricks[i], WallModel.CEMENT, cracks[i]);
            }
            else if (i % 3 == 1) {
                tmp[i] = makeBrick(bricks[i], WallModel.CLAY, cracks[i]);
            }
            else {
                tmp[i] = makeBrick(bricks[i], WallModel.STEEL, cracks[i]);
            }
        }
        return tmp;
    }

    private void setMaxBallCount() {
        maxBallCount[0] = 3;
        maxBallCount[1] = 3;
        maxBallCount[2] = 3;
        maxBallCount[3] = 2;
        maxBallCount[4] = 2;
    }

    private BrickModel makeBrick(Rectangle brick, int type, Path crack) {
        return switch (type) {
            case CLAY -> new ClayBrickModel(brick);
            case STEEL -> new SteelBrickModel(brick);
            case CEMENT -> new CementBrickModel(brick, crack);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

    public void move() {
        ball.move();
        player.move();
    }

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

    private boolean impactLeftRightWall() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());

        boolean left = intersect.getBoundsInLocal().getMinX() == wall.getBoundsInLocal().getMinX();
        boolean right = intersect.getBoundsInLocal().getMaxX() == wall.getBoundsInLocal().getMaxX();

        return left || right;
    }

    private boolean impactTopWall() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        return intersect.getBoundsInLocal().getMinY() == wall.getY();
    }

    private boolean impactBottomWall() {
        return !wall.intersects(ball.getBallFace().getBoundsInLocal());
    }

    private boolean ballHitTopPlayer() {
        Shape intersect = Shape.intersect(ball.getBallFace(), player.getPlayerFace());
        return intersect.getBoundsInLocal().getMinY() == player.getPlayerFace().getY();
    }

    private boolean impactBrick() {
        for(BrickModel b : bricks) {
            if(b.isBroken().get()) {
                continue;
            }

            Shape intersect = Shape.intersect(b.getBrickFace(), ball.getBallFace());
            if(intersect.getBoundsInLocal().getMaxX() != -1) { //ball hits brick
                if(intersect.getBoundsInLocal().getHeight() < intersect.getBoundsInLocal().getWidth()){
                    ball.reverseY();
                }
                else {
                    ball.reverseX();
                }
                return b.setImpact();
            }
        }
        return false;
    }

    public void wallReset() {
        for(BrickModel b : bricks) {
            b.repair();
        }
        brickCount.set(bricks.length);

        resetBallCount();
        ballPlayerReset();
    }

    public void ballPlayerReset() {
        player.moveToStart();
        ball.moveToStart();
        ball.setMoveDirection();
        ballLost = false;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public BallModel getBall() {
        return ball;
    }

    public IntegerProperty getBallCount() {
        return ballCount;
    }

    public void resetBallCount() { ballCount.set(maxBallCount[level.get() - 1]); }

    public boolean ballEnd(){
        return ballCount.get() == 0;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public BrickModel[] getBricks() {
        return bricks;
    }

    public IntegerProperty getBrickCount() {
        return brickCount;
    }

    public boolean isDone() {
        return brickCount.get() == 0;
    }

    public IntegerProperty getLevel() {
        return level;
    }

    public BooleanProperty hasNextLevel() {
        return nextLevel;
    }

    public void nextLevel() {
        ballCount.set(maxBallCount[level.get()]);
        bricks = allLevels[level.get()];
        level.set(level.get() + 1);
        this.brickCount.set(bricks.length);
        int x = level.get();
        nextLevel.set(x < allLevels.length);
    }
}
