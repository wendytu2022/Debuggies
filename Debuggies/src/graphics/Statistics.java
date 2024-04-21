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

public class Statistics extends Box{
		
	/* Setting private variables */
	private GameContainer gc;
	private TrueTypeFont font, bigfont;
	private int time;
	private int green;
	private int yellow;
	private int red;
	
	
	/* --- Command Line Generation --- */
	 public static TrueTypeFont getNewFont(String fontName, int fontSize) throws SlickException {
	        // Load the TrueTypeFont from the given font file with specified size
	        Font awtFont = new Font(fontName, Font.BOLD, fontSize);	
	        return new TrueTypeFont(awtFont, true);
	 }	
	
	/* --- Constructor --- */
	public Statistics(GameContainer gc, Input user_input) throws SlickException {
		super();
		this.gc = gc;
		this.font = getNewFont("Courier New", 50);
		this.bigfont = getNewFont("Courier New", 57);
		this.time = 0;
		this.green = 0;
		this.yellow = 0;
		this.red = 0;
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
		Color semiTransparent = new Color(0.304f, 0.0131f, 0.03082f, 0.8f);
		g.setColor(semiTransparent);
		g.fillRect(((int) ((0.3f)*Config.SCREEN_WIDTH)),
				((int) ((0.2f)*Config.SCREEN_HEIGHT)),
				((int) ((0.4f)*Config.SCREEN_WIDTH)),
				((int) ((0.6f)*Config.SCREEN_HEIGHT)));
		font.drawString(((int) ((0.423f)*Config.SCREEN_WIDTH)), (int) ((0.25f)*Config.SCREEN_HEIGHT), "Work Report: ", Color.red);
		font.drawString(((int) ((0.33f)*Config.SCREEN_WIDTH)), (int) ((0.35f)*Config.SCREEN_HEIGHT), "Time Survived: ", Color.red);
		font.drawString(((int) ((0.63f)*Config.SCREEN_WIDTH)), (int) ((0.35f)*Config.SCREEN_HEIGHT), time+"", Color.red);
		font.drawString(((int) ((0.33f)*Config.SCREEN_WIDTH)), (int) ((0.42f)*Config.SCREEN_HEIGHT), "Green Killed: ", Color.red);
		font.drawString(((int) ((0.63f)*Config.SCREEN_WIDTH)), (int) ((0.42f)*Config.SCREEN_HEIGHT), green+"", Color.red);
		font.drawString(((int) ((0.33f)*Config.SCREEN_WIDTH)), (int) ((0.49f)*Config.SCREEN_HEIGHT), "Yellow Killed: ", Color.red);
		font.drawString(((int) ((0.63f)*Config.SCREEN_WIDTH)), (int) ((0.49f)*Config.SCREEN_HEIGHT), yellow+"", Color.red);
		font.drawString(((int) ((0.33f)*Config.SCREEN_WIDTH)), (int) ((0.56f)*Config.SCREEN_HEIGHT), "Red Killed: ", Color.red);
		font.drawString(((int) ((0.63f)*Config.SCREEN_WIDTH)), (int) ((0.56f)*Config.SCREEN_HEIGHT), red+"", Color.red);

		g.setColor(Color.red);
//		g.drawLine(((int) ((0.3f)*Config.SCREEN_WIDTH)), (int) ((0.3f)*Config.SCREEN_HEIGHT), ((int) ((0.75f)*Config.SCREEN_WIDTH)), (int) ((0.3f)*Config.SCREEN_HEIGHT));
		g.fillRect(((int) ((0.3f)*Config.SCREEN_WIDTH)), (int) ((0.33f)*Config.SCREEN_HEIGHT), (int) ((0.4f)*Config.SCREEN_WIDTH), (int) ((0.002f)*Config.SCREEN_HEIGHT));
		g.fillRect(((int) ((0.3f)*Config.SCREEN_WIDTH)), (int) ((0.64f)*Config.SCREEN_HEIGHT), (int) ((0.4f)*Config.SCREEN_WIDTH), (int) ((0.002f)*Config.SCREEN_HEIGHT));
		
		
		bigfont.drawString(((int) ((0.33f)*Config.SCREEN_WIDTH)), (int) ((0.68f)*Config.SCREEN_HEIGHT), "Status: ", Color.red);

//		g.setColor(new Color(0.884f, 0.031f, 0.0882f, 1f));
//		g.fillRect(((int) ((0.325f)*Config.SCREEN_WIDTH)),
//				((int) ((0.225f)*Config.SCREEN_HEIGHT)),
//				((int) ((0.35f)*Config.SCREEN_WIDTH)),
//				((int) ((0.55f)*Config.SCREEN_HEIGHT)));
//		font.drawString(((int) (Config.SCREEN_WIDTH - (0.39f)*Config.SCREEN_WIDTH)), (int) (Config.SCREEN_HEIGHT - (0.25f)*Config.SCREEN_HEIGHT), "Problem: ", Color.green);
//		font.drawString(((int) (Config.SCREEN_WIDTH - (0.39f)*Config.SCREEN_WIDTH)), (int) (Config.SCREEN_HEIGHT - (0.17f)*Config.SCREEN_HEIGHT), "Line: ", Color.green);
//
//		// Draw the timer
//		g.setColor(Color.black);
//		g.fillRect(
//				((int) (Config.SCREEN_WIDTH - (0.05f) * Config.SCREEN_WIDTH)),
//				((int) (Config.SCREEN_HEIGHT - (0.1f)*Config.SCREEN_HEIGHT)),
//				((int) (Config.SCREEN_WIDTH * 0.02f)),
//				-((int) (Config.SCREEN_HEIGHT - (0.3f)*Config.SCREEN_HEIGHT)));
//		
//		g.setColor(new Color(0.137f, 0.402f, 0.502f));
//		g.fillRect(
//				((int) (Config.SCREEN_WIDTH - (0.05f) * Config.SCREEN_WIDTH)),
//				((int) (Config.SCREEN_HEIGHT - (0.1f)*Config.SCREEN_HEIGHT)),
//				((int) (Config.SCREEN_WIDTH * 0.02f)),
//				-((int) ((Config.SCREEN_HEIGHT - (0.3f)*Config.SCREEN_HEIGHT) * aimTime)));
//		
	}

	public void setStat(int time, int green, int yellow, int red) {
		this.time = time;
		this.green = green;
		this.yellow = yellow;
		this.red = red;
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