package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random; // Importa a classe Random para gerar números aleatórios

import javax.imageio.ImageIO;

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_Ismael extends Entity {

	// Construtor do NPC
	public NPC_Ismael(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		direction = "down"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {
	    try {
	        BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/npc/sprite_Ismael_walk_idle.png"));

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

	        faceImage = setup("/npc/ismael_talking");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void setDialogue() {
		
		dialogues[0] = "Shalom Silas, eu tive uma ideia GENIAL! Um jogo onde o personagem principal é... EU! Mas não é qualquer jogo não, é um jogo onde você pode ser professor e aluno ao mesmo tempo!";
		dialogues[1] = "E sabe quem seria o chefão final? Você, Silas! Com terno, gravata... e poderes de coordenador!";
		dialogues[2] = "Já falei com o pessoal da TI. Eles disseram que se eu parar de usar o servidor como nuvem pessoal, talvez eles ajudem.";
		dialogues[3] = "Silas, você já jogou todos os Resident Evil? Porque eu joguei TODOS. E eu ainda jogo! TODO DIA!";
		dialogues[4] = "Tô pensando em fazer uma side quest que é só sobre você tentando corrigir TCC atrasado.";
		dialogues[5] = "Ah, e adicionei um easter egg: se você digitar 'coordenador' três vezes, aparece um minigame de corrigir trabalho com prazos absurdos.";
		dialogues[6] = "Mas calma, Silas, vai ter ética, vai ter metodologia... vai ter APA mesmo, de cabeça e tudo!";
		dialogues[7] = "Esse jogo vai revolucionar a educação. Vai ser tipo Pokémon misturado com PowerPoint!";
		dialogues[8] = "Silas... esse jogo é maneirão. Confia.";

		
	}
	
	// Define o comportamento do NPC (movimento aleatório)
	@Override
	public void setAction() {
	    actionDuration++;

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
		
		
		
		//Faz coisas especificas do personagem
		
		super.speak();
	}
	
}
