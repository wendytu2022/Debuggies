package graphics;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

import core.Config;
import core.states.ExampleState;

public class Prompt extends Box{
		
	/* Setting private variables */
	private GameContainer gc;
	private TrueTypeFont font;
	
	
	/* --- Command Line Generation --- */
	 public static TrueTypeFont getNewFont(String fontName, int fontSize) throws SlickException {
	        // Load the TrueTypeFont from the given font file with specified size
	        Font awtFont = new Font(fontName, Font.PLAIN, fontSize);	
	        return new TrueTypeFont(awtFont, true);
	 }	
	
	/* --- Constructor --- */
	public Prompt(GameContainer gc, Input user_input) throws SlickException {
		super();
		this.gc = gc;
		this.font = getNewFont("Helvetica", 38);
	}
	
	public void initialize() {
//		line = new TextField(gc, font, 50, 400, (int) (0.95f * Config.SCREEN_WIDTH), (int) (0.05f * Config.SCREEN_HEIGHT));
//		line.setFocus(false);
	}
	
	public void setFocus(boolean b) {
//		line.setFocus(b);
	}
	
//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {		
		Color semiTransparent = new Color(0f, 0f, 0f, 0.5f);
		g.setColor(semiTransparent);
		g.fillRect(((int) (Config.SCREEN_WIDTH - (0.4f)*Config.SCREEN_WIDTH)),
				((int) (Config.SCREEN_HEIGHT - (0.8f)*Config.SCREEN_HEIGHT)),
				((int) (Config.SCREEN_WIDTH - (0.65f)*Config.SCREEN_WIDTH)),
				((int) (Config.SCREEN_HEIGHT - (0.3f)*Config.SCREEN_HEIGHT)));
		
		font.drawString(((int) (Config.SCREEN_WIDTH - (0.39f)*Config.SCREEN_WIDTH)), (int) (Config.SCREEN_HEIGHT - (0.25f)*Config.SCREEN_HEIGHT), "Problem: ", Color.green);
		font.drawString(((int) (Config.SCREEN_WIDTH - (0.39f)*Config.SCREEN_WIDTH)), (int) (Config.SCREEN_HEIGHT - (0.17f)*Config.SCREEN_HEIGHT), "Line: ", Color.green);

		
//		g.drawRect(((int) (Config.SCREEN_WIDTH - (0.4f)*Config.SCREEN_WIDTH)),
//				((int) (Config.SCREEN_HEIGHT - (0.8f)*Config.SCREEN_HEIGHT)),
//				((int) (Config.SCREEN_WIDTH - (0.65f)*Config.SCREEN_WIDTH)),
//				((int) (Config.SCREEN_HEIGHT - (0.3f)*Config.SCREEN_HEIGHT)));
	}

	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(int key) {
	}
//
//	@Override
//	protected void keyPress(int key) {		
//	}

	
		
}