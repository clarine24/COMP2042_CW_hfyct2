package com.game.BrickDestroy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public abstract class BrickCrackModel extends BrickModel {
    private Path crackPath;
    private BooleanProperty crack;

    public BrickCrackModel(Rectangle brick, String name, int fullStrength, Path crack) {
        super(brick, name, fullStrength);
        crackPath = new Path(crack.getElements());
        this.crack = new SimpleBooleanProperty(false);
    }

    public void setCrack(boolean crack) {
        this.crack.set(crack);
    }

    @Override
    public boolean setImpact() {
        super.impact();
        if(!super.isBroken().get()) {
            setCrack(true);
            return false;
        }
        setCrack(false);
        return true;
    }

    @Override
    public void repair() {
        super.repair();
        crack.set(false);
    }

    public BooleanProperty isCrack() {
        return crack;
    }
}
