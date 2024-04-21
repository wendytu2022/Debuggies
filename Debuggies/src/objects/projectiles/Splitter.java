package objects.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import core.Config;
import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import objects.Entity;
import objects.Entity.Team;
import objects.GameObject;

public class Splitter extends GameObject {
	
	private Entity origin;
	
	private float damage;
	private float timeElapsed;
	
	public Splitter(Entity origin, Vector velocity) {
		super();
		
		this.origin = origin;
		
		// Assign position and velocity
		this.position.assign(origin.getPosition());
		this.velocity.assign(velocity);
				
		// Projectiles feel no friction
		this.ignoreFriction = true;		
		
		// Set Collision Box
		collisionBox = Polygon.circle(2f, 5);
		collisionBox.setCenter(this.position);
		
		width = 4f;
		height = 4f;
		
		// No sprite
		sprite = null;
		
		// Set damage
		damage = 25f;
		
		timeElapsed = 0f;
	}
	
	// Updates the GameObject
	public void update(float deltaTime) { 
		// Autokill after 10 seconds
		timeElapsed += deltaTime;
		
		// Split after 3 seconds
		if (timeElapsed > 1.5f) {
			final int numSplits = 5;
			final float radianOffset = (float) (Math.PI / 2 / numSplits);
			
			for (int i = 0; i < numSplits; i++) {
				Vector direction = position.lookAt(ExampleState.player.getPosition()).normalize();
				direction.rotateInplace((float) (radianOffset * i - Math.PI / 4));
				
				Spike s = new Spike(origin, direction.scale(45f));
				s.getPosition().assign(position);
			}
			
			
			// Destroy this object
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