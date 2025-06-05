package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.obj_Key;
import object.obj_Letter;
import object.obj_Letter2;

// A classe Player representa o personagem controlado pelo jogador.
// Ela herda da classe Entity (entidade genérica no jogo).
public class Player extends Entity {

	KeyHandler keyH; // Objeto responsável por lidar com as entradas do teclado
	
	// Posições fixas do jogador na tela. Ele está sempre no centro da tela.
	public final int screenX;
	public final int screenY;
	
	// Arrays de sprites de animação: [direção][frame]
	BufferedImage[][] walkSprites = new BufferedImage[4][4]; // sprites de movimento
	BufferedImage[][] idleSprites = new BufferedImage[4][4]; // sprites de personagem parado
	
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	
	public boolean nearInteractable = false;
	int indexGaveteiro1 = 0;
	
	// Construtor do jogador
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp); // chama o construtor da classe Entity
		this.keyH = keyH; // armazena a referência ao KeyHandler

		// Centraliza o jogador na tela
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2); 
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		// Define a área sólida (retângulo de colisão) do jogador
		int solidAreaX = 24;  // Posição X da área sólida
		int solidAreaY = 42;  // Posição Y da área sólida
		int solidAreaWidth = 16;  // Largura da área sólida
		int solidAreaHeight = 22;  // Altura da área sólida

		// Agora cria-se o solidArea com as variáveis definidas acima
		solidArea = new Rectangle(solidAreaX, solidAreaY, solidAreaWidth, solidAreaHeight);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues(); // inicializa valores como posição, direção e vida
		loadPlayerSprites(); // carrega os sprites do jogador
		setItems();
	}
	public void obterItem(Entity item) {
	    if (inventory.size() < maxInventorySize) {
	        inventory.add(item);
	        gp.ui.showMessage("Pressione 'C' para abrir o inventário");
	    }
	}
	
	// Define valores iniciais do jogador
	public void setDefaultValues() {
		worldX = gp.tileSize * 29; // Posição inicial no mundo (X)
		worldY = gp.tileSize * 25; // Posição inicial no mundo (Y)
		speed = 5;                 // Velocidade de movimento
		direction = "down";        // Direção inicial

		// Status de vida
		maxLife = 6;
		life = maxLife;
		
		
	}
	public void setDefaultPositions() {
		
		gp.currentMap = 0;
		worldX = gp.tileSize * 29; // Posição inicial no mundo (X)
		worldY = gp.tileSize * 25; // Posição inicial no mundo (Y)
		direction = "down";        // Direção inicial
		
	}

	public void setItems() {
		
		//inventory.clear(); //LIMPA O INVENTARIO DO PLAYER CASO MORRA, E DEIXA APENAS O PADROES

	}
	
	// Carrega os sprites do jogador a partir da imagem sprite sheet
	public void loadPlayerSprites() {
		try {
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/sprite_Silas_walk_idle.png"));

			// A imagem tem 32 sprites, os 16 primeiros são de caminhada, os 16 últimos de idle (parado)
			for (int i = 0; i < 32; i++) {
				BufferedImage frame = spriteSheet.getSubimage(i * 32, 0, 32, 32);
				if (i < 16) {
					walkSprites[i / 4][i % 4] = frame; // walkSprites[linha da direção][coluna do frame]
				} else {
					idleSprites[(i - 16) / 4][(i - 16) % 4] = frame; // idleSprites[linha da direção][coluna do frame]
				}
			}
		} catch (IOException e) {
			e.printStackTrace(); // Mostra erro se falhar ao carregar a imagem
		}
	}
	
	// Atualiza o estado do jogador a cada frame
	public void update() {
		boolean moving = false;

		// Checa colisão com NPCs e interage se possível
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		gp.keyH.enterPressed = false; // Garante que o enter só seja considerado uma vez
		gp.eHandler.checkEvent();
		gp.cChecker.checkTile(this);
		// Checa colisão com objetos e coleta se possível
		int objIndex = gp.cChecker.checkObjetct(this, true);
		pickUpObject(objIndex);	
		// Verifica se alguma tecla de direção foi pressionada
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			moving = true;
			
			// Define a direção com base na tecla pressionada
			if (keyH.upPressed) direction = "up";
			if (keyH.downPressed) direction = "down";
			if (keyH.leftPressed) direction = "left";
			if (keyH.rightPressed) direction = "right";

			collisionOn = false; // Reinicia a colisão

			// Checa colisão com tiles
			gp.cChecker.checkTile(this);

			

			// Antes ou depois de checar NPCs/objetos
			nearInteractable = false;
			if (gp.cChecker.checkEntity(this, gp.npc) != 999) {
			    nearInteractable = true;
			    gp.ui.interactKey = "enter"; // muda para tecla ENTER

			}
			if (gp.cChecker.checkObjetct(this, false) != 999) {
			    nearInteractable = true;
			    gp.ui.interactKey = "enter"; // muda para tecla ENTER

			}


			// Checa eventos no mapa (ex: teleportes, cutscenes)
			

			// Se não houve colisão, move o jogador na direção definida
			if (!collisionOn && keyH.enterPressed == false) {
				switch (direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
		}

		// Atualiza contador de sprite (animação)
		spriteCounter++;
		if (spriteCounter > 10) {
			spriteNum++;
			if (spriteNum > 3) spriteNum = 0; // Loop entre 0, 1, 2, 3
			spriteCounter = 0;
		}
		
		
	}

	// Coleta de objeto (por enquanto vazio)
	public void pickUpObject(int i) {
		if(i != 999) {
			//lógica futura para coleta de item
			
			String objectName = gp.obj[gp.currentMap][i].name;
			gp.player.nearInteractable = true;
            gp.ui.interactKey = "f"; // muda para tecla F
			
			switch(objectName) {
			case "Door":
				break;
			case "Gaveteiro":
				if(gp.keyH.fPressed) {
					  if(gp.currentMap == 1 && indexGaveteiro1 == 0) {
						  gp.player.obterItem(new obj_Key(gp));      
						  gp.player.obterItem(new obj_Letter(gp));
						  indexGaveteiro1 = 1;
						  gp.obj[gp.currentMap][i].ObjImage = setup("/objects/Gaveteiro_aberto");;
					  }
				break;
				}
			}
		}
	}
	
	// Interação com NPCs
	public void interactNPC(int i) {
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
				gp.npc[gp.currentMap][i].speak(); // Faz o NPC falar
			}
		}
	}
	
	// Desenha o jogador na tela
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int dirIndex = 0;

		// Converte a direção para o índice da matriz de sprites
		switch (direction) {
			case "down": dirIndex = 2; break;
			case "left": dirIndex = 1; break;
			case "right": dirIndex = 0; break;
			case "up": dirIndex = 3; break;
		}
		

		// Define se está se movendo ou parado
		boolean isMoving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
		
		// Escolhe o sprite correto com base na direção e movimento
		image = isMoving ? walkSprites[dirIndex][spriteNum] : idleSprites[dirIndex][spriteNum];

		// Desenha o jogador no centro da tela
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		//g2.setColor(Color.red);
		//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
	}
}
