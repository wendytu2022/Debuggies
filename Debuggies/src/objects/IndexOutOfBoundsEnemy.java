package objects;


import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Config;
import core.states.ExampleState;
import geometry.Vector;
import graphics.GraphicsManager;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

public class IndexOutOfBoundsEnemy extends Enemy {
	
	protected float transparency = 1f;
	
	protected float teleportTimer = 5.f;
	protected float timeBetweenShots = 3.f;
	
	public IndexOutOfBoundsEnemy() {
		super(Team.Enemy, 100, EnemyErrorType.IndexOutOfBounds);
		
		
	}
	
	// Teleport, attack, teleport, attack
	@Override
	public void update(float deltaTime) {
		// teleport, attack, teleport, attack
		teleportTimer -= deltaTime;
		timeBetweenShots -= deltaTime;
		
		if (teleportTimer < 0) {
			// Teleport
			teleportTimer = 5f;
			
			int max = 10;
			int min = 1;
			int range = max - min + 1;
			float randX1 = (float)(Math.random() * range) + min;
			float randY1 = (float)(Math.random() * range) + min;
			
			position.x = randX1;
			position.y = randY1;
		}
		
		if (teleportTimer < 1.f) {
			transparency = Math.max(0.f, transparency - 1 / 60f);
		}
		else {
			transparency = Math.min(1.f, transparency + 1 / 60f);
		}
		
		if (timeBetweenShots < 0) {
			timeBetweenShots = 3.f;
			
			// Shoot bullet
			Vector direction = position.lookAt(ExampleState.player.getPosition()).normalize();
						
			final float bulletVelo = 40f;
			new Spike(this, direction.scale(bulletVelo));
		}
		
		
	}
	
	// Renders the GameObject
	@Override
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
			sprite.setAlpha(transparency);
			
			// Find top-left corner of object
			Vector topleft = position.offset(-width / 2, height / 2);
			
			Vector screen = GraphicsManager.WorldToScreen(topleft);
			sprite.draw(screen.x, screen.y);
		}
			
	}
}
