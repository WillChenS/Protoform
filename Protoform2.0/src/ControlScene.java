import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ControlScene extends Scene{
	
	public ControlScene(Pane pane, PlayerSprite player) {
		super(pane);
		
		this.addEventHandler(KeyEvent.KEY_PRESSED, e ->{
			if(e.getCode() == KeyCode.RIGHT) {
				player.walkRight();
			}
			if(e.getCode() == KeyCode.LEFT) {
				player.walkLeft();
			}
			if(e.getCode() == KeyCode.UP) {
				player.jump();
			}
		});
		
		this.addEventHandler(KeyEvent.KEY_RELEASED, e ->{
			if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT) {
				player.stopWalking();
			}
		});
		
	}
	
}
