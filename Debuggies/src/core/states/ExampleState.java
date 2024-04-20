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
import graphics.Box;
import graphics.CommandLine;
import graphics.Exit;
import graphics.GraphicsManager;
import graphics.ImageManager;
import graphics.Lefttool;
import graphics.Prompt;
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
	
<<<<<<< Updated upstream
=======
	// Paused
	private boolean paused;
	
	// Lalt
	private boolean alt;
		
>>>>>>> Stashed changes
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
	private Prompt background;
	
	/* --- Inherited Methods --- */
	// Runs when game state is initialized (on constructor call)
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		user_input = gc.getInput();
		
<<<<<<< Updated upstream
=======
		paused = false;
		alt = false;
		
		// Create Objects Array
>>>>>>> Stashed changes
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
		background = new Prompt(gc, user_input);
		background
			.setX(0.5f * Config.SCREEN_WIDTH)
			.setY(0.95f * Config.SCREEN_HEIGHT)
			.setWidth((int) (Config.SCREEN_WIDTH - (0.2f)*Config.SCREEN_WIDTH))
			.setHeight((int) (Config.SCREEN_HEIGHT - (0.2f)*Config.SCREEN_HEIGHT))
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
<<<<<<< Updated upstream
		if (key == Input.KEY_RIGHT) {
			addOffset(-offsetSize, 0);
=======
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
		
	}
	
	// Begin aim mode
	private void beginAiming(float timer) {
		// If we're to turn aiming on, select a GameObject to target
		if (!aiming) {
			// Select object
			nextTarget(1);
			
			// Detach the position so that we can allow camera movement
			GraphicsManager.center = GraphicsManager.center.copy(); 
			
			// Set focus on CMD line for typing
			cl.setFocus(true);
>>>>>>> Stashed changes
		}
	}
	
	// Runs when the mouse is pressed
	@Override
	public void mousePressed(int key, int x, int y) { 
		 
<<<<<<< Updated upstream
=======
		 // Orientate the bullet in the direction that the mouse is
		 Vector direction = player.getPosition().lookAt(spawn).normalize().scale(45.f);
		 
		 Spike s = new Spike(player, direction);
		 
>>>>>>> Stashed changes
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
		
<<<<<<< Updated upstream
=======
		// Render target if aiming
		if (aiming) {
			// Rescale target
			targetImage = targetImage.getScaledCopy((int) (TargetSize * Config.PIXELS_PER_UNIT), (int) (TargetSize * Config.PIXELS_PER_UNIT));
			
			// Draw target on screen center
			Vector screen = GraphicsManager.WorldToScreen(targetEntity.getPosition());
			targetImage.draw(screen.x - (TargetSize * Config.PIXELS_PER_UNIT) / 2, screen.y - (TargetSize * Config.PIXELS_PER_UNIT) / 2);
			
			// Draw command line
			background.draw(g);
			g.setColor(Color.green);
			cl.draw(g);
		}
		
>>>>>>> Stashed changes
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
		
<<<<<<< Updated upstream
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
=======
		// Freeze all entities on aim
		if (aiming) {
			aimingTimer -= 1 / 60.f;
			
			// Check that aiming mode is not over
			if (aimingTimer < 0) {
				aiming = false;
				
				GraphicsManager.center = player.getPosition();
				targetAnimate = 0f;
				
				// Set focus on CMD line to false
				cl.setFocus(false);
			}
			
			// Handle aiming animation
			targetAnimate = Math.min(1, targetAnimate + 0.05f);
			
			Vector offset = GraphicsManager.center.lookAt(targetEntity.getPosition());
			offset.scaleInplace(targetAnimate);
			GraphicsManager.center.offsetInplace(offset.x, offset.y);
			
			
			if (aiming && user_input.isKeyPressed(Input.KEY_LALT)) {
				if (alt) {
					cl.problem_line.setFocus(true);
				} else {
					alt = !alt;
					cl.problem_line.setFocus(false);
					cl.line_line.setFocus(false);
				}
			}
			
			
			/* Interaction with the command line */
			if (user_input.isKeyPressed(Input.KEY_ENTER)){
            	/* Check if the answer in the text field is correct */
                if (cl.check()) {
                    // If success, kill enemy
                    targetEntity.remove();
                    nextTarget(1);
                } else {                }
            	/* Empty the text field */
                cl.problem_line.setText("");
                cl.line_line.setText("");

                /* Set the user back to the problem command line*/
                cl.problem_line.setFocus(true);
                
            }
			// CMD line in here? Show command line and allow us to type in stuff
			// Handle Command Line Tabbing
			if (cl.problem_line.hasFocus()) {
	            // Check if Enter key is pressed
	            if (user_input.isKeyPressed(Input.KEY_TAB)) {
	                cl.line_line.setFocus(true);
	            }
            }
			if (cl.line_line.hasFocus()) {
	            // Check if Enter key is pressed
	            if (user_input.isKeyPressed(Input.KEY_TAB)) {
	                cl.problem_line.setFocus(true);
	            }
            }
		} else {
			// Handle Movement Input (CANNOT MOVE ON AIM)
			Input input = gc.getInput();
			final float offsetSize = 35.f;
			
			Vector velocity = new Vector();
			
			if (input.isKeyDown(Input.KEY_S)) {
				velocity.y -= offsetSize;
			} 
			if (input.isKeyDown(Input.KEY_W)) {
				velocity.y += offsetSize;
			} 
			if (input.isKeyDown(Input.KEY_A)) {
				velocity.x -= offsetSize;
			} 
			if (input.isKeyDown(Input.KEY_D)) {
				velocity.x += offsetSize;
			}
			
			player.getVelocity().assign(velocity);
			
			// If not aiming, run game as normal
			// Update Physics
			PhysicsManager.UpdatePhysics(1 / 60.f);
						
			// Update all gameobjects
			for (GameObject o : objects) {
				o.update(1 / 60.f);
			}
				
>>>>>>> Stashed changes
		}
	}
	
	public void addOffset(float x, float y) {
		player.getPosition().x = player.getPosition().x + x;
		player.getPosition().y = player.getPosition().y + y;

	}
	
	
}