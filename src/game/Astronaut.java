// Astronaut.java

// TO-DO: USE NEW ACTIONS AND ACTIONQUEUE IN ASTRONAUT'S TICK

package game;

import core.Engine;
import core.Entity;
import core.Action;
import core.ActionQueue;
import core.Sprite;

import game.food.Food;
import game.furniture.Chair;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class Astronaut extends Entity {

	/*
	 * /// Developer note Lines 55 and 157-159 are currently throwing null pointer
	 * exceptions, likely because the chair and currentChair objects are not being
	 * properly initialized. This inhibits the functionality of chairs having an
	 * "occupied" mode, so it has been commented out for now. However, it needs to
	 * repaired somewhat soon, or else multiple astronauts will use the same chair
	 * simultaneously. I am currently working on the issue, but suggestions are
	 * appreciated. However, this isn't *that* big of an issue, so it can just sort
	 * of stay in the background for now while more pressing things are addressed. -
	 * Jack
	 */

	private Random rand;
	private int fatigue;
	private int fatigueThreshold;
	private ActionQueue actionQueue;
	private ArrayList<Entity> inventory;

	public Astronaut(Point p) {
		super("Astronaut Abe", new Sprite("@", Color.WHITE, p));

		rand = new Random();
		fatigue = 0;
		fatigueThreshold = 250;
		inventory = new ArrayList<Entity>();
		actionQueue = new ActionQueue();
		actionQueue.setDefaultAction(new RandomlyWalk());
	}

	public Astronaut() {
		this(new Point(0, 0));
	}

	public void tick(Engine e) {
		// This version of the Tick function is being used purely for testing purposes
		// NOTE! default action is RandomlyWalk

		if (fatigue >= fatigueThreshold)
			actionQueue.addToFront(new Rest());

		actionQueue.tick(e);
	}


	////////////////////////////////////////////////////// ACTION CLASSES
	// These are actions that the Astronaut can take, to be used with the ActionQueue
	// It's worth noting that these classes can access Astronaut's private variables

	// walk randomly
	private class RandomlyWalk implements Action {

		@Override
		public boolean perform(Engine eng) {

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

			if (eng.floorExists(newPos)) {
				getSprite().setPos(newPos);
			}

			fatigue += 1;

			return true;
		}
	}

	// find a chair and sit in it
	private class Rest implements Action {

		@Override
		public boolean perform(Engine eng) {
			Chair desiredChair = (Chair) find(eng, "Chair", fatigue);

			if (fatigue < fatigueThreshold)
				return true;

			if (desiredChair != null &&  !desiredChair.isOccupied() &&
				desiredChair.getPos().distance(getPos()) == 0) { // Runs if already at the chair

				fatigue -= 5;

			} else if (desiredChair != null) {
				goTo(eng, desiredChair); // Moves the Astronaut towards the chair
			} else {
				fatigue -= 1;
			}

			return false;

		}

	}


}
