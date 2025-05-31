package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter2 extends Entity{

		public obj_Letter2(GamePanel gp) {
			super(gp);
			
			name = "Anotação sala 2";
			ObjImage = setup("/objects/letterS");
			description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 2.";
		}
}

