package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// Ccreate 
public class Lefttool extends Box{

	private static Image img = null;
	private GameContainer gc;
	
	public Lefttool(GameContainer gc) {
		super();
		this.gc = gc;
	}

//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
	
		try {
			img = new Image("res/lefttool.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		g.drawImage(img.getScaledCopy(273, 1200), 0, 60);
	}

		
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
	}


}

