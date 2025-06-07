package main;

import entity.NPC_Carol;
import entity.NPC_Nelipe;
import entity.NPC_Ismael;
import entity.NPC_Nelder;
import object.obj_Boots;
import object.obj_Chest;
import object.obj_Door;
import object.obj_Gaveteiro;
import object.obj_Key;
import tilesOverlay.over_Teto01;
import tilesOverlay.over_Teto02;
import tilesOverlay.over_Teto03;
import tilesOverlay.over_Teto04;
import tilesOverlay.over_Teto05;
import tilesOverlay.over_Teto06;

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
		gp.obj[mapNum][i].worldX = gp.tileSize*56;
		gp.obj[mapNum][i].worldY = gp.tileSize*45;
		i++;
		gp.obj[mapNum][i] = new obj_Gaveteiro(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*55;
		gp.obj[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		mapNum = 2;	
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*69;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		gp.obj[mapNum][i] = new obj_Gaveteiro(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*76;
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
		gp.npc[mapNum][i] = new NPC_Nelipe(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 27;
		gp.npc[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
		
		// SETA NPC NO MAPA 1
		mapNum = 1;	
		gp.npc[mapNum][i] = new NPC_Ismael(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 53;
		gp.npc[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		
		mapNum = 2;	
		gp.npc[mapNum][i] = new NPC_Carol(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 74;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		mapNum = 3;	
		gp.npc[mapNum][i] = new NPC_Nelder(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 27;
		gp.npc[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		
	}
	
	public void setTilesOver() {
		
		// SETA NPC NO MAPA 0
		int mapNum = 0;	
		int i = 0;
		gp.tilesOver[mapNum][i] = new over_Teto01(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 24;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_Teto02(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 25;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_Teto03(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 26;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_Teto04(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 24;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_Teto05(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 25;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_Teto06(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 26;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
	}
}
