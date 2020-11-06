package core;
import java.awt.Point;

class Engine {
	/*
	 * Runs the gameloop
	 * Holds world data
	 * Sends and requests data to/from view
	 * Holds ArrayList of everything
	 * Sends list of things coordinates and display information to view
	 * Everything gets information from here
	 */
	
	private Sprite[][][] map; // This holds everything in the world
	
	public Engine() { // Default constructor is likely the only one needed
		map = new Sprite[64][64][64];
	}
	
	public Sprite[][][] getMap() { // Returns the map; to be used by View primarily
		return this.map;
	}
	
	public void addToMap(Sprite obj, int x, int y) { // Adds Sprite obj to the map at point (x, y)
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) { // Runs if an object is being added outside of the map
			throw new IllegalStateException("An object is being added outside of the map at point (" + x + ", " + y + ")");
		}
		int firstEmptySpot = 0; // Stores the depth of the first empty spot on grid square
		for(int i = 0; i < map[0][0].length; i++) { // Find the first empty spot on that grid square
			if(map[x][y][i] == null) {
				firstEmptySpot = i;
				break;
			}
		}
		if(firstEmptySpot >= 64) { // Runs if more than 64 objects are being added to the point
			throw new IllegalStateException("Too many objects are at point (" + x + ", " + y + ")");
		}
		map[x][y][firstEmptySpot] = obj; // Sets that spot to Sprite obj
		obj.setPos(new Point(x, y));
	}
	
	public void removeFromMap(Sprite obj, int x, int y) { // Removes Sprite obj from the map at point (x, y)
		for(int i = 0; i < map[0][0].length; i++) {
			if(map[0][0][i].equals(obj)) {
				removePoint(x, y, i);
			}
		}
	}
	
	private void removePoint(int x, int y, int depth) { // Helper method to remove something at a point in the map and resolve anything below it
		for(int i = depth; i < map[0][0].length-1; i++) {
			map[x][y][i] = map[x][y][i+1];
		}
	}
	
}