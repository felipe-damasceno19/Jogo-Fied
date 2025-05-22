package object;

import entity.Entity;
import main.GamePanel;

public class obj_Chest extends Entity{
	
	public obj_Chest(GamePanel gp) {
		
		super(gp);
		
		name = "Chest";
		down1 = setup("/objects/chest(OLD)");
		collision = true;
			
	}
}