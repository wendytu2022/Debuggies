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

public class CommandLine extends Box{
		
	/* Setting private variables */
	private GameContainer gc;
	private Input user_input;
	private TrueTypeFont font;
	
	/* --- Text Field Variables --- */
	public TextField problem_line;
	public TextField line_line;
	
	private String problem_answer = "dummy answer";
	private String line_number = "dummy answer";
	
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
		line.setFocus(true);

		/* Initialize the Error Prompt */
		problem_line = new TextField(gc, font,((int) (0.70f * Config.SCREEN_WIDTH)),
				((int) (0.75f * Config.SCREEN_HEIGHT)),
				((int) (0.24f * Config.SCREEN_WIDTH)),
				((int) (0.05f * Config.SCREEN_HEIGHT)));

		problem_line.setTextColor(Color.green);
		problem_line.setFocus(false);
		
		/* Initialize the Error Line Prompt*/
		line_line = new TextField(gc, font,((int) (0.70f * Config.SCREEN_WIDTH)),
				((int) (0.825f * Config.SCREEN_HEIGHT)),
				((int) (0.24f * Config.SCREEN_WIDTH)),
				((int) (0.05f * Config.SCREEN_HEIGHT)));

		line_line.setTextColor(Color.green);
		line_line.setFocus(false);
	}
	
	public void setFocus(boolean b) {
		problem_line.setFocus(b);
	}
		
	
//	Directly draw the image to the game, top left corner
	public void draw(Graphics g) {
		
		/* Visualize/draw the text fields*/
		Color semiTransparent = new Color(0f, 0f, 0f, 0.5f);
		problem_line.setBorderColor(Color.black);
		problem_line.setBackgroundColor(semiTransparent);
		problem_line.setTextColor(Color.green);
		problem_line.render(gc, g);
		
		line_line.setBorderColor(Color.black);
		line_line.setBackgroundColor(semiTransparent);
		line_line.setTextColor(Color.green);
		line_line.render(gc, g);
	}

	public boolean check() {
		return (problem_line.getText().equals(problem_answer) && 
				line_line.getText().equals(line_number)) ;
	}
	
	public void setAnswer(String p, String l) {
		this.problem_answer = p;
		this.line_number = l;
	}
	
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		
	}
	
//	public void update(int key) {
//        if (user_input.isKeyPressed(Input.KEY_ENTER)) {
//            // Clear the text field
////            line.setText("");
//        }	
//	}

//	@Override
//	protected void keyPress(int key) {		
//	}

	
		
}