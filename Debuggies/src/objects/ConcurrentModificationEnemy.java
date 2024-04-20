package objects;

import core.states.ExampleState;
import geometry.Vector;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;
import objects.projectiles.Splitter;

public class ConcurrentModificationEnemy extends Enemy {
	private static final float ShotDelay = 3f;
	protected float timeBetweenShots = 3;
	
	public ConcurrentModificationEnemy() {
		super(Team.Enemy, 200, EnemyErrorType.ConcurrentModification);
	}
	
	@Override
	public void update(float deltaTime) {
		Entity player = ExampleState.player;
		
		timeBetweenShots -= deltaTime;
		
		// Set velocity to always be moving towards player
		Vector direction = position.lookAt(player.getPosition()).normalize();
		
		final float velo = 3.f;
		velocity.assign(direction);
		velocity.scaleInplace(velo);
		
		// projectiles are characters (curly brace, semicolon)
		if (timeBetweenShots < 0) {
			timeBetweenShots = ShotDelay;
			
			// Shoot at player
			final float bulletVelo = 15f;
			new Splitter(this, direction.scale(bulletVelo));
		}
	}
}
