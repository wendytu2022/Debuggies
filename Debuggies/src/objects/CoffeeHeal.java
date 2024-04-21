package objects;

import core.Main;
import geometry.Polygon;
import graphics.ImageManager;

public class CoffeeHeal extends GameObject {
	static final float size = 5.5f;
    
	public CoffeeHeal() {
    	super();
    	
        this.ignoreFriction = false;
        
        this.sprite = ImageManager.getImage("coffee.png");
        
        this.width = size;
        this.height = size;
        
        collisionBox = Polygon.rectangle(0.75f * size, 0.85f * size);
		collisionBox.setCenter(this.position);
    }
    
    // Collision Callback
 	public void onCollide(GameObject o) { 
 		if (o instanceof Player) {
 			Player p = (Player) o;
 			p.health = (int) Math.min(100, p.health + 15);
 			
 			this.remove();
 		}
 	}
}