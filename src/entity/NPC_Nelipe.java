package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random; // Importa a classe Random para gerar números aleatórios

import javax.imageio.ImageIO;

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_Nelipe extends Entity {

	// Construtor do NPC
	public NPC_Nelipe(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		npcBeepIndex = 9; // índice do som específico da Carol
		direction = "rigth"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {
	    try {
	        BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/npc/sprite_felipe_walk_idle.png"));

	        // Walk: frames 0 a 15
	        for (int i = 0; i < 16; i++) {
	            BufferedImage frame = sheet.getSubimage(i * 32, 0, 32, 32);
	            npcWalkSprites[i / 4][i % 4] = frame;
	        }

	        // Idle: frames 16 a 31
	        for (int i = 16; i < 32; i++) {
	            BufferedImage frame = sheet.getSubimage(i * 32, 0, 32, 32);
	            npcIdleSprites[(i - 16) / 4][(i - 16) % 4] = frame;
	        }

	        faceImage = setup("/npc/felipe_talking");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void setDialogue() {
		
		dialogues[0] = "Estou com muito medo... Um dos meus colegas pode ser o assassino.";
		dialogues[1] = "Caso precise de ajuda, tente conversar com os outros professores.";
		dialogues[2] = "Espero que você já tenha começado a procurar as anotações. Isso é muito importante! Assim que encontrar alguma, venha falar comigo de novo.";
		dialogues[3] = "Acho que vou aprovar todos os alunos... O clima está péssimo para pensar em NAF.";
		dialogues[4] = "Você já foi na festa do arroz, a do Manhoso? É um evento bem diferenciado...";
		dialogues[5] = "Aproveitando que você está aqui: o pessoal do TI disse que vão finalmente fazer um upgrade no roteador! Vão instalar um com duas antenas. Até que enfim!";
		dialogues[6] = "Estava vendo um vídeo no YouTube e descobri que vale muito a pena montar um ecossistema da Positivo. Vou trocar todos os meus aparelhos!";
		dialogues[7] = "Falei da festa do arroz porque ela me traz lembranças da minha adolescência... Que tempo bom! Me ajuda a esquecer, mesmo que por um momento, tudo o que aconteceu.";

		
	}
	
	// Define o comportamento do NPC (movimento aleatório)
	@Override
	public void setAction() {
	    // Impede o movimento aleatório enquanto o gameStage.currentStage for 0
	    if (gp.gameStage.currentStage < 2) {
	        moving = false; // NPC parado
	        actionDuration = 0; // Reseta o contador de ações para garantir que não haja mudança de direção
	        return; // Retorna sem fazer mais nada
	    }

	    actionDuration++;  // Incrementa o contador de ações

	    if (moving) {
	        if (actionDuration >= 60) { // Move por 1 segundo (ajuste se quiser)
	            moving = false;
	            actionDuration = 0;
	            waitTime = new Random().nextInt(6) + 5; // 5 a 10 segundos
	            waitTime *= 60; // em frames (60 fps)
	        }
	    } else {
	        if (actionDuration >= waitTime) {
	            // Decide nova direção aleatória
	            Random random = new Random();
	            int i = random.nextInt(100) + 1;

	            if (i <= 25) direction = "up";
	            else if (i <= 50) direction = "down";
	            else if (i <= 75) direction = "left";
	            else direction = "right";

	            moving = true;
	            actionDuration = 0;
	        }
	    }
	}

	
	public void speak() {
		
		
		gp.ui.setNpcFace(this.faceImage, npcBeepIndex);
		//Faz coisas especificas do personagem
		
		super.speak();
	}
	
}
