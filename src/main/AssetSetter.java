package main;

import entity.NPC_OldMan;
import object.obj_Boots;
import object.obj_Chest;
import object.obj_Door;
import object.obj_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new obj_Door(gp);
		gp.obj[0].worldX = gp.tileSize*53;
		gp.obj[0].worldY = gp.tileSize*38;
		
		
	
	}
	public void setNpc() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 54;
		gp.npc[0].worldY = gp.tileSize * 37;
		
	
		
	}
}
