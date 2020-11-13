// floor.java
package core;

import java.awt.Color;
import java.awt.Point;

public class Floor {
	
	private Sprite sprite;
	
	public Floor(Sprite s) {
		this.sprite = s;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public Point getPos() {
		return this.sprite.getPos();
	}
	
}
