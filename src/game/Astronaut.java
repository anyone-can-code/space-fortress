// Astronaut.java

package game;

import core.Engine;
import core.Entity;
import core.Sprite;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Astronaut extends Entity {
	
	private Random rand;
	
	public Astronaut(Point p) {
		super("Astronaut Abe", new Sprite("@", Color.WHITE, p));
		
		rand = new Random();
	}
	
	public Astronaut() {
		this(new Point(0, 0));
	}
	
	public void tick(Engine e) {
		//~ Point newPos = new Point(getPos());
		//~ System.out.println(newPos);
		//~ switch (rand.nextInt(4)) {
			//~ case 0:
				//~ newPos.x += 1;
				//~ break;
				
			//~ case 1:
				//~ newPos.y += 1;
				//~ break;
				
			//~ case 2:
				//~ newPos.x -= 1;
				//~ break;
				
			//~ case 3:
				//~ newPos.y -= 1;
				//~ break;
		//~ }
		//~ if (e.floorExists(newPos))
			//~ this.getSprite().setPos(newPos);
	}
	
}
