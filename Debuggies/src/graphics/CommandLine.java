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
import objects.Enemy;

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
	
	private Image[] problem_set = new Image[6];
	private String[] problem_list = new String[6];
	private String[] number_list = new String[6];
	private Enemy enemy;
	
	/* --- Command Line Generation --- */
	 public static TrueTypeFont getNewFont(String fontName, int fontSize) throws SlickException {
	        // Load the TrueTypeFont from the given font file with specified size
	        Font awtFont = new Font(fontName, Font.PLAIN, fontSize);	
	        return new TrueTypeFont(awtFont, true);
	 }	
	
	/* --- Constructor --- */
	public CommandLine(GameContainer gc, Input user_input) throws SlickException {
		super();
		this.enemy = null;
		this.gc = gc;
		this.user_input = user_input;
		this.font = getNewFont("Helvetica", 38);
	}
	
	public void setEnemy(Enemy targetEntity) {
		this.enemy = targetEntity;
	}
	public void initialize() {
		
		problem_list = new String[]
				{"array",
				 "concurrent",
				 "null",
				 "overflow",
				 "syntax",
				 "variable"};
		
		number_list = new String[] 
				{"69",
				 "48",
				 "27",
				 "39",
				 "66",
				 "77"};
		String folder = "res/Code Snippets/";
		problem_set = new Image[] 
				{ImageManager.getImage("ArrayIndexOutOfBoundsException.png",folder),
				 ImageManager.getImage("ConcurrentModificationException.png",folder),
				 ImageManager.getImage("NullPointerException.png",folder),
				 ImageManager.getImage("StackOverflowError.png",folder),
				 ImageManager.getImage("SyntaxError.png",folder),
				 ImageManager.getImage("UninitializedVariable.png",folder)
				};
		
		
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
		
		g.drawImage(problem_set[enemy.enemyErrorType.ordinal()].getScaledCopy((int)(0.31f * Config.SCREEN_WIDTH), (int) (0.51f  * Config.SCREEN_HEIGHT)), 
				(int) (0.62f * Config.SCREEN_WIDTH), 
				(int) (0.225f * Config.SCREEN_HEIGHT));
		
	}

	public boolean check() {
		return (problem_line.getText().equals(problem_answer) && 
				line_line.getText().equals(line_number)) ;
	}
	
	public void setAnswer(Enemy enemy_number) {
		this.problem_answer = problem_list[enemy_number.enemyErrorType.ordinal()];
		this.line_number = number_list[enemy_number.enemyErrorType.ordinal()];
	}
	
	@Override
	protected void mouseClick(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		
	}
	

	
		
}