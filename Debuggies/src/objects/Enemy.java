package objects;

public class Enemy extends Entity{
	public enum EnemyErrorType { 
		NullPointer, 
		StackOverflow, 
		IndexOutOfBounds, 
		ConcurrentModification,
		SyntaxError,
		UninitializedVar };
		
	public EnemyErrorType enemyErrorType;
	
	public Enemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health);
		this.enemyErrorType = enemyErrorType;
	}
	
	@Override
	public void update(float deltaTime) {
		this.update(deltaTime);
		
	}
	
}
