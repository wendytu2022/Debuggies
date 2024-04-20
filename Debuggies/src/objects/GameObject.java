package objects;

import org.newdawn.slick.Image;

import physics.Polygon;
import physics.Vector;

// Represents a generic gameobject
public class GameObject {
	// 
	protected Vector position;
	protected Vector velocity;
	protected Vector acceleration;
	
	protected Polygon collisionBox;
	
	// 
	protected Image sprite;
	
	
	public GameObject() {
		position = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		
		sprite = null;
		collisionBox = null;
	}
	
}