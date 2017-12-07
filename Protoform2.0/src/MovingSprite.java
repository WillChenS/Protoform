import java.util.LinkedList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class MovingSprite extends Sprite {

	private boolean forward = true;
	private double speed;
	private double jumpPower;
	public static double GRAVITY = 0.3;
	private Image[] currentState;
	private int currentStateIndex = 0;
	private double attackRange;
	private double originalPixelWidth;
	private boolean widthExpanded = false;
	private boolean isAttacking = false;

	private Image[] walking;
	private Image[] idle;
	private Image[] jumping;
	private Image[] dying;
	private Image[] hurting;
	private Image[] attacking;

	public MovingSprite(Image image, int x, int y, double width, double height, double speed, double jumpPower) {
		super(image, x, y, width, height);
		this.speed = speed;
		this.jumpPower = jumpPower;
		originalPixelWidth = getPixelWidth();
	}

	@Override
	public void render(GraphicsContext gc) {
		accelerateY(GRAVITY);
		if (forward)
			super.render(gc);
		else
			super.renderBackwards(gc);
	}
	
	public void reverseRender(GraphicsContext gc) {
		accelerateY(GRAVITY);
		if (!forward)
			super.render(gc);
		else
			super.renderBackwards(gc);
	}

	public void incrementState() {
		if (currentState == jumping) {
			if (currentStateIndex == currentState.length - 1)
				return;
		}
		currentStateIndex++;
		if (currentStateIndex >= currentState.length)
			currentStateIndex = 0;
		super.setImage(currentState[currentStateIndex]);
	}

	public void walkRight() {
		super.setXSpeed(speed);
		if (currentState != jumping && walking != null)
			currentState = walking;
		forward = true;
		correctExpansion();
	}

	public void walkLeft() {
		super.setXSpeed(-speed);
		if (currentState != jumping && walking != null)
			currentState = walking;
		forward = false;
		correctExpansion();
	}

	public void stopWalking() {
		if (currentState != jumping && idle != null && !isAttacking()) {
			super.setXSpeed(0);
			currentState = idle;
		}
	}

	public void clear(GraphicsContext gc) {
		super.clear(gc);
	}

	public void residualClear(GraphicsContext gc) {
		gc.clearRect(getPixelX(), getPixelY(), originalPixelWidth + attackRange, getPixelHeight());
	}

	public void jump() {
		if (currentState != jumping && jumping != null) {
			super.setYSpeed(-jumpPower);
			currentState = jumping;
		}
		else super.setYSpeed(-jumpPower);
		correctExpansion();
	}

	private void correctExpansion() {
		if (widthExpanded) {
			decrementWidth(attackRange);
			widthExpanded = false;
		}
	}

	public void dying() {
		currentState = dying;
	}

	@Override
	public abstract Rectangle2D getHitbox();

	public void attack() {
		if (!isAttacking()) {
			setXSpeed(0);
			currentState = attacking;
			currentStateIndex = 0;
			isAttacking = true;
			incrementWidth(attackRange);
			widthExpanded = true;
			if (!forward)
				decrementX(attackRange);
		}
	}

	public void setAttackRange(double range) {
		attackRange = range;
		originalPixelWidth = getPixelWidth();
	}

	public void resolveCollisions(LinkedList<Sprite> surroundingSprites) {
		// set speed, decide if block is top or side
		if (getPixelX() <= 0) {
			//setXSpeed(0);
			setPixelX(0);
		}
		if (getRightBorder() >= ProtoformPane.canvasWidth) {
			//setXSpeed(0);
			setPixelX(ProtoformPane.canvasWidth - getPixelWidth());
		}
		for (Sprite sprite : surroundingSprites) {
			if(sprite instanceof BossSprite && ((BossSprite)sprite).isDead()) sprite.setErase();
			if (sprite instanceof EndTile == false) {
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
								if (currentState == jumping) {
									if (getXSpeed() == 0)
										currentState = idle;
									else
										currentState = walking;
								}
							} else {
								// left
								setXSpeed(0);
								setPixelX(sprite.getRightBorder());
								if (currentState == attacking && forward == false && sprite instanceof MovingSprite) {
									if(sprite instanceof MonsterSprite) sprite.setErase();
									sprite.startTimer();
									((MovingSprite)sprite).leftReact();
								}
							}
							if (sprite instanceof MovingSprite && !isAttacking() && this instanceof BossSprite == false)
								leftReact();
						} else {
							if (wy > -hx) {
								// right
								setXSpeed(0);
								setPixelX(sprite.getPixelX() - this.getPixelWidth());
								if (currentState == attacking && forward == true && sprite instanceof MovingSprite) {
									if (sprite instanceof MonsterSprite) sprite.setErase();
									sprite.startTimer();
									((MovingSprite)sprite).rightReact();
								}

							} else {
								// top
								setYSpeed(0);
								setPixelY(sprite.getPixelY() + sprite.getPixelHeight());
							}
							if (sprite instanceof MovingSprite && !isAttacking && this instanceof BossSprite == false)
								rightReact();
						}
					}
				}
			}
		}
	}

	public abstract void leftReact();

	public abstract void rightReact();

	public void setWalkingFrames(Image[] frames) {
		walking = frames;
	}

	public void setIdleFrames(Image[] frames) {
		idle = frames;
	}

	public void setDyingFrames(Image[] frames) {
		dying = frames;
	}

	public void setJumpingFrames(Image[] frames) {
		jumping = frames;
	}

	public void setHurtingFrames(Image[] frames) {
		hurting = frames;
	}

	public void setAttackingFrames(Image[] frames) {
		attacking = frames;
	}

	public void setInitialState() {
		currentState = idle;
	}

	public void setHurtState() {
		currentState = hurting;
	}

	public void update(int time) {
		super.update(time);
	}

	public boolean isAttacking() {
		return (isAttacking);
	}

	public void endAttack() {
		isAttacking = false;
		if (getXSpeed() == 0)
			currentState = idle;
		else
			currentState = walking;
		correctExpansion();
		if (!forward)
			incrementX(attackRange);
		currentStateIndex = 0;
	}

	public void resetCurrentStateIndex() {
		currentStateIndex = 0;
	}

	public boolean dead() {
		setXSpeed(0);
		return super.shouldErase();
	}
	
	public void setDyingState() {
		currentState = dying;
	}

}
