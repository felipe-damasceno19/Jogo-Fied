package main;

// Importações necessárias para gráficos, imagens e entrada de dados
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

// Importação das classes do jogo (objetos e entidades)
import object.obj_Heart;
import object.obj_Key;
import entity.Entity;

public class UI {

    GamePanel gp; // Referência ao painel principal do jogo
    Graphics2D g2; // Objeto gráfico para desenhar na tela

    // Fontes do estilo Undertale para títulos e diálogos
    Font undertaleFontSans, undertaleFontRegular;

    // Imagens usadas no HUD de vida (coração cheio, metade e vazio)
    BufferedImage heart_full, heart_half, heart_blank;

    // Sistema de mensagens temporárias na tela
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    // Sinalizador para o término do jogo
    public boolean gameFinished = false;

    // Texto de diálogo atual
    public String currentDialogue = "";

    // Número do comando selecionado no menu (usado na tela de título)
    public int commandNum = 0;

    // Imagem de fundo da tela de título
    BufferedImage titleScreenImage;

    // Sprite do rosto do NPC atual (para a caixa de diálogo)
    public BufferedImage npcFaceImage;

    // Controle da animação do rosto do NPC (alterna entre dois frames)
    int npcFaceAnimationCounter = 0;
    int npcFaceAnimationSpeed = 12; // tempo entre trocas de frame
    boolean npcFaceToggle = false;

    // Sistema de digitação progressiva de texto
    String fullText = "";         // Texto completo a ser exibido
    String displayedText = "";    // Parte já exibida na tela
    int textCharIndex = 0;        // Quantos caracteres já foram mostrados
    int textDisplaySpeed = 2;     // Velocidade de digitação (menor = mais rápido)
    int textCounter = 0;          // Contador para controlar o tempo entre cada letra

    // Lista com todas as linhas do texto, quebradas automaticamente
    java.util.List<String> fullTextLines = new ArrayList<>();
    int currentLineStart = 0; // Índice da primeira linha atualmente visível

    // ===============================
    // CONSTRUTOR DA CLASSE
    // ===============================
    public UI(GamePanel gp) {
        this.gp = gp;

        // Tenta carregar a imagem da tela de título
        try {
            InputStream is = getClass().getResourceAsStream("/title/uninta_quest.png");
            titleScreenImage = ImageIO.read(is);
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

        // Carrega as imagens de HUD do coração (vida do jogador)
        Entity heart = new obj_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    // Mostra uma mensagem simples (ex: "Item coletado!")
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

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

    // Define a imagem do rosto do NPC que está falando
    public void setNpcFaceImage(BufferedImage faceImage) {
        this.npcFaceImage = faceImage;
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
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    // Desenha os corações (vida) do jogador
    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // Desenha os corações vazios (base)
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reinicia e desenha os corações preenchidos
        x = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

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
        if (commandNum == 0) g2.drawString(">", x - gp.tileSize, y);

        text = "CARREGAR JOGO";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) g2.drawString(">", x - gp.tileSize, y);

        text = "SAIR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) g2.drawString(">", x - gp.tileSize, y);
    }

    // Desenha a palavra "PAUSA" no meio da tela
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    // Desenha a caixa de diálogo com texto animado e rosto do NPC
    public void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize - 4;

        // Animação do rosto (2 frames lado a lado)
        if (npcFaceImage != null) {
            int faceSize = gp.tileSize * 2;
            int frameWidth = npcFaceImage.getWidth() / 2;
            int frameHeight = npcFaceImage.getHeight();
            int frameX = npcFaceToggle ? frameWidth : 0;
            BufferedImage faceSprite = npcFaceImage.getSubimage(frameX, 0, frameWidth, frameHeight);
            g2.drawImage(faceSprite, x, y, faceSize, faceSize, null);

            npcFaceAnimationCounter++;
            if (npcFaceAnimationCounter > npcFaceAnimationSpeed) {
                npcFaceToggle = !npcFaceToggle;
                npcFaceAnimationCounter = 0;
            }
        }

        // Texto com digitação progressiva
        int textX = x + gp.tileSize * 2 + 20;
        String[] visibleLines = getVisibleLinesText().split("\n");
        int lineHeight = 40;
        int totalTextHeight = visibleLines.length * lineHeight;
        int textY = y + ((gp.tileSize * 2) - totalTextHeight) / 2 + 30;

        if (textCharIndex < getVisibleLinesText().length()) {
            textCounter++;
            if (textCounter > textDisplaySpeed) {
                textCharIndex++;
                textCounter = 0;
            }
        }

        String visibleText = getVisibleLinesText().substring(0, Math.min(textCharIndex, getVisibleLinesText().length()));
        for (String line : visibleText.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
    }

    // Retorna até 3 linhas visíveis por vez, separadas por \n
    private String getVisibleLinesText() {
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
            }
        }
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
	
	//METODO PARA CENTRALIZAR ELEMENTOS
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
