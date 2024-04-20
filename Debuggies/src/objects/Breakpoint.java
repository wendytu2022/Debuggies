package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import core.Config;
import core.Main;
import core.states.ExampleState;
import geometry.Polygon;
import geometry.Vector;
import graphics.GraphicsManager;
import graphics.ImageManager;
import objects.Entity.Team;

public class Breakpoint extends GameObject { 
    static final float radius = 15f;
    
	public Breakpoint() {
    	super();
    	
        this.ignoreFriction = false;
        
        this.sprite = ImageManager.getImage("breakpoint.png");
        
        this.width = radius;
        this.height = radius;
        
        collisionBox = Polygon.circle(0.15f * radius, 5);;
		collisionBox.setCenter(this.position);
		
        
        
    }
    
    // Collision Callback
 	public void onCollide(GameObject o) { 
 		if (o instanceof Player) {
 			this.remove();
 			Main.example.beginAiming(10.f);
 		}
 	}
 	
    
    
}