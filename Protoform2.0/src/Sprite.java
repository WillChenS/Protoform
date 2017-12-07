<<<<<<< HEAD
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	
	private Image frame;
	private int xpos;
	private int ypos;
	private double pixelX;
	private double pixelY;
	private double width;
	private double height;
	private double pixelWidth;
	private double pixelHeight;
	private double xspeed;
	private double yspeed;
	private boolean toBeErased = false;
	private int internalTimer = 0;
	private boolean timerStarted = false;
	
	public Sprite(Image image, int x, int y, double width, double height) {
		this.frame = image;
		this.xpos = x;
		this.ypos = y;
		pixelX = x * Mapper.TILE_WIDTH;
		pixelY = y * Mapper.TILE_HEIGHT;
		this.width = width;
		this.height = height;
		pixelWidth = width * Mapper.TILE_WIDTH;
		pixelHeight = height * Mapper.TILE_WIDTH;
		this.xspeed = 0;
		this.yspeed = 0;
	}
	
	public void update(int time) {
		pixelX += xspeed*time;
		pixelY += yspeed*time;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(frame, pixelX, pixelY, pixelWidth, pixelHeight);
		if(timerStarted) incrementTimer();
	}
	
	public void renderBackwards(GraphicsContext gc) {
		gc.drawImage(frame, pixelX + pixelWidth, pixelY, -pixelWidth, pixelHeight);
		if(timerStarted) incrementTimer();
	}
	
	public void clear(GraphicsContext gc) {
		gc.clearRect(pixelX, pixelY, pixelWidth, pixelHeight);
	}
	
	public void incrementWidth(double w) {
		pixelWidth += w;
	}
	
	public void decrementWidth(double w) {
		pixelWidth -= w;
	}
	
	public void decrementX(double x) {
		pixelX -= x;
	}
	
	public void incrementX(double x) {
		pixelX += x;
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
	
	public void setXSpeed(double speed) {
		xspeed = speed;
	}
	
	public double getXSpeed() {
		return xspeed;
	}
	
	public double getYSpeed() {
		return yspeed;
	}
	
	public void setYSpeed(double speed) {
		yspeed = speed;
	}
	
	public void accelerateY(double dv) {
		yspeed += dv;
	}
	
	public int getX() {
		return xpos;
	}
	
	public int getY() {
		return ypos;
	}
	
	public double getPixelX() {
		return pixelX;
	}
	
	public double getPixelY() {
		return pixelY;
	}
	
	public double getPixelWidth() {
		return pixelWidth;
	}
	
	public double getPixelHeight() {
		return pixelHeight;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getRightBorder() {
		return pixelWidth + pixelX;
	}
	
	public double getBotBorder() {
		return pixelHeight + pixelY;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setPixelX(double x) {
		pixelX = x;
	}
	
	public void setPixelY(double y) {
		pixelY = y;
	}
	
	public double getBottom() {
		return pixelY - pixelHeight;
	}
	
	public double getRightBound() {
		return pixelX + pixelWidth;
	}
	
	public double getMidX() {
		return pixelX + 0.5*pixelWidth;
	}
	
	public double getMidY() {
		return pixelY + 0.5*pixelHeight;
	}
	
	public void setErase() {
		toBeErased = true;
	}
	
	public boolean shouldErase() {
		return toBeErased;
	}
	
	public int getTimer() {
		return internalTimer;
	}
	
	public void incrementTimer() {
		internalTimer++;
	}
	
	public void resetTimer() {
		internalTimer = 0;
	}
	
	public void startTimer() {
		timerStarted = true;
	}

}
=======
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
>>>>>>> be47220a2d0a237f3cc7a96c748cbc1a94741040
