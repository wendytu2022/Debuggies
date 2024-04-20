package geometry;

import geometry.Vector;

// Represents a generic convex polygon for collisions
public class Polygon {
	// List of vertices for the polygon. MUST form a convex polygon
	private Vector[] vertices;
	
	// Constructors
	public Polygon(Vector[] vertices) {
		this.vertices = vertices;
	}
	
	public Polygon() {
		vertices = null;
	}
	
	// Returns if the polygon has collided with another polygon
	public boolean collidesWith(Polygon p) {
		return false;
	}
	
	
}