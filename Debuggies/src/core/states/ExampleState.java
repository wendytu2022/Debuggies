package core.states;
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
import graphics.Exit;
import graphics.GraphicsManager;
import graphics.ImageManager;
import geometry.Polygon;
import geometry.Vector;
import objects.GameObject;
import objects.physics.PhysicsManager;

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
			.setWidth(10)
			.setHeight(10)
			.initialize();	
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