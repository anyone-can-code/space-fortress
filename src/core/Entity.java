// Entity.java

package core;

import java.awt.Point;
import java.util.ArrayList;

public class Entity {
	/*
	 * Something that can move around the interact with stuff
	 * Every tick it changes state according to a function
	 */
	
	private String name;
	private Sprite sprite;
	private boolean busy;
	
	public Entity() {
		this.name = "Null Entity";
		this.sprite = null;
		this.busy = false;

	}
	
	public Entity(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
		this.busy = false;
	}
	
	// Get methods
	public String getName() {
		return name;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Point getPos() {
		return sprite.getPos();
	}

	public boolean getBusy() { return busy; }

	public void setBusy(boolean newVal) { this.busy = newVal; }

	public String toString() {
		return this.getName();
	}
	
	// This method is to be overwritten by every child object of Entity
	public void tick(Engine e) {}


	protected Entity find(Engine e, Class<?> c, float thresholdDistance) { // This returns an Entity within the
																				// required radius of thresholdDistance
																				// of the desired type
		ArrayList<Entity> world = e.getWorld();
		for (Entity entity : world) {
			if (entity.getClass().isAssignableFrom(c) && !entity.getBusy()
					&& entity.getPos().distance(this.getPos()) <= thresholdDistance) {
				return entity;
			}
		}
		return null;
	}

	protected void goTo(Engine e, Entity other) {
		Point newPos = new Point(getPos());
		if (other == null)
			return;
		if (this.getPos().x > other.getPos().x) {
			newPos.x -= 1;
			if (e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.x = this.getPos().x;
		}
		if (this.getPos().x < other.getPos().x) {
			newPos.x += 1;
			if (e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.x = this.getPos().x;
		}
		if (this.getPos().y > other.getPos().y) {
			newPos.y -= 1;
			if (e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.y = this.getPos().y;
		}
		if (this.getPos().y < other.getPos().y) {
			newPos.y += 1;
			if (e.floorExists(newPos)) {
				this.getSprite().setPos(newPos);
				return;
			}
			newPos.y = this.getPos().y;
		}
	}
}
