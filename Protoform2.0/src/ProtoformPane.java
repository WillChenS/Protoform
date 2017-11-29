import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;

public class ProtoformPane extends Pane{
	
	public enum GameStage{
		START, MIDDLE, FINAL
	}
	
	private int canvasWidth = Mapper.MAP_WIDTH*Mapper.TILE_WIDTH;
	private int canvasHeight = Mapper.MAP_HEIGHT*Mapper.TILE_WIDTH;
	private Canvas canvas;
	private GraphicsContext gc;
	private Mapper map;
	
	private static String startDirectory = "Maps/startMap.txt";
	
	public ProtoformPane(GameStage stage){
		
		super();
		
		canvas = new Canvas(canvasWidth,canvasHeight);
		gc = canvas.getGraphicsContext2D();
		
		this.getChildren().add(canvas);
		
		if (stage == GameStage.START) {
			map = new Mapper(new File(startDirectory));
		}
		
		renderInitialSprites();
		setBackgroundImage();
	}
	
	private void renderInitialSprites() {
		Sprite[][] spriteMap = map.initialMap(Tile.Terrain.ROCKY);
		for (int i = 0; i < spriteMap.length; i++) {
			for(int j = 0; j < spriteMap[0].length; j++) {
				if(spriteMap[i][j] instanceof Sprite) spriteMap[i][j].render(gc);
			}
		}
	}
	
	private void setBackgroundImage() {
		BackgroundImage wall= new BackgroundImage(new Image("Images/wall.jpg",canvasWidth,canvasHeight,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		this.setBackground(new Background(wall));
	}

}
