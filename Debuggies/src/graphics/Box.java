package graphics;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Box {
//	centerX: x-axis center of the box
//	centerY: y-axis center of the box
//	width: width of the box
//	height: height of the box
	
	protected float centerX, centerY, width, height;

//	Abstract method for subclasses that require interaction
	protected abstract void mouseClick(float mouseX, float mouseY);
	
//	Values should be updated by subclasses
	public Box() {
//		Setting Default Values
//		Position
		centerX = -1;
		centerY = -1;		
//		Size
		width = -1;
		height = -1;
	}
	
//	Initializing Methods
	public void initialize() {}
	public void update() {}
	
//	
	
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
	
	public void draw(Graphics d) {
//		Setting Color of Box
		d.setColor(Color.white);
		
//		Parameters: X, Y, Width, Height
		d.fillRect(0, 0, 10, 10);;
	}

	
	
}
