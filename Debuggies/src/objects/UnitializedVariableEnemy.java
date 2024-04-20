package objects;

import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;

public class UnitializedVariableEnemy extends Enemy {
	public UnitializedVariableEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		// invisible/slightly transparent enemy (regular bullets)
	}
}
