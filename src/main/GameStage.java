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
    int dialogueCounts[] = new int[10];  // Array de inteiros que armazena o número de diálogos por conjunto
    boolean openCulpritSelection = false;
    
    
    
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
    			if(countFrames >= time) {startDialogueSequence(0); }
    			countFrames++;
    			
    			break;
    		case 2:
    			if(gp.tasksComplete && gp.interactionFinalNelipe) {
    				startDialogueSequence(2);
    			}
    			break;
    		case 3:
    			
    			if(openCulpritSelection) {
    				gp.gameState = gp.culpritSelectionState;
    				gp.ui.drawCulpritSelectionScreen();
    			}
    			
    			System.out.println(openCulpritSelection);
    			break;
    		case 4:    			
    			startDialogueSequence(1);
    			break;
    		case 5:    			
    			gp.ui.startChoice("O que você escolhe fazer? Quer entregar o Nelipe para a direção?");
    			break;
    	}
    }

    // Define os diálogos
    public void setDialogue() {
    	
    	
    	// Matriz de diálogos
    	dialogues[0][0] = "Simas! Ainda bem que você chegou.";
    	dialogues[0][1] = "A situação é grave: o troféu do Intercursos desapareceu.";
    	dialogues[0][2] = "O ladrão desligou a energia e escondeu os três fusíveis em algum lugar do campus.";
    	dialogues[0][3] = "Sem eles, não há como reativar o painel elétrico.";
    	dialogues[0][4] = "Ouça com atenção, pois cada segundo conta.";
    	dialogues[0][5] = "Para se mover, use W, A, S, D ou as setas.";
    	dialogues[0][6] = "Interaja com portas e objetos usando F e com pessoas usando ENTER.";
    	dialogues[0][7] = "Pressione C para abrir o inventário e ESC para acessar o menu.";
    	dialogues[0][8] = "Quando reunir os três fusíveis, leve-os até o quadro de energia, ao lado direito da quadra.";
    	dialogues[0][9] = "Só então as portas vão destravar e você poderá falar com os professores.";
    	dialogues[0][10] = "Não temos alunos hoje — todos ficaram chocados com o roubo.";
    	dialogues[0][11] = "Mas os professores estão aqui e guardam informações valiosas... talvez até pistas do ladrão.";
    	dialogues[0][12] = "Você também pode verificar as escrivaninhas deles.";
    	dialogues[0][13] = "O culpado espalhou anotações por lá, como se quisesse ser descoberto.";
    	dialogues[0][14] = "Todas as escrivaninhas estão trancadas. Vai ser preciso arrombá-las.";
    	dialogues[0][15] = "Há um clipe perdido pelo campus que pode ajudar com isso. Fique atento!";
    	dialogues[0][16] = "E lembre-se: quando juntar todas as notas, volte e fale comigo novamente.";
    	dialogues[0][17] = "Estou confiando em você, Simas.";
    	dialogues[0][18] = "A ONIINTA inteira conta com sua coragem!";


    	// Total de linhas por SET
    	dialogueCounts[0] = 19; 
    	
    
    	// Matriz de diálogos - Revelação do Nelipe
    	dialogues[1][0] = "Então você percebeu... sim, fui eu.";
    	dialogues[1][1] = "Mas entenda, Simas, eu não fiz isso pelo troféu em si.";
    	dialogues[1][2] = "Ele não tem valor nenhum para mim.";
    	dialogues[1][3] = "Eu precisava que todos olhassem para o roubo, para o escândalo, para a distração.";
    	dialogues[1][4] = "O Conselho da ONINTA planejava cortar os investimentos no nosso curso de tecnologia.";
    	dialogues[1][5] = "Queriam fechar turmas, dispensar professores, acabar com anos de trabalho.";
    	dialogues[1][6] = "Enquanto todos se preocupavam com esse roubo, eu consegui atrasar reuniões e travar decisões.";
    	dialogues[1][7] = "Tudo para ganhar tempo.";
    	dialogues[1][8] = "Tempo para mostrar que o curso merece sobreviver.";
    	dialogues[1][9] = "Eu sabia que você encontraria as pistas.";
    	dialogues[1][10] = "Eu queria ser descoberto... mas não antes de conseguir o que precisava.";
    	dialogues[1][11] = "O troféu era apenas um símbolo, nada além disso.";
    	dialogues[1][12] = "Agora você entende, Simas.";
    	dialogues[1][13] = "Eu não queria fama, não queria glória.";
    	dialogues[1][14] = "Eu queria apenas tempo.";
    	dialogues[1][15] = "Se todos se distraíssem com o troféu, eu poderia salvar nosso curso.";
    	dialogues[1][16] = "Mas a decisão final... não cabe mais a mim.";
    	dialogues[1][17] = "Cabe a você.";

    	dialogueCounts[1] = 18;
    	
    	
       	dialogues[2][0] = "Olha só, seu rosto é de alguém que descobriu algo...";
    	dialogues[2][1] = "Acho que você já juntou todas as notas e já deve ter um palpite de quem seja.";
    	dialogues[2][2] = "Com tudo que descobriu até agora, quem você acha que é?";
    	
    	dialogueCounts[2] = 3;
    	

    }


    
    
    
    public void startDialogueSequence(int setIndex) {
        // Verifica se o conjunto de diálogos existe
        if (setIndex < dialogues.length) {
            // Pega o valor de `gp.closedDialogues` antes de exibir o diálogo
            int currentDialogueIndex = gp.closedDialogues; 

            // Verifica se o próximo diálogo existe e não é nulo
            if (currentDialogueIndex < dialogueCounts[setIndex] && dialogues[setIndex][currentDialogueIndex] != null && !dialogues[setIndex][currentDialogueIndex].isEmpty()) {
                // Exibe o diálogo atual
            	gp.ui.showGenericBox = true;
                openDialog(dialogues[setIndex][currentDialogueIndex], face[0]); 
            }

            
            // Verifica se `gp.closedDialogues` foi incrementado
                if (gp.closedDialogues >= dialogueCounts[setIndex]) {
                    // A sequência de diálogos terminou, chama a função de ação após os diálogos
                    onDialogueCompleted(setIndex);  // Chama o método para realizar alguma ação após os diálogos
                }
            }
        }

    
    
  
    

    // Método que é chamado quando os diálogos são concluídos
    public void onDialogueCompleted(int setIndex) {
        // Exemplo: Avançar para o próximo estágio
        currentStage++;  // Incrementa o estágio atual
        gp.ui.showGenericBox = false;  // Esconde a caixa de diálogo
        gp.closedDialogues = 0;
        
        if(setIndex == 2) {
        	openCulpritSelection = true;
        }
        if(setIndex == 1) {
        	currentStage = 5;
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


