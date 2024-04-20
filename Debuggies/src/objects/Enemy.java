package objects;

public class Enemy extends Entity{
	enum EnemyErrorType { 
		NullPointer, 
		StackOverflow, 
		IndexOutOfBounds, 
		ConcurrentModification,
		SyntaxError,
		UninitializedVar };
		
	protected EnemyErrorType enemyErrorType;
	
	public Enemy(Team team, int health, EnemyErrorType enemyErrorType) {
		super(team, health);
		this.enemyErrorType = enemyErrorType;
	}
	
}
