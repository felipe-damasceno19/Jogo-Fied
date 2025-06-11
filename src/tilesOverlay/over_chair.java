package tilesOverlay;

import entity.Entity;
import main.GamePanel;

public class over_chair extends Entity {

	public over_chair (GamePanel gp) {
		
		super(gp);
		ObjImage = setup("/tiles_over/chair");
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 35;
		solidArea.width = 50;
		solidArea.height = 10;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
