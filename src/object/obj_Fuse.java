package object;

import entity.Entity;
import main.GamePanel;

public class obj_Fuse extends Entity {

	public obj_Fuse(GamePanel gp) {
		
		super(gp);
		
		name = "Fuse";
	
		ObjImage = setup("/objects/fuse");
		description = "[" + name + "]\nUse isto para restaurar a energia da faculdade.";
		
		collision = true;

		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
}
