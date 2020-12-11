package game.furnature;

import core.Engine;
import core.Entity;
import core.Sprite;
import java.awt.Color;
import java.awt.Point;

public class Chair extends Entity {

    public Chair(Point p) {
        super("Chair", new Sprite("C", Color.white, p));
    }

    public Chair() { this(new Point(0, 0)); }

    public void tick(Engine e) {

    }

}
