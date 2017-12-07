import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SlimeSprite extends MonsterSprite{
	
	private static final Image initialImage = new Image("Images/placeholder1.png");
	private static final int HEIGHT = 1;
	private static final int WIDTH = 1;
	private static final double SPEED = 2;
	private static final double JUMP = 0;

	private int moveCycle = 0;
	private int secondMove = 40;
	private int lastCycle = 2*secondMove+1;

	public SlimeSprite(int x, int y) {
		super (initialImage, x, y, WIDTH, HEIGHT, SPEED, JUMP);
		
		Image[]idle= {
				new Image("Images/placeholder1.png")
		};
		super.setIdleFrames(idle);
		
		Image [] hurting = {
				new Image("Images/placeholderhit.png")
		};
		super.setHurtingFrames(hurting);
		
		super.setInitialState();
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getPixelX(), super.getPixelY(), 
				super.getPixelWidth(), super.getPixelHeight());
	}
	
	public void act() {
		if(!isDead()) {
			if(moveCycle <= secondMove) walkLeft();
			else walkRight();
			moveCycle++;
			if(moveCycle > lastCycle) moveCycle = 0;
		}
	}

}
