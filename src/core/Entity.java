package core;

class Entity {
	/*
	 * Something that can move around the interact with stuff
	 * Every tick it changes state according to a function
	 */
	
	private String name;
	private Sprite sprite;
	
	public Entity() {
		this.name = "Null Entity";
		this.sprite = null;
	}
	
	public Entity(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
	}
	
	// Get methods
	public String getName() {
		return name;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	// This method is to be overwritten by every child object of Entity
	public void tick() {}
}