package objects;

public class Entity extends GameObject {
    public enum Team { Ally, Neutral, Enemy };
    
    protected Team team;
    protected int health;
    
    public Entity(Team team, int health) {
        this.ignoreFriction = false;
        
        this.team = team;
        this.health = health;
    }
    
    // Accessors
    public Team getTeam() {
        return team;
    }
    
    @Override
    public void update(float deltaTime) {
        // Calls superclass update method
        super.update(deltaTime);    
    }
    
    // Take entity damage
    public void takeDamage(float dmg) {
        health -= dmg;
        
        // Destroy object if dead
        if (health <= 0) {
            this.remove();
        }
    }
    
}