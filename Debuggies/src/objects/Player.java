package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.states.ExampleState;
import geometry.Polygon;
import graphics.ImageManager;

public class Player extends Entity {
	
	private static final float FrameTime = 0.1f;
	
	private float elapsedTime;
	
	public Player() {
		super(Team.Ally, 100);
		
		// Preload all the images
		ImageManager.getImage("duggieonline.png");
		ImageManager.getImage("duggieleft.png");
		ImageManager.getImage("duggiefront.png");
		ImageManager.getImage("duggieright.png");
		
		this.ignoreFriction = false;
		
		this.sprite = ImageManager.getImage("duggiefront.png");
		this.width = 15f;
		this.height = 15f; 
		
		collisionBox = Polygon.rectangle(0.3f * width, 0.85f * height);
		collisionBox.setCenter(position);	
		

		elapsedTime = 0;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		elapsedTime += deltaTime;
	}
	
	@Override
	public void render(Graphics g) {
		// Animation Update
		if (ExampleState.aiming == true) {
			sprite = ImageManager.getImage("duggieonline.png");
		} else if (velocity.x == 0) {
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
				
		super.render(g);
	}
}