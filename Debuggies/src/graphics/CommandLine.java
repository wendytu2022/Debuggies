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

import core.states.ExampleState;

public class CommandLine extends Box{
		
	/* Setting private variables */
	private GameContainer gc;
	private Input user_input;
	private TrueTypeFont font;
	
	/* --- Text Field Variables --- */
	public TextField line;

	
	/* --- Command Line Generation --- */
	 public static TrueTypeFont getNewFont(String fontName, int fontSize) throws SlickException {
	        // Load the TrueTypeFont from the given font file with specified size
	        Font awtFont = new Font(fontName, Font.PLAIN, fontSize);	
	        return new TrueTypeFont(awtFont, true);
	 }	
	
	/* --- Constructor --- */
	public CommandLine(GameContainer gc, Input user_input) throws SlickException {
		super();
		this.gc = gc;
		this.user_input = user_input;
		this.font = getNewFont("Helvetica", 38);
	}
	
	public void initialize() {
		line = new TextField(gc, font, 400, 400, 1000, 100);
		line.setFocus(false);
	}
	
	public void setFocus(boolean b) {
		line.setFocus(b);
	}
	
//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
//		font.drawString(500, 500, "Testing Font", Color.green);
		line.setBorderColor(Color.black);
		line.setBackgroundColor(Color.lightGray);
		line.setTextColor(Color.green);
		line.render(gc, g);
	}

	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(int key) {
        if (user_input.isKeyPressed(Input.KEY_ENTER)) {
            // Clear the text field
            line.setText("");
        }	
	}

	@Override
	protected void keyPress(int key) {		
	}

	
		
}