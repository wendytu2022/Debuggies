package core.states;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;
import graphics.BottomBar;
import graphics.CommandLine;
import graphics.Exit;
import graphics.ImageManager;
import graphics.Lefttool;
import graphics.Toolbar;
import geometry.Polygon;
import objects.GameObject;
import objects.physics.PhysicsManager;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;


public class ExampleState extends BasicGameState {
	private int id; // GameState ID
	
	private Shape shape;
	
	private Input user_input;
	
	// Constructor
	public ExampleState(int id) { 
		this.id = id; 
		
		this.shape = new Circle(100, 100, 3f);
	}
		
	/* --- Accessor Methods --- */
	@Override
	public int getID() { 
		return id; 
	}
	
	
	/* --- Boxes --- */
	private Exit exit; // Escape Button
//	private Toolbar toolbar; // Toolbar Icon
//	private Lefttool lefttool; // Left Tool Icon
//	private BottomBar bottomBar; // Bottom Bar icon
	private CommandLine cl; // Command Line Text
	
	/* --- Inherited Methods --- */
	// Runs when game state is initialized (on constructor call)
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		user_input = gc.getInput();
		
		p1.setPosition(o1.getPosition());
		p1.getCenter().offsetInplace(450, 500);
		p2.getCenter().offsetInplace(500, 550);
		
		//Create and scale the size of the box to press
		exit = new Exit(gc);
		exit
			.setX(0)
			.setY(0)
			.setWidth(15)
			.setHeight(15)
			.initialize();	
		
		cl = new CommandLine(gc, user_input);
		cl
			.setX(0.5f * 1080)
			.setY(0.9f * 1920)
			.setWidth(0.95f * 1080)
			.setHeight(0.055f * 1920)
			.initialize();
		
//		toolbar = new Toolbar(gc);
//		toolbar
//				.setX(10)
//				.setY(10)
//				.setWidth(1)
//				.setHeight(1)
//				.initialize();	
//		lefttool = new Lefttool(gc);
//		lefttool
//				.setX(10)
//				.setY(10)
//				.setWidth(1)
//				.setHeight(1)
//				.initialize();	
//		bottomBar = new BottomBar(gc);
//		bottomBar
//				.setX(10)
//				.setY(10)
//				.setWidth(1)
//				.setHeight(1)
//				.initialize();	
	}
	
	
	// Runs when game state is entered
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	// Runs when game state switches to another state
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) {		
		
	}

	// Runs when a key is pressed
	@Override // Input Determining
	public void keyPressed(int key, char c) {
		float amount = 50000.f;
		
		switch (c) {
		case 'w':
			o1.setAcceleration(0, amount);
			break;
		case 'a':
			o1.setAcceleration(-amount, 0);
			break;
		case 's':
			o1.setAcceleration(0, -amount);
			break;
		case 'd':
			o1.setAcceleration(amount, 0);
			break;
		}
    
    if (key == Input.KEY_ESCAPE) {
			  System.exit(0);
		}
	}
	
	// Runs when the mouse is pressed
	@Override
	public void mousePressed(int key, int x, int y) { 
		 
	}
	
	Polygon p1 = Polygon.circle(100.f, 10);
	Polygon p2 = Polygon.rectangle(25, 25);
	
	GameObject o1 = new GameObject();
	
	// Called one every frame for rendering
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(new Color(0.2f, 0.3f, 0.5f));
		g.clear();
		g.setBackground(new Color(0.14f, 0.14f, 0.15f)); // background VSCode color
		exit.draw(g);
		cl.draw(g);
//		toolbar.draw(g);
//		lefttool.draw(g);
//		bottomBar.draw(g);
		PhysicsManager.UpdatePhysics(1 / 60.f);
		p1.rotateInplace(0.05f);
		
		if (p1.collidesWith(p2)) {
			p1.render(g, new Color(0.5f, 0.2f, 0.5f));
			p2.render(g, new Color(0.5f, 0.2f, 0.5f));
		}
		else {
			System.out.println("No Collide");
			p1.render(g, new Color(0.15f, 0.2f, 0.3f));
			p2.render(g, new Color(0.15f, 0.2f, 0.3f));
		}
		
		
	}

	// Called once every frame for updating
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		
		if (user_input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			// Attain the mouse x and y at the current time of the click
			float curr_x = user_input.getMouseX();
			float curr_y = user_input.getMouseY();
			if (exit.handleMouse(curr_x, curr_y)) {
				System.out.println("handled");
			} else {
				System.out.println("not handeled");
			}
			
		}
		 if (cl.line.hasFocus()) {
	            // Check if Enter key is pressed
	            if (user_input.isKeyPressed(Input.KEY_ENTER)) {
	                // Clear the text field
	                cl.line.setText("");
	            }
	        }
					
	}
	
	
}