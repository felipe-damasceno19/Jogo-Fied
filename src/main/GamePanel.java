// Pacote principal do jogo
package main;

import java.awt.AlphaComposite;
import java.awt.Color;               // Classe para definir cores
import java.awt.Composite;
import java.awt.Dimension;          // Usada para definir dimensões da tela
import java.awt.Font;
import java.awt.Graphics;           // Contexto gráfico básico
import java.awt.Graphics2D;         // Contexto gráfico mais avançado (2D)
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;          // Componente Swing que permite desenhar elementos gráficos

import entity.Entity;
import entity.Player;               // Importa a classe do jogador
import environment.EnvironmentManager;
import tile.TileManager;           // Importa a classe que gerencia os tiles

// Classe principal do painel do jogo
public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 32;             // Tamanho original de cada tile (em pixels)
    final int scale = 2;                         // Fator de escala para ampliar os tiles
    
    public final int tileSize = originalTileSize * scale; // Tamanho final dos tiles (64x64 px)
    public final int maxScreenCol = 20;          // Número máximo de colunas visíveis na tela
    public final int maxScreenRow = 12;          // Número máximo de linhas visíveis na tela
    public final int screenWidth = tileSize * maxScreenCol; // Largura total da tela em pixels
    public final int screenHeight = tileSize * maxScreenRow; // Altura total da tela em pixels
    
    //TELA CHEIA
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = true;
    
    
    
    //CONFIGURAÇÕES DO MUNDO
    public int maxWorldCol = 50;           // Máximo de colunas do mundo (mapa)
    public int maxWorldRow = 50;           // Máximo de linhas do mundo (mapa)
    public final int maxMap = 10;
    public int currentMap = 0;

    int FPS = 60;                                 // Taxa de quadros por segundo
    
    public GameStage gameStage = new GameStage(this);; // Instância da classe GameStage
    
    
    public KeyHandler keyH = new KeyHandler(this);   // Manipulador de teclas
    TileManager tileM = new TileManager(this);   // Gerenciador de tiles
    Sound music = new Sound();
    Sound se = new Sound();                    
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;    // Thread principal do jogo
    
    //ENTIDADES E OBJETOS
    public Player player = new Player(this, keyH); // Instância do jogador
    public Entity obj[][] = new Entity[maxMap][100];
    public Entity npc[][] = new Entity[maxMap][100];
    public Entity tilesOver[][] = new Entity[maxMap][100];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int lockPickState = 7;
    public final int powerBoxState = 8;
    public final int culpritSelectionState = 9;

    
    //FUNDO BORRADO
    BufferedImage blurredBackground;

    // Flags
    int closedDialogues = 0;
    int currentTeleport = 0;
    
    // Construtor: configurações iniciais do painel
    public GamePanel() {
        
    	this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Define tamanho da janela
        this.setBackground(Color.black);         // Cor de fundo da tela
        this.setDoubleBuffered(true);            // Otimiza o desenho dos gráficos
        this.addKeyListener(keyH);               // Adiciona escutador de teclado
        this.setFocusable(true);  				 // Permite que o painel receba foco do teclado
    }

    public void setupGame() {
    	
    	aSetter.setObject();
    	aSetter.setTilesOver();
    	aSetter.setNpc();
    	playMusic(1);
    	eManager.setup();
    	gameState = titleState;
    	
    	tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    	g2 = (Graphics2D)tempScreen.getGraphics();
    	
    	if(fullScreenOn == true) {
    		
    		setFullScreen(); //TELA CHEIA
    	}
    	
    }
    
    //METODO CASO O JOGADOR TENTE NOVAMENTE APOS O GAME OVER
    public void retry() {
    	
    	player.setDefaultPositions();
    	
    	//PODE SE ADICIONAR ITENS E OBJETOS, CASO DESEJE QUE ELES VOLTEM AS POSIÇÕES INICIAIS AO TENTAR NOVAMENTE
    }
    
    //METODO CASO O JOGADOR REINICIE O JOGO
    public void restart() {
    	
    	player.setDefaultValues();
    	player.setDefaultPositions();
    	player.setItems();

    	aSetter.setObject();
    	aSetter.setTilesOver();
    	aSetter.setNpc();
    }
    
    public void setFullScreen() {
    	
    	// RESOLUÇÃO DE ACORDO COM A TELA DO APARELHO LOCAL
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	gd.setFullScreenWindow(Main.window);
    	
    	// PEGA A LARGURA E ALTURA DA TELA CHEIA
    	screenWidth2 = Main.window.getWidth();
    	screenHeight2 = Main.window.getHeight();
    	
    	}
    
    // Inicia a thread do jogo
    public void startGameThread() {
        
    	gameThread = new Thread(this);           // Cria nova thread usando a própria classe
        gameThread.start();                      // Inicia a execução da thread
    }

    // Método principal do loop de jogo (run)
    public void run() {
        
    	double drawInterval = 1000000000 / FPS;  // Intervalo entre cada frame (em nanossegundos)
        double delta = 0;                        // Acumulador de tempo
        long lastTime = System.nanoTime();       // Último tempo registrado
        long currentTime;
        long timer = 0;                          // Temporizador para calcular FPS real
        int drawCount = 0;                       // Contador de frames desenhados

        while (gameThread != null) {             // Enquanto a thread estiver rodando
            
        	currentTime = System.nanoTime();     // Pega o tempo atual
            delta += (currentTime - lastTime) / drawInterval; // Calcula tempo decorrido
            lastTime = currentTime;

            if (delta >= 1) { // Se passou tempo suficiente para 1 frame
                
            	update();                        // Atualiza lógica do jogo
            	drawToTempScreen();	
            	drawToScreen();
                delta--;                         // Decrementa o delta
                drawCount++;                     // Aumenta contagem de quadros
            }

            if (timer >= 1000000000) {           // A cada 1 segundo (1 bilhão de nanossegundos)
                System.out.println("FPS: " + drawCount); // Mostra FPS no console
                drawCount = 0;
                timer = 0;
            }
        }
    }

    // Atualiza o estado do jogo
    public void update() {
    	
    	if(gameState == playState) {
    		
    		// Atualiza posição e estado do jogador
    		player.update();       
    		
            // Verifica a parte do Jogo;
            if(gameStage.currentStage == 0) {
            	gameStage.currentStage = 2; 
            	//gameStage.checkStage();
            }
            
    		//NPC
    		for(int i = 0; i < npc[1].length; i++) {
    			
    			if(npc[currentMap][i] != null) {
    				
    				npc[currentMap][i].update();
    				
    			}
    		}
    	}
    	if(gameState == pauseState) {
    		
    	}
                          
    }

    public void drawToTempScreen() {
        
        //DEBUG
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }   
        
        //TELA INICIAL
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileM.draw(g2); // Desenha o mapa (tiles)

            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            for (int i = 0; i < tilesOver[1].length; i++) {
                if (tilesOver[currentMap][i] != null) {
                    entityList.add(tilesOver[currentMap][i]);
                }
            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            entityList.clear();

            eManager.draw(g2); 
            
            ui.draw(g2);

        }

      //DEBUG
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
            
            // Definindo a fonte e cor para o texto do debug
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            // Exibe as coordenadas do jogador
            g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y); y += lineHeight;

            // Cálculo da coluna e linha do tile baseado na posição do jogador
            int tileCol = (player.worldX + player.solidArea.x) / tileSize;
            int tileRow = (player.worldY + player.solidArea.y) / tileSize;
            g2.drawString("Col: " + tileCol, x, y); y += lineHeight;
            g2.drawString("Row: " + tileRow, x, y); y += lineHeight;

            // Número do tile (baseado na posição do tile no grid do mapa)
            int tileNum = tileM.mapTileNum[currentMap][tileCol][tileRow]; // Pega o número do tile
            g2.drawString("Tile Number: " + tileNum, x, y); y += lineHeight;

            // Caminho do tile - Acessando o caminho diretamente do TileManager
            String tilePath = tileM.fileNames.get(tileNum); // Obtém o caminho do tile com base no índice
            g2.drawString("Tile Path: " + tilePath, x, y); y += lineHeight;
            // Cálculo de tempo de desenho
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
        }

    }

 
 
    public void drawToScreen() {
    	Graphics g = getGraphics();
    	g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
    	g.dispose();
    	
    }
    
    
    public void playMusic(int i) {
    	
    	music.setFile(i);
    	music.play();
    	music.loop();
    }
    public void stopMusic() {
    	
    	music.stop();
    }
    public void playSE(int i) {
    	
    	se.setFile(i);
    	se.play();
    }
    public BufferedImage setupImg(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
