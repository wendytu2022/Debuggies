package objects;

import core.states.ExampleState;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

public class ConcurrentModificationEnemy extends Enemy {
	protected int timeBetweenShots = 3;
	public ConcurrentModificationEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		// regular shooting, just change color of bullets
		if (deltaTime > timeBetweenShots) {
			
		}
		
	}
}
