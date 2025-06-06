package main;

import java.awt.Color;
import java.awt.Graphics;
import entity.Entity;
import object.obj_Gaveteiro;
import object.obj_Key;
import object.obj_Letter;

public class EventHandler {

	GamePanel gp; // Referência ao painel principal do jogo
	EventRect eventRect[][][]; // Matriz de áreas de evento para cada tile do mundo
	
	int previousEventX, previousEventY; // Posição do último evento ativado
	boolean canTouchEvent = true; // Flag que determina se o player pode ativar outro evento
	
	public EventHandler(GamePanel gp) {
		
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; // Inicializa a matriz de eventos com base no tamanho do mundo
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();   // Cria uma nova área de evento para cada tile
			eventRect[map][col][row].x = 23;				 // Define posição X relativa dentro do tile
			eventRect[map][col][row].y = 23; 			 // Define posição Y relativa dentro do tile
			eventRect[map][col][row].width = 30; 			 // Largura da área de detecção
			eventRect[map][col][row].height = 30; 		 // Altura da área de detecção
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x; // Armazena valor original para reset
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++; // Avança para o próximo tile na coluna
			if(col == gp.maxWorldCol) { // Quando termina a linha, vai para a próxima
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
    
	public void checkEvent() {
		
		// CHECA SE O PLAYER ESTÁ A MAIS DE 1 TILE DE DISTÂNCIA DO ÚLTIMO EVENTO
		int xDistance = Math.abs(gp.player.worldX - previousEventX); // Distância horizontal
		int yDistance = Math.abs(gp.player.worldY - previousEventY); // Distância vertical
		int distance = Math.max(xDistance, yDistance); // Considera a maior das distâncias
		//System.out.println(gp.keyH.enterPressed);
		if(distance > gp.tileSize) { // Se o player se afastou o suficiente
			canTouchEvent = true; // Permite ativar novos eventos
		}
		
		if (canTouchEvent) { // Se pode ativar eventos
	        // Verifica se a tecla F foi pressionada
	        boolean fPressedNow = gp.keyH.fPressed; // Obtém o estado atual de F
	        // MAPA 0
	        
	        if (gp.currentMap == 0) {
	            // Checa se o player colidiu com algum evento e executa se sim
	        	
	        	
	        	// PORTAS MAPA 0
	        	
	        	// Teleporta para o mapa 1 - SALA 1
	            if (hit(0, 33, 25, "any") && fPressedNow) {teleport(1, 57, 51);}
	           
	            // Teleporta para o mapa 2 - SALA 2
	            if (hit(0, 35, 25, "any") && fPressedNow) {teleport(2, 69, 33);}
	            
	            // Teleporta para o mapa 3 - CORREDOR
	            if (hit(0, 37, 28, "any") && fPressedNow) {teleport(3, 22, 84);}
	            
	            // Teleporta para Consultorio
	            if (hit(0, 26, 23, "any") && fPressedNow) {teleport(3, 73, 77);}
	            
	        } else if(gp.currentMap == 1) { // MAPA 01
			    
	        	if(hit(1, 57, 52, "any") && fPressedNow){teleport(0,33,25);} 
			    }

			else if(gp.currentMap == 2) { // MAPA 02
			    if(hit(2, 69, 34, "any") && fPressedNow){teleport(0,35,24);} 
			}
			else if(gp.currentMap == 3) { // MAPA 03

			    // VOLTA MAPA PRINCIPAL
			    if(hit(3, 22, 85, "any") && fPressedNow){teleport(0,37,27);}

			    // ENTRA E SAI SALA 1 - BLOCO 1
			    if(hit(3, 24, 83, "any") && fPressedNow){teleport(3,25,41);} 
			    if(hit(3, 25, 42, "any") && fPressedNow){teleport(3,24,83);}

			    // ENTRA E SAI SALA 2 - BLOCO 1
			    if(hit(3, 28, 83, "any") && fPressedNow){teleport(3,69,33);}
			    if(hit(3, 69, 34, "any") && fPressedNow){teleport(3,28,83);}

			    // ENTRA E SAI SALA 3 - BLOCO 1
			    if(hit(3, 33, 83, "any") && fPressedNow){teleport(3,35,20);} 
			    if(hit(3, 35, 21, "any") && fPressedNow){teleport(3,33,83);}
			    
			    
			    // Sai do Consultorio
	            if (hit(3, 73, 78, "any") && fPressedNow) {teleport(0, 26, 23);}
			    			   
			}			
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		// Atualiza as posições da área sólida do jogador e do retângulo de evento para posição absoluta no mundo
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
		eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
		
		// Verifica colisão entre a área do jogador e o retângulo de evento

		if(map == gp.currentMap) {
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				// Verifica se a direção do jogador é a exigida ou se qualquer direção serve
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					gp.player.nearInteractable = true;
					gp.ui.interactKey = "f"; // muda para tecla F

					// Armazena a posição onde o evento foi ativado
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			// Reseta as posições dos retângulos para seus valores padrões
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		
		return hit; // Retorna se houve colisão válida com o evento
	}
	
	public void teleport(int map, int col, int row) {
		
		if(map != gp.currentMap) gp.currentMap = map;	
		
		
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		
		
		canTouchEvent = false;
		gp.player.nearInteractable = false;
		//gp.playSE(01);
		
		// Atualiza a área de colisão do jogador imediatamente após o teleporte
	    gp.player.solidArea.x = gp.player.worldX + gp.player.solidAreaDefaultX;
	    gp.player.solidArea.y = gp.player.worldY + gp.player.solidAreaDefaultY;

	    // Força a atualização da colisão do jogador após o teleporte
	    gp.cChecker.checkTile(gp.player); // Aqui, estamos forçando uma nova checagem de colisão após o teleporte
	    
	    gp.gameStage.checkStage();
	}

}
