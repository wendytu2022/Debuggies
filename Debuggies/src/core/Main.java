package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.states.ExampleState;

public class Main extends StateBasedGame 
{
	// GameState IDs
	public static final int EXAMPLE_ID = 0;
      
    // BasicGameStates 
    public static ExampleState example;
    
    private static AppGameContainer appgc;
    
    
	public Main(String name) { 
		super(name); 
		
		this.example = new ExampleState(EXAMPLE_ID);
	}

	public static int getScreenWidth() { return appgc.getScreenWidth(); }
	public static int getScreenHeight() { return appgc.getScreenHeight(); }
	
	public static int getFPS() { return appgc.getFPS(); }
	
	// Initialize list of states
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(example);
	}

	// Main method
	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("Example Application"));
			
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			
			// Set size of window, and set full screen
			appgc.setDisplayMode(getScreenWidth(), getScreenHeight(), true);
			
			// Set FPS Rate
			appgc.setTargetFrameRate(60);
			
			// Begin application, entering first state added
			appgc.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}