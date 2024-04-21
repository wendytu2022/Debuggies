package objects.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import core.Config;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import objects.Entity;
import objects.Entity.Team;
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
		collisionBox = Polygon.circle(0.75f, 5);
		collisionBox.setCenter(this.position);
		
		width = 1.5f;
		height = 1.5f;
		
		// No sprite
		sprite = null;
		
		// Set damage
		damage = 10f;
		
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
	
	// Render
	public void render(Graphics g) {
		Vector screen = GraphicsManager.WorldToScreen(position);
		
		if (origin.getTeam() == Team.Ally)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fill(new Circle(screen.x, screen.y, 0.75f * Config.PIXELS_PER_UNIT));
		
		// collisionBox.render(g, Color.white);
		
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