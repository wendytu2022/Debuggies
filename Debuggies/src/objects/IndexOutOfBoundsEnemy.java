package objects;

import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;

public class IndexOutOfBoundsEnemy extends Enemy {
	public IndexOutOfBoundsEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		// teleport, attack, teleport, attack
	}
}
