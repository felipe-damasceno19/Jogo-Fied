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
                        "Naquela manhã, nada parecia diferente.", 
                        "Até que a professora Carol abriu a porta da enfermaria",
                        "E encontrou algo que jamais esqueceria."
                    ));
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena2.png")),
                        "Ali, no chão frio, jazia o corpo da psicóloga da faculdade.",
                        "Sem testemunhas.",
                        "Sem som.",
                        "Só o silêncio e o sangue...",                       
                        "A investigação inicial foi rápida",
                        "Demais."
                    ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena3.png")),
                            "Silas, o coordenador do curso, foi chamado com urgência.",
                            "Disseram que o assassino pode estar entre os professores.",
                            "E que pistas foram encontradas…?",
                            "Como o lendário coordenador, seu dever é descobrir a verdade. Antes que alguém mais se machuque."
                        ));
                    segments.add(new CutsceneSegment(
                            ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena4.png")),
                            "Enquanto dirige, uma dúvida o persegue, como o nevoeiro que cobre o caminho...",
                            "E se o culpado estiver te esperando?",
                            "Ou pior",
                            "...e se ele não souber o que fez."
                        ));
                 
                    break;
                // Adicione mais cutscenes aqui
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        playCurrentDialogue();
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
        }
    }

    public void draw(Graphics2D g2) {
        if (!active) return;

        if (currentSegment < segments.size()) {
            CutsceneSegment seg = segments.get(currentSegment);
            if (seg.image != null) {
                g2.drawImage(seg.image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }
        }

        
        int boxX = 150;
        int boxHeight = (int)(gp.tileSize * 2); 
        int boxY = gp.screenHeight - boxHeight - 30;
        int boxWidth = gp.screenWidth - 300;

        gp.ui.drawSubWindow(boxX, boxY, boxWidth, boxHeight);

        // 🔠 Fonte continua confortável
        g2.setFont(gp.ui.undertaleFontSans.deriveFont(38f));
        g2.setColor(java.awt.Color.white);

        // ✍️ Tipagem com bip

        String visibleText = getVisibleLinesTextForCutscene();

        if (gp.ui.textCharIndex < visibleText.length()) {
            gp.ui.textCounter++;
            if (gp.ui.textCounter > gp.ui.textDisplaySpeed) {
                char nextChar = visibleText.charAt(gp.ui.textCharIndex);
                if (Character.isLetterOrDigit(nextChar)) {
                    gp.playSE(5);
                }
                gp.ui.textCharIndex++;
                gp.ui.textCounter = 0;
            }
        }

        String toDraw = visibleText.substring(0, Math.min(gp.ui.textCharIndex, visibleText.length()));


        // 📍 Posicionamento ajustado
        int textX = boxX + 24;
        int textY = boxY + 40; // deslocamento interno vertical inicial

        // ↕️ Espaçamento maior entre linhas para preencher melhor a caixa
        int lineSpacing = 48;

        for (String line : toDraw.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += lineSpacing;
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
                    // Cutscene terminou!
                    active = false;
                    gp.gameState = gp.playState;

                    // Avança para o próximo estágio da narrativa
                    gp.gameStage.currentStage = 1;
                    gp.gameStage.countFrames = 0;
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

        public CutsceneSegment(BufferedImage image, String... dialogues) {
            this.image = image;
            this.dialogues = dialogues;
        }
    }
}
