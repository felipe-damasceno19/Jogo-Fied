package object;


import entity.Entity;
import main.GamePanel;

public class obj_Door extends Entity {

	public obj_Door(GamePanel gp) {
		
		super(gp);
		name = "Door";
		ObjImage = setup("/objects/door");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
	
	
}
