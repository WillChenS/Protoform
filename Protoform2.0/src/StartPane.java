
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartPane extends BorderPane{
	
	private PlayerSprite player;
	
	public StartPane(Stage stage, String titleString, String play) {
		super();
		this.setPrefSize(1000,500);
		
		Label title = new Label (titleString);
		//this.setTop(title);
		title.setFont(new Font ("Arial", 60));
		title.setTextFill(Color.RED);
		BorderPane.setAlignment(title, Pos.CENTER);
		
		VBox menu = new VBox();
		menu.setSpacing(30);
		menu.setAlignment(Pos.BASELINE_CENTER);
		Button start = new Button(play);
			start.setPrefHeight(400);
			start.setPrefWidth(300);
		start.setOnAction(e -> {
			ProtoformPane.resetStages();
			ProtoformPane gameScreen = new ProtoformPane(ProtoformPane.GameStage.START, stage);
			player = gameScreen.getPlayer();
			Scene gameScene = new Scene(gameScreen);
			setUpControls(gameScene, player);
			stage.setScene(gameScene);
			stage.centerOnScreen();
			});
		Button quit = new Button("Quit");
			quit.setPrefHeight(400);
			quit.setPrefWidth(300);
		quit.setOnAction(e -> stage.close());
		Image control = new Image("Images/Controls.png");
		ImageView controls = new ImageView(control);
		controls.setFitHeight(250);
		controls.setFitWidth(400);
		Label author = new Label("DISCLAIMER: This program is only for educational purposes, and all images belong to their respective owners.");
		author.setFont(new Font ("Arial", 20));
		author.setTextFill(Color.AZURE);
		menu.getChildren().addAll(title, start, quit, controls, author);
		this.setCenter(menu);
		
		BackgroundImage wall = new BackgroundImage(new Image("Images/gray.png"),
		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		this.setBackground(new Background(wall));
	}
	
	public static void setUpControls(Scene scene, PlayerSprite player) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e ->{
			if(e.getCode() == KeyCode.RIGHT) {
				player.walkRight();
			}
			if(e.getCode() == KeyCode.LEFT) {
				player.walkLeft();
			}
			if(e.getCode() == KeyCode.UP) {
				player.jump();
			}
			if(e.getCode() ==KeyCode.A) {
				player.attack();
			}
		});
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, e ->{
				player.stopWalking();
		});
	}
	
}
