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



    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startCutscene(String cutsceneID) {
        gp.gameState = gp.cutsceneState;
        active = true;
        segments.clear();
        currentSegment = 0;
        currentDialogueIndex = 0;

        try {
            switch (cutsceneID) {
                case "intro":
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena1.png")),
                        -1,
                        true,
                        "Parecia mais uma noite comum, nada parecia diferente." 
                    ));
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena2.png")),
                        -1,
                        true,
                        "Professora Carol estava indo buscar alguns papéis na enfermaria."
                        
                    ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena3.png")),
                            13,
                            true,
                            "Ao abrir a porta..."

                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena4.png")),
                            6,
                            true,
                            "...Ela se depara com algo que jamais esqueceria."
                            
                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena5.png")),
                            15,
                            true,
                            "Ali, no chão frio, jazia o corpo da psicóloga da faculdade.",
                            "Sem testemunhas.",
                            "Sem som.",
                            "Só o silêncio e o sangue...",                       
                            "A investigação inicial foi rápida",
                            "Demais..."

                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena6.png")),
                            7,
                            true,
                            "Silas, o coordenador do curso, foi chamado com urgência."

                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena6.1.png")),
                            -1,
                            true,
                            "Disseram que o assassino pode estar entre os professores.",
                            "E que pistas foram encontradas…?"

                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena7.png")),
                            -1,
                            true,
                            "Como o lendário coordenador, seu dever é descobrir a verdade. Antes que alguém mais se machuque!"
   
                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena8.png")),
                            14,
                            true,
                            "Enquanto dirige, uma dúvida o persegue, como o nevoeiro que cobre o caminho...",
                            "E se o culpado estiver te esperando?",
                            "Ou pior..."
                            
                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena9.png")),
                            16,
                            true,
                            "...e se ele não souber o que fez?"
                           
                        ));
                 
                    break;
                case "end1":
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaFinal.png")),
                            -1,
                            true,
                            "Silas segura o objeto com força.",
                            "Felipe permanece em silêncio.", 
                            "Ele não tenta fugir."
                           
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cenaFinal2.png")),
                            -1,
                            true,
                            "Apenas sussurra, como um último pedido:" 
                			 ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            -1,
                            true,
                            "Obrigado."
                            ));
                 	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            17,
                            false
                    
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
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/posCreditos.png")),
                            -1,                           
                            true,
                            "Cara das câmeras falando:", 
                            "O que aquele cara tá fazendo ali sozinho?",
                            "E por que ele tá segurando uma faca?"
                    
                        ));
                	break;
                case "end2":
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            -1,
                            true,
                            "Felipe não sorri. Mas algo nele muda.",
                            "Por alguns segundos, ele só respira… fundo, pesado.", 
                            "E então, ele sussurra:" 
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            -1,
                            true,
                            "Ela voltou." 
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/CenaFinalFalso.png")),
                            -1,
                            true,
                            "Você chama seu nome, mas ele não responde.", 
                            "O som dos passos dele ecoa no corredor vazio.", 
                            "E então... só o silêncio." 
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/CenaFinalFalso.png")),
                            18,
                            true, 
                            "O som dos passos dele ecoa no corredor vazio.", 
                            "E então... só o silêncio." 
                    
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
                	
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            -1,
                            true,
                            "Seis meses se passaram desde aquela noite.", 
                            "Você tentou seguir em frente."
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/tela_preta.png")),
                            8,
                            true,
                            "Mas esta madrugada... algo acordou você, um barulho."
                    
                        ));
                	segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/posCreditosFalso.png")),
                            -1,
                            true,
                            "Uma presença está parada na porta do seu quarto.", 
                            "Observando. Silenciosa. Esperando." 
             
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
                	    gp.gameState = gp.playState;

                	    gp.gameStage.currentStage = 1;
                	    gp.gameStage.countFrames = 0;
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
