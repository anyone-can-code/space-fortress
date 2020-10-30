// View.java

package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class View extends JPanel {

	// 3d array where first 2 ds are row and column, 3ed d is array of all Sprites in a location
	private Sprite[][][] world;

	public View() {
		// should initialize a test world here
		setBackground(Color.RED);
	}

	public void update(Sprite[][][] updatedWorld) {
		world = updatedWorld;
	}
	
	public void paint(Graphics gBad) {
			
		//Graphics2D Object
		Graphics2D g = (Graphics2D) gBad;

		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, getWidth(), getHeight());

		g.drawRect(10, 10, 30, 30);

	}
	
}
