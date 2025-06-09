package object;

import entity.Entity;
import main.GamePanel;

public class obj_Key extends Entity {

	
	public obj_Key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		ObjImage = setup("/objects/key");
		description = "[" + name + "]\nEu quero todos os paus.";
		
	}
	
}
