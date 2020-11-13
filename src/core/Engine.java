// Engine.java

package core;

import java.awt.Point;
import java.util.ArrayList;

public class Engine {
	
	/*
	 * Runs the gameloop
	 * Holds world data
	 * Sends and requests data to/from view
	 * Holds ArrayList of everything
	 * Sends list of things coordinates and display information to view
	 * Everything gets information from here
	 */
	 
	
	// stuff for simulation
	boolean running = true;
	private ArrayList<Entity> world;
	
	// stuff for View
	private Sprite[][][] map;
	private View gameView;
	
	//////////////////////////////////////////////////////////////////// instance variables above, methods below.
	
	// Default constructor
	public Engine() {
		map = new Sprite[64][64][64];
	}
	
	// update the world as fast as possible
	public void run() {
		while (true) {
			if (running) {
				tick();
			}
		}
	}
	
	// updates the world
	public void tick() {
		for (Entity e : world) {
			e.tick();
		}
	}
	
	// returns an ArrayList of the entities at a given position
	public ArrayList<Entity> getEntitiesAtPos(Point p) {
		
		ArrayList<Entity> relevantEntities = new ArrayList<Entity>();
		for (Entity e : world) {
			if (e.getPos() == p) {
				relevantEntities.add(e);
			}
		}
		
		return relevantEntities;
	}
	
	// adds an entity to the world
	public void addToWorld(Entity toAdd) {
		world.add(toAdd);
	}
	
	// removes specified entity from the world
	public void removeFromWorld(Entity toDie) {
		
		for (Entity e : world) {
			if (toDie == e) {
				world.remove(e);
				break;
			}
		}
	}
			
	//////////////////////////////////////////////////////////////////// public methods pertaining to the map
	
	// sets a reference to a view, to update it when state changes
	public void setView(View v) {
		gameView = v;
		updateView();
	}
	
	public Sprite[][][] getMap() { // Returns the map; to be used by View primarily
		reassembleMap();
		return this.map;
	}
	
	private void reassembleMap() {
		for (Entity e : world) {
			addToMap(e.getSprite(), e.getPos().x, e.getPos().y);
		}
	}
	
	//////////////////////////////////////////////////////////////////// private methods related to using the map for the view
	
	private void addToMap(Sprite obj, int x, int y) { // Adds Sprite obj to the map at point (x, y)
		if(x < 0 || y < 0 || y >= map.length || x >= map[0].length) { // Runs if an object is being added outside of the map
			throw new IllegalStateException("An object is being added outside of the map at point (" + x + ", " + y + ")");
		}
		int firstEmptySpot = 0; // Stores the depth of the first empty spot on grid square
		for(int i = 0; i < map[0][0].length; i++) { // Find the first empty spot on that grid square
			if(map[y][x][i] == null) {
				firstEmptySpot = i;
				break;
			}
		}
		if(firstEmptySpot >= 64) { // Runs if more than 64 objects are being added to the point
			throw new IllegalStateException("Too many objects are at point (" + x + ", " + y + ")");
		}
		map[y][x][firstEmptySpot] = obj; // Sets that spot to Sprite obj
		obj.setPos(new Point(x, y));
		
		updateView();
	}
	
	private void removeFromMap(Sprite obj, int x, int y) { // Removes Sprite obj from the map at point (x, y)
		for(int i = 0; i < map[0][0].length; i++) {
			if(map[0][0][i].equals(obj)) {
				removePoint(x, y, i);
			}
		}
	}
	
	private void removePoint(int x, int y, int depth) { // Helper method to remove something at a point in the map and resolve anything below it
		for(int i = depth; i < map[0][0].length-1; i++) {
			map[y][x][i] = map[y][x][i+1];
		}
		updateView();
	}
	
	// updates the View to reflect current state
	private void updateView() {
		gameView.update(map);
	}
	
}
