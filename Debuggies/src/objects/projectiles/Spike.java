package objects.projectiles;

import geometry.Polygon;
import geometry.Vector;
import objects.Entity;
import objects.GameObject;

public class Spike extends GameObject {
	
	private Entity origin;
	
	private float damage;
	
	public Spike(Entity origin, Vector velocity) {
		super();
		
		this.origin = origin;
		
		// Assign position and velocity
		this.position.assign(origin.getPosition());
		this.velocity.assign(velocity);
				
		// Projectiles feel no friction
		this.ignoreFriction = true;		
		
		// Set Collision Box
		collisionBox = Polygon.circle(3.5f, 8);
		collisionBox.setCenter(this.position);
		
		// Set position and velocity
		damage = 15f;
	}
	
	@Override
	public void onCollide(GameObject o) {
		if (o instanceof Entity) {
			// Deal damage if on different teams 
			Entity e = (Entity) o;
			
			if (origin.getTeam() != e.getTeam()) {
				e.takeDamage(damage);
				// Destroy this projectile
				this.remove();
			}
			
		}
	}
}