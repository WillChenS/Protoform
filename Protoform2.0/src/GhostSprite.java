import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GhostSprite extends MonsterSprite{
	
	private static final Image initialImage = new Image("Images/placeholder1.png");
	private static final double HEIGHT = 1.2;
	private static final double WIDTH = 1.1;
	private static final double SPEED = 0.8;
	private static final double JUMP = 0;
	private static final int CYCLECOUNT = 30;

	private Random gen = new Random();
	private int move = gen.nextInt(2);
	private int cycle = CYCLECOUNT;

	public GhostSprite(int x, int y) {
		super (initialImage, x, y, WIDTH, HEIGHT, SPEED, JUMP);
		
		Image[]walking= {
				new Image("Images/Ghost Sprite/Move 1 .png"),
				new Image("Images/Ghost Sprite/Move 1 .png"),
				new Image("Images/Ghost Sprite/Move 2.png"),
				new Image("Images/Ghost Sprite/Move 2.png"),
				new Image("Images/Ghost Sprite/Move 3 .png"),
				new Image("Images/Ghost Sprite/Move 3 .png")
		};
		super.setWalkingFrames(walking);
		
		Image [] hurting = {
				new Image("Images/Ghost Sprite/Die 1 .png"),
				new Image("Images/Ghost Sprite/Die 2 .png"),
				new Image("Images/Ghost Sprite/Die 3 .png")
		};
		super.setHurtingFrames(hurting);
		
		Image[] idle = {
				new Image("Images/Ghost Sprite/Stand .png")
		};
		super.setIdleFrames(idle);
		
		super.setInitialState();
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getPixelX(), super.getPixelY(), 
				super.getPixelWidth(), super.getPixelHeight());
	}
	
	public void act() {
		if(!isDead()) {
			if(cycle == 0) {
				move = gen.nextInt(2);
				cycle = CYCLECOUNT;
			}
			if(move == 0) walkRight();
			if(move == 1) walkLeft();
			cycle--;
		}
	}
	
	public void render(GraphicsContext gc) {
		super.reverseRender(gc);
	}

}
