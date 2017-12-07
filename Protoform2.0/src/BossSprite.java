
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class BossSprite extends MovingSprite {
	
	private static final Image initialImage = new Image("Images/Penguin/stand.0.png");
	private static final int HEIGHT = 3;
	private static final int WIDTH = 3;
	private static final double SPEED = 1.0;
	private static final double JUMP = 0.2;
	private static final int STUNFRAMES = 5;
	private Character stats;
	private boolean jumping = false;
	
	private int moveCycle = 0;
	private int secondMove = 500;
	private int lastCycle = 2*secondMove+1;
	private int hurtCounter = 0;

	public BossSprite(int x, int y) {
		super (initialImage, x, y, WIDTH, HEIGHT, SPEED, JUMP);
		this.stats = new Character(5);
		Image[]idle= {
				new Image("Images/Penguin/stand.0.png"),
				new Image("Images/Penguin/stand.1.png"),
				new Image("Images/Penguin/stand.2.png"),
				new Image("Images/Penguin/stand.3.png"),
				new Image("Images/Penguin/stand.7.png"),
				new Image("Images/Penguin/stand.8.png")
		};
		super.setIdleFrames(idle);
		
		Image[] hurting = { new Image("Images/Penguin/hit1.0.png")};
		super.setHurtingFrames(hurting);
		
		Image [] dying = {
				new Image("Images/Penguin/die1.0.png"),
				new Image("Images/Penguin/die1.1.png"),
				new Image("Images/Penguin/die1.2.png"),
				new Image("Images/Penguin/die1.3.png"),
				new Image("Images/Penguin/die1.4.png")
		};
		super.setDyingFrames(dying);
		
		Image[]walking = {
				new Image("Images/Penguin/move.1.png"),
				new Image("Images/Penguin/move.1.png"),
				new Image("Images/Penguin/move.2.png"),
				new Image("Images/Penguin/move.2.png"),
				new Image("Images/Penguin/move.3.png"),
				new Image("Images/Penguin/move.3.png"),
				new Image("Images/Penguin/move.4.png"),
				new Image("Images/Penguin/move.4.png"),
				new Image("Images/Penguin/move.5.png"),
				new Image("Images/Penguin/move.5.png")
		};
		super.setWalkingFrames(walking);
		
		super.setInitialState();
		resetTimer();
	}
	
	public void update(int time) {
		super.update(time);
		accelerateY(1);
		act();		
	}
	
	public void act() {
		if(!isDead() && hurtCounter == 0) {
			if(moveCycle <= secondMove) {
				walkLeft();
				if(!jumping) {
					jump();
					jumping = true;
				}
				else jumping = false;
			}
			else {
				walkRight();
			}
			moveCycle++;
			if(moveCycle > lastCycle) moveCycle = 0;
		}
		else if (!isDead()) hurtCounter--;
	}
	
	public void jump() {
		super.setPixelY(this.getPixelY() - 5);
		super.setYSpeed(-JUMP);
	}
	
	public void render(GraphicsContext gc) {
		super.reverseRender(gc);
	}
	
	public void leftReact() {
		
		if(stats.isDead()) {
			setErase();
			resetCurrentStateIndex();
			setDyingState();
			startTimer();
		}
		else {
			setHurtState();
			hurtCounter = STUNFRAMES;
			setXSpeed(0);
			stats.takeDamage();
		}
	}
	
	public void rightReact() {
		if(stats.isDead()) {
			setErase();
			setDyingState();
			resetCurrentStateIndex();
			startTimer();
		}
		else {
			setHurtState();
			hurtCounter = STUNFRAMES;
			setXSpeed(0);
			stats.takeDamage();
		}
	}
	
	public boolean isDead() {
		return stats.isDead();
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getPixelX(), super.getPixelY(), 
				super.getPixelWidth(), super.getPixelHeight());
	}
	
}
