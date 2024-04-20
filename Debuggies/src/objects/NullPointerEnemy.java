package objects;

import objects.Enemy.EnemyErrorType;

public class NullPointerEnemy extends Enemy {
	public NullPointerEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update() {
		// attack at one point of the screen
	}
}
