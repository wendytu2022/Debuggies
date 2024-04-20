package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import geometry.Polygon;
import geometry.Vector;
import objects.physics.PhysicsManager;

// Represents a generic gameobject
public class GameObject {
	// Physics Variables
	protected Vector position;
	protected Vector velocity;
	protected Vector acceleration;
	
	// For Collision Handling
	protected Polygon collisionBox;
	
	// For Rendering
	protected Image sprite;
	
	public GameObject() {
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		
		sprite = null;
		collisionBox = null;
		
		// Subscribe to physics
		PhysicsManager.AddObject(this);
	}
	
	// Setter Methods
	public void setAcceleration(float x, float y) {
		acceleration.x = x;
		acceleration.y = y;
	}
	
	// Accessor Methods
	public Vector getPosition() {
		return position;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public Vector getAcceleration() {
		return acceleration;
	}
	
	// Checks for collision with another gameobject
	public boolean collidesWith(GameObject o) {
		if (collisionBox == null || o.collisionBox == null)
			return false;
		else
			return collisionBox.collidesWith(o.collisionBox);
	}
	
	// Renders the GameObject
	public void render(Graphics g) {}
	
	// Updates the GameObject
	public void update() { }
	
	// Collision Callback
	public void onCollide(GameObject o) {}
	
}