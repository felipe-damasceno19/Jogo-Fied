package main;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import entity.NPC_Carol;
import entity.NPC_Nelipe;
import entity.NPC_Ismael;

public class GameStage {

    GamePanel gp;
    Entity[] npc;
    private int currentDialogue = 0;
    public int currentStage = 0;
    private String[] dialogMessages;
    String dialogues[][] = new String[10][20];
    BufferedImage face[] = new BufferedImage[10];
    public int countFrames = 0;
    int time = 2;
    
    
    
    public GameStage(GamePanel gp) {
        this.gp = gp;        
        npc = new Entity[3];  // Inicializa o array de NPCs
        setDialogue();
        face[0] = setup("/npc/felipe_talking");  // Exemplo de imagem do NPC
    }
    
    public void checkStage() {
    	switch(currentStage) {
    		case 0:
    			gp.cutsceneManager.startCutscene("intro");
    			break;
    		case 1:
    			// Espera passar alguns milesimos de segundo pra rodar o primeiro dialogo;    			
    			if(countFrames >= time) {initialStage(); }
    			countFrames++;
    			
    			break;
    		case 2:
    			
    			break;
    	}
    }

    // Define os diálogos
    public void setDialogue() {
        dialogues[0][0] = "Bem-vindo de volta, Silas. Veio investigar o assassinato? Esse é o nosso coordenador!";
        dialogues[0][1] = "Tenho certeza de que você vai encontrar o culpado!";
        dialogues[0][2] = "Acho que você já tem um nome em mente, não é mesmo?";
        dialogues[0][3] = "Havia uns boatos que a psicóloga da faculdade estava de olho em um dos professores da sede!! Parece que esse professor era um pouco ‘peculiar’.";
        dialogues[0][4] = "Mas a identidade dele nunca foi revelada por questões de privacidade. Você entende, né?";
        dialogues[0][5] = "Ela só o registrou nas anotações do diário dela.";
        dialogues[0][6] = "E, para o nosso azar, o assassino encontrou o diário antes da gente.";
        dialogues[0][7] = "Conseguimos achar algumas páginas espalhadas pelas salas da faculdade.";
        dialogues[0][8] = "Parece que ele as largou por aí... Por quê?";
        dialogues[0][9] = "Talvez para nos dar um desafio, ou só pra tirar sarro da nossa cara.";
        dialogues[0][10] = "Nunca se sabe o que se passa na cabeça de gente assim.";
        dialogues[0][11] = "O culpado rabiscou os campos que revelavam o nome dele, deixando apenas umas informações vagas, que até agora não conseguimos entender.";
        dialogues[0][12] = "Mas tenho certeza de que você, o lendário coordenador, vai desvendar esse mistério!";
        dialogues[0][13] = "Pressione W, A, S, D para se mover, ENTER para interagir com pessoas";
        dialogues[0][14] = "F com objetos/portas e C para ver seu inverntário.";
        dialogues[0][15] = "Boa sorte!!";

    }


    // Método para iniciar com Dialogo Nelipe
    public void initialStage() {
    		gp.ui.showGenericBox = true;

           // Chama o método apenas após 1 segundo
            if (gp.closedDialogues < dialogues[0].length && dialogues[0][gp.closedDialogues] != null && !dialogues[0][gp.closedDialogues].isEmpty()) {
                openDialog(dialogues[0][gp.closedDialogues], face[0]);  // Exibe o diálogo atual
                
                if(gp.closedDialogues == 15) {
                	currentStage++;
                	gp.ui.showGenericBox = false;

                }
            }
		
    }
    
    // Exibe o diálogo
    public void openDialog(String text, BufferedImage faceImage) {
        gp.ui.setNpcFaceImage(faceImage); // Exibe a imagem do NPC
        gp.ui.startDialogue(text);  // Exibe o texto do diálogo
        gp.gameState = gp.dialogueState; // Muda para o estado de diálogo
    }

    // Configura a imagem do NPC
    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}


