package graphics;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Box {

	//centerX: x-axis center of the box
	//centerY: y-axis center of the box
	//width: width of the box
	//height: height of the box
	
	protected float centerX, centerY, width, height;

//	Abstract method for subclasses that require interaction with the mouse (most subclasses will use this)
	protected abstract void mouseClick(float mouseX, float mouseY);
//	protected abstract void keyPress(int key);
	
//	Values should be updated by subclasses
	public Box() {
//		Setting Default Values: -1 (All values should be changed for all subclasses)
//		Position
		centerX = 0;
		centerY = 0;		
//		Size
		width = 0;
		height = 0;
	}
	
//	Initializing Methods
	public void initialize() {}
	public void update() {}
	
	
//	Setting Functions to update values
	public Box setX(float x) {
		centerX = x; 
		return this;
	}
	
	public Box setY(float y) {
		centerY = y;
		return this;
	}
	
	public Box setHeight(float h) {
		height = h;
		return this;
	}
	
	public Box setWidth(float w) {
		width = w;
		return this;
	}
	
	public boolean handleMouse(float mouseX, float mouseY) {
		if ( (centerX - width / 2 < mouseX) && (mouseX < centerX + width / 2)
				&& (centerY - height / 2 < mouseY) && (mouseY < centerY + height / 2) ) {
			mouseClick(mouseX, mouseY);
			return true;
		} else 
			return false;
	}
	
	public void draw(Graphics g){
//		Setting Color of Box
		g.setColor(Color.white);
		
//		Parameters: X, Y, Width, Height
		g.fillRoundRect(centerX, centerY, width, height, 2);;
	}

	
	
}
