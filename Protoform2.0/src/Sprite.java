import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	
	private Image frame;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private int pixelWidth;
	private int pixelHeight;
	private int xspeed;
	private int yspeed;
	
	public Sprite(Image image, int x, int y, int width, int height) {
		this.frame = image;
		this.xpos = x;
		this.ypos = y;
		this.width = width;
		this.height = height;
		pixelWidth = width * Mapper.TILE_WIDTH;
		pixelHeight = height * Mapper.TILE_WIDTH;
		this.xspeed = 0;
		this.yspeed = 0;
	}
	
	public void update(int time) {
		xpos += xspeed*time;
		ypos += yspeed*time;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(frame, xpos, ypos, pixelWidth, pixelHeight);
	}
	
	public void clear(GraphicsContext gc) {
		gc.clearRect(xpos, ypos, pixelWidth, pixelHeight);
	}
	
	public abstract Rectangle2D getHitbox();
	
	public boolean collidesWith(Sprite other) {
		return other.getHitbox().intersects(this.getHitbox());
	}
	
	public Image getImage() {
		return frame;
	}
	
	public void setImage(Image image) {
		frame = image;
	}
	
	public void setXSpeed(int speed) {
		xspeed = speed;
	}
	
	public void setYSpeed(int speed) {
		yspeed = speed;
	}
	
	public void accelerateY(int dv) {
		yspeed += dv;
	}
	
	public int getX() {
		return xpos;
	}
	
	public int getY() {
		return ypos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
