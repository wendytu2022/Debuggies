package objects;

import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;

public class StackOverflowEnemy extends Enemy {
	public StackOverflowEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		// shoot a booty ton of bullets
	}
}
