package tilesOverlay;

import entity.Entity;
import main.GamePanel;

public class over_wallace02 extends Entity {

	public over_wallace02 (GamePanel gp) {
		
		super(gp);
		ObjImage = setup("/tiles_over/wallace_02");
		collision = false;
		
		solidArea.x = 0;
		solidArea.y = 8;
		solidArea.width = 32;
		solidArea.height = 70;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
