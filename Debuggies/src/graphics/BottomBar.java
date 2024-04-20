package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// Ccreate 
public class BottomBar extends Box{

	private static Image img = null;
	private GameContainer gc;
	
	public BottomBar(GameContainer gc) {
		super();
		this.gc = gc;
	}

//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
	
		try {
			img = new Image("res/bottombar.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		g.drawImage(img.getScaledCopy(1920, 30), 0, 1050);
	}

		
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
	}

	@Override
	protected void keyPress(int key) {
	}

}

