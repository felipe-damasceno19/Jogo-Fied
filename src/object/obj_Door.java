package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class obj_Door extends SuperObject {

	GamePanel gp;
	
	public obj_Door(GamePanel gp) {
		
		this.gp = gp;
		name = "Door";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
		 collision = true;
	}
	
	
	
}
