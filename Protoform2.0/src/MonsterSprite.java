
import javafx.scene.image.Image;


public abstract class MonsterSprite extends MovingSprite {
	
	private boolean isDead = false;

	public MonsterSprite(Image image, int x, int y, double width, double height, double speed, double jumpPower) {
		super(image, x, y, width, height, speed, jumpPower);
	}
	
	public void update(int time) {
		super.update(time);
		act();		
	}
	
	public abstract void act();
	
	public void leftReact() {
		
		if(super.dead()) {
			setHurtState();
			isDead = true;
			resetCurrentStateIndex();
		}
	}
	
	public void rightReact() {
		if(super.dead()) {
			setHurtState();
			isDead = true;
			resetCurrentStateIndex();
		}
	}
	
	public boolean isDead() {
		return isDead;
	}
	
}
