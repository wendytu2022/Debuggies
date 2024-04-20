package core.states;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;

public class ExampleState extends BasicGameState {
	private int id; // GameState ID
	
	private Shape shape;
	
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
		shape.setCenterX(shape.getCenterX() + 0.1f);
		shape.setCenterY(shape.getCenterY() + 0.1f);
		
		
	}

	// Called once every frame for updating
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		
	}
	
	
}