import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class Tile extends Sprite{
	
	public enum Terrain {
		ROCKY, GRASSY
	}
	
	private static Image rocky = new Image("Images/rocky01.png");
	private static Image grassy = new Image ("Images/leafy_ground01.png");
	
	private static final int BLOCK_SIZE = 1;
	
	public Tile(Terrain t, int xpos, int ypos) {
		super(rocky, xpos, ypos, BLOCK_SIZE, BLOCK_SIZE);
		if (t == Terrain.ROCKY) super.setImage(rocky);
		else if (t == Terrain.GRASSY) super.setImage(grassy);
	}
	
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getX(), super.getY(), BLOCK_SIZE, BLOCK_SIZE);
	}
	
}
