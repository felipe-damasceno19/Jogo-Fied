package object;

import entity.Entity;
import main.GamePanel;

public class obj_Boots extends Entity {
	

	public obj_Boots(GamePanel gp) {
		super(gp);
	
		name = "boots";
		down1 = setup("/objects/boots");

	}
	
}
