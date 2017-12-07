
public class Character {
	
	int health;
	
	public Character(int health) {
		this.health = health;
	}
	
	public void takeDamage() {
		health--;
	}
	
	public boolean isDead() {
		if(health <= 0) return true;
		else return false;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		health = h;
	}

}
