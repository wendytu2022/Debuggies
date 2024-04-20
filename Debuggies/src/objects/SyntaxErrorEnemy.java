package objects;

import core.states.ExampleState;
import geometry.Vector;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

public class SyntaxErrorEnemy extends Enemy {
	
	private static final float ShotDelay = 3f;
	
	protected float timeBetweenShots;
	
	public SyntaxErrorEnemy() {
		super(Team.Enemy, 100, EnemyErrorType.SyntaxError);
		
		timeBetweenShots = ShotDelay;
	}
	
	@Override
	public void update(float deltaTime) {
		Entity player = ExampleState.player;
		
		timeBetweenShots -= deltaTime;
		
		// Set velocity to always be moving towards player
		Vector direction = position.lookAt(player.getPosition()).normalize();
		
		final float velo = 5.f;
		velocity.assign(direction);
		velocity.scaleInplace(velo);
		
		// projectiles are characters (curly brace, semicolon)
		if (timeBetweenShots < 0) {
			timeBetweenShots = ShotDelay;
			
			// Shoot at player
			final float bulletVelo = 40f;
			new Spike(this, direction.scale(bulletVelo));
		}
	}
}
