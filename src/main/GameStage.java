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
    			if(gp.ui.powerRestored == true) {
    				
    			}
    			break;
    		case 3:
    			if(gp.tasksComplete == true) {
    				
    			}
    			break;
    	}
    }

    // Define os diálogos
    public void setDialogue() {
    	dialogues[0][0] = "Bem-vindo de volta, Silas. Veio investigar o assassinato? Esse é o nosso coordenador!";
    	dialogues[0][1] = "Ah... Sabíamos que você viria. Tenho certeza de que vai encontrar o culpado.";
    	dialogues[0][2] = "Aposto que já tem um nome em mente, não é?";
    	dialogues[0][3] = "Ouvi uns boatos de que a psicóloga da faculdade estava de olho em um dos professores da sede.";
    	dialogues[0][4] = "Diziam que esse professor era... um tanto peculiar.";
    	dialogues[0][5] = "Mas, por questões de privacidade, ela nunca revelou quem era. Só o registrou em seu diário.";
    	dialogues[0][6] = "E, para o nosso azar, o assassino encontrou esse diário antes de nós.";
    	dialogues[0][7] = "Algumas páginas foram encontradas espalhadas pelas salas da faculdade.";
    	dialogues[0][8] = "Parece que ele as largou de propósito... Mas por quê?";
    	dialogues[0][9] = "Talvez pra nos provocar... ou só pra brincar com a nossa cara.";
    	dialogues[0][10] = "Nunca se sabe o que se passa na cabeça de gente assim.";
    	dialogues[0][11] = "O culpado rabiscou os trechos que revelavam sua identidade, deixando apenas pistas vagas.";
    	dialogues[0][12] = "Mas se tem alguém capaz de decifrar isso, é você, Silas — o lendário coordenador!";
    	dialogues[0][13] = "Ah, mais um detalhe... Por conta da comoção, só os professores de Sistemas vieram hoje. Não há nenhum aluno na faculdade.";
    	dialogues[0][14] = "Além disso, o assassino desativou a energia do campus. É por isso que tá esse breu todo.";
    	dialogues[0][15] = "Como você sabe, as portas são elétricas e só funcionam com energia.";
    	dialogues[0][16] = "Você vai precisar encontrar os fusíveis espalhados pelo mapa e levá-los até a caixa de energia ao lado do banheiro do auditório";
    	dialogues[0][17] = "Use W, A, S, D para se mover, ENTER para interagir com pessoas, F para objetos e portas, e C para ver seu inventário.";
    	dialogues[0][18] = "Boa sorte, Silas!";


    }


    // Método para iniciar com Dialogo Nelipe
    public void initialStage() {
    		gp.ui.showGenericBox = true;
   

           // Chama o método apenas após 1 segundo
            if (gp.closedDialogues < dialogues[0].length && dialogues[0][gp.closedDialogues] != null && !dialogues[0][gp.closedDialogues].isEmpty()) {
                openDialog(dialogues[0][gp.closedDialogues], face[0]);  // Exibe o diálogo atual
                
                if(gp.closedDialogues == 18) {
                	currentStage++;
                	gp.ui.showGenericBox = false;

                }
            }
		
    }
    
    public void openDoor() {
    	gp.doorLocked = 1;
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


