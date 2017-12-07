import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PenguSprite extends MonsterSprite{
	
	private static final Image initialImage = new Image("Images/Penguin/stand.0.png");
	private static final int HEIGHT = 1;
	private static final int WIDTH = 1;
	private static final double SPEED = 0.5;
	private static final double JUMP = 0;

	private int moveCycle = 0;
	private int secondMove = 200;
	private int lastCycle = 2*secondMove+1;

	public PenguSprite(int x, int y) {
		super (initialImage, x, y, WIDTH, HEIGHT, SPEED, JUMP);
		
		Image[]idle= {
				new Image("Images/Penguin/stand.0.png"),
				new Image("Images/Penguin/stand.1.png"),
				new Image("Images/Penguin/stand.2.png"),
				new Image("Images/Penguin/stand.3.png"),
				new Image("Images/Penguin/stand.7.png"),
				new Image("Images/Penguin/stand.8.png")
		};
		super.setIdleFrames(idle);
		
		Image [] hurting = {
				new Image("Images/Penguin/die1.0.png"),
				new Image("Images/Penguin/die1.1.png"),
				new Image("Images/Penguin/die1.2.png"),
				new Image("Images/Penguin/die1.3.png"),
				new Image("Images/Penguin/die1.4.png")
		};
		super.setHurtingFrames(hurting);
		
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

	public void render(GraphicsContext gc) {
		super.reverseRender(gc);
	}
}
