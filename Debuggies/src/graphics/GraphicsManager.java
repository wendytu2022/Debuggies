package graphics;

import core.Config;
import geometry.Vector;

// Static class that manages
// Manages graphics for the game
public class GraphicsManager {
	// Center of rendering
	public static Vector center;
	
	// Convert world positions to screen positions
	public static Vector WorldToScreen(Vector worldCoords) {
		// Find relative offset
		Vector output = new Vector(worldCoords.x - center.x, center.y - worldCoords.y);
		
		// Scale the output by pixels
		output.scaleInplace(Config.PIXELS_PER_UNIT);
		
		// Center on screen
		output.offsetInplace(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
		
		return output;
	}
	
	// Convert screen position to world position
	public static Vector ScreenToWorld(Vector screenCoords) {
		Vector output = new Vector(screenCoords.x - Config.SCREEN_WIDTH / 2, screenCoords.y - Config.SCREEN_HEIGHT / 2);
		output.scaleInplace(1 / Config.PIXELS_PER_UNIT);
		output.offsetInplace(center.x, -center.y);
		output.y = -output.y;
		
		return output;
	}
}