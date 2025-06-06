package tilesOverlay;

import entity.Entity;
import main.GamePanel;

public class over_Teto03 extends Entity {

	public over_Teto03 (GamePanel gp) {
		
		super(gp);
		ObjImage = setup("/tiles_over/teto03");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 40;
		solidArea.width = 48;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
