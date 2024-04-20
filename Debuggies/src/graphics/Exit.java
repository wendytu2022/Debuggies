package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import core.states.ExampleState;

// Ccreate 
public class Exit extends Box{

	private static Image img = null;
	private GameContainer gc;
	
	public Exit(GameContainer gc) {
		super();
		this.gc = gc;
	}

//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
	
		try {
			img = new Image("res/exit_icon.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		g.drawImage(img,0,0);
	}

		
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		System.exit(0);;
	}

	@Override
	protected void keyPress(int key) {
		  if (key == Input.KEY_ESCAPE) {
			  System.exit(0);
		  }
	}

}

