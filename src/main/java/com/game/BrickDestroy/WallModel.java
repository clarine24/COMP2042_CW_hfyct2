package com.game.BrickDestroy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WallModel {
    private PlayerModel player;
    private BallModel ball;
    private BrickModel[] bricks;

    private Rectangle wall;

    private static final int LEVELS_COUNT = 4;
    private BrickModel[][] allLevels;
    private IntegerProperty level;

    private int ballCount;
    private boolean ballLost;

    public WallModel(Rectangle wall, Rectangle player, Circle ball, Rectangle[] bricks) {
        this.wall = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        this.player = new PlayerModel(player, this.wall);
        this.ball = new RubberBallModel(ball);

        level = new SimpleIntegerProperty(0);
        allLevels = makeLevels(bricks);

        ballCount = 3;
        ballLost = false;
    }

    private BrickModel[][] makeLevels(Rectangle[] bricks) {
        BrickModel[][] tmp = new BrickModel[1][];

        tmp[0] = makeSingleTypeLevel(bricks);
        //tmp[1] = makeChessboardLevel();
        //tmp[2] = makeChessboardLevel();
        //tmp[3] = makeChessboardLevel();

        return tmp;
    }

    private BrickModel[] makeSingleTypeLevel(Rectangle[] bricks) {
        BrickModel[] tmp = new BrickModel[bricks.length];

        for(int i=0; i<tmp.length; i++) {
            tmp[i] = new ClayBrickModel(bricks[i]);
        }

        return tmp;
    }

    private BrickModel[] makeChessboardLevel() {
        return null;
    }

    public void move() {
        ball.move();
        player.move();
    }

    public void findImpacts() {
        if(impactBottomBorder()) {
            ballCount--;
            ballLost = true;
            System.out.println("bottom");
        }
        else if(impactTopBorder()) {
            ball.reverseY();
            System.out.println("top");
        }
        else if(impactLeftRightBorder()) {
            ball.reverseX();
            System.out.println("Hit border");
        }
        else if (ballHitPlayer()) {
            ball.reverseY();
            System.out.println("player");
        }
    }

    private boolean impactLeftRightBorder() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());

        boolean left = intersect.getBoundsInLocal().getMinX() == wall.getBoundsInLocal().getMinX();
        boolean right = intersect.getBoundsInLocal().getMaxX() == wall.getBoundsInLocal().getMaxX();

        if(left || right) {
            return true;
        }
        return false;
    }

    private boolean impactTopBorder() {
        Shape intersect = Shape.intersect(wall, ball.getBallFace());
        if(intersect.getBoundsInLocal().getMinY() == wall.getY()) {
                return true;
        }
        return false;
    }

    private boolean impactBottomBorder() {
        if(wall.intersects(ball.getBallFace().getBoundsInLocal())) {
            return false;
        }
        return true;
    }

    private boolean ballHitPlayer() {
        return ball.getBallFace().intersects(player.getPlayerFace().getBoundsInLocal());
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

    public BrickModel[][] getAllLevels() {
        return allLevels;
    }

    public IntegerProperty getLevel() {
        return level;
    }
}
