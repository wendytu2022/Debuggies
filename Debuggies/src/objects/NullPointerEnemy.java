package objects;

import core.states.ExampleState;
import objects.Enemy.EnemyErrorType;
import objects.projectiles.Spike;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NullPointerEnemy extends Enemy {
	protected int timeBetweenShots = 3;
	public NullPointerEnemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health, enemyErrorType);
	}
	
	@Override
	public void update(float deltaTime) {
		if (deltaTime > timeBetweenShots) {
			// attack directly at player all the time
			Spike bullet = new Spike(this, position.lookAt(ExampleState.player.getPosition()));
		}
		// attack at one point of the screen
	}
}
