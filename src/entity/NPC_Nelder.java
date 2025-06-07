package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random; // Importa a classe Random para gerar números aleatórios

import javax.imageio.ImageIO;

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_Nelder extends Entity {

	// Construtor do NPC
	public NPC_Nelder(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		direction = "down"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {
	    try {
	        BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/npc/sprite_helder_walk_idle.png"));

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

	        faceImage = setup("/npc/helder_talking");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void setDialogue() {
		
		dialogues[0] = "Eu tentei acessar os registros da enfermaria, mas parte deles foi apagada. Alguém sabia exatamente o que queria esconder.";
		dialogues[1] = "Conheci bem a psicóloga... Ela desconfiava de alguém, mas nunca me disse quem. Preferiu guardar em anotações.";
		dialogues[2] = "E antes que pergunte... não, não me venha com 'basicamente'. Nada neste caso é básico, óbvio.";
		dialogues[3] = "Se eu soubesse quem foi, você já teria recebido um relatório completo. Mas tudo o que tenho são dados incompletos.";


		
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
