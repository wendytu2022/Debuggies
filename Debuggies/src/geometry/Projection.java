package geometry;

// Projection Inner Class
// Internally used to handle collisions
public class Projection {
	public float lower;
	public float upper;
	
	// Constructor
	public Projection(float l, float u) {
		lower = l;
		upper = u;
	}
	
	// Determines if this projections overlaps another
	public boolean overlaps(Projection p) {
		if (upper < p.lower || p.upper < lower) 
			return false;
		else 
			return true;
	}
	
}