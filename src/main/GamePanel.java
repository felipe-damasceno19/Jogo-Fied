// Pacote principal do jogo
package main;

import java.awt.Color;               // Classe para definir cores
import java.awt.Dimension;          // Usada para definir dimensões da tela
import java.awt.Graphics;           // Contexto gráfico básico
import java.awt.Graphics2D;         // Contexto gráfico mais avançado (2D)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;          // Componente Swing que permite desenhar elementos gráficos

import entity.Entity;
import entity.Player;               // Importa a classe do jogador
import tile.TileManager;           // Importa a classe que gerencia os tiles

// Classe principal do painel do jogo
public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;             // Tamanho original de cada tile (em pixels)
    final int scale = 3;                         // Fator de escala para ampliar os tiles
    
    public final int tileSize = originalTileSize * scale; // Tamanho final dos tiles (64x64 px)
    public final int maxScreenCol = 16;          // Número máximo de colunas visíveis na tela
    public final int maxScreenRow = 12;          // Número máximo de linhas visíveis na tela
    public final int screenWidth = tileSize * maxScreenCol; // Largura total da tela em pixels
    public final int screenHeight = tileSize * maxScreenRow; // Altura total da tela em pixels

    
    //CONFIGURAÇÕES DO MUNDO
    public final int maxWorldCol = 50;           // Máximo de colunas do mundo (mapa)
    public final int maxWorldRow = 50;           // Máximo de linhas do mundo (mapa)
   

    int FPS = 60;                                 // Taxa de quadros por segundo

    
    
    public KeyHandler keyH = new KeyHandler(this);   // Manipulador de teclas
    TileManager tileM = new TileManager(this);   // Gerenciador de tiles
    Sound music = new Sound();
    Sound se = new Sound();                    
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;    // Thread principal do jogo
    
    //ENTIDADES E OBJETOS
    public Player player = new Player(this, keyH); // Instância do jogador
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    
    
    // Construtor: configurações iniciais do painel
    public GamePanel() {
        
    	this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Define tamanho da janela
        this.setBackground(Color.black);         // Cor de fundo da tela
        this.setDoubleBuffered(true);            // Otimiza o desenho dos gráficos
        this.addKeyListener(keyH);               // Adiciona escutador de teclado
        this.setFocusable(true);                 // Permite que o painel receba foco do teclado
    }

    public void setupGame() {
    	
    	aSetter.setObject();
    	aSetter.setNpc();
    	playMusic(1);
    	gameState = titleState;
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
            //timer += (currentTime - lastTime);   // Soma ao temporizador de FPS
            lastTime = currentTime;

            if (delta >= 1) { // Se passou tempo suficiente para 1 frame
                
            	update();                        // Atualiza lógica do jogo
                repaint();                       // Redesenha tela
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
    		
    		player.update();       // Atualiza posição e estado do jogador
    		
    		//NPC
    		for(int i = 0; i < npc.length; i++) {
    			
    			if(npc[i] != null) {
    				
    				npc[i].update();
    			}
    		}
    	}
    	if(gameState == pauseState) {
    		
    	}
                          
    }

    // Método que desenha os elementos na tela
    @Override
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);                 // Limpa a tela antes de redesenhar
        Graphics2D g2 = (Graphics2D) g;          // Usa Graphics2D para desenho mais avançado

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }   
        
        //TELA INICIAL
        if(gameState == titleState) {
        	
        	ui.draw(g2);
        }
        
        else {
        	
            tileM.draw(g2);                 // Desenha o mapa (tiles)
          
            entityList.add(player);                         // Adiciona o jogador (player) à lista de entidades.

            for(int i = 0; i < npc.length; i++) {           // Percorre o array de NPCs (personagens não jogáveis).
                if(npc[i] != null) {                        // Verifica se o NPC atual não é nulo.
                    entityList.add(npc[i]);                 // Adiciona o NPC à lista de entidades.
                }
            }

            for(int i = 0; i < obj.length; i++) {           // Percorre o array de objetos (itens no mapa, por exemplo).
                if(obj[i] != null) {                        // Verifica se o objeto atual não é nulo.
                    entityList.add(obj[i]);                 // Adiciona o objeto à lista de entidades.
                }
            }

            // Ordena as entidades com base na coordenada Y (profundidade/posição vertical).
            Collections.sort(entityList, new Comparator<Entity>() {
                
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY); // Compara a posição Y no mundo.
                    return result; // Entidades com Y menor são desenhadas antes (mais "atrás").
                }
            });

            // Desenha todas as entidades ordenadas na tela.
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);                 // Chama o método draw de cada entidade.
            }

            entityList.clear();

            
            ui.draw(g2); 		 //Desenha a interface do jogo
        
        }
          
        //DEBUG
        if(keyH.checkDrawTime == true) {
        	long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: "+passed, 10, 400);
            System.out.println("Draw time: "+passed);
        }
       
        g2.dispose();        // Libera recursos gráficos
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
}
