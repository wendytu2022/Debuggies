package objects;

import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

import geometry.Vector;

public class StackOverflowEnemy extends Enemy {
	// shorter time between shots to shoot a ton of bullets
	protected int timeBetweenShots = 1;
	public StackOverflowEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	// shoot a booty ton of bullets
	@Override
	public void update(float deltaTime) {
		if (deltaTime > timeBetweenShots) {
			int max = 10;
			int min = 1;
			int range = max - min + 1;
			float randX = (float)(Math.random() * range) + min;
			float randY = (float)(Math.random() * range) + min;
			
			Spike bullet = new Spike(this, new Vector(randX, randY));
		}
		
		
	}
}
