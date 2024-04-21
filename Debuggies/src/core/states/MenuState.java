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

import core.Config;
import geometry.Vector;
import graphics.EndButton;
import graphics.Exit;
import graphics.GraphicsManager;
import graphics.ImageManager;
import graphics.StartButton;
import objects.Player;
import objects.projectiles.Spike;

public class MenuState extends BasicGameState {
	
	private int id; //MenuState ID
	private Input user_input; //User input
	public static boolean h;
	
	
	public MenuState(int id) {
		this.id = id;
	}

	@Override
	public int getID() {
		return id;
	}

	private StartButton start;
	private EndButton end;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		user_input = gc.getInput();
		
		start = new StartButton(gc,sbg);
		start
			.setX(Config.SCREEN_WIDTH * 0.18f)
			.setY(Config.SCREEN_HEIGHT * 0.78f)
			.setWidth(Config.SCREEN_WIDTH * 0.25f)
			.setHeight(Config.SCREEN_HEIGHT * 0.25f)
			.initialize();	
		
		end = new EndButton(gc,sbg);
		end
			.setX(Config.SCREEN_WIDTH * 0.61f)
			.setY(Config.SCREEN_HEIGHT * 0.79f)
			.setWidth(Config.SCREEN_WIDTH * 0.25f)
			.setHeight(Config.SCREEN_HEIGHT * 0.25f)
			.initialize();	
		
		h = false;
	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(new Color(1.0f,1.0f,1.0f)); // background VSCode color
		
		Image logo = ImageManager.getImage("logo.png");
		int newWidth = logo.getWidth() * Config.SCREEN_HEIGHT / logo.getHeight();
		g.drawImage(logo.getScaledCopy(newWidth, Config.SCREEN_HEIGHT), (Config.SCREEN_WIDTH - newWidth) / 2, 0);
		
		start.draw(g);
		end.draw(g);
		
		if (user_input.isKeyDown(Input.KEY_H)){
			Image help = ImageManager.getImage("manual.png");
			g.drawImage(help.getScaledCopy(Config.SCREEN_WIDTH + 5, Config.SCREEN_HEIGHT + 5), 0, 0);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int n) throws SlickException {
		if ( user_input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
			float mouseX = user_input.getMouseX();
			float mouseY = user_input.getMouseY();
			start.handleMouse(mouseX, mouseY);
			end.handleMouse(mouseX, mouseY);
		}
		if (user_input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		
	}


}
