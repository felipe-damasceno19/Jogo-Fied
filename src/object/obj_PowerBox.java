package object;

import entity.Entity;
import main.GamePanel;

public class obj_PowerBox extends Entity{

	public obj_PowerBox(GamePanel gp) {
		
	
		super(gp);
		name = "Power_Box";
		ObjImage = setup("/objects/powerBox");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 40;
		solidArea.width = 48;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}

}
