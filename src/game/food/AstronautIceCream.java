package game.food;

import core.Entity;
import core.Sprite;
import core.Engine;

import java.awt.Point;
import java.awt.Color;

public class AstronautIceCream extends Entity implements Food {

    public AstronautIceCream(Point p) {
        super("Astronaut Ice Cream", new Sprite("i", Color.CYAN, p));
    }

    public void tick(Engine e) {

    }

    public int getNutrition() {
        return 1;
    }

}