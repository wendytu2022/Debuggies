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
public class StartButton extends Box{

	private static Image start_button;
	private GameContainer gc;
	private StateBasedGame sbg;
	
	public StartButton(GameContainer gc, StateBasedGame sbg) {
		super();
		this.gc = gc;
		this.sbg = sbg;
	}

//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
	
		start_button = ImageManager.getImage("green_bug.png");
		g.drawImage(start_button.getScaledCopy((int)(Config.SCREEN_WIDTH * 0.25f), (int)(Config.SCREEN_HEIGHT * 0.25f)), Config.SCREEN_WIDTH * 0.15f, Config.SCREEN_HEIGHT * 0.75f);
	}

		
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		sbg.enterState(0);
	}

}

