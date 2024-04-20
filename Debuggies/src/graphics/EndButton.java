package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.Config;
import core.states.ExampleState;

// Ccreate 
public class EndButton extends Box{

	private static Image exit_button;
	private GameContainer gc;
	
	public EndButton(GameContainer gc, StateBasedGame sbg) {
		super();
		this.gc = gc;
	}

//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
	
		exit_button = ImageManager.getImage("quit.png");
		g.drawImage(exit_button.getScaledCopy((int)(Config.SCREEN_WIDTH * 0.25f), (int)(Config.SCREEN_HEIGHT * 0.25f)), Config.SCREEN_WIDTH * 0.58f, Config.SCREEN_HEIGHT * 0.75f);
	}

		
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		gc.exit();
	}

}

