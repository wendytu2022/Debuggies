package objects;


import java.util.concurrent.TimeUnit;

import geometry.Vector;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

public class IndexOutOfBoundsEnemy extends Enemy {
	protected int timeBetweenShots = 3;
	public IndexOutOfBoundsEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	// Teleport, attack, teleport, attack
	@Override
	public void update(float deltaTime) {
		if (deltaTime > timeBetweenShots) {
			// teleport to a random location
			int max = 10;
			int min = 1;
			int range = max - min + 1;
			float randX1 = (float)(Math.random() * range) + min;
			float randY1 = (float)(Math.random() * range) + min;
			float randX2 = (float)(Math.random() * range) + min;
			float randY2 = (float)(Math.random() * range) + min;
			position.assign(new Vector(randX1, randY1));
			
			// attack
			Spike bullet = new Spike(this, new Vector(randX2, randY2));
		}
	}
}
