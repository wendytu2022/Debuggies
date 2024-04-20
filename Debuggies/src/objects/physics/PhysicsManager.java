package objects.physics;

import java.util.ArrayList;

import geometry.Vector;
import objects.GameObject;

// Static class that handles physics for the game
public class PhysicsManager {
	// List of objects to perform physics on
	private static ArrayList<GameObject> objects = new ArrayList<>();
	
	// Subscribes object to the physics engine
	public static void AddObject(GameObject o) {
		objects.add(o);
	}
	
	// Updates physics once for all objects
	public static void UpdatePhysics(float deltaTime) {
		for (GameObject o : objects) {
			// Update velocity and position
			Vector position = o.getPosition();
			Vector velocity = o.getVelocity();
			Vector acceleration = o.getAcceleration();
			
			// Dampen velocity
			velocity.scaleInplace(0.85f);
			
			// Update velocity and position
			velocity.offsetInplace(acceleration.scale(deltaTime));
			position.offsetInplace(velocity.scale(deltaTime));
			
			// Reset acceleration
			acceleration.x = 0;
			acceleration.y = 0;
		}
	}
}