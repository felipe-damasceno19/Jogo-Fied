package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class obj_Chest extends SuperObject{

	GamePanel gp;
	
	public obj_Chest(GamePanel gp) {
		
		this.gp = gp;
		name = "Chest";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
