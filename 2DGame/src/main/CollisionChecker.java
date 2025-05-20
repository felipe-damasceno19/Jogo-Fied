package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker (GamePanel gp){
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {

	    // Pega a posição da **borda esquerda** da área de colisão no mundo
	    int entityLeftWorldX = entity.worldX + entity.solidArea.x;

	    // Pega a posição da **borda direita** da área de colisão no mundo
	    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

	    // Pega a posição da **borda superior** da área de colisão no mundo
	    int entityTopWorldY = entity.worldY + entity.solidArea.y;

	    // Pega a posição da **borda inferior** da área de colisão no mundo
	    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

	    // Converte essas posições para coordenadas de **coluna e linha do mapa**
	    int entityLeftCol = entityLeftWorldX / gp.tileSize;
	    int entityRightCol = entityRightWorldX / gp.tileSize;
	    int entityTopRow = entityTopWorldY / gp.tileSize;
	    int entityBottomRow = entityBottomWorldY / gp.tileSize;

	    int tileNum1, tileNum2; // Dois tiles que serão checados dependendo da direção

	    // Verifica em qual direção o personagem está se movendo
	    switch(entity.direction) {

	        case "up":
	            // Calcula a nova linha superior após se mover para cima
	            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

	            // Pega os dois tiles da linha superior (esquerda e direita)
	            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

	            // Se qualquer um desses tiles tiver colisão, ativa o flag
	            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
	                entity.collisionOn = true;
	            }
	            break;

	        case "down":
	            // Calcula a nova linha inferior após se mover para baixo
	            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

	            // Pega os dois tiles da linha inferior (esquerda e direita)
	            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

	            // Verifica colisão
	            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
	                entity.collisionOn = true;
	            }
	            break;

	        case "left":
	            // Calcula a nova coluna à esquerda após se mover para a esquerda
	            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

	            // Pega os dois tiles da coluna esquerda (em cima e embaixo)
	            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

	            // Verifica colisão
	            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
	                entity.collisionOn = true;
	            }
	            break;

	        case "right":
	            // Calcula a nova coluna à direita após se mover para a direita
	            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

	            // Pega os dois tiles da coluna direita (em cima e embaixo)
	            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

	            // Verifica colisão
	            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
	                entity.collisionOn = true;
	            }
	            break;
	    }
	}
	
	public int checkObjetct(Entity entity, boolean player) {

		int index = 999; // Valor padrão (sem colisão encontrada)

		for(int i = 0; i < gp.obj.length; i++) {

			if(gp.obj[i] != null) { // Se existe um objeto na posição i

				// Ajusta a área sólida da entidade com base na posição no mundo
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				// Ajusta a área sólida do objeto com base na posição no mundo
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				switch(entity.direction) {
				case "up": // Se a entidade está indo pra cima
					entity.solidArea.y -= entity.speed; // Move a área sólida pra onde ela estaria
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // Se colidiu com o objeto
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true; // Marca que houve colisão
						}
						if(player == true) {
							index = i; // Salva o índice do objeto colidido
						}
					}
					break;

				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;

				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;

				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}

				// Reseta a área sólida pros valores padrão depois do teste
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index; // Retorna o índice do objeto colidido (ou 999 se não colidiu)
	}

	//COLISAO DE MONSTROS E NPCS
	public int checkEntity(Entity entity, Entity[] target) {
		
		int index = 999; // Valor padrão (sem colisão encontrada)

		for(int i = 0; i < target.length; i++) {

			if(target[i] != null) { // Se existe um objeto na posição i

				// Ajusta a área sólida da entidade com base na posição no mundo
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				// Ajusta a área sólida do objeto com base na posição no mundo
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

				switch(entity.direction) {
				case "up": // Se a entidade está indo pra cima
					entity.solidArea.y -= entity.speed; // Move a área sólida pra onde ela estaria
					if(entity.solidArea.intersects(target[i].solidArea)) { // Se colidiu com o objeto
						
						entity.collisionOn = true; // Marca que houve colisão
						
						index = i; // Salva o índice do objeto colidido
						}
					
					break;

				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionOn = true;
						
						index = i;
					}
					break;

				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionOn = true;						
						
						index = i;
						
					}
					break;

				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						
						entity.collisionOn = true;						
						
						index = i;					
					}
					break;
				}

				// Reseta a área sólida pros valores padrão depois do teste
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	public void checkPlayer(Entity entity) {
		
		// Ajusta a área sólida da entidade com base na posição no mundo
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		// Ajusta a área sólida do objeto com base na posição no mundo
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

		switch(entity.direction) {
		case "up": // Se a entidade está indo pra cima
			entity.solidArea.y -= entity.speed; // Move a área sólida pra onde ela estaria
			if(entity.solidArea.intersects(gp.player.solidArea)) { // Se colidiu com o objeto
				
				entity.collisionOn = true; // Marca que houve colisão
				
				}
			
			break;

		case "down":
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
				entity.collisionOn = true;
				
			}
			break;

		case "left":
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
				entity.collisionOn = true;						
				
				
			}
			break;

		case "right":
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
				entity.collisionOn = true;						
						
			}
			break;
		}

		// Reseta a área sólida pros valores padrão depois do teste
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
}
