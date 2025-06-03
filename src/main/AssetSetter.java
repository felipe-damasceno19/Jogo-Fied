package main;

import entity.NPC_Carol;
import entity.NPC_OldMan;
import object.obj_Boots;
import object.obj_Chest;
import object.obj_Door;
import object.obj_Gaveteiro;
import object.obj_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;	
		int i = 0;
		//gp.obj[mapNum][i] = new obj_Door(gp);
		//gp.obj[mapNum][i].worldX = gp.tileSize*53;
		//gp.obj[mapNum][i].worldY = gp.tileSize*38;
		//i++;
		
		mapNum = 1;	
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*57;
		gp.obj[mapNum][i].worldY = gp.tileSize*52;
		i++;
		gp.obj[mapNum][i] = new obj_Gaveteiro(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*56;
		gp.obj[mapNum][i].worldY = gp.tileSize*45;
		i++;
		
		mapNum = 2;	
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*69;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		gp.obj[mapNum][i] = new obj_Gaveteiro(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*75;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		mapNum = 3;	
		
		// Porta Corredor - Bloco 1
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*85;
		i++;
		
		// Porta Sala 2 Bloco 1
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		// Porta Sala 2 Bloco 1
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*69;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		// Porta Sala 3 - Bloco 1
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*35;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		// Porta Consultorio - Mapa 0
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*73;
		gp.obj[mapNum][i].worldY = gp.tileSize*78;
		i++;
		
		gp.obj[mapNum][i] = new obj_Gaveteiro(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*74;
		gp.obj[mapNum][i].worldY = gp.tileSize*74;
		i++;
		
		
	
	}
	public void setNpc() {
		
		
		// SETA NPC NO MAPA 0
		int mapNum = 0;	
		int i = 0;
//		gp.npc[mapNum][i] = new NPC_OldMan(gp);
//		gp.npc[mapNum][i].worldX = gp.tileSize * 56;
//		gp.npc[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		
		// SETA NPC NO MAPA 1
		mapNum = 1;	
		gp.npc[mapNum][i] = new NPC_OldMan(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 56;
		gp.npc[mapNum][i].worldY = gp.tileSize * 44;
		i++;
		
		
		mapNum = 2;	
		gp.npc[mapNum][i] = new NPC_Carol(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 74;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		
	}
}
