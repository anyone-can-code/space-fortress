// Sprite.java
package core;

import java.awt.Color;
import java.awt.Point;

public class Sprite {
	
	private String symbol;
	private Color color;
	private Point pos;
	
	public Sprite(String s, Color c, Point p) {
		
		if (s.equals("")) this.symbol = " ";
		else this.symbol= s.substring(0, 1);

		this.color = c;

		this.pos = p;
		
	}

	public Color getColor() {
		return this.color;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public Point getPos() {
		return this.pos;
	}
	
	public void setPos(Point newPos) {
		this.pos = newPos;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Sprite)) {
			return false;
		}
		if(this.symbol == ((Sprite)other).getSymbol() && this.color == ((Sprite)other).getColor()) {
			return true;
		}
		return false;
	}

}
