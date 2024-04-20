package objects;

import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

import core.states.ExampleState;
import geometry.Vector;

public class StackOverflowEnemy extends Enemy {
	private static final int numShots = 5;
	private static final float ShotDelay = 0.25f;
	
	// shorter time between shots to shoot a ton of bullets
	protected float timeBetweenShots;
	
	public StackOverflowEnemy() {
		super(Team.Enemy, 300, EnemyErrorType.StackOverflow);
		
		timeBetweenShots = ShotDelay;
	}
	
	// shoot a booty ton of bullets
	@Override
	public void update(float deltaTime) {
		Entity player = ExampleState.player;
		
		timeBetweenShots -= deltaTime;
		
		// Set velocity to always be moving towards player
		Vector direction = position.lookAt(player.getPosition()).normalize();
		
		final float velo = 1.5f;
		velocity.assign(direction);
		velocity.scaleInplace(velo);
		
		if (timeBetweenShots < 0) {
			timeBetweenShots = ShotDelay;
			
			final int max = 10;
			final int min = -10;
			final int range = max - min + 1;
			
			for (int i = 0; i < numShots; i++) {
				float randX = (float)(Math.random() * range) + min;
				float randY = (float)(Math.random() * range) + min;

				new Spike(this, new Vector(randX, randY));
			}
			
			
		}
		
		// shoot a booty ton of bullets
	}

}
