package entity;

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
	
	public BufferedImage up1, up2, up3, up4, up5, up6, up_stop1, up_stop2, up_stop3, up_stop4, up_stop5, up_stop6,
	down1, down2, down3, down4, down5, down6, down_stop1, down_stop2, down_stop3, down_stop4, down_stop5, down_stop6,
	left1, left2, left3, left4, left5, left6, left_stop1, left_stop2, left_stop3, left_stop4, left_stop5, left_stop6,
	right1, right2, right3, right4, right5, right6, right_stop1, right_stop2, right_stop3, right_stop4, right_stop5, right_stop6;
	public BufferedImage faceImage;
	
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
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
		
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			
			dialogueIndex = 0;
		}
		
		gp.ui.startDialogue(dialogues[dialogueIndex]);
		
	    gp.ui.setNpcFaceImage(faceImage);
		dialogueIndex++;
		
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
		
		setAction(); //Métodos criados nas subclasses tem prioridade
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObjetct(this, false);
		gp.cChecker.checkPlayer(this);
		
		// Se não houve colisão, move o jogador na direção escolhida
		if(collisionOn == false) {
			switch(direction) {
				case "up":    worldY -= speed; break;
				case "down":  worldY += speed; break;
				case "left":  worldX -= speed; break;
				case "right": worldX += speed; break;
			}
		}
	
	// Atualiza contagem de sprites para animação
	spriteCounter++;
	if (spriteCounter > 12) {
		if(spriteNum == 1) {
			spriteNum = 2;
		}
		else if(spriteNum == 2) {
			spriteNum = 1;
		}
		spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
	
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
			&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
			&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
			&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
