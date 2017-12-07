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
