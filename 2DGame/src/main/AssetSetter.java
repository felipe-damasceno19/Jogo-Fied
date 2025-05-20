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
		
	
	}
	public void setNpc() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
		
	}
}
