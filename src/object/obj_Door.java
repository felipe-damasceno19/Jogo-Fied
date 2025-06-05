package object;

import java.awt.Color;
import java.awt.Graphics;

import entity.Entity;
import main.GamePanel;

public class obj_Door extends Entity {

	public boolean locked = true;
	
	public obj_Door(GamePanel gp) {
		
		super(gp);
		name = "Door";
		ObjImage = setup("/objects/doorVidro");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 40;
		solidArea.width = 48;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
}
