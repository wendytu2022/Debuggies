package core.states;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Config;
import core.Main;

public class ExampleState extends BasicGameState {
	private int id; // GameState ID
	
	private Shape shape;
	private Shape shape2;
	
	private float offsetSize = 1 * Config.PIXELS_PER_UNIT;
	
	// Constructor
	public ExampleState(int id) { 
		this.id = id; 
		
		this.shape = new Circle(500, 500, 20f);
		this.shape2 = new Circle(100, 700, 20f);
	}
		
	/* --- Accessor Methods --- */
	@Override
	public int getID() { 
		return id; 
	}
	
	/* --- Inherited Methods --- */
	// Runs when game state is initialized (on constructor call)
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
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
		g.draw(shape);
		
		shape.setCenterX(shape.getCenterX());
		shape.setCenterY(shape.getCenterY());	
		
		g.draw(shape2);
		
		shape2.setCenterX(shape2.getCenterX());
		shape2.setCenterY(shape2.getCenterY());
		
				
	}

	// Called once every frame for updating
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_DOWN)) {				 
			keyPressed(Input.KEY_DOWN, '0'); 
		} else if (input.isKeyDown(Input.KEY_UP)) {
			keyPressed(Input.KEY_UP, '0');
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			keyPressed(Input.KEY_LEFT, '0');
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			keyPressed(Input.KEY_RIGHT, '0');
		}
	}
	
	public void addOffset(float x, float y) {
		shape.setCenterX(shape.getCenterX() + x);
		shape.setCenterY(shape.getCenterY() + y);
		
		shape2.setCenterX(shape2.getCenterX() + x);
		shape2.setCenterY(shape2.getCenterY() + y);
	}
	
	
}