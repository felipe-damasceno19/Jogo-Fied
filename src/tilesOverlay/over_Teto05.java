package tilesOverlay;

import entity.Entity;
import main.GamePanel;

public class over_Teto05 extends Entity {

	public over_Teto05 (GamePanel gp) {
		
		super(gp);
		ObjImage = setup("/tiles_over/teto05");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 8;
		solidArea.width = 48;
		solidArea.height = 70;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
