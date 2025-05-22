package object;

import entity.Entity;
import main.GamePanel;

public class obj_Key extends Entity {

	
	public obj_Key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1 = setup("/objects/key");
	}
	
}
