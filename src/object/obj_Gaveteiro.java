package object;

import entity.Entity;
import main.GamePanel;

public class obj_Gaveteiro extends Entity {

	public obj_Gaveteiro(GamePanel gp) {
		
		super(gp);
		name = "Gaveteiro";
		ObjImage = setup("/objects/Gaveteiro");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 40;
		solidArea.width = 48;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
