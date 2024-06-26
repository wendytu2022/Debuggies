package geometry;

public class Vector {
	public float x;
	public float y;
	
	// Constructors
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	// STATIC FUNCTION
	// Calculates the dot product between two vectors
	public static float dotProduct(Vector v1, Vector v2) {
		float output = v1.x * v2.x + v1.y * v2.y;
		return output;
	}
	
	// STATIC FUNCTION
	// Returns the additive inverse of the vector (-x, -y)
	public static Vector addInverse(Vector v) {
		return new Vector(-v.x, -v.y);
	}
	
	// Given a destination, returns the vector pointing to
	// this destination
	public Vector lookAt(Vector destination) {
		Vector output = new Vector(destination.x - x, destination.y - y);
		return output;
	}
	
	// Assigns the values from parameter vector into this vector
	public void assign(Vector v) {
		x = v.x;
		y = v.y;
	}
	
	// Copies this vector
	public Vector copy() {
		return new Vector(x, y);
	}
	
	// Returns the magnitude of the vector
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	// Normalizes a vector and rerturns a copy of it
	public Vector normalize() {
		Vector copy = copy();
		copy.scaleInplace(1 / copy.magnitude());
		return copy;
	}
	
	// Rotates (in-place) the vector by radius theta
	public void rotateInplace(float radians) {
		float rotateX = (float) (Math.cos(radians) * x - Math.sin(radians) * y);
		float rotateY = (float) (Math.sin(radians) * x + Math.cos(radians) * y);
		
		x = rotateX;
		y = rotateY;
	}
	
	// Scales (in-place) the vector by scale s
	public void scaleInplace(float scale) {
		x *= scale;
		y *= scale;
	}
	
	// Retunrs a new vector representing this vector scaled
	public Vector scale(float scale) {
		Vector output = new Vector(x * scale, y * scale);
		return output;
	}
	
	// Offsets (in-place) the vector by position x,y
	public void offsetInplace(Vector offset) {
		offsetInplace(offset.x, offset.y);
	}
	
	public void offsetInplace(float offsetX, float offsetY) {
		x += offsetX;
		y += offsetY;
	}
	
	// Returns a new vector representing the offset of the vector by x,y
	public Vector offset(float offsetX, float offsetY) {
		return new Vector(x + offsetX, y + offsetY);
	}
}