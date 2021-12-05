package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static java.lang.Boolean.getBoolean;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;
    private BrickModel[] bricks;

    private Rectangle wall;

    private static final int LEVELS_COUNT = 4;
    private BrickModel[][] allLevels;
    private IntegerProperty level;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private int ballCount;
    private boolean ballLost;

    private int brickCount;
    private int lineCount;

    public WallModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks) {
        this.wall = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, this.wall);
        this.ball = new RubberBallModel(ball);

        ballCount = 3;
        ballLost = false;

        brickCount = bricks.length;
        lineCount = 3;

        level = new SimpleIntegerProperty(0);
        allLevels = makeLevels(bricks);
    }

    private BrickModel[][] makeLevels(Rectangle[] bricks) {
        BrickModel[][] tmp = new BrickModel[LEVELS_COUNT][];

        tmp[0] = makeSingleTypeLevel(bricks, CLAY);
        tmp[1] = makeChessboardLevel(bricks, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(bricks, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(bricks, STEEL, CEMENT);

        return tmp;
    }

    private BrickModel[] makeSingleTypeLevel(Rectangle[] bricks, int type) {
        BrickModel[] tmp = new BrickModel[brickCount];

        for(int i=0; i<tmp.length; i++) {
            tmp[i] = makeBrick(bricks[i], type);
        }
        return tmp;
    }

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

    private BrickModel makeBrick(Rectangle brick, int type) {
        BrickModel out;
        switch(type) {
            case CLAY:
                out = new ClayBrickModel(brick);
                break;
            case STEEL:
                out = new SteelBrickModel(brick);
                break;
            case CEMENT:
                out = new CementBrickModel(brick);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return out;
    }

    public void move() {
        ball.move();
        player.move();
    }

    public void findImpacts() {
        if(impactBottomWall()) {
            ballCount--;
            ballLost = true;
            System.out.println("bottom");
        }
        else if(impactTopWall()) {
            ball.reverseY();
            System.out.println("top");
        }
        else if(impactLeftRightWall()) {
            ball.reverseX();
            System.out.println("Hit border");
        }
        else if (ballHitPlayer()) {
            ball.reverseY();
            System.out.println("player");
        }
        else if (impactBrick()) {
            brickCount--;
            System.out.println("brick");
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

    private boolean ballHitPlayer() {
        return ball.getBallFace().intersects(player.getPlayerFace().getBoundsInLocal());
    }

    private boolean impactBrick() {
        for(BrickModel b : bricks) {
            if(b.isBroken().get()) {
                continue;
            }

            Shape intersect = Shape.intersect(b.brickFace, ball.getBallFace());
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
        /*
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
         */

        ballCount = 3;
        ballPlayerReset();
    }

    public void ballPlayerReset() {
        player.moveToStart();
        ball.moveToStart();
        ball.setMoveDirection();
        ballLost = false;
    }

    public Rectangle getWall() {
        return wall;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public BallModel getBall() {
        return ball;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public BrickModel[] getBricks() {
        return bricks;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public IntegerProperty getLevel() {
        return level;
    }

    public boolean hasLevel() {
        int x = level.get();
        return x < allLevels.length;
    }

    public void nextLevel() {
        bricks = allLevels[level.get()];
        level.set(level.get() + 1);
        this.brickCount = bricks.length;
    }
}
