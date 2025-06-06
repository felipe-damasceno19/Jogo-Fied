package tilesOverlay;

import entity.Entity;
import main.GamePanel;

public class over_Teto04 extends Entity {

	public over_Teto04 (GamePanel gp) {
		
		super(gp);
		ObjImage = setup("/tiles_over/teto04");
		collision = true;
		
		solidArea.x = 32;
		solidArea.y = 8;
		solidArea.width = 32;
		solidArea.height = 70;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
