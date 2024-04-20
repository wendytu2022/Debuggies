package objects.physics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import core.Config;
import geometry.Projection;
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
	
	// Unsubscribes object from the physics engine
	public static void RemoveObject(GameObject o) {
		objects.remove(o);
	}
	
	// Updates physics once for all objects
	public static void UpdatePhysics(float deltaTime) {
		// Remove all marked for removal
		objects.removeIf(x -> x.removalMarked());
		
		// Update object positions and velocities
		for (GameObject o : objects) {
			// Update velocity and position
			Vector position = o.getPosition();
			Vector velocity = o.getVelocity();
			Vector acceleration = o.getAcceleration();
			
			// Update velocity and position
			velocity.offsetInplace(acceleration.scale(deltaTime));
			position.offsetInplace(velocity.scale(deltaTime));
			
			// Dampen velocity
			if (!o.isFrictionless())
				velocity.scaleInplace(Config.FRICTION);
		}
		
		// Check for collision, and call callback function on collision
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				
				if (o1.collidesWith(o2)) {
					o1.onCollide(o2);
					o2.onCollide(o1);
				}
			}
			
		}
	}
}