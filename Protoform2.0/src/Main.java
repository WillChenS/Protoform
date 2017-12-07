<<<<<<< HEAD
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage stage) throws Exception{
		
		StartPane startPane = new StartPane(stage, "Protoform", "Enter the dungeon");		
		Scene startScene = new Scene(startPane);
		stage.setScene(startScene);
		stage.centerOnScreen();
		stage.setTitle("Protoform");
		stage.show();
		
	}
	
	public static void main (String[]args) {
		launch(args);
	}
	
}
=======
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception{
		
		ProtoformPane gamePlatform = new ProtoformPane(ProtoformPane.GameStage.START);
		Scene scene = new Scene(gamePlatform);
		stage.setScene(scene);
		stage.setTitle("Protoform");
		stage.show();
		
	}
	
	public static void main (String[]args) {
		launch(args);
	}
	
}
>>>>>>> be47220a2d0a237f3cc7a96c748cbc1a94741040
