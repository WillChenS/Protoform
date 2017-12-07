import java.util.LinkedList;
import java.util.TimerTask;

public class UpdateTask extends TimerTask {
		
		public LinkedList<Sprite> sprites;
		int updateCount = 0;
		int updateNumber;
		
		public UpdateTask(LinkedList<Sprite> sprites, int updateNumber) {
			super();
			this.sprites = sprites;
			this.updateNumber = updateNumber;
		}
		
		public void run() {
			for(Sprite sprite:sprites) {
				if (sprite instanceof MovingSprite) {
					sprite.update(1);
					((MovingSprite)sprite).resolveCollisions(sprites);
				}
			}
			updateCount++;
			if(updateCount >= updateNumber) this.cancel();
		}
}