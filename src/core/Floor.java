// floor.java
package core;

import java.awt.Color;
import java.awt.Point;

public class Floor {
	
	private Sprite sprite;
	
	public Floor(Sprite s) {
		this.sprite = s;
	}
	
	public Floor(Point p) {
		this.sprite = new Sprite("#", Color.WHITE, p);
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public Point getPos() {
		return this.sprite.getPos();
	}
	
}
