package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Config;
import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import graphics.ImageManager;

public class Player extends Entity {
	
	private static final float FrameTime = 0.1f;
	
	private final static int MaxHealth = 100;
	private final static float BarWidth = 7.5f;
	private final static float BarHeight = 1.5f;
	
	private float attacking;
	private float elapsedTime;
	
	public Player() {
		super(Team.Ally, MaxHealth);
		
		// Preload all the images
		ImageManager.getImage("duggieonline.png");
		ImageManager.getImage("duggieleft.png");
		ImageManager.getImage("duggiefront.png");
		ImageManager.getImage("duggieright.png");
		
		this.ignoreFriction = false;
		
		this.sprite = ImageManager.getImage("duggiefront.png");
		this.width = 11.5f;
		this.height = 11.5f; 
		
		collisionBox = Polygon.rectangle(0.3f * width, 0.85f * height);
		collisionBox.setCenter(position);	
		

		elapsedTime = 0;
	}
	
	// Marks the player to render the "attack" animation
	public void markAttack(float time) {
		attacking = time;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if (attacking > 0)
			attacking -= deltaTime;

		elapsedTime += deltaTime;
	}
	
	@Override
	public void render(Graphics g) {
		// Animation Update
		if (ExampleState.aiming == true) {
			sprite = ImageManager.getImage("duggieonline.png");
		} 
		else if (attacking > 0) {
			sprite = ImageManager.getImage("duggiejump.png");
		}
		else if (velocity.x == 0) {
			sprite = ImageManager.getImage("duggiefront.png");
		}
		else {
			if (velocity.x < 0) {
				if ((int) (elapsedTime / FrameTime) % 2 == 0) {
					sprite = ImageManager.getImage("duggieleft.png");
				}
				else {
					sprite = ImageManager.getImage("duggiefront.png");
				}
			}
				
			else {
				if ((int) (elapsedTime / FrameTime) % 2 == 0) {
					sprite = ImageManager.getImage("duggieright.png");
				}
				else {
					sprite = ImageManager.getImage("duggiefront.png");
				}
			}
		}
		
		// Render Everything
		super.render(g);
		
		// Render Player Health
		Vector center = position.offset(0, -height / 2 - BarHeight / 2 - 1f);
		Vector topLeft = GraphicsManager.WorldToScreen(center.offset(-BarWidth / 2, BarHeight / 2));
		
		g.setColor(Color.red);
		g.fillRect(topLeft.x, topLeft.y, BarWidth * Config.PIXELS_PER_UNIT, BarHeight * Config.PIXELS_PER_UNIT);
		g.setColor(Color.green);
		g.fillRect(topLeft.x, topLeft.y, BarWidth * health / MaxHealth * Config.PIXELS_PER_UNIT, BarHeight* Config.PIXELS_PER_UNIT);
		
	}
	
	
}