package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	// Substituir dezenas de imagens separadas
	public BufferedImage[][] npcWalkSprites = new BufferedImage[4][4];
	public BufferedImage[][] npcIdleSprites = new BufferedImage[4][4];

	public boolean moving = false;
	public int actionDuration = 0; // Contador de tempo para mover ou parar
	public int waitTime = 0;       // Tempo a esperar quando parado


	public BufferedImage faceImage;
	public BufferedImage ObjImage;
	
	
	public String direction = "rigth";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 60);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	//STATUS DE PERSONAGEM
	public int maxLife;
	public int life;
		
	//Atributos dos Itens
	public String description = "";
	public String conteudo = "";
	public BufferedImage imagemCarta;
	
	public int npcBeepIndex = 9; // índice padrão de som
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			
			dialogueIndex = 0;
		}
		
		gp.ui.startDialogue(dialogues[dialogueIndex]);
		gp.gameState = gp.dialogueState; // Muda o estado do jogo para diálogo
	    gp.ui.setNpcFaceImage(faceImage);
		dialogueIndex++;
		
		
	}
	
	public void faceImage() {
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
			
		}
	}
	

	public void update() {
	    setAction();

	    if(gp.gameStage.currentStage == 0) {
	    	collisionOn = false;
	    	return;
	    }
	    collisionOn = false;
	    gp.cChecker.checkTile(this);
	    gp.cChecker.checkObjetct(this, false);
	    gp.cChecker.checkPlayer(this);

	    moving = false;

	    if (!collisionOn) {
	        switch (direction) {
	            case "up": worldY -= speed; moving = true; break;
	            case "down": worldY += speed; moving = true; break;
	            case "left": worldX -= speed; moving = true; break;
	            case "right": worldX += speed; moving = true; break;
	        }
	    }
	    

	    spriteCounter++;
	    if (spriteCounter > 12) {
	        spriteNum = (spriteNum + 1) % 4;
	        spriteCounter = 0;
	    }
	}


	
	public void draw(Graphics2D g2) {
        BufferedImage imageToDraw = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Verifica se a entidade está na tela
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (npcWalkSprites[0][0] != null) {
                // É um NPC animado
                int dirIndex = 0; // down por padrão
                if ("right".equals(direction)) dirIndex = 0;
                else if ("left".equals(direction)) dirIndex = 1;
                else if ("down".equals(direction)) dirIndex = 2;
                else if ("up".equals(direction)) dirIndex = 3;

                if (moving) {
                    imageToDraw = npcWalkSprites[dirIndex][spriteNum];
                } else {
                    imageToDraw = npcIdleSprites[dirIndex][spriteNum];
                }
            } else if (ObjImage != null) {
                // É um objeto (ex: baú)
                imageToDraw = ObjImage;
            } else if (image != null) {
                // Imagem genérica
                imageToDraw = image;
            }
            
            // Aqui desenhamos a área de colisão (solidArea)
            if (gp.keyH.showDebugText == true) {  // Condição para desenhar apenas em modo de depuração
                g2.setColor(Color.RED);  // Cor do retângulo (pode ser alterada)
                g2.setStroke(new BasicStroke(2));  // Define a espessura da linha
                g2.drawRect(
                    screenX + solidArea.x,  // Posição X da colisão no NPC
                    screenY + solidArea.y,  // Posição Y da colisão no NPC
                    solidArea.width,         // Largura da colisão
                    solidArea.height         // Altura da colisão
                );
            }

            if (imageToDraw != null) {
                g2.drawImage(imageToDraw, screenX, screenY, gp.tileSize, gp.tileSize, null);
            } else {
                System.out.println("⚠️ Entidade sem imagem: " + name);
            }
        }
    }


	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
