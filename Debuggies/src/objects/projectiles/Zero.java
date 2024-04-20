package objects.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import core.Config;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import graphics.ImageManager;
import objects.Entity;
import objects.Entity.Team;
import objects.GameObject;

public class Zero extends GameObject {
	
	private Entity origin;
	
	private float damage;
	private float timeElapsed;
	
	public Zero(Entity origin, Vector velocity) {
		super();
		
		this.origin = origin;
		
		// Assign position and velocity
		this.position.assign(origin.getPosition());
		this.velocity.assign(velocity);
				
		// Projectiles feel no friction
		this.ignoreFriction = true;		
		
		// Set Collision Box
		collisionBox = Polygon.circle(1.f, 5);
		collisionBox.setCenter(this.position);
		
		width = 3f;
		height = 3f;

		sprite = ImageManager.getImage("zero.png");
		
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
		
		 this.rotation += 0.03;
	}
	
	// Render
	public void render(Graphics g) {
		Vector screen = GraphicsManager.WorldToScreen(position);
		
		collisionBox.render(g, Color.white);
		super.render(g);
		
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