import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProtoformPane extends Pane {

	public enum GameStage {
		START, MIDDLE, PENULTIMATE, FINAL
	}

	public static int canvasWidth = Mapper.MAP_WIDTH * Mapper.TILE_WIDTH;
	public static int canvasHeight = Mapper.MAP_HEIGHT * Mapper.TILE_WIDTH;
	private Canvas canvas;
	private GraphicsContext gc;
	private Mapper map;
	private LinkedList<Sprite> sprites;
	private int animationCounter = 0;
	private int controlLockCounter = 0;
	private int deathAnimationCounter = 0;
	private int attackCounter = 0;
	public static int stageCounter = 0;
	private Stage stage;
	private int playerHealth = PlayerStats.PLAYER_HP;
	private Label health;
	private Timeline animation;

	private static String startDirectory = "Maps/startMap.txt";
	private static String middleDirectory = "Maps/midMap.txt";
	private static String penuDirectory = "Maps/penultimateMap.txt";
	private static String endDirectory = "Maps/endMap.txt";

	public ProtoformPane(GameStage gameStage, Stage stage) {

		super();
		sprites = new LinkedList<Sprite>();
		this.stage = stage;
		
		canvas = new Canvas(canvasWidth, canvasHeight);
		gc = canvas.getGraphicsContext2D();
		
		BorderPane pane = new BorderPane();
		getChildren().add(pane);
		pane.setCenter(canvas);
		health = new Label("HEALTH: " + playerHealth);
		health.setFont(new Font("Century Gothic", 30));
		health.setTextFill(Color.RED);
		pane.setTop(health);
		

		if (gameStage == GameStage.START) {
			map = new Mapper(new File(startDirectory));
		}

		else if (gameStage == GameStage.MIDDLE) {
			map = new Mapper(new File(middleDirectory));
		}
		
		else if (gameStage == GameStage.PENULTIMATE) {
			map = new Mapper(new File(penuDirectory));
		}
		
		else if (gameStage == GameStage.FINAL) {
			map = new Mapper(new File(endDirectory));
		}

		renderInitialSprites();
		setBackgroundImage();
		setUpAnimation();
	}

	private void renderInitialSprites() {
		Sprite[][] spriteMap = map.initialMap(Tile.Terrain.ROCKY);
		for (int i = 0; i < spriteMap.length; i++) {
			for (int j = 0; j < spriteMap[0].length; j++) {
				if (spriteMap[i][j] instanceof Sprite) {
					spriteMap[i][j].render(gc);
					sprites.add(spriteMap[i][j]);
				}
			}
		}
	}

	private void setBackgroundImage() {
		BackgroundImage wall = new BackgroundImage(new Image("Images/wall.png", canvasWidth, canvasHeight, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		this.setBackground(new Background(wall));
	}

	private void setUpAnimation() {
		animation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			if(getPlayer().getHitbox().intersects(endTile().getHitbox())) {
				nextScene();
				animation.stop();
			}
			Iterator<Sprite> i = sprites.iterator();
			while (i.hasNext()) {
				Sprite thisSprite = (Sprite) (i.next());
				if (thisSprite.getTimer() >= 70 && thisSprite.shouldErase()) {
					thisSprite.clear(gc);
					i.remove();
				}
			}
			for (Sprite sprite : sprites) {
				sprite.clear(gc);
				if (sprite instanceof MovingSprite) {
					((MovingSprite) sprite).residualClear(gc);
					sprite.update(1);
					((MovingSprite) sprite).resolveCollisions(sprites);
					if (((MovingSprite) sprite).isAttacking()) {
						if (animationCounter % 9 == 0) {
							((MovingSprite) sprite).incrementState();
							if (sprite instanceof PlayerSprite)
								((PlayerSprite) sprite).lockControls();
							attackCounter++;
							if (attackCounter > 4) {
								((PlayerSprite) sprite).unlockControls();
								((PlayerSprite) sprite).endAttack();
								attackCounter = 0;
							}
						}
					} else if (animationCounter % 5 == 0)
						((MovingSprite) sprite).incrementState();
					if (sprite instanceof PlayerSprite) {
						playerHealth = ((PlayerSprite)sprite).health();
						if(playerHealth < 0) playerHealth = 0;
						health.setText("HEALTH: " + playerHealth);
						if (((PlayerSprite) sprite).controlsLocked() && deathAnimationCounter == 0) {
							controlLockCounter++;
							if (controlLockCounter > 30) {
								((PlayerSprite) sprite).unlockControls();
								controlLockCounter = 0;
							}
						}
						if (((PlayerSprite) sprite).gameEnd()) {
							((MovingSprite) sprite).dying();
							((PlayerSprite) sprite).lockControls();
							deathAnimationCounter++;
							if (deathAnimationCounter > 100) {
								backToStart();
								animation.stop();
							}
						}
					}
				}
				sprite.render(gc);
			}
			animationCounter++;					
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	private void backToStart() {
		StartPane start = new StartPane(stage, "GAME OVER...", "Try Again");
		Scene startScene = new Scene(start);
		stage.setScene(startScene);
		stage.centerOnScreen();
	}
	
	private void finishGame() {
		StartPane start = new StartPane(stage, "You Won!", "Play Again");
		Scene startScene = new Scene(start);
		stage.setScene(startScene);
		stage.centerOnScreen();
	}

	private void nextScene() {	
		boolean stillPlaying = true;
		GameStage gameStage = GameStage.START;
		if(stageCounter == 0) gameStage = GameStage.MIDDLE;
		else if(stageCounter == 1) gameStage = GameStage.PENULTIMATE;
		else if(stageCounter == 2) gameStage = GameStage.FINAL;
		else {
			finishGame();
			stillPlaying = false;
		}
		if(stillPlaying) {
			ProtoformPane midScene = new ProtoformPane(gameStage, stage); 
			Scene gameScene = new Scene(midScene); 
			StartPane.setUpControls(gameScene,midScene.getPlayer()); 
			stage.setScene(gameScene); 
			stage.centerOnScreen();
			midScene.getPlayer().setHealth(playerHealth);
		}
		if(stageCounter >= 3) stageCounter = 0;
		stageCounter++;
	}
	
	public static void resetStages() {
		stageCounter = 0;
	}

	public PlayerSprite getPlayer() {
		return map.getPlayer();
	}
	
	public EndTile endTile() {
		return map.getEndTile();
	}

}
