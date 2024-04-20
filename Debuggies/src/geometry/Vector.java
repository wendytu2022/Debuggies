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
	
	// Rotates (in-place) the vector by radius theta
	
	
}