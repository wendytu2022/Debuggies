package objects;

import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.ImageManager;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;
import objects.projectiles.Zero;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NullPointerEnemy extends Enemy {
	protected float timeBetweenShots = 3;

	private static final int numShots = 2;
	private static final float ShotDelay = 0.25f;

	public NullPointerEnemy() {
		super(Team.Enemy, 100, EnemyErrorType.NullPointer);

		this.sprite = ImageManager.getImage("red_bug.png");

		this.width = 22.5f;
		this.height = 22.5f;

		this.collisionBox = Polygon.rectangle(0.35f * width, 0.35f * height);
		this.collisionBox.setCenter(position);

		timeBetweenShots = ShotDelay;
	}

	@Override
	public void update(float deltaTime) {
		Entity player = ExampleState.player;

		timeBetweenShots -= deltaTime;

		// Set velocity to always be moving towards player
		Vector direction = position.lookAt(player.getPosition()).normalize();

		final float velo = 0.75f;
		velocity.assign(direction);
		velocity.scaleInplace(velo);

		if (timeBetweenShots < 0) {
			timeBetweenShots = ShotDelay;

			final int max = 10;
			final int min = -10;
			final int range = max - min + 1;

			for (int i = 0; i < numShots; i++) {
				float randX = (float) (Math.random() * range) + min;
				float randY = (float) (Math.random() * range) + min;

				new Zero(this, (new Vector(randX, randY)).scale(3.5f));
			}

		}
		// attack at one point of the screen
	}
}
