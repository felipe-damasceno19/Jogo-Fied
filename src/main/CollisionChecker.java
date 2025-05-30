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

	            // Verificação para garantir que os índices de tile estão dentro dos limites
	            if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow) {
	                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
	            } else {
	                tileNum1 = -1; // Atribui um valor inválido se os índices estiverem fora dos limites
	            }

	            if (entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow) {
	                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
	            } else {
	                tileNum2 = -1;
	            }

	            // Se qualquer um desses tiles tiver colisão, ativa o flag
	            if (tileNum1 != -1 && gp.tileM.tile[tileNum1].collision || tileNum2 != -1 && gp.tileM.tile[tileNum2].collision) {
	                entity.collisionOn = true;
	            }
	            break;

	        // Os outros casos (down, left, right) devem seguir a mesma lógica de verificação de limites

	        case "down":
	            // Calcula a nova linha inferior após se mover para baixo
	            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

	            // Verificação para garantir que os índices de tile estão dentro dos limites
	            if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
	                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
	            } else {
	                tileNum1 = -1;
	            }

	            if (entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
	                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
	            } else {
	                tileNum2 = -1;
	            }

	            // Verifica colisão
	            if (tileNum1 != -1 && gp.tileM.tile[tileNum1].collision || tileNum2 != -1 && gp.tileM.tile[tileNum2].collision) {
	                entity.collisionOn = true;
	            }
	            break;

	        case "left":
	            // Calcula a nova coluna à esquerda após se mover para a esquerda
	            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

	            // Verificação para garantir que os índices de tile estão dentro dos limites
	            if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow) {
	                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
	            } else {
	                tileNum1 = -1;
	            }

	            if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
	                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
	            } else {
	                tileNum2 = -1;
	            }

	            // Verifica colisão
	            if (tileNum1 != -1 && gp.tileM.tile[tileNum1].collision || tileNum2 != -1 && gp.tileM.tile[tileNum2].collision) {
	                entity.collisionOn = true;
	            }
	            break;

	        case "right":
	            // Calcula a nova coluna à direita após se mover para a direita
	            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

	            // Verificação para garantir que os índices de tile estão dentro dos limites
	            if (entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && entityTopRow >= 0 && entityTopRow < gp.maxWorldRow) {
	                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
	            } else {
	                tileNum1 = -1;
	            }

	            if (entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {
	                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
	            } else {
	                tileNum2 = -1;
	            }

	            // Verifica colisão
	            if (tileNum1 != -1 && gp.tileM.tile[tileNum1].collision || tileNum2 != -1 && gp.tileM.tile[tileNum2].collision) {
	                entity.collisionOn = true;
	            }
	            break;
	    }
	}

	public int checkObjetct(Entity entity, boolean player) {

		int index = 999; // Valor padrão (sem colisão encontrada)

		for(int i = 0; i < gp.obj[1].length; i++) {

			if(gp.obj[gp.currentMap][i] != null) { // Se existe um objeto na posição i

				// Ajusta a área sólida da entidade com base na posição no mundo
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				// Ajusta a área sólida do objeto com base na posição no mundo
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

				switch(entity.direction) {
				case "up": // Se a entidade está indo pra cima
					entity.solidArea.y -= entity.speed; // Move a área sólida pra onde ela estaria
					if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) { // Se colidiu com o objeto
						if(gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionOn = true; // Marca que houve colisão
						}
						if(player == true) {
							index = i; // Salva o índice do objeto colidido
						}
					}
					break;

				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;

				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;

				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
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
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			}
		}
		return index; // Retorna o índice do objeto colidido (ou 999 se não colidiu)
	}

	//COLISAO DE MONSTROS E NPCS
	public int checkEntity(Entity entity, Entity[][] target) {
		
		int index = 999; // Valor padrão (sem colisão encontrada)

		for(int i = 0; i < target[1].length; i++) {

			if(target[gp.currentMap][i] != null) { // Se existe um objeto na posição i

				// Ajusta a área sólida da entidade com base na posição no mundo
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				// Ajusta a área sólida do objeto com base na posição no mundo
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

				switch(entity.direction) {
				case "up": // Se a entidade está indo pra cima
					entity.solidArea.y -= entity.speed; // Move a área sólida pra onde ela estaria
					if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) { // Se colidiu com o objeto
						
						entity.collisionOn = true; // Marca que houve colisão
						
						index = i; // Salva o índice do objeto colidido
						}
					
					break;

				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						
						entity.collisionOn = true;
						
						index = i;
					}
					break;

				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						
						entity.collisionOn = true;						
						
						index = i;
						
					}
					break;

				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
						
						entity.collisionOn = true;						
						
						index = i;					
					}
					break;
				}

				// Reseta a área sólida pros valores padrão depois do teste
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
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
