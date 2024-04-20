package objects;

import core.states.ExampleState;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;

public class SyntaxErrorEnemy extends Enemy {
	protected int timeBetweenShots = 3;
	public SyntaxErrorEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		// projectiles are characters (curly brace, semicolon)
		if (deltaTime > timeBetweenShots) {
			
		}
	}
}
