package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter extends Entity{

	public obj_Letter(GamePanel gp) {
		super(gp);
		
		name = "Anotação sala 1";
		ObjImage = setup("/objects/letter");
		description = "[" + name + "]\nUma carta misteriosa.";
	}
}
