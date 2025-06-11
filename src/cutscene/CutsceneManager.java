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
                        "Em uma noite sombria,", 
                        "algo estranho aconteceu na mansão.",
                        "As luzes piscaram por um instante...",
                        "e então, tudo ficou em silêncio."
                    ));
                    segments.add(new CutsceneSegment(
                        ImageIO.read(getClass().getResourceAsStream("/cutscenes/cena2.png")),
                        "Você acorda com um ruído distante...",
                        "Será que foi apenas um sonho?",
                        "Ou um aviso do que está por vir?"
                    ));
                    break;
                // Adicione mais cutscenes aqui
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        playCurrentDialogue();
    }

    private void playCurrentDialogue() {
        if (currentSegment < segments.size()) {
            CutsceneSegment seg = segments.get(currentSegment);
            if (currentDialogueIndex < seg.dialogues.length) {
                gp.ui.startDialogue(seg.dialogues[currentDialogueIndex]);
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

        // Caixa de diálogo na parte inferior
        gp.ui.drawSubWindow(80, gp.screenHeight - gp.tileSize * 3 - 40, gp.screenWidth - 160, gp.tileSize * 3);
        g2.setFont(gp.ui.undertaleFontSans.deriveFont(26f));
        g2.setColor(java.awt.Color.white);

        // Animação de digitação
        if (gp.ui.textCharIndex < gp.ui.getVisibleLinesText().length()) {
            gp.ui.textCounter++;
            if (gp.ui.textCounter > gp.ui.textDisplaySpeed) {
                gp.ui.textCharIndex++;
                gp.ui.textCounter = 0;
            }
        }

        // Texto atual visível
        String visibleText = gp.ui.getVisibleLinesText();
        String toDraw = visibleText.substring(0, Math.min(gp.ui.textCharIndex, visibleText.length()));
        int x = 100;
        int y = gp.screenHeight - gp.tileSize * 2;

        for (String line : toDraw.split("\n")) {
            g2.drawString(line, x, y);
            y += 32;
        }
    }

    public void next() {
        String visibleText = gp.ui.getVisibleLinesText();
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
                    active = false;
                    gp.gameState = gp.playState;
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
