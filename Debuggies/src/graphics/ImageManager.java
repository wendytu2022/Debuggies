package graphics;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// Static Class for image loading and rendering
public class ImageManager {
	final static String Folder = "res/";
	
	// Stores all images loaded
	private static HashMap<String, Image> images = new HashMap<>();
	
	// Get an image, or load it if it doesn't exist
	public static Image getImage(String file) {
		// Add to cache if it doesn't exist
		if (!images.containsKey(file)) {
			Image newImage;
			
			try {
				newImage = new Image(Folder + file);
				images.put(file, newImage);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}			
		
		return images.get(file);
	}
	
	
}