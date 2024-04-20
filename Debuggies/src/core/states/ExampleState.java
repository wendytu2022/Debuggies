package core.states;
import java.awt.Font;
import java.util.ArrayList;

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

import core.Config;
import core.Main;
import graphics.BottomBar;
import graphics.CommandLine;
import graphics.Exit;
import graphics.GraphicsManager;
import graphics.ImageManager;
import graphics.Lefttool;
import graphics.Toolbar;
import geometry.Polygon;
import geometry.Vector;
import objects.GameObject;
import objects.physics.PhysicsManager;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;


public class ExampleState extends BasicGameState {
	private int id; // GameState ID
	
	// Player Object
	private GameObject player;
	
	// Game Entities
	private ArrayList<GameObject> objects;
	
	// 
	private float offsetSize = 2.5f;
	
	private Input user_input;
	
	// Constructor
	public ExampleState(int id) { 
		this.id = id; 
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
		
		objects = new ArrayList<>();
		
		player = new GameObject();
		player.getPosition().x = -1.5f;
		
		objects.add(player);
		
		GameObject o1 = new GameObject();
		GameObject o2 = new GameObject();
		
		o1.getPosition().x = (float) (500 * Math.random()) - 5f;
		o2.getPosition().x = (float) (500 * Math.random()) - 15f;
		
		objects.add(o1);
		objects.add(o2);
		
		// Set Camera onto Player
		GraphicsManager.center = player.getPosition();
		
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
		if (key == Input.KEY_DOWN) {
			addOffset(0, -offsetSize);
		}
		if (key == Input.KEY_UP) {
			addOffset(0, offsetSize);
		}
		if (key == Input.KEY_LEFT) {
			addOffset(offsetSize, 0);
		}
		if (key == Input.KEY_RIGHT) {
			addOffset(-offsetSize, 0);
		}
	}
	
	// Runs when the mouse is pressed
	@Override
	public void mousePressed(int key, int x, int y) { 
		 
	}
	
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

		// Render all gameobjects
		for (GameObject o : objects) {
			o.render(g);
		}
		
		// Draw GUI
		exit.draw(g);
				
	}

	// Called once every frame for updating
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		Input input = gc.getInput();
		GraphicsManager.center = player.getPosition();
		
		// Input Handling
		if (input.isKeyDown(Input.KEY_Q))
			Config.PIXELS_PER_UNIT = Config.PIXELS_PER_UNIT + 1;
		if (input.isKeyDown(Input.KEY_E))
			Config.PIXELS_PER_UNIT = Config.PIXELS_PER_UNIT - 1;
		
		if (input.isKeyDown(Input.KEY_DOWN)) {				 
			addOffset(0, -offsetSize);
		} else if (input.isKeyDown(Input.KEY_UP)) {
			addOffset(0, offsetSize);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			addOffset(-offsetSize, 0);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			addOffset(offsetSize, 0);
		}
		 if (cl.line.hasFocus()) {
	            // Check if Enter key is pressed
	            if (user_input.isKeyPressed(Input.KEY_ENTER)) {
	                // Clear the text field
	                cl.line.setText("");
	            }
	        }
					
		// Update Physics
		PhysicsManager.UpdatePhysics(1 / 60.f);
		
		// Update all gameobjects
		for (GameObject o : objects) {
			o.update();
		}
	}
	
	public void addOffset(float x, float y) {
		player.getPosition().x = player.getPosition().x + x;
		player.getPosition().y = player.getPosition().y + y;

	}
	
	
}