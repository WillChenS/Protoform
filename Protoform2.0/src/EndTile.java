
import java.util.LinkedList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class EndTile extends MovingSprite {

	private static Image image = new Image("Images/portal.png");
	private static final int BLOCK_HEIGHT = 2;
	private static final int BLOCK_WIDTH = 1;

	public EndTile(int xpos, int ypos) {
		super(image, xpos, ypos, BLOCK_WIDTH, BLOCK_HEIGHT, 0, 0);
		Image[] images = { image };
		setIdleFrames(images);
		setInitialState();
	}

	@Override
	public Rectangle2D getHitbox() {
		return new Rectangle2D(super.getPixelX(), super.getPixelY(), super.getPixelWidth(), super.getPixelHeight());
	}

	@Override
	public void resolveCollisions(LinkedList<Sprite> surroundingSprites) {
		for (Sprite sprite : surroundingSprites) {
			if (super.collidesWith(sprite) && this != sprite) {
				double minkowskiWidth = 0.5 * (sprite.getPixelWidth() + getPixelWidth());
				double minkowskiHeight = 0.5 * (sprite.getPixelHeight() + getPixelHeight());
				double dx = sprite.getMidX() - getMidX();
				double dy = sprite.getMidY() - getMidY();
				if (Math.abs(dx) <= minkowskiWidth && Math.abs(dy) <= minkowskiHeight) {
					double wy = minkowskiWidth * dy;
					double hx = minkowskiHeight * dx;
					if (wy > hx) {
						if (wy > -hx) {
							// bot
							setYSpeed(0);
							setPixelY(sprite.getPixelY() - this.getPixelHeight());
						}
					}
				}
			}
		}

	}

	@Override
	public void leftReact() {

	}

	@Override
	public void rightReact() {

	}

}
