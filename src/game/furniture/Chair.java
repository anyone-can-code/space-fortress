package game.furniture;

import core.Engine;
import core.Entity;
import core.Sprite;
import java.awt.Color;
import java.awt.Point;

public class Chair extends Entity {

    private boolean occupied;

    public Chair(Point p) {
        super("Chair", new Sprite("C", Color.white, p));
        occupied = false;
    }

    public Chair() { this(new Point(0, 0)); }

    public void tick(Engine e) {

    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean o) {
        occupied = o;
    }

}
