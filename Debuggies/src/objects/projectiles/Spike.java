package objects.projectiles;

import geometry.Polygon;
import geometry.Vector;
import objects.Entity;
import objects.GameObject;

public class Spike extends GameObject {
	
	private Entity origin;
	
	private float damage;
	private float timeElapsed;
	
	public Spike(Entity origin, Vector velocity) {
		super();
		
		this.origin = origin;
		
		// Assign position and velocity
		this.position.assign(origin.getPosition());
		this.velocity.assign(velocity);
				
		// Projectiles feel no friction
		this.ignoreFriction = true;		
		
		// Set Collision Box
		collisionBox = Polygon.circle(1.5f, 5);
		collisionBox.setCenter(this.position);
		
		width = 1.5f;
		height = 1.5f;
		
		// Set damage
		damage = 15f;
		
		timeElapsed = 0f;
	}
	
	// Updates the GameObject
	public void update(float deltaTime) { 
		// Autokill after 10 seconds
		timeElapsed += deltaTime;
		
		if (timeElapsed > 10) {
			this.remove();
		}
	}
		
	@Override
	public void onCollide(GameObject o) {
		if (o instanceof Entity) {
			// Deal damage if on different teams 
			Entity e = (Entity) o;
			
			if (origin.getTeam() != e.getTeam()) {
				// Deal Damage
				e.takeDamage(damage);
				
				// Perform Minor Knockback
				e.getVelocity().offsetInplace(velocity);;
				
				// Destroy this projectile
				this.remove();
			}
			
		}
	}
}