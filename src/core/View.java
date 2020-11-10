// View.java

package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class View extends JPanel {

	// 3d array where first 2 ds are row and column, 3ed d is array of all Sprites in a location
	// 64x64x64
	private Sprite[][][] world;

	public View() {

	}

	public void update(Sprite[][][] updatedWorld) {
		world = updatedWorld;
	}
	
	public void paint(Graphics gBad) {
			
		//Graphics2D Object
		Graphics2D g = (Graphics2D) gBad;

		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		// widest character plus padding
		int spriteBoxSize = g.getFontMetrics().stringWidth("M");
		
		// iterate through the three dimensions of world
		for (int r = 0; r < 64; r++) {
			for (int c = 0; c < 64; c++) {
				for (int d = 0; d < 64; d++) {
					
					Sprite spr = world[r][c][d];
					
					if (!(spr == null)) {
						g.setColor(spr.getColor());
						g.drawString(spr.getSymbol(), 20 + (spriteBoxSize * c), 20 + (spriteBoxSize * r));
					}
				}
			}
		}

	}
	
}
