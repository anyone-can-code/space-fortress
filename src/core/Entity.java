// Entity.java

package core;

import java.awt.Point;

public class Entity {
	/*
	 * Something that can move around the interact with stuff
	 * Every tick it changes state according to a function
	 */
	
	private String name;
	private Sprite sprite;
	private boolean inUse;
	
	public Entity() {
		this.name = "Null Entity";
		this.sprite = null;
		inUse = false;

	}
	
	public Entity(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
		this.inUse = false;
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

	public boolean getInUse() { return inUse; }

	public void setInUse(boolean newVal) { this.inUse = newVal; }

	public String toString() {
		return this.getName();
	}
	
	// This method is to be overwritten by every child object of Entity
	public void tick(Engine e) {}
}
