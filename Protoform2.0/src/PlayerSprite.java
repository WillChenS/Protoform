

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class PlayerSprite extends MovingSprite{

	private static final int HEIGHT = 2;
	private static final int WIDTH = 1;
	private static final double SPEED = 3;
	private static final double JUMP = 10;
	private static final Image initialImage = new Image("Images/Knight PNG/Knight_idle_01.png");
	private boolean isProgressing = false;
	
	private PlayerStats stats;
	private boolean controlLocked = false;

	public PlayerSprite(int x, int y) {
		super (initialImage, x, y, WIDTH, HEIGHT, SPEED, JUMP);
		
		stats = new PlayerStats();
		
		Image[]walking = {
				new Image("Images/Knight PNG/Knight_walk_01.png"),
				new Image("Images/Knight PNG/Knight_walk_02.png"),
				new Image("Images/Knight PNG/Knight_walk_03.png"),
				new Image("Images/Knight PNG/Knight_walk_04.png"),
				new Image("Images/Knight PNG/Knight_walk_05.png"),
				new Image("Images/Knight PNG/Knight_walk_06.png")
		};
		super.setWalkingFrames(walking);
		
		Image[]idle= {
				new Image("Images/Knight PNG/Knight_idle_01.png"),
				new Image("Images/Knight PNG/Knight_idle_02.png"),
				new Image("Images/Knight PNG/Knight_idle_03.png"),
				new Image("Images/Knight PNG/Knight_idle_04.png"),
				new Image("Images/Knight PNG/Knight_idle_05.png"),
				new Image("Images/Knight PNG/Knight_idle_06.png")
		};
		super.setIdleFrames(idle);
		
		Image[]jumping = {
				new Image("Images/Knight PNG/Knight_jump_01.png"),
				new Image("Images/Knight PNG/Knight_jump_02.png"),
		};
		super.setJumpingFrames(jumping);
		
		Image[]hurting = {
				new Image("Images/Knight PNG/Knight_hurt_01.png"),
				new Image("Images/Knight PNG/Knight_hurt_02.png"),
		};
		super.setHurtingFrames(hurting);
		
		Image[]attack = {
			new Image("Images/Knight PNG/Knight_attack_01.png"),
			new Image("Images/Knight PNG/Knight_attack_02.png"),
			new Image("Images/Knight PNG/Knight_attack_03.png"),
			new Image("Images/Knight PNG/Knight_attack_04.png"),
			new Image("Images/Knight PNG/Knight_attack_05.png"),
			new Image("Images/Knight PNG/Knight_attack_06.png")
		};
		super.setAttackingFrames(attack);
		
		Image[]die = {
			new Image("Images/Knight PNG/Knight_die_01.png"),
			new Image("Images/Knight PNG/Knight_die_02.png"),
			new Image("Images/Knight PNG/Knight_die_03.png"),
			new Image("Images/Knight PNG/Knight_die_04.png"),
			new Image("Images/Knight PNG/Knight_die_05.png"),
			new Image("Images/Knight PNG/Knight_die_06.png"),
			new Image("Images/Knight PNG/Knight_die_07.png"),
			new Image("Images/Knight PNG/Knight_die_08.png")
		};
		super.setDyingFrames(die);
		
		super.setInitialState();
		super.setAttackRange(stats.getAttackRange());
	}
	
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getPixelX(), super.getPixelY(), 
				super.getPixelWidth(), super.getPixelHeight());
	}
	
	public void leftReact() {
		setHurtState();
		if(!controlLocked && !isAttacking()) stats.takeDamage();
		controlLocked = true;
		setXSpeed(2*SPEED);
		if(stats.getHealth() <= 0) setXSpeed(0);
	}
	
	public void rightReact() {
		setHurtState();
		if(!controlLocked && !isAttacking()) stats.takeDamage();
		controlLocked = true;
		setXSpeed(-2*SPEED);
		if(stats.getHealth() <= 0) setXSpeed(0);
	}
	
	public void walkLeft() {
		if(!controlLocked) super.walkLeft();
	}
	
	public void walkRight() {
		if(!controlLocked) super.walkRight();
	}
	
	public void jump() {
		if(!controlLocked) super.jump();
	}
	
	public void unlockControls() {
		controlLocked = false;
	}
	
	public void lockControls() {
		controlLocked = true;
	}
	
	public boolean controlsLocked() {
		return controlLocked;
	}
	
	public void update(int time) {
		super.update(time);
	}
	
	public boolean gameEnd() {
		if(this.getPixelY() > ProtoformPane.canvasHeight) return true;
		return stats.isDead();
	}
	
	public void progress() {
		isProgressing = true;
	}
	
	public boolean isProgressing() {
		return isProgressing;
	}
	
	public int health() {
		return stats.getHealth();
	}
	
	public void setHealth(int h) {
		stats.setHealth(h);
	}
	
	}
