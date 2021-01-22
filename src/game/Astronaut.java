// Astronaut.java

package game;

import core.Engine;
import core.Entity;
import core.Sprite;
import game.food.Food;
import game.furnature.Chair;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class Astronaut extends Entity {

	/*
	/// Developer note
	Lines 55 and 157-159 are currently throwing null pointer exceptions, likely because the chair and currentChair objects are not being properly initialized.
	This inhibits the functionality of chairs having an "occupied" mode, so it has been commented out for now. However, it needs to repaired somewhat soon, or else multiple astronauts will use the same chair simultaneously.
	I am currently working on the issue, but suggestions are appreciated. However, this isn't *that* big of an issue, so it can just sort of stay in the background for now while more pressing things are addressed.
	- Jack
	 */
	
	private Random rand;
	private int fatigue;
	private int fatigueThreshold;
	private boolean sitting;
	private ArrayList<Entity> inventory;
	
	public Astronaut(Point p) {
		super("Astronaut Abe", new Sprite("@", Color.WHITE, p));

		rand = new Random();
		fatigue = 0;
		fatigueThreshold = 250;
		sitting = false;
		inventory = new ArrayList<Entity>();
	}
	
	public Astronaut() {
		this(new Point(0, 0));
	}
	
	public void tick(Engine e) {
		// This version of the Tick function is being used purely for testing purposes

		if(sitting) { // If the player is sitting
			// Next bit of code find the chair they are sitting in
			Entity currentChair = null;
			for(Entity entity : e.getEntitiesAtPos(this.getPos())) {
				if(entity.getName().equals("Chair")) {
					currentChair = entity;
					break;
				}
			}
			if(fatigue <= 5) { // If they are ready to get up
				sitting = false; // Stand up
				//currentChair.setInUse(false);
			}
			else { // If they are to keep sitting
				sit(currentChair);
			}
		}
		else { // If they are not sitting
			if (fatigue <= fatigueThreshold) { // If they are not tired
				boolean didAction = false;
				// eat food if it is held and removes fatigue
				if (!didAction) {
					var tempInventory = new ArrayList<Entity>(inventory);
					for (Entity et : tempInventory) {
						if (et instanceof Food) {
							Food f = (Food) et;
							if (fatigue > f.getNutrition()) {
								fatigue = Math.max(0, fatigue - f.getNutrition());
								inventory.remove(et);
								didAction = true;
							}
						}
					}
				}
				// go to food if it's nearby
				if (!didAction) {
					Entity desiredFood = find(e, "Astronaut Ice Cream", 10);
					if (desiredFood != null) {
						goTo(e, desiredFood);
						if (desiredFood.getPos().distance(this.getPos()) == 0) {
							this.inventory.add(desiredFood);
							e.removeFromWorld(desiredFood);
							didAction = true;
						}
						didAction = true;
					}
				}
				// walk randomly if everything else's been done
				if (!didAction) this.randomlyWalk(e);
			}
			else { // If they are tired
				// Go find a chair and sit in it
				Entity desiredChair = find(e, "Chair", 20);
				if(desiredChair != null && desiredChair.getPos().distance(this.getPos()) == 0) { // Runs if already at the chair
					sitting = true;
				}
				else {
					goTo(e, desiredChair); // Moves the Astronaut towards the chair
				}
			}
		}
	}

	private Entity find(Engine e, String entityName, float thresholdDistance) { // This returns an Entity within the required radius of thresholdDistance of the desired type
		ArrayList<Entity> world = e.getWorld();
		for(Entity entity : world) {
			if(entity.getName().equals(entityName) && !entity.getInUse() && entity.getPos().distance(this.getPos()) <= thresholdDistance) {
				return entity;
			}
		}
		return null;
	}


	/*
	The following methods are "desire methods". They are the actions the Astronaut can take, depending on what they want to do.
	In this sense, the tick() method essentially uses a series of decision trees to determine which of these methods to run.
	 */

	private void randomlyWalk(Engine e) {
		Point newPos = new Point(getPos());
		switch (rand.nextInt(4)) {
			case 0:
				newPos.x += 1;
				break;

			case 1:
				newPos.y += 1;
				break;

			case 2:
				newPos.x -= 1;
				break;

			case 3:
				newPos.y -= 1;
				break;
		}
		if (e.floorExists(newPos)) {
			this.getSprite().setPos(newPos);
		}
		fatigue++;
	}

	private void goTo(Engine e, Entity other) {
		Point newPos = new Point(getPos());
		if(other == null)
			return;
		if(this.getPos().x > other.getPos().x) {
			newPos.x -= 1;
			if(e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.x = this.getPos().x;
		}
		if(this.getPos().x < other.getPos().x) {
			newPos.x += 1;
			if(e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.x = this.getPos().x;
		}
		if(this.getPos().y > other.getPos().y) {
			newPos.y -= 1;
			if(e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.y = this.getPos().y;
		}
		if(this.getPos().y < other.getPos().y) {
			newPos.y += 1;
			if(e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.y = this.getPos().y;
		}
	}

	public void sit(Entity chair) {
		/*
		if(!chair.getInUse()) {
			chair.setInUse(true);
		}
		*/
		fatigue--;
	}
	
}
