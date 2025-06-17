package main;

// Importações necessárias para gráficos, imagens e entrada de dados
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

// Importação das classes do jogo (objetos e entidades)
import object.obj_Heart;
import object.obj_Key;
import entity.Entity;

public class UI {

    GamePanel gp; // Referência ao painel principal do jogo
    Graphics2D g2; // Objeto gráfico para desenhar na tela

    // Fontes do estilo Undertale para títulos e diálogos
    public Font undertaleFontSans, undertaleFontRegular;

    // Sistema de mensagens temporárias na tela
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    // Sinalizador para o término do jogo
    public boolean gameFinished = false;
    public boolean lockPickActive = false;

    //LOCKPICK PUZZLE
    public object.obj_Gaveteiro lockPickTarget = null;
 
    public int lockPickProgress = 0;
    public int lockPickStage = 1;
    public int lockPickSpeed = 2;
    public boolean lockPickSucess = false;
    public int lockPickAngle = 0;  // Ângulo atual do ponteiro (0-359)
    public int lockPickSweetSpot = 0;  // Ângulo correto para destrancar
    
    //POWERBOX PUZZLE
    public int[][] energyGrid = new int[4][4]; // códigos de peças
    public int[][] rotationGrid = new int[4][4]; // rotação de cada peça
    public int energyRow = 0, energyCol = 0;
    public boolean powerPuzzleActive = false;
    public boolean powerRestored = false;
    
 // Puzzle de fios
    public int wireSelectedLeft = -1;
    public boolean[] wireConnected = new boolean[4];     // status de cada fio
    public int[] wireMatches = new int[4];               // posição embaralhada dos fios
    public int[] playerConnections = new int[4];         // conexões feitas pelo jogador
    public Color[] wireColors = { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };
    public int wireCursorLeft = 0;
    public int wireCursorRight = 0;
    public boolean choosingLeft = true;



    // Texto de diálogo atual
    public String currentDialogue = "";

    // Número do comando selecionado no menu (usado na tela de título)
    public int commandNum = 0;

    // Imagem de fundo da tela de título
    BufferedImage titleScreenImage, selectorImage;

    // Sprite do rosto do NPC atual (para a caixa de diálogo)
    public BufferedImage npcFaceImage;
    
    //posição dos slots
    public int slotCol = 0;
    public int slotRow = 0;
    
    int subState = 0;
    
    // Controle da animação do rosto do NPC (alterna entre dois frames)
    int npcFaceAnimationCounter = 0;
    int npcFaceAnimationSpeed = 12; // tempo entre trocas de frame
    boolean npcFaceToggle = false;

    // Sistema de digitação progressiva de texto
    public String fullText = "";         // Texto completo a ser exibido
    public String displayedText = "";    // Parte já exibida na tela
    public int textCharIndex = 0;        // Quantos caracteres já foram mostrados
    public int textDisplaySpeed = 2;     // Velocidade de digitação (menor = mais rápido)
    public int textCounter = 0;          // Contador para controlar o tempo entre cada letra

    // Lista com todas as linhas do texto, quebradas automaticamente
    public java.util.List<String> fullTextLines = new ArrayList<>();
    public int currentLineStart = 0; // Índice da primeira linha atualmente visível
    
 // Sprites de botões animados
    BufferedImage[] enterButtonFrames;
    BufferedImage[] fButtonFrames;
    Map<String, BufferedImage[]> buttonSprites = new HashMap<>();
    public String interactKey = "f"; // tecla padrão
    int frameIndex = 0;
    int frameCounter = 0;
    int frameSpeed = 15; 

    public boolean showGenericBox = false;

    
    public int selectedCulpritIndex = 0;
    public int culpritChosen = -1;
    public BufferedImage[] culpritSprites = new BufferedImage[5];
    public String[] culpritNames = { "Carol", "Ismael", "Nelder", "Nelipe", "Vilson" };
    private long lastMoveTime = 0;  // Última vez que o movimento foi realizado
    private final long moveCooldown = 200;  // Tempo de espera entre movimentos (em milissegundos)
    
    public int currentNpcBeepIndex = 9; // 5 = som padrão

    
    // sistema de escolha final
    
    public boolean choiceActive = false;
    public String choiceQuestion = "";
    public String[] choiceOptions = {"Sim", "Não"};
    public int selectedChoiceIndex = 0;
    public Boolean choiceResult = null;
    public long lastChoiceMoveTime = 0;
    public final long choiceMoveCooldown = 200; // milissegundos


    

    // ===============================
    // CONSTRUTOR DA CLASSE
    // ===============================
    
    
    public UI(GamePanel gp) {
        this.gp = gp;

        // Tenta carregar a imagem da tela de título
        try {
            InputStream is = getClass().getResourceAsStream("/title/menu_tela.png");
            titleScreenImage = ImageIO.read(is);
            
            InputStream is1 = getClass().getResourceAsStream("/objects/lupa.png");
            selectorImage = ImageIO.read(is1);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tenta carregar as fontes personalizadas
        try {
            InputStream is = getClass().getResourceAsStream("/font/DETERMINATIONMONOWEBREGULAR-Z5OQ.ttf");
            undertaleFontRegular = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/DETERMINATIONSANSWEBREGULAR-369X.ttf");
            undertaleFontSans = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        
        try {
            for (int i = 0; i < 5; i++) {
                culpritSprites[i] = ImageIO.read(getClass().getResourceAsStream("/culprit/npc_" + i + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        enterButtonFrames = loadButtonSprites("/buttons/press_enter.png", 2);
        fButtonFrames = loadButtonSprites("/buttons/press_f.png", 2);
        
        buttonSprites.put("f", fButtonFrames);
        buttonSprites.put("enter", enterButtonFrames);



    }

    // Método principal de desenho da UI, chamado a cada frame
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(undertaleFontSans);
        g2.setColor(Color.white);

        // Redireciona o desenho com base no estado atual do jogo
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        
        if (gp.gameState == gp.playState) {
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (showGenericBox) {
            drawEmptyDialogueBox();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if(gp.gameState == gp.characterState) {
        	drawCharacterScreen();
        	drawInventory();
        }
        
        if (gp.gameState == gp.playState) {
            if (gp.player.nearInteractable) {
            	drawInteractionPrompt();
            }
        }
        if(gp.gameState == gp.optionsState) {
        	drawOptionsScreen();
        }
        if(gp.gameState == gp.gameOverState) {
        	drawGameOverScreen();
        }
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
            g2.setColor(Color.white);
            g2.drawString(message, 30, 60); // posição da mensagem na tela (x=30, y=60)

            messageCounter++;
            if (messageCounter > 240) { // dura 2 segundos (se o jogo roda a 60 FPS)
                messageOn = false;
            }
        }
        if (lockPickActive) {
            drawLockpickPuzzle();
        }
        if (powerPuzzleActive) {
        	drawPowerPuzzle(g2);
        }
        if (gp.gameState == gp.culpritSelectionState) {
            drawCulpritSelectionScreen();
        }
        if (choiceActive) {
            drawChoiceScreen(g2);
        }



    }

    // Mostra uma mensagem simples (ex: "Item coletado!")
    public void showMessage(String text) {
        message = text;
        messageOn = true;
        messageCounter = 0;
    }
    
    
    public void startChoice(String question) {
        choiceActive = true;
        choiceQuestion = question;
        selectedChoiceIndex = 0;
        choiceResult = null;
        gp.gameState = gp.choiceState;
        lastChoiceMoveTime = System.currentTimeMillis(); // previne avanço imediato


    }
    
    public void drawChoiceScreen(Graphics2D g2) {
        // Fundo preto com leve transparência
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int boxWidth = gp.screenWidth - gp.tileSize * 4;
        int boxHeight = gp.tileSize * 4;
        int x = gp.tileSize * 2;
        int y = gp.screenHeight / 2 - boxHeight / 2;

        drawSubWindow(x, y, boxWidth, boxHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.white);

        // === Centraliza a pergunta na vertical da caixa ===
        FontMetrics fm = g2.getFontMetrics();
        int textHeight = fm.getHeight();
        int questionY = y + (boxHeight / 2) - textHeight;
        int questionX = getXforCenteredText(choiceQuestion);
        g2.drawString(choiceQuestion, questionX, questionY);

        // === Opções abaixo da pergunta ===
        int optionY = questionY + gp.tileSize;
        int spacing = 150;
        int baseX = gp.screenWidth / 2 - ((choiceOptions.length - 1) * spacing / 2);

        for (int i = 0; i < choiceOptions.length; i++) {
            // Define a cor baseada na seleção
            if (i == selectedChoiceIndex) {
                g2.setColor(Color.YELLOW);
            } else {
                g2.setColor(Color.WHITE);
            }

            String option = choiceOptions[i];
            int optionWidth = fm.stringWidth(option);
            int optionX = baseX + i * spacing - optionWidth / 2;
            g2.drawString(option, optionX, optionY);
        }
    }


        
    public void drawCulpritSelectionScreen() {
        g2.setColor(new Color(0, 0, 0, 220));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Título no topo
        String title = "Faça sua acusação! Quem é o assassino?";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        int titleX = getXforCenteredText(title);
        g2.setColor(Color.white);
        g2.drawString(title, titleX, gp.tileSize * 2);

        // Instrução embaixo
        String hint = "Use A e D para mover. ENTER para confirmar.";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
        int hintX = getXforCenteredText(hint);
        int hintY = gp.screenHeight - gp.tileSize;
        g2.drawString(hint, hintX, hintY);

        // Espaçamento e posicionamento
        int totalNPCs = 5;
        int spacing = gp.tileSize * 3;
        int spriteSize = gp.tileSize * 4;

        int middleY = (gp.tileSize * 2 + hintY) / 2;  // meio entre o título e o texto
        int startX = (gp.screenWidth - (spacing * (totalNPCs - 1))) / 2;

        // Verifica o tempo atual para permitir a movimentação
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime > moveCooldown) {
            if (gp.keyH.leftPressed) {
                selectedCulpritIndex = Math.max(0, selectedCulpritIndex - 1);  // Move para a esquerda
                lastMoveTime = currentTime;  // Atualiza o tempo do último movimento
            } 
            if (gp.keyH.rightPressed) {
                selectedCulpritIndex = Math.min(totalNPCs - 1, selectedCulpritIndex + 1);  // Move para a direita
                lastMoveTime = currentTime;  // Atualiza o tempo do último movimento
            }
        }

        for (int i = 0; i < totalNPCs; i++) {
            int x = startX + i * spacing;
            int spriteY = middleY - spriteSize / 2;

            if (i == selectedCulpritIndex) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36f));
                String arrow = "^";
                int arrowWidth = g2.getFontMetrics().stringWidth(arrow);
                g2.drawString(arrow, x - arrowWidth / 2, spriteY - 15); // 15 pixels acima do sprite
            }

            // Desenha o sprite
            g2.drawImage(culpritSprites[i], x - spriteSize / 2, spriteY, spriteSize, spriteSize, null);

            // Nome abaixo do sprite
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20f));
            String name = culpritNames[i];
            int nameWidth = g2.getFontMetrics().stringWidth(name);
            g2.drawString(name, x - nameWidth / 2, spriteY + spriteSize + 20);
        }
    }
    
    // ===============================
    // Metódos de Dialogo
    // ===============================
    
    
    // Prepara o texto de diálogo para ser exibido com digitação progressiva
    public void startDialogue(String text) {
        fullText = text;
        displayedText = "";
        textCharIndex = 0;
        textCounter = 0;
        fullTextLines.clear();
        currentLineStart = 0;

        // Quebra automática do texto a cada 35 caracteres
        while (text.length() > 0) {
            int endIndex = Math.min(35, text.length());
            String line = text.substring(0, endIndex);
            int lastSpace = line.lastIndexOf(" ");
            if (endIndex == 35 && lastSpace > 0) {
                line = text.substring(0, lastSpace);
                endIndex = lastSpace;
            }
            fullTextLines.add(line);
            text = text.substring(endIndex).trim();
        }
    }
    
    public void startPowerPuzzle() {
        powerPuzzleActive = true;
        powerRestored = false;
        wireSelectedLeft = -1;
        wireCursorLeft = 0;
        wireCursorRight = 0;
        choosingLeft = true;

        for (int i = 0; i < 4; i++) {
            wireConnected[i] = false;
            playerConnections[i] = -1;
        }

        // Embaralha os fios da direita
        java.util.List<Integer> indices = new java.util.ArrayList<>();
        for (int i = 0; i < 4; i++) indices.add(i);
        java.util.Collections.shuffle(indices);
        for (int i = 0; i < 4; i++) {
            wireMatches[i] = indices.get(i);
        }
    }


    // Define a imagem do rosto do NPC que está falando
    public void setNpcFaceImage(BufferedImage faceImage) {
        this.npcFaceImage = faceImage;
    }
    
    public void setNpcFace(BufferedImage faceImage, int beepIndex) {
        this.npcFaceImage = faceImage;
        this.currentNpcBeepIndex = beepIndex;
    }


 // Desenha dialogos na tela
    public void drawDialogueScreen() {
        // Ajustando a largura da caixa e centralizando horizontalmente
        int width = gp.screenWidth / 2;  // Ajuste a largura como preferir
        int height = gp.tileSize * 3;
        int x = (gp.screenWidth - width) / 2;  // Centralizando horizontalmente
        int y = gp.tileSize / 2;  // Mantendo a posição vertical como estava

        // Desenha a caixa de diálogo
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize - 32;

        // Animação do rosto do NPC (se existir)
        if (npcFaceImage != null) {
            int faceSize = gp.tileSize * 2;
            int frameWidth = npcFaceImage.getWidth() / 2;
            int frameHeight = npcFaceImage.getHeight();

            // Se ainda estiver digitando, anima. Se já terminou, usa frame parado
            boolean animarFace = textCharIndex < getVisibleLinesText().length();

            int frameX = (animarFace && npcFaceToggle) ? frameWidth : 0;
            BufferedImage faceSprite = npcFaceImage.getSubimage(frameX, 0, frameWidth, frameHeight);
            g2.drawImage(faceSprite, x, y, faceSize, faceSize, null);

            // Só anima se ainda estiver digitando
            if (animarFace) {
                npcFaceAnimationCounter++;
                if (npcFaceAnimationCounter > npcFaceAnimationSpeed) {
                    npcFaceToggle = !npcFaceToggle;
                    npcFaceAnimationCounter = 0;
                }
            }
        }
        // Texto com digitação progressiva
        int textX = x + gp.tileSize * 2 + 20;
        String[] visibleLines = getVisibleLinesText().split("\n");
        int lineHeight = 40;
        int totalTextHeight = visibleLines.length * lineHeight;
        int textY = y + ((gp.tileSize * 2) - totalTextHeight) / 2 + 32;

        if (textCharIndex < getVisibleLinesText().length()) {
            textCounter++;
            if (textCounter > textDisplaySpeed) {
                textCharIndex++;
                textCounter = 0;
            }
        }
        
        if (textCharIndex < getVisibleLinesText().length()) {
            textCounter++;
            if (textCounter > textDisplaySpeed) {
                char nextChar = getVisibleLinesText().charAt(textCharIndex);

                // ✅ Toca som apenas para letras e números (evita espaços e pontuação)
                if (Character.isLetterOrDigit(nextChar)) {
                	gp.playSE(currentNpcBeepIndex);
                }

                textCharIndex++;
                textCounter = 0;
            }
        }

        // Exibe o texto
        String visibleText = getVisibleLinesText().substring(0, Math.min(textCharIndex, getVisibleLinesText().length()));
        for (String line : visibleText.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
    }
    
    // Retorna até 3 linhas visíveis por vez, separadas por \n
    public String getVisibleLinesText() {
        StringBuilder sb = new StringBuilder();
        for (int i = currentLineStart; i < currentLineStart + 3 && i < fullTextLines.size(); i++) {
            sb.append(fullTextLines.get(i)).append("\n");
        }
        return sb.toString();
    }

    // Reage ao pressionar ENTER durante um diálogo
    public void handleDialogueEnter() {
        String visibleText = getVisibleLinesText();
        if (textCharIndex < visibleText.length()) {
            textCharIndex = visibleText.length(); // Mostra tudo
        } else {
            currentLineStart += 3;
            textCharIndex = 0;
            textCounter = 0;
            if (currentLineStart >= fullTextLines.size()) {
                gp.gameState = gp.playState; // Fim do diálogo
                
               if(gp.gameStage.currentStage == 1) {
            	   gp.closedDialogues++;
               } else if(gp.gameStage.currentStage == 2 && gp.closedFinalDialogueSilas) {
            	   gp.gameStage.currentStage++;
               }
            }
        }
    }
      
    public void drawEmptyDialogueBox() {
        int width = gp.screenWidth / 2;
        int height = gp.tileSize * 3;
        int x = (gp.screenWidth - width) / 2;
        int y = gp.tileSize / 2;

        drawSubWindow(x, y, width, height);
    }

    
    // ===============================
    // TELAS DE INTERAÇÃO
    // ===============================
    
    
    // Desenha a tela de título (menu inicial)
    public void drawTitleScreen() {
        if (titleScreenImage != null) {
            g2.drawImage(titleScreenImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        String text = "";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 6;

        // Sombra e título
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Menu principal com opções
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NOVO JOGO";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0 && selectorImage != null) {
        	
        	int iconX = x - gp.tileSize + 27;
        	int iconY = y - gp.tileSize + 37;
        	g2.drawImage(selectorImage, iconX, iconY, 24, 24, null);
        }

        text = "SAIR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1 && selectorImage != null) {
        	
        	int iconX = x - gp.tileSize + 27;
        	int iconY = y - gp.tileSize + 37;
        	g2.drawImage(selectorImage, iconX, iconY, 24, 24, null);
        }
    }
    
    // Desenha tela de pause
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }
       
    // Desenha Game over
    public void drawGameOverScreen() {
    	
    	g2.setColor(new Color (0,0,0,150));
    	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    	
    	int x;
    	int y;
    	String text;
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
    	
    	text = "GAME OVER";
    	
    	//SOMBRA
    	g2.setColor(Color.black);
    	x = getXforCenteredText(text);
    	y = gp.tileSize * 4;
    	g2.drawString(text, x, y);
    	
    	//GAME OVER
    	g2.setColor(Color.white);
    	g2.drawString(text, x-4, y-4);
    	
    	//TENTE NOVAMENTE
    	g2.setFont(g2.getFont().deriveFont(50f));
    	text = "TENTE NOVAMENTE";
    	x = getXforCenteredText(text);
    	y += gp.tileSize * 4;
    	g2.drawString(text, x, y);
    	if(commandNum == 0) {
    		g2.drawImage(selectorImage, x - gp.tileSize + 27, y - gp.tileSize + 37, 24, 24, null);
    	}
    	
    	//VOLTAR AO MENU
    	text = " MENU";
    	x = getXforCenteredText(text);
    	y += 55;
    	g2.drawString(text, x, y);
    	if(commandNum == 1) {
    		g2.drawImage(selectorImage, x - gp.tileSize + 27, y - gp.tileSize + 37, 24, 24, null);
    	}
    }
    
    public void drawInteractionPrompt() {
        String text = "Aperte '" + interactKey.toUpperCase() + "' para interagir";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int padding = 20;
        int spriteSize = 40;

        int boxWidth = textWidth + spriteSize + padding * 2;
        int boxHeight = 60;

        int boxX = gp.screenWidth - boxWidth - gp.tileSize / 2;
        int boxY = gp.screenHeight - boxHeight - gp.tileSize / 4;

        // Fundo
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        // Borda
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        // Sprite da tecla atual
        BufferedImage[] keyFrames = getKeyFrames(interactKey.toLowerCase());
        if (keyFrames != null) {
            BufferedImage currentFrame = keyFrames[frameIndex];

            int spriteX = boxX + padding;
            int spriteY = boxY + (boxHeight - spriteSize) / 2;

            g2.drawImage(currentFrame, spriteX, spriteY, spriteSize, spriteSize, null);

            // Animação
            frameCounter++;
            if (frameCounter > frameSpeed) {
                frameIndex = (frameIndex + 1) % keyFrames.length;
                frameCounter = 0;
            }
        }

        // Texto
        g2.setColor(Color.white);
        int textX = boxX + padding + spriteSize + 10;
        int textY = boxY + boxHeight / 2 + 8;
        g2.drawString(text, textX, textY);
    }
   
    // Conteúdo dos itens
    public void drawCharacterScreen() {
    	//FRAME
    	final int frameX = gp.tileSize-20;
    	final int frameY = gp.tileSize;
    	final int frameWidth = gp.tileSize*12;
    	final int frameHeight = gp.tileSize*10;
    	drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	
    	int textX = frameX *9;
    	int textY = frameY + 50;
    	g2.setFont(g2.getFont().deriveFont(21F)); 
    	int itemIndex = getItemIndexOnSlot();
    	
    	if(itemIndex < gp.player.inventory.size()) {
    			for(String line: gp.player.inventory.get(itemIndex).conteudo.split("\n")) {
    			g2.drawString(line, textX, textY);
    			textY += 32;
    			}
    	}
    }
    
    // Desenha inventario
    public void drawInventory() {
    	
    	//FRAME
    	int frameX = (gp.tileSize * 13)+20;
    	int frameY = gp.tileSize;
    	int frameWidth = gp.tileSize*6;
    	int frameHeight = gp.tileSize*5;
    	drawSubWindow(frameX, frameY, frameWidth, frameHeight);      
    
    	//SLOT
    	final int slotXstart = frameX + 20;
    	final int slotYstart = frameY + 20;
    	int slotX = slotXstart;
    	int slotY = slotYstart;
    	int slotSize = gp.tileSize+6;
    	
    	//DESENHANDO ITENS
    	for(int i = 0; i < gp.player.inventory.size(); i++) {
    		g2.drawImage(gp.player.inventory.get(i).ObjImage, slotX, slotY, null );
    		slotX += slotSize;
    		
    		if(i == 4 || i == 9 || i == 14) {
    			slotX = slotXstart;
    			slotY += slotSize;
    		} 
    	}
    	
    	//CURSOR
    	int cursorX = slotXstart + (slotSize * slotCol);
    	int cursorY = slotYstart + (slotSize * slotRow);
    	int cursorWidth = gp.tileSize;
    	int cursorHeight = gp.tileSize;
    	
    	//Desenho do Cursor
    	g2.setColor(Color.white);
    	g2.setStroke(new BasicStroke(3));
    	g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    	
    	//Descriprion frame
    	int dFrameX = frameX;
    	int dFrameY = frameY + frameHeight + 10;
    	int dFrameWidth = frameWidth;
    	int dFrameHeight = gp.tileSize*3;
    	drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
    	
    	//Texto da Descrição
    	int textX = dFrameX + 20;
    	int textY = dFrameY + gp.tileSize;
    	g2.setFont(g2.getFont().deriveFont(28F)); 
    	
    	int itemIndex = getItemIndexOnSlot();
    	
    	if(itemIndex < gp.player.inventory.size()) {
    		final int tframeX = gp.tileSize+10;
        	final int tframeY = gp.tileSize+30;
        	final int tframeWidth = gp.tileSize*5;
        	final int tframeHeight = gp.tileSize*8;
    			drawSubWindowImg(tframeX, tframeY, tframeWidth, tframeHeight, gp.player.inventory.get(itemIndex).imagemCarta);
    			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
    				g2.drawString(line, textX, textY);
    				textY += 32;
    			}
    		}
    	}   

    // Desenha opções menu
    public void drawOptionsScreen() {
    	
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(32F));
    	
    	//SUB JANELA
    	int frameX = gp.tileSize * 6;
    	int frameY = gp.tileSize;
    	int frameWidth = gp.tileSize * 8;
    	int frameHeight = gp.tileSize * 10;
    	drawSubWindow(frameX, frameY, frameWidth, frameHeight);	
    	
    	switch(subState) {
    	case 0: options_top(frameX, frameY); break;
    	case 1: option_fullScreenNotification(frameX, frameY); break;
    	case 2: options_control(frameX, frameY); break;
    	case 3: options_endGameConfirmation(frameX, frameY); break;
    	}
 
    	gp.keyH.enterPressed = false;
    }
    
    public void options_top(int frameX, int frameY) {
    	
    	int textX;
    	int textY;
    	
    	//TITULO
    	String text = "CONFIGURAÇÕES";
    	textX = getXforCenteredText(text);
    	textY = frameY + gp.tileSize;
    	g2.drawString(text, textX, textY);
    	
    	// TELA CHEIA
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("TELA CHEIA", textX, textY);
        if(commandNum == 0) {
        	
        	g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
            	if(gp.fullScreenOn == false) {
            		gp.fullScreenOn = true;
            	
            	}
            	else if (gp.fullScreenOn == true) {
            		gp.fullScreenOn = false;
            	}
            subState = 1;
            }
            
        }
        
        //MUSICA
        textY += gp.tileSize;
        g2.drawString("MÚSICA", textX, textY);
        if(commandNum == 1) {
        	g2.drawString(">", textX - 25, textY);
        	
        }
        
        //EFEITO SONORO
        textY += gp.tileSize;
        g2.drawString("EFEITOS SONORO", textX, textY);
        if(commandNum == 2) {
        	g2.drawString(">", textX - 25, textY);
        	
        }
                
        //CONTROLES
        textY += gp.tileSize;
        g2.drawString("CONTROLE", textX, textY);
        if(commandNum == 3) {
        	g2.drawString(">", textX - 25, textY);
        	if(gp.keyH.enterPressed == true) {
        		subState = 2;
        		commandNum = 0;
        	}
        	
        }
        
        //FECHAR O JOGO
        textY += gp.tileSize;
        g2.drawString("MENU", textX, textY);
        if(commandNum == 4) {
        	g2.drawString(">", textX - 25, textY);
        	if(gp.keyH.enterPressed == true){
        		subState = 3;
        		commandNum = 0;
        	}
        	
        }
        
        //VOLTAR
        textY += gp.tileSize * 2;
        g2.drawString("VOLTAR AO JOGO", textX, textY);
        if(commandNum == 5) {
        	g2.drawString(">", textX - 25, textY);
        	if(gp.keyH.enterPressed == true) {
        		gp.gameState = gp.playState;
        		commandNum = 0;
        	}
        	
        }
        
        //CAIXA DE CONFIRMAÇÃO DA TELA CHEIA
        textX = frameX + gp.tileSize * 5;
        textY = frameY + gp.tileSize * 2 + 40;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true) {
        	g2.fillRect(textX, textY, 24, 24);
        }
        
        //VOLUME DA MUSICA
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); //120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        //EFEITO SONORO
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        gp.config.saveConfig();
    }
     
    public void option_fullScreenNotification(int frameX, int frameY) {
    	
    	int textX = frameX + gp.tileSize;
    	int textY = frameY + gp.tileSize * 3;
    	
    	currentDialogue = "PARA APLICAR A MUDANÇA \nÉ NECESSÁRIO REINICIAR O JOGO";
    	
    	for(String line: currentDialogue.split("\n")) {
    		g2.drawString(line, textX, textY);
    		textY += 40;
    	}
    	
    	//VOLTAR
    	textY = frameY + gp.tileSize * 9;
    	g2.drawString("VOLTAR", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString(">", textX - 25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    		}
    	}
    	
    }
    
    public void options_control(int frameX, int frameY) {
    	
    	int textX;
    	int textY;
    	
    	String text = "CONTROLES";
    	textX = getXforCenteredText(text);
    	textY = frameY + gp.tileSize;
    	g2.drawString(text, textX, textY);
    	
    	textX = frameX + gp.tileSize;
    	textY += gp.tileSize;
    	g2.drawString("ANDAR", textX, textY); textY += gp.tileSize;
    	g2.drawString("INTERAGIR", textX, textY); textY += gp.tileSize;
    	g2.drawString("MENU DE PERSONAGEM", textX, textY); textY += gp.tileSize;
    	g2.drawString("PAUSE", textX, textY); textY += gp.tileSize;
    	g2.drawString("CONFIGURAÇÕES", textX, textY); textY += gp.tileSize;
    	
    	textX = frameX + gp.tileSize * 6;
    	textY = frameY + gp.tileSize * 2;
    	g2.drawString("W A S D", textX, textY); textY += gp.tileSize;
    	g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
    	g2.drawString("C", textX, textY); textY += gp.tileSize;
    	g2.drawString("P", textX, textY); textY += gp.tileSize;
    	g2.drawString("ESC", textX, textY); textY += gp.tileSize;
    	
    	//BOTAO DE VOLTAR
    	textX = frameX + gp.tileSize;
    	textY = frameY + gp.tileSize * 9;
    	g2.drawString("VOLTAR", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString(">", textX - 25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    			commandNum = 3;
    		}
    	}
    	
    }
    
    public void options_endGameConfirmation(int frameX, int frameY) {
    	
    	int textX = frameX + gp.tileSize;
    	int textY = frameY + gp.tileSize * 3;
    	
    	currentDialogue = "SAIR DO JOGO  E \nVOLTAR A TELA DE INICIO?";
    	
    	for(String line: currentDialogue.split("\n")) {
    		g2.drawString(line, textX, textY);
    		textY += 40;
    	}
    	
    	//SIM
    	String text = "SIM";
    	textX = getXforCenteredText(text);
    	textY += gp.tileSize * 3;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 0) {
    		g2.drawString(">", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			gp.stopMusic();
    			gp.playMusic(1);
    			subState = 0;
    			gp.gameState = gp.titleState;
    		}
    	}
    	
    	//NÃO
    	text = "NÃO";
    	textX = getXforCenteredText(text);
    	textY += gp.tileSize;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 1) {
    		g2.drawString(">", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    			commandNum = 4;
    		}
    	}
    }
    
    public int getItemIndexOnSlot() {
    	int itemIndex = slotCol + (slotRow*5);
    	return itemIndex;
    }
  
    
    // ===============================
    // MÉTODOS VARIADOS
    // ===============================
    
    
    public BufferedImage[] loadButtonSprites(String path, int frameCount) {
        BufferedImage[] frames = new BufferedImage[frameCount];

        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedImage spriteSheet = ImageIO.read(is);

            int frameWidth = spriteSheet.getWidth() / frameCount;
            int frameHeight = spriteSheet.getHeight();

            for (int i = 0; i < frameCount; i++) {
                frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
            }

        } catch (IOException e) {
            System.out.println("⚠️ Erro ao carregar sprites do botão em: " + path);
            e.printStackTrace();
        }

        return frames;
    }

    public BufferedImage[] getKeyFrames(String key) {
        return buttonSprites.getOrDefault(key, null);
    }
    
    // Desenha uma caixa arredondada com borda branca
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 240); // preto com transparência
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255); // branco para a borda
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    public void drawSubWindowImg(int x, int y, int width, int height, BufferedImage caminhoImg) {
        Color c = new Color(0, 0, 0, 240); // preto com transparência
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.drawImage(caminhoImg, x, y, null );
    }
    
    // ===============================
    // PUZZLES
    // ===============================
    
    
    // Desenha puzzle LockPick
    public void drawLockpickPuzzle() {
    	
    	int centerX = gp.screenWidth / 2;
    	int centerY = gp.screenHeight / 2;
    	int radius = 80;
    	int margin = 40;

    	int boxX = centerX - (radius + margin);
    	int boxY = centerY - (radius + margin + 40); // sobe um pouco pra título caber
    	int boxW = (radius + margin) * 2;
    	int boxH = (radius + margin) * 2 + 60; // altura ajustada pro texto

    	// Fundo preto com borda branca ajustado ao círculo
    	g2.setColor(new Color(0, 0, 0, 220));
    	g2.fillRoundRect(boxX, boxY, boxW, boxH, 35, 35);
    	g2.setColor(Color.white);
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(boxX, boxY, boxW, boxH, 35, 35);


    	// Título centralizado
    	String title = "LOCKPICKING - ETAPA " + lockPickStage;
    	g2.setFont(undertaleFontSans.deriveFont(Font.PLAIN, 48F));
    	int titleWidth = g2.getFontMetrics().stringWidth(title);
    	g2.setColor(Color.white);
    	g2.drawString(title, centerX - titleWidth / 2, boxY - 10); // alinhado ao topo da caixa

    	// Círculo
    	g2.setColor(Color.gray);
    	g2.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

    	// 🔰 Sweet spot VISÍVEL (verde) — MAIS LONGO
    	g2.setColor(Color.green);
    	double sweetRadians = Math.toRadians(lockPickSweetSpot);
    	int sweetLength = radius - 5; // linha um pouco menor que o raio
    	int sweetX = centerX + (int)(sweetLength * Math.cos(sweetRadians));
    	int sweetY = centerY + (int)(sweetLength * Math.sin(sweetRadians));

    	g2.setStroke(new BasicStroke(4));
    	g2.drawLine(centerX, centerY, sweetX, sweetY);

    	// 🔁 Ponteiro giratório (vermelho)
    	lockPickAngle = (lockPickAngle + lockPickSpeed) % 360;
    	g2.setColor(Color.red);
    	double angleRadians = Math.toRadians(lockPickAngle);
    	int pointerX = centerX + (int)(radius * Math.cos(angleRadians));
    	int pointerY = centerY + (int)(radius * Math.sin(angleRadians));
    	g2.setStroke(new BasicStroke(3));
    	g2.drawLine(centerX, centerY, pointerX, pointerY);

    	// Instruções centralizadas
    	String instr1 = "Pressione ENTER quando o ponteiro";
    	String instr2 = "alinhar com o marcador verde";

    	g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
    	int instr1Width = g2.getFontMetrics().stringWidth(instr1);
    	int instr2Width = g2.getFontMetrics().stringWidth(instr2);

    	g2.setColor(Color.white);
    	g2.drawString(instr1, centerX - instr1Width / 2, centerY + radius + 90);
    	g2.drawString(instr2, centerX - instr2Width / 2, centerY + radius + 125);

    }

    public void drawPowerPuzzle(Graphics2D g2) {
        int x1 = gp.screenWidth / 4;
        int x2 = gp.screenWidth * 3 / 4;
        int yStart = gp.screenHeight / 4;
        int spacing = 80;

        g2.setColor(new Color(0, 0, 0, 220));
        g2.fillRoundRect(gp.screenWidth / 2 - 250, yStart - 60, 500, 400, 30, 30);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(28f));
        g2.drawString("Ligue os fios", gp.screenWidth / 2 - 80, yStart - 20);

        for (int i = 0; i < 4; i++) {
            int yLeft = yStart + i * spacing;
            int yRight = yStart + wireMatches[i] * spacing;

            // Linhas desenhadas com base nas conexões do jogador
            if (playerConnections[i] != -1) {
                g2.setColor(wireColors[i]);
                g2.setStroke(new BasicStroke(5));
                g2.drawLine(x1 + 20, yLeft + 20, x2 - 20, yStart + playerConnections[i] * spacing + 20);
            }

            // Bolinhas
            g2.setColor(wireColors[i]);
            g2.fillOval(x1 - 20, yLeft, 40, 40); // esquerda
            g2.fillOval(x2 - 20, yRight, 40, 40); // direita
        }

        // Cursor
        int cursorY = yStart + (choosingLeft ? wireCursorLeft : wireCursorRight) * spacing;
        int cursorX = choosingLeft ? x1 - 10 : x2 - 10;
        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(cursorX, cursorY, 40, 40);
    }

	
	//METODO PARA CENTRALIZAR ELEMENTOS
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
