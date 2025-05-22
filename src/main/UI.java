package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import object.obj_Heart;
import entity.Entity;

import javax.imageio.ImageIO;

import object.obj_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font undertaleFontSans, undertaleFontRegular;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;

	BufferedImage titleScreenImage;
	public BufferedImage npcFaceImage; // imagem atual do rosto do NPC

	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		try {
		    InputStream is = getClass().getResourceAsStream("/title/uninta_quest.png");
		    titleScreenImage = ImageIO.read(is);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
		try {
			
			InputStream is = getClass().getResourceAsStream("/font/DETERMINATIONMONOWEBREGULAR-Z5OQ.ttf");
			undertaleFontRegular = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/DETERMINATIONSANSWEBREGULAR-369X.ttf");
			undertaleFontSans = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		//CRIANDO HUD DOS OBJETOS
		Entity heart = new obj_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
		
	}
	
	//METODO PARA PEGAR A IMAGEM DO NPC
	public void setNpcFaceImage(BufferedImage faceImage) {
	    this.npcFaceImage = faceImage;
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(undertaleFontSans);
		g2.setColor(Color.white);
		
		
		//TELA INICIAL
		if(gp.gameState == gp.titleState) {
			
			drawTitleScreen();
		}
		
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			
			drawPlayerLife();
		}
		
		//PAUSE
		if(gp.gameState == gp.pauseState) {
			
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {		
			drawPlayerLife();
			drawDialogueScreen();
			
		}
	}
	
	public void drawPlayerLife() {
	    
		//gp.player.life = 2;
		
	    // Posição inicial dos corações
	    int x = gp.tileSize / 2;
	    int y = gp.tileSize / 2;
	    int i = 0;

	    // Desenha os corações vazios (base)
	    while(i < gp.player.maxLife / 2) {
	        g2.drawImage(heart_blank, x, y, null); // desenha coração vazio
	        i++;
	        x += gp.tileSize; // vai para a próxima posição
	    }

	    // Reinicia posição e contador
	    x = gp.tileSize / 2;
	    y = gp.tileSize / 2;
	    i = 0;

	    // Desenha a vida atual por cima dos vazios
	    while(i < gp.player.life) {
	        g2.drawImage(heart_half, x, y, null); // desenha meio coração

	        i++;

	        // Se tiver mais um ponto de vida, completa o coração
	        if(i < gp.player.life) {
	            g2.drawImage(heart_full, x, y, null); // desenha coração cheio
	        }

	        i++;
	        x += gp.tileSize; // próxima posição
	    }
	}

	
	public void drawTitleScreen() {
	
		  if (titleScreenImage != null) {
		        g2.drawImage(titleScreenImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
		    } else {
		        // Fallback caso a imagem não carregue
		        g2.setColor(new Color(0, 0, 0));
		        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		    }
		
		//g2.setColor(new Color(0, 0, 0));
		//g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		
		//NOME DO TITULO
		//g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		//String text = "Uninta Quest";
		 String text = "";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 6;
		
		//SOMBRA
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		//COR PRINCIPAL
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//IMAGEM DO PERSONAGEM
		//x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		//y += gp.tileSize+2;
		//g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);		
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		
		text = "NOVO JOGO";
		x = getXforCenteredText(text); //CENTRALIZA O TEXTO
		y += gp.tileSize * 3.5; //POSIÇÃO Y DA OPÇÃO
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			
			g2.drawString(">", x-gp.tileSize, y);
		}
		text = "CARREGAR JOGO";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		if(commandNum == 1) {
			
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "SAIR";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		if(commandNum == 2) {
			
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	
	//METODO PARA DESENHAR A TELA DE PAUSA
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		String text = "PAUSA";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {

	    // JANELA
	    int x = gp.tileSize * 2;
	    int y = gp.tileSize / 2;
	    int width = gp.screenWidth - (gp.tileSize * 4);
	    int height = gp.tileSize * 4;

	    drawSubWindow(x, y, width, height);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
	    x += gp.tileSize;
	    y += gp.tileSize;

	    // DESENHAR A IMAGEM DO ROSTO
	    if (npcFaceImage != null) {
	        int faceSize = gp.tileSize * 2; //POSIÇÃO DO SPRITE DO ROSTO DO NPC NO DIALOGO
	        g2.drawImage(npcFaceImage, x, y, faceSize, faceSize, null); //DESENHA O SPRITE DO NPC NO DIALOGO
	    }

	    // DESENHAR O TEXTO (um pouco mais para o lado para não sobrepor a imagem)
	    int textX = x + gp.tileSize * 3 + 20; // espaço depois da imagem
	    int textY = y + 40;

	    for (String line : currentDialogue.split("\n")) {
	        g2.drawString(line, textX, textY);
	        textY += 40;
	    }
	}

	//TELA DO DIALOGO
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 240);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5 , y+5, width-10, height-10, 25, 25);
	}
	
	//METODO PARA CENTRALIZAR ELEMENTOS
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
