package objects;

import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.ImageManager;
import objects.Enemy.EnemyErrorType;
import objects.Entity.Team;
import objects.projectiles.Spike;
import objects.projectiles.Syntax;

public class SyntaxErrorEnemy extends Enemy {
	
	private static final float ShotDelay = 6f;
	
	protected float timeBetweenShots;
	
	public SyntaxErrorEnemy() {
		super(Team.Enemy, 100, EnemyErrorType.SyntaxError);
		
		this.sprite = ImageManager.getImage("green_bug.png");
		
		this.width = 15f;
		this.height = 15f;

		this.collisionBox = Polygon.rectangle(0.35f * width, 0.35f * height);
		this.collisionBox.setCenter(position);
		
		timeBetweenShots = ShotDelay;
	}
	
	@Override
	public void remove() {
		super.remove();
		ExampleState.green += 1;
	}
	
	@Override
	public void update(float deltaTime) {
		Entity player = ExampleState.player;
		
		timeBetweenShots -= deltaTime;
		
		// Set velocity to always be moving towards player
		Vector direction = position.lookAt(player.getPosition()).normalize();
		
		final float velo = 3.f;
		velocity.assign(direction);
		velocity.scaleInplace(velo);
		
		// projectiles are characters (semicolon)
		if (timeBetweenShots < 0) {
			timeBetweenShots = ShotDelay;
			
			// Shoot at player
			final float bulletVelo = 20f;
			new Syntax(this, direction.scale(bulletVelo));
		}
	}
}
