package core.states;
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
import graphics.Exit;
import graphics.ImageManager;
import geometry.Polygon;
import objects.GameObject;
import objects.physics.PhysicsManager;

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
//		exit.draw(g);
		Image im = ImageManager.getImage("exit_icon.png");
		im.getScaledCopy(15, 15);
		im.draw();
		
		exit.draw(g);
		g.setColor(new Color(0.2f, 0.3f, 0.5f));
		
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
		
		exit.update();
		
	}
	
	
}