package cutscene;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class CutsceneManager {

    GamePanel gp;

    private boolean active = false;
    private List<CutsceneSegment> segments = new ArrayList<>();
    private int currentSegment = 0;
    private int currentDialogueIndex = 0;
    private List<String> cutsceneTextLines = new ArrayList<>();
    private boolean fadeIn = false;
    private boolean fadeOut = false;
    private int fadeAlpha = 255; // começa com tela preta
    private int fadeSpeed = 5;   // quanto menor, mais suave
    private Runnable onFadeComplete = null;
    private String currentCutsceneID = "";




    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startCutscene(String cutsceneID) {
    	currentCutsceneID = cutsceneID;
    	gp.gameState = gp.cutsceneState;
        active = true;
        segments.clear();
        currentSegment = 0;
        currentDialogueIndex = 0;

        try {
            switch (cutsceneID) {
                case "intro":
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew01.png")),
                        -1,
                        true,
                        "Já passa das dez da noite. O portão está fechado, as ruas silenciosas,",
                        "e apenas algumas lâmpadas de parede iluminam o muro branco com suas sombras alongadas.",
                        "Mas algo aconteceu nesta noite… algo que vai marcar a memória de todos." 
                    ));
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew02.png")),
                        -1,
                        true,
                        "A vitrine central está aberta. O troféu do Intercursos, orgulho dos estudantes, não está mais lá.",
                        "Em seu lugar, apenas marcas de dedos no vidro e arranhões recentes no pedestal.",
                        "O prêmio mais importante da ONIINTA foi levado, e o responsável tentou apagar seus rastros… ",
                        "Mas sempre sobra alguma pista."
                        
                    ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew03.png")),
                            -1,
                            true,
                            "Como se não bastasse, o ladrão desligou o quadro de energia.",
                            "O disjuntor principal foi forçado para baixo e três fusíveis foram arrancados, espalhados pelo campus.",
                            "Sem luz, corredores permanecem em trevas, câmeras estão cegas e, pior ainda, as portas continuam travadas.",
                            "Quem planejou isso sabia exatamente o que fazia."

                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew04.png")),
                            -1,
                            true,
                            "Pesadas portas de ferro bloqueiam a passagem. Elas dependem da energia para liberar os trincos magnéticos.",
                            "Com o disjuntor desativado e os fusíveis ausentes, a faculdade inteira se tornou um labirinto fechado.",
                            "Ninguém entra. Ninguém sai. A única saída: religar a energia."
                            
                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew05.png")),
                            -1,
                            true,
                            "O coração da ONIINTA. No centro, o coreto de telhado vermelho contrasta com o piso de ladrilhos escuros.",
                            "É aqui que a busca começa. O silêncio da noite é quebrado apenas por passos apressados.",
                            "Um professor aguarda, nervoso, olhando em volta.",
                            "Ele sabe que Simas é a única esperança de encontrar o troféu e restaurar a ordem."

                        ));
                 
                    break;
                case "end1":
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/f1c1.png")),
                            -1,
                            true,
                            "Não importa os motivos.",
                            "O que ele fez não pode ser esquecido.", 
                            "Preciso levar isso à direção."
                           
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/f1c2.png")),
                            -1,
                            true,
                            "É melhor acabar com isso de uma vez.",
                            "A verdade precisa ser dita."
                			 ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/f1c3.png")),
                            -1,
                            true,
                            "A ONINTA soube da verdade.",
                            "O futuro do curso de tecnologia mudou para sempre."
                            ));
                 	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/Creditos1.png")),
                            -1,
                            false
                    
                        ));
                 	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/Creditos2.png")),
                            -1,
                            false
                    
                        ));
                	break;
                case "end2":
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaNew05.png")),
                            -1,
                            true,
                            "Ele errou, mas… ",
                            "Talvez tenha feito isso por um motivo maior.",
                            "Esse segredo ficará entre nós."
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/f2c2.png")),
                            -1,
                            true,
                            "Ele parece mais leve.",
                            "Como se tivesse tirado um peso dos ombros." 
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/f2c3.png")),
                            -1,
                            true,
                            "A verdade ficou escondida.",
                            "O curso de tecnologia foi salvo…",
                            "mas a que custo?"
                    
                        ));
        
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/Creditos1.png")),
                            -1,
                            false
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/Creditos2.png")),
                            -1,
                            false
                    
                        ));
                	break;
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }

        playCurrentDialogue();
        fadeIn = true;
        fadeAlpha = 255;
        fadeOut = false;

    }
    
    private String getVisibleLinesTextForCutscene() {
        StringBuilder sb = new StringBuilder();
        int maxLines = 4;

        for (int i = 0; i < maxLines && i < cutsceneTextLines.size(); i++) {
            sb.append(cutsceneTextLines.get(i)).append("\n");
        }

        return sb.toString();
    }

    
    private void prepareCutsceneDialogue(String text) {
        cutsceneTextLines.clear();

        int maxCharsPerLine = 60; // você pode ajustar isso para caber melhor na sua caixa

        while (text.length() > 0) {
            int endIndex = Math.min(maxCharsPerLine, text.length());
            String line = text.substring(0, endIndex);
            int lastSpace = line.lastIndexOf(" ");

            if (endIndex == maxCharsPerLine && lastSpace > 0) {
                line = text.substring(0, lastSpace);
                endIndex = lastSpace;
            }

            cutsceneTextLines.add(line);
            text = text.substring(endIndex).trim();
        }

        gp.ui.textCharIndex = 0;
        gp.ui.textCounter = 0;
    }



    private void playCurrentDialogue() {
        if (currentSegment < segments.size()) {
            CutsceneSegment seg = segments.get(currentSegment);
            if (currentDialogueIndex < seg.dialogues.length) {
            	prepareCutsceneDialogue(seg.dialogues[currentDialogueIndex]);

            }

            if (currentDialogueIndex == 0 && seg.soundIndex >= 0) {
                gp.playSE(seg.soundIndex); // só toca se for um índice válido
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!active) return;

        if (currentSegment < segments.size()) {
            CutsceneSegment seg = segments.get(currentSegment);

            // ✅ SEMPRE desenha a imagem
            if (seg.image != null) {
                g2.drawImage(seg.image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }

            // ✅ SOMENTE desenha caixa de diálogo se necessário
            if (seg.showDialogue) {
                int boxX = 150;
                int boxHeight = (int)(gp.tileSize * 2); 
                int boxY = gp.screenHeight - boxHeight - 30;
                int boxWidth = gp.screenWidth - 300;

                gp.ui.drawSubWindow(boxX, boxY, boxWidth, boxHeight);

                g2.setFont(gp.ui.undertaleFontSans.deriveFont(38f));
                g2.setColor(java.awt.Color.white);

                String visibleText = getVisibleLinesTextForCutscene();
                if (gp.ui.textCharIndex < visibleText.length()) {
                    gp.ui.textCounter++;
                    if (gp.ui.textCounter > gp.ui.textDisplaySpeed) {
                        char nextChar = visibleText.charAt(gp.ui.textCharIndex);
                        if (Character.isLetterOrDigit(nextChar)) {
                            gp.playSE(9);
                        }
                        gp.ui.textCharIndex++;
                        gp.ui.textCounter = 0;
                    }
                }

                String toDraw = visibleText.substring(0, Math.min(gp.ui.textCharIndex, visibleText.length()));

                int textX = boxX + 24;
                int textY = boxY + 40;
                int lineSpacing = 48;

                for (String line : toDraw.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += lineSpacing;
                }
            }
        }

        // 🎬 FADE EFFECT
        if (fadeIn || fadeOut) {
            if (fadeIn) {
                fadeAlpha -= fadeSpeed;
                if (fadeAlpha <= 0) {
                    fadeAlpha = 0;
                    fadeIn = false;
                }
            }
            if (fadeOut) {
                fadeAlpha += fadeSpeed;
                if (fadeAlpha >= 255) {
                    fadeAlpha = 255;
                    fadeOut = false;
                    if (onFadeComplete != null) {
                        onFadeComplete.run(); // executa ação pós-fade
                        onFadeComplete = null;
                    }
                }
            }

            g2.setColor(new java.awt.Color(0, 0, 0, fadeAlpha));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }
    }


    public void next() {
        String visibleText = getVisibleLinesTextForCutscene();
        
        if (gp.ui.textCharIndex < visibleText.length()) {
            gp.ui.textCharIndex = visibleText.length(); // mostra tudo de uma vez
            return;
        }

        currentDialogueIndex++;

        if (currentSegment < segments.size()) {
            CutsceneSegment seg = segments.get(currentSegment);
            if (currentDialogueIndex < seg.dialogues.length) {
                playCurrentDialogue();
            } else {
                currentSegment++;
                currentDialogueIndex = 0;

                if (currentSegment < segments.size()) {
                    playCurrentDialogue();
                } else {
                    fadeOut = true;
                    fadeAlpha = 0;
                    fadeIn = false;

                    onFadeComplete = () -> {
                        active = false;

                        if (currentCutsceneID.equals("end1")) {
                            gp.gameState = gp.titleState;
                            gp.gameStage.currentStage = 0;
                            gp.gameStage.countFrames = 0;
                            gp.stopMusic();
                            gp.playMusic(1);
                        } 
                        else if(currentCutsceneID.equals("end2")) {
                        	 gp.gameState = gp.titleState;
                             gp.gameStage.currentStage = 0;
                             gp.gameStage.countFrames = 0;
                             gp.stopMusic();
                             gp.playMusic(1);
                        }
                        else {
                            gp.gameState = gp.playState;
                            gp.gameStage.currentStage = 1;
                            gp.gameStage.countFrames = 0;
                        }
                    };
                }
            }
        }
    }



    public boolean isActive() {
        return active;
    }

    // Classe interna representando cada imagem + diálogos
    private static class CutsceneSegment {
        public BufferedImage image;
        public String[] dialogues;
        public int soundIndex;
        public boolean showDialogue;

        public CutsceneSegment(BufferedImage image, int soundIndex, boolean showDialogue, String... dialogues) {
            this.image = image;
            this.soundIndex = soundIndex;
            this.dialogues = dialogues;
            this.showDialogue = showDialogue;
            
        }
    }
}
