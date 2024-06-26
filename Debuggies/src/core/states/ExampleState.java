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
import graphics.Box;
import graphics.CommandLine;
import graphics.Exit;
import graphics.GraphicsManager;
import graphics.ImageManager;
import graphics.Prompt;
import graphics.Statistics;
import geometry.Polygon;
import geometry.Vector;
import objects.GameObject;
import objects.IndexOutOfBoundsEnemy;
import objects.NullPointerEnemy;
import objects.StackOverflowEnemy;
import objects.SyntaxErrorEnemy;
import objects.UninitializedVariableEnemy;
import objects.Player;
import objects.Enemy;
import objects.Breakpoint;
import objects.CoffeeHeal;
import objects.ConcurrentModificationEnemy;
import objects.Entity;
import objects.Entity.Team;
import objects.physics.PhysicsManager;
import objects.projectiles.Spike;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;


public class ExampleState extends BasicGameState {
	private int id; // GameState ID
	
	// Player Object
	public static Player player;
	
	// Paused
	private boolean paused;
	
	// Lalt
	private boolean alt;

	// Game Entities
	private Entity sampleEnemy;
	
	public static ArrayList<GameObject> objects;
	public static ArrayList<GameObject> newObjects;
	
	// Aim State
	private final static int TargetSize = 15;
	
	private Image targetImage;  // Image to be rendered
	public static boolean aiming;		// Tracks whether or not we are aiming or not
	private float aimingTimer;  // Tracks how long the aiming should run for
	
	private Enemy targetEntity; // Object we're focused on
	private int targetIndex;    // Tracks index in the objects array to find our next target
	
	// Timer for Breakpoint Orbs
	private static final float BreakpointRespawn = 7.5f;
	private float breakpointTimer;
	
	// Timer for CofeeHeal Orbs
	private static final float HealRespawn = 10f;
	private float healTimer;
	
	private float targetAnimate;
	
	private Input user_input;
	
	public static int green;
	public static int yellow;
	public static int red;
	
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
	private CommandLine cl; // Command Line Text
	private Prompt background;
	private Statistics stat;
	
	/* --- Inherited Methods --- */
	// Runs when game state is initialized (on constructor call)
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		user_input = gc.getInput();
		
		paused = false;
		alt = false;
		
		int time = 0;
		int green = 0;
		int yellow = 0;
		int red = 0;
		
		breakpointTimer = BreakpointRespawn;
		healTimer = HealRespawn;
		
		// Create Objects Array
		objects = new ArrayList<>();
		newObjects = new ArrayList<>();
		
		PhysicsManager.Reset();
		
		// Create Player
		player = new Player();
		
		float rad = (float) (Math.random() * 2 * Math.PI);
		float randomX = 35.f * (float) Math.cos(rad);
		float randomY = 35.f * (float) Math.sin(rad);
		
		Breakpoint b = new Breakpoint();
		b.getPosition().assign(player.getPosition().offset(randomX, randomY));			
		
		// Create Enemies
		final float radius = 100f;
		
		for (int i = 0; i < 3; i++) {
			sampleEnemy = new StackOverflowEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));
		}
		
		for (int i = 0; i < 3; i++) {
			sampleEnemy = new SyntaxErrorEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));	
		}
		
		for (int i = 0; i < 3; i++) {
			sampleEnemy = new UninitializedVariableEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));	
		}
		
		for (int i = 0; i < 3; i++) {
			sampleEnemy = new IndexOutOfBoundsEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));	
		}
		
		for (int i = 0; i < 3; i++) {
			sampleEnemy = new ConcurrentModificationEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));	
		}
		
		for (int i = 0; i < 2; i++) {
			sampleEnemy = new NullPointerEnemy();
			
			float radians = (float) (Math.random() * 2 * Math.PI);
			sampleEnemy.getPosition().assign(
					new Vector(
							(float) (Math.cos(radians) * radius),
							(float) (Math.sin(radians) * radius)));	
		}
		
		// Load target graphic
		targetImage = ImageManager.getImage("target.png");
		targetIndex = 0;
		aiming = false;
		targetAnimate = 0.0f;
		
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
			.setX(0.5f * Config.SCREEN_WIDTH)
			.setY(0.95f * Config.SCREEN_HEIGHT)
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
		stat = new Statistics(gc, user_input);
		stat
			.setX(0.5f * Config.SCREEN_WIDTH)
			.setY(0.5f * Config.SCREEN_HEIGHT)
			.setWidth(0.5f * Config.SCREEN_WIDTH)
			.setHeight(0.5f * Config.SCREEN_HEIGHT)
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
		if (key == Input.KEY_SPACE) {
			paused = !paused;
		}
		
		// Do nothing if paused
		if (paused)
			return;
		
		if (key == Input.KEY_T) {
			// Begin aiming for 10 seconds if T is pressed (later will be a "breakpoint orb")
            cl.problem_line.setText("");
            cl.line_line.setText("");
			beginAiming(10.f); 
		}
		// Step between targets
		else if (aiming) {
			if (key == Input.KEY_RIGHT) {
				nextTarget(1);
			}
			else if (key == Input.KEY_LEFT) {
				nextTarget(-1);
			}
		}
		
//		if (!aiming && key == Input.KEY_ESCAPE) {
//			System.exit(0);
//		}
		
	}

	
	// Begin aim mode
	public void beginAiming(float timer) {
		// If we're to turn aiming on, select a GameObject to target
		if (!aiming) {
			// Select object
			nextTarget(1);
			
			// Detach the position so that we can allow camera movement
			GraphicsManager.center = GraphicsManager.center.copy(); 
			
			// Set focus on CMD line for typing
			cl.setFocus(true);
            cl.problem_line.setText("");
            cl.line_line.setText("");
		}
		
		aimingTimer = timer;
		aiming = true;
	}
	
	// Find the next target
	private void nextTarget(int offset) {
		boolean found = false;
		
		for (int i = 0; i < objects.size(); i++) {
			targetIndex = (targetIndex + objects.size() + offset) % objects.size();
			GameObject o = objects.get(targetIndex);

			// Skip if marked for removal
			if (o.removalMarked())
				continue;
			
			// Check that target is an entity, and it is a
			// class we can kill
			if (o instanceof Enemy) {
				Enemy e = (Enemy) o;
				
				if (e.getTeam() != Team.Ally) {
					found = true; 
					
					targetEntity = e;
					targetAnimate = 0f;
					
					cl.setEnemy(targetEntity);
					cl.setAnswer(targetEntity);
					
					break;
				}
			}

		}
		
		// If we don't find a target, force exit out of our aim mode
		if (!found) {
			aiming = false;
			
			GraphicsManager.center = player.getPosition();
			targetAnimate = 0f;
			
			// Set focus on CMD line to false
			cl.setFocus(false);
			
		}
			
	}
	
	// Runs when the mouse is pressed
	@Override
	public void mousePressed(int key, int x, int y) { 
		 // Do nothing if paused
		 if (paused || aiming)
			 return;
		
		 if (!player.removalMarked()) {
			// Otherwise, shoot a bullet from the player 
			 Vector spawn = GraphicsManager.ScreenToWorld(new Vector(x, y));
			 
			 player.markAttack(0.1f);
			 
			 // Orientate the bullet in the direction that the mouse is
			 Vector direction = player.getPosition().lookAt(spawn).normalize().scale(45.f);
			 Spike s = new Spike(player, direction);
			 
		 }
	}
	
	// Called one every frame for rendering
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(new Color(0.2f, 0.3f, 0.5f));
		g.clear();
		g.setBackground(new Color(0.14f, 0.14f, 0.15f)); // background VSCode color
		
		// Render grid
		Vector topLeft = GraphicsManager.ScreenToWorld(new Vector(0,0));
		Vector bottomRight = GraphicsManager.ScreenToWorld(new Vector(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
		
		g.setColor(new Color(0.56f, 0.93f, 0.56f, 0.45f));
		
		// Vertical Lines
		for (float x = 10.f * (float) Math.floor(topLeft.x / 10.f); 
				x <= 10.f * (float) Math.ceil(bottomRight.x / 10.f); x += 10f) {
			Vector screen = GraphicsManager.WorldToScreen(new Vector(x, 0));
			
			g.drawLine(screen.x, 0, screen.x, Config.SCREEN_HEIGHT);
		}
		
		// Horizontal Lines
		for (float y = 10.f * (float) Math.ceil(topLeft.y / 10.f);
				y >= 10.f * (float) Math.floor(bottomRight.y / 10.f); y -= 10f) {
			Vector screen = GraphicsManager.WorldToScreen(new Vector(0, y));
			g.drawLine(0, screen.y, Config.SCREEN_WIDTH, screen.y);
		}
		
//		toolbar.draw(g);
//		lefttool.draw(g);
//		bottomBar.draw(g);
		
		// Render all gameobjects
		for (GameObject o : objects) {
			o.render(g);
		}
		
		// Render target if aiming
		if (aiming) {
			// Rescale target
			targetImage = targetImage.getScaledCopy((int) (TargetSize * Config.PIXELS_PER_UNIT), (int) (TargetSize * Config.PIXELS_PER_UNIT));
			
			// Draw target on screen center
			Vector screen = GraphicsManager.WorldToScreen(targetEntity.getPosition());
			targetImage.draw(screen.x - (TargetSize * Config.PIXELS_PER_UNIT) / 2, screen.y - (TargetSize * Config.PIXELS_PER_UNIT) / 2);
			
			// Draw command line
			background.draw(g, aimingTimer / 10.f);
			g.setColor(Color.green);
			
			cl.draw(g);
		}
		
		// Draw GUI
//		exit.draw(g);
				
		if (paused) {
			g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.25f));
			g.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
			
			Image pause = ImageManager.getImage("pause.png");
			pause.setAlpha(0.25f);
			pause.draw(Config.SCREEN_WIDTH / 2 - pause.getWidth() / 2, Config.SCREEN_HEIGHT / 2 - pause.getHeight() / 2);
		}
		 if (player.removalMarked() ) {
			 stat.setStat(green,yellow,red);
			 stat.draw(g); 
		 }
	}

	// Called once every frame for updating
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		// Do nothing if paused
		if (paused)
			return;
		
		// Remove marked objects
		objects.removeIf(x -> x.removalMarked());
		objects.addAll(newObjects);
		newObjects.clear();
		
		// Zoom based on aim
		Config.PIXELS_PER_UNIT = 10f + targetAnimate * 5f;
		
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
			
			
			if (aiming && (user_input.isKeyPressed(Input.KEY_LALT) || user_input.isKeyPressed(Input.KEY_LCONTROL))) {
				if (alt) {
					alt = !alt;
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
                    aimingTimer = (float) Math.min(10f, aimingTimer + 2f);
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
			// Check to create breakpoint
			breakpointTimer -= 1 / 60.f;
			
			if (breakpointTimer < 0) {
				breakpointTimer = BreakpointRespawn;
				
				float radians = (float) (Math.random() * 2 * Math.PI);
				float randomX = 25.f * (float) Math.cos(radians);
				float randomY = 25.f * (float) Math.sin(radians);
				
				Breakpoint b = new Breakpoint();
				b.getPosition().assign(player.getPosition().offset(randomX, randomY));			
			}
			
			healTimer -= 1 / 60.f;
			
			if (healTimer < 0) {
				healTimer = HealRespawn;
				
				float radians = (float) (Math.random() * 2 * Math.PI);
				float randomX = 25.f * (float) Math.cos(radians);
				float randomY = 25.f * (float) Math.sin(radians);
				
				CoffeeHeal c = new CoffeeHeal();
				c.getPosition().assign(player.getPosition().offset(randomX, randomY));
			}
			
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
				
		}
		if (!aiming && user_input.isKeyPressed(Input.KEY_ESCAPE)) {
			 sbg.getState(0).init(gc, sbg);
			 MenuState.h = false;
	         sbg.enterState(1);
			 green = 0;
			 yellow = 0;
			 red = 0;
		}
		
		
	}
		
}