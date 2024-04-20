package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Config;
import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import graphics.ImageManager;
import objects.physics.PhysicsManager;

// Represents a generic gameobject
public class GameObject {
	// Physics Variables
	// If friction dampening should be ignored or not
	protected boolean ignoreFriction; 
	
	protected Vector position;
	protected Vector velocity;
	protected Vector acceleration;
	
	// For Collision Handling
	protected Polygon collisionBox;
	
	// For Rendering
	protected Image sprite;
	
	protected float rotation;
	
	protected float width;
	protected float height;
	
	// Mark for removal
	protected boolean remove;
	
	public GameObject() {
		ignoreFriction = false;
		remove = false;
		
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		
		width = 4.5f;
		height = 4.5f;
		
		sprite = ImageManager.getImage("exit_icon.png");
		
		// Initialize Collision Box
		collisionBox = Polygon.circle(4.5f, 5 + (int) (Math.random() * 3));;
		collisionBox.setCenter(this.position);
		
		// Subscribe to Game
		ExampleState.newObjects.add(this);
		
		// Subscribe to physics
		PhysicsManager.AddObject(this);
		
	}
	
	// Setter Methods
	public void setAcceleration(float x, float y) {
		acceleration.x = x;
		acceleration.y = y;
	}
	
	// Rotate the object a specified amount of radians
	public void rotateInplace(float radians) {
		// Rotate both the collision box and sprite
		collisionBox.rotateInplace(radians);
		
		// Save rotation for sprite
		rotation += radians;
	}
	
	// Accessor Methods
	public boolean isFrictionless() {
		return ignoreFriction;
	}
	
	public Polygon getCollisionBox() {
		return collisionBox;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public Vector getAcceleration() {
		return acceleration;
	}
	
	public boolean removalMarked() {
		return remove;
	}
	
	// Checks for collision with another gameobject
	public boolean collidesWith(GameObject o) {
		if (collisionBox == null || o.collisionBox == null)
			return false;
		else
			return collisionBox.collidesWith(o.collisionBox);
	}
	
	// Renders the GameObject
	public void render(Graphics g) {
		// Render the collision box
		collisionBox.render(g, Color.white);
		
		// Render the sprite
		if (sprite != null) {
			// Rescale sprite
			sprite = sprite.getScaledCopy((int) (width * Config.PIXELS_PER_UNIT), (int) (height * Config.PIXELS_PER_UNIT));
			// Rotate sprite to match our rotation
			sprite.setCenterOfRotation((int) (width * Config.PIXELS_PER_UNIT) / 2, (int) (height * Config.PIXELS_PER_UNIT) / 2);
			sprite.rotate(-rotation * 180f);
			
			// Find top-left corner of object
			Vector topleft = position.offset(-width / 2, height / 2);
			
			Vector screen = GraphicsManager.WorldToScreen(topleft);
			sprite.draw(screen.x, screen.y);
		}
			
	}
	
	// Updates the GameObject
	public void update(float deltaTime) { }
	
	// Collision Callback
	public void onCollide(GameObject o) { }
	
	// Deletes the GameObject
	public void remove() {
		remove = true;
	}
	
}