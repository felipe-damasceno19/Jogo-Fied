package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

// Classe Player (jogador), que herda da classe Entity (não fornecida aqui)
public class Player extends Entity {

	KeyHandler keyH; // Referência ao manipulador de teclas
	
	public final int screenX;  // Posição X do jogador na tela (fixa)
	public final int screenY;  // Posição Y do jogador na tela (fixa)
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);  // Armazena o painel do jogo
		this.keyH = keyH; // Armazena o manipulador de teclas
		
		// Centraliza o jogador na tela
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2); 
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		//Colisão do jogador
		solidArea = new Rectangle();
		solidArea.x = 18; //Margem esquerda
		solidArea.y = 18; //Margem superior
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16;
		solidArea.height = 22;

		setDefaultValues(); // Define posição inicial, direção e velocidade
		getPlayerImage(); // Carrega as imagens do jogador
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23; // Posição inicial X no mundo
		worldY = gp.tileSize * 21; // Posição inicial Y no mundo
		speed = 4; // Velocidade do jogador
		direction = "down"; // Direção inicial
		
		// PLAYER STATUS
		maxLife = 6;
		life = maxLife;
		
	}
	
	public void getPlayerImage() {
			
		up1 = setup("/player/Silas_top1");
		up2 = setup("/player/Silas_top2");
		up3 = setup("/player/Silas_top3");
		up4 = setup("/player/Silas_top4");
		up5 = setup("/player/Silas_top5");
		up6 = setup("/player/Silas_top6");
		
		down1 = setup("/player/Silas Down1");
		down2 = setup("/player/Silas Down2");
		down3 = setup("/player/Silas Down3");
		down4 = setup("/player/Silas Down4");
		down5 = setup("/player/Silas Down5");
		down6 = setup("/player/Silas Down6");
		
		left1 = setup("/player/Silas_left1");
		left2 = setup("/player/Silas_left2");
		left3 = setup("/player/Silas_left3");
		left4 = setup("/player/Silas_left4");
		left5 = setup("/player/Silas_left5");
		left6 = setup("/player/Silas_left6");
		
		right1 = setup("/player/Silas_right1");
		right2 = setup("/player/Silas_right2");
		right3 = setup("/player/Silas_right3");
		right4 = setup("/player/Silas_right4");
		right5 = setup("/player/Silas_right5");
		right6 = setup("/player/Silas_right6");
		
		up_stop1 = setup("/player/Silas_stp_up1");
		up_stop2 = setup("/player/Silas_stp_up2");
		up_stop3 = setup("/player/Silas_stp_up3");
		up_stop4 = setup("/player/Silas_stp_up4");
		up_stop5 = setup("/player/Silas_stp_up5");
		up_stop6 = setup("/player/Silas_stp_up6");
		
		down_stop1 = setup("/player/Silas_stp_down1");
		down_stop2 = setup("/player/Silas_stp_down2");
		down_stop3 = setup("/player/Silas_stp_down3");
		down_stop4 = setup("/player/Silas_stp_down4");
		down_stop5 = setup("/player/Silas_stp_down5");
		down_stop6 = setup("/player/Silas_stp_down6");
		
		left_stop1 = setup("/player/Silas_stp_left1");
		left_stop2 = setup("/player/Silas_stp_left2");
		left_stop3 = setup("/player/Silas_stp_left3");
		left_stop4 = setup("/player/Silas_stp_left4");
		left_stop5 = setup("/player/Silas_stp_left5");
		left_stop6 = setup("/player/Silas_stp_left6");
		
		right_stop1 = setup("/player/Silas_stp_right1");
		right_stop2 = setup("/player/Silas_stp_right2");
		right_stop3 = setup("/player/Silas_stp_right3");
		right_stop4 = setup("/player/Silas_stp_right4");
		right_stop5 = setup("/player/Silas_stp_right5");
		right_stop6 = setup("/player/Silas_stp_right6");
	}
		

	public void update() { 

		// Verifica se alguma tecla de movimento está sendo pressionada
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

			// Define a direção com base na tecla pressionada
			if(keyH.upPressed) {
			    direction = "up";
			}
			if(keyH.downPressed) {
			    direction = "down";
			}
			if(keyH.leftPressed) {
			    direction = "left";
			}
			if(keyH.rightPressed) {
			    direction = "right";
			}

			
			//COLISAO DE TILES
			collisionOn = false; // Marca a colisão como falsa antes de checar
			gp.cChecker.checkTile(this); 	// Checa colisão com o mapa, usando o CollisionChecker
			
			//COLISAO DE OBJETOS
			int objIndex = gp.cChecker.checkObjetct(this, true); //Checa a colisao do objeto 
			pickUpObject(objIndex);
			
			//COLISAO DE NPCS 
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//COLISAO DE EVENTOS
			gp.eHandler.checkEvent();
			
		
			// Se não houve colisão, move o jogador na direção escolhida
			if(collisionOn == false && keyH.enterPressed == false) {
				switch(direction) {
					case "up":    worldY -= speed; break;
					case "down":  worldY += speed; break;
					case "left":  worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			gp.keyH.enterPressed = false;
		}
		
		
		// Atualiza contagem de sprites para animação
		spriteCounter++;
		if (spriteCounter > 10) {
			spriteNum++; // Muda para próximo sprite
			if (spriteNum > 6) {
				spriteNum = 1; // Reinicia após o último sprite
			}
			spriteCounter = 0;
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			
			
			}
		}
	
	public void interactNPC(int i) {
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			
		}

	}
	
	
	public void draw(Graphics2D g2) {
	    BufferedImage image = null; // Imagem que será desenhada

	    // Escolhe sprite com base na direção e se está se movendo
	    switch (direction) {
	        case "up":
	            BufferedImage[] upImages = {up1, up2, up3, up4, up5, up6};
	            BufferedImage[] upStopImages = {up_stop1, up_stop2, up_stop3, up_stop4, up_stop5, up_stop6};
	            
	            if (keyH.upPressed) {
	                image = upImages[spriteNum - 1]; // Em movimento
	            } else {
	                image = upStopImages[spriteNum - 1]; // Parado
	            }
	            break;
	            
	        case "down":
	            BufferedImage[] downImages = {down1, down2, down3, down4, down5, down6};
	            BufferedImage[] downStopImages = {down_stop1, down_stop2, down_stop3, down_stop4, down_stop5, down_stop6};
	            
	            if (keyH.downPressed) {
	                image = downImages[spriteNum - 1];
	            } else {
	                image = downStopImages[spriteNum - 1];
	            }
	            break;
	            
	        case "left":
	            BufferedImage[] leftImages = {left1, left2, left3, left4, left5, left6};
	            BufferedImage[] leftStopImages = {left_stop1, left_stop2, left_stop3, left_stop4, left_stop5, left_stop6};
	            
	            if (keyH.leftPressed) {
	                image = leftImages[spriteNum - 1];
	            } else {
	                image = leftStopImages[spriteNum - 1];
	            }
	            break;
	            
	        case "right":
	            BufferedImage[] rightImages = {right1, right2, right3, right4, right5, right6};
	            BufferedImage[] rightStopImages = {right_stop1, right_stop2, right_stop3, right_stop4, right_stop5, right_stop6};
	            
	            if (keyH.rightPressed) {
	                image = rightImages[spriteNum - 1];
	            } else {
	                image = rightStopImages[spriteNum - 1];
	            }
	            break;
	    }

	    // Desenha a imagem do jogador
	    if (image != null) {
	        g2.drawImage(image, screenX, screenY, null);
	    }
	    
	    // --- DEBUG: Desenha a hitbox (área de colisão) ---
	    Color originalColor = g2.getColor();
	    g2.setColor(Color.RED);

	    // Posição real da hitbox na tela (ajustada pela câmera)
	    int hitboxX = screenX + solidArea.x;
	    int hitboxY = screenY + solidArea.y;

	    g2.drawRect(hitboxX, hitboxY, solidArea.width, solidArea.height);

	    g2.setColor(originalColor); // Restaura a cor original
	}

}