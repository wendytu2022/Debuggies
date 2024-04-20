package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Config;
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
	protected float width;
	protected float height;
	
	public GameObject() {
		ignoreFriction = false;
		
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		
		sprite = ImageManager.getImage("exit_icon.png");
		collisionBox = Polygon.circle(5, 5 + (int) (Math.random() * 3));;
		collisionBox.setCenter(this.position);
		
		width = 5f;
		height = 5f;
		
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
		sprite.rotate(radians);
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
	public void render(Graphics g) {
		// Render the collision box
		collisionBox.render(g, Color.white);
		
		// Render the sprite
		if (sprite != null) {
			// Rescale sprite
			sprite = sprite.getScaledCopy((int) (width * Config.PIXELS_PER_UNIT), (int) (height * Config.PIXELS_PER_UNIT));
			
			Vector screen = GraphicsManager.WorldToScreen(position);
			sprite.draw(screen.x, screen.y);
		}
			
	}
	
	// Updates the GameObject
	public void update() { }
	
	// Collision Callback
	public void onCollide(GameObject o) { }
	
	// Deletes the GameObject
	public void remove() {
		PhysicsManager.RemoveObject(this);
	}
	
}