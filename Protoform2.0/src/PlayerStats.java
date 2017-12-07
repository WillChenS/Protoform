

public class PlayerStats extends Character {

	public static final int PLAYER_HP = 3;
	private double attackRange = 50;
	
	public PlayerStats() {
		super(PLAYER_HP);
	}
	
	public double getAttackRange() {
		return attackRange;
	}

}
