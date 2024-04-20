package geometry;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import graphics.GraphicsManager;

import geometry.Projection;

// Represents a generic convex polygon for collisions
public class Polygon {
	// List of vertices for the polygon. MUST form a convex polygon
	private Vector[] vertices;
	
	// What the polygon is centered around
	private Vector center;
	
	// Constructors	
	public Polygon(int number) {
		vertices = new Vector[number];
		center = new Vector();
	}
	
	// Sets the position of the polygon
	public void setPosition(Vector v) {
		center = v;
	}
		
	// Accessors
	public Vector getCenter() { 
		return center; 
	}
	
	// STATIC METHOD
	// Create a circle with # vertices defining the border
	// Minimum 3
	public static Polygon circle(float radius, int numVertices) {
		final float radianOffset = (float) (2 * Math.PI / numVertices);
		
		Polygon p = new Polygon(numVertices + 1);
		
		for (int i = 0; i < numVertices; i++) {
			Vector v = new Vector(radius, 0);
			v.rotateInplace(radianOffset * i);
			p.vertices[i] = v;
		}
		
		Vector v = new Vector(radius, 0);
		p.vertices[numVertices] = v;
		
		return p;
	}
	
	
	// STATIC METHOD
	// Create a rectangle
	public static Polygon rectangle(float width, float height) {
		Polygon p = new Polygon(5);
		
		float w = width / 2;
		float h = height / 2;
		
		p.vertices[0] = new Vector(-w, -h);
		p.vertices[1] = new Vector(-w, h);
		p.vertices[2] = new Vector(w, h);
		p.vertices[3] = new Vector(w, -h);
		p.vertices[4] = new Vector(-w, -h);
		
		return p;
	}
	
	// Set Associated Player Center
	public void setCenter(Vector v) {
		center = v;
	}
	
	// Debug renderig
	public void render(Graphics g, Color rgb) {
		for (int i = 0; i < vertices.length - 1; i++) {
			Vector v1 = vertices[i].offset(center.x, center.y);
			Vector v2 = vertices[i + 1].offset(center.x, center.y);
			
			v1 = GraphicsManager.WorldToScreen(v1);
			v2 = GraphicsManager.WorldToScreen(v2);
			
			g.setColor(rgb);
			g.drawLine(v1.x, v1.y, v2.x, v2.y);
		}
	}
	
	// Rotates the polygon (in-place)
	public void rotateInplace(float radians) {
		for (Vector v : vertices)
			v.rotateInplace(radians);
	}
	
	// Returns if the polygon has collided with another polygon
	public boolean collidesWith(Polygon p) {
		// Project this polygon onto all edges of this polygon, checking
		// for intersection
		for (int i = 0; i < vertices.length - 1; i++) {
			// Get vectors
			Vector v1 = vertices[i];
			Vector v2 = vertices[i + 1];
			
			// Get the normal to these vertices
			Vector normal = new Vector(v2.y - v1.y, v1.x - v2.x);
			
			// Project both shapes onto this normal
			Projection p1 = this.projectOntoEdge(normal, Vector.addInverse(v1));
			Projection p2 = p.projectOntoEdge(normal, Vector.addInverse(v1).offset(-center.x + p.center.x, -center.y + p.center.y));
			
			// Check if projections overlap. If they don't, return false (we found an axis of separation)
			if (!p1.overlaps(p2))
				return false;
			
		}
		
		// Repeat this check for all edges of the other polygon, checking for
		// intersection
		for (int i = 0; i < p.vertices.length - 1; i++) {
			// Get vectors
			Vector v1 = p.vertices[i];
			Vector v2 = p.vertices[i + 1];
			
			// Get the normal to these vertices
			Vector normal = new Vector(v2.y - v1.y, v1.x - v2.x);
			
			// Project both shapes onto this normal
			Projection p1 = p.projectOntoEdge(normal, Vector.addInverse(v1));
			Projection p2 = this.projectOntoEdge(normal, Vector.addInverse(v1).offset(-p.center.x + center.x, -p.center.y + center.y));
			
			// Check if projections overlap. If they don't, return false (we found an axis of separation)
			if (!p1.overlaps(p2))
				return false;
			
		}
		
		return true;
	}
	
	public Projection projectOntoEdge(Vector edge, Vector offset) {
		// Project all vertices of the polygon onto the edge 
		float init = Vector.dotProduct(edge, vertices[0].offset(offset.x, offset.y));
		Projection projection = new Projection(init, init);
		
		// Iterate through all vertices, projecting as needed
		for (Vector v : vertices) {
			// Project vertex onto the edge
			float proj = Vector.dotProduct(edge, v.offset(offset.x, offset.y));
			
			// Update the projection
			if (proj < projection.lower)
				projection.lower = proj;
			
			if (proj > projection.upper)
				projection.upper = proj;
		}
		
		return projection;
	}
	
	
}