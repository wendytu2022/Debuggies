package objects;

public class Entity extends GameObject {
	enum Team { Ally, Neutral, Enemy };
	
	protected Team team;
	protected int health;
	
	public Entity(Team team, int health) {
		this.team = team;
		this.health = health;
	}
	
}