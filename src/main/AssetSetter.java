package main;

import entity.NPC_Carol;
import entity.NPC_Nelipe;
import entity.NPC_Vilson;
import entity.NPC_Ismael;
import entity.NPC_Nelder;
import object.obj_Boots;
import object.obj_Caderno;
import object.obj_Chest;
import object.obj_Clip;
import object.obj_Door;
import object.obj_Fuse;
import object.obj_Gaveteiro;
import object.obj_Key;
import object.obj_Letter;
import object.obj_Letter2;
import object.obj_Letter3;
import object.obj_Letter4;
import object.obj_Letter5;
import object.obj_PowerBox;
import tilesOverlay.over_Teto01;
import tilesOverlay.over_Teto02;
import tilesOverlay.over_Teto03;
import tilesOverlay.over_Teto04;
import tilesOverlay.over_Teto05;
import tilesOverlay.over_Teto06;
import tilesOverlay.over_chair;
import tilesOverlay.over_wallace01;
import tilesOverlay.over_wallace02;

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
		
		//PUZZLE CAIXA DE ENERGIA
//		gp.obj[mapNum][i] = new obj_Fuse(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 66;  // coluna 10
//		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
//		i++;
//		
//		gp.obj[mapNum][i] = new obj_Clip(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 66;  // coluna 10
//		gp.obj[mapNum][i].worldY = gp.tileSize * 23;   // linha 8
//		i++;
//		
//		
//		gp.obj[mapNum][i] = new obj_Fuse(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 43;  // coluna 10
//		gp.obj[mapNum][i].worldY = gp.tileSize * 54;   // linha 8
//		i++;
//		
//		gp.obj[mapNum][i] = new obj_Fuse(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 44;  // coluna 10
//		gp.obj[mapNum][i].worldY = gp.tileSize * 23;   // linha 8
//		i++;
//		
//		gp.obj[mapNum][i] = new obj_PowerBox(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 46;  // coluna 10
//		gp.obj[mapNum][i].worldY = gp.tileSize * 34;   // linha 8
//		i++;
		
		
		gp.obj[mapNum][i] = new obj_Fuse(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 39;  // coluna 10
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
		i++;
		
		gp.obj[mapNum][i] = new obj_Clip(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 40;  // coluna 10
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
		i++;
		
		
		gp.obj[mapNum][i] = new obj_Fuse(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 41;  // coluna 10
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
		i++;
		
		gp.obj[mapNum][i] = new obj_Fuse(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 42;  // coluna 10
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
		i++;
		
		gp.obj[mapNum][i] = new obj_PowerBox(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 43;  // coluna 10
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;   // linha 8
		i++;
		
		
		
		//Mapa Ismael
		mapNum = 1;	
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*56;
		gp.obj[mapNum][i].worldY = gp.tileSize*45;
		i++;
		
		obj_Gaveteiro gav1 = new obj_Gaveteiro(gp);
		gav1.worldX = 44 * gp.tileSize;
		gav1.worldY = 24 * gp.tileSize;
		gav1.storedItems.add(new obj_Letter(gp));
		gp.obj[0][5] = gav1;
		
		//Mapa Carol
		mapNum = 2;	
		gp.obj[mapNum][i] = new obj_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*69;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		obj_Gaveteiro gav2 = new obj_Gaveteiro(gp);
		gav2.worldX = 74 * gp.tileSize;
		gav2.worldY = 21 * gp.tileSize;
		gav2.storedItems.add(new obj_Letter2(gp));
		gp.obj[2][1] = gav2;
		
		//Mapa Helder, Vilson
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
		
		obj_Gaveteiro gav3 = new obj_Gaveteiro(gp);
		gav3.worldX = 26 * gp.tileSize;
		gav3.worldY = 37 * gp.tileSize;
		gav3.storedItems.add(new obj_Letter3(gp));
		gp.obj[3][2] = gav3;
		
		obj_Gaveteiro gav4 = new obj_Gaveteiro(gp);
		gav4.worldX = 74 * gp.tileSize;
		gav4.worldY = 21 * gp.tileSize;
		gav4.storedItems.add(new obj_Letter4(gp));
		gp.obj[3][3] = gav4;
		
		obj_Gaveteiro gav5 = new obj_Gaveteiro(gp);
		gav5.worldX = 35 * gp.tileSize;
		gav5.worldY = 14 * gp.tileSize;
		gav5.storedItems.add(new obj_Letter5(gp));
		gp.obj[3][4] = gav5;
		
		obj_Gaveteiro gav6 = new obj_Gaveteiro(gp);
		gav6.worldX = 74 * gp.tileSize;
		gav6.worldY = 74 * gp.tileSize;
		gav6.storedItems.add(new obj_Caderno(gp));
		gp.obj[3][5] = gav6;
		
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
		gp.npc[mapNum][i].worldX = gp.tileSize * 70;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		mapNum = 3;	
		gp.npc[mapNum][i] = new NPC_Nelder(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 27;
		gp.npc[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		mapNum = 3;	
		gp.npc[mapNum][i] = new NPC_Vilson(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 71;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
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
		
		gp.tilesOver[mapNum][i] = new over_wallace02(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 63;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_wallace01(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 63;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 30;
		i++;
		
		
		// CADEIRAS
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 38;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 40;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 39;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 27;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 44;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 46;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 45;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 27;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 47;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 27;
		i++;
		
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 34;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 38;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 40;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 44;
		i++;
		
		gp.tilesOver[mapNum][i] = new over_chair(gp);
		gp.tilesOver[mapNum][i].worldX = gp.tileSize * 65;
		gp.tilesOver[mapNum][i].worldY = gp.tileSize * 46;
		i++;
	}
}
