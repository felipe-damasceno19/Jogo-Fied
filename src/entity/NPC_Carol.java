package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random; // Importa a classe Random para gerar números aleatórios

import javax.imageio.ImageIO;

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_Carol extends Entity {

	// Construtor do NPC
	public NPC_Carol(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		npcBeepIndex = 11; // índice do som específico da Carol

		direction = "down"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {
	    try {
	        BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/npc/sprite_carol_walk_idle.png"));

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

	        faceImage = setup("/npc/carol_talking");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void setDialogue() {
		
		dialogues[0] = "Aê, garoto! Agora sim, luz de volta. Até parece cena de filme de investigação. Mas falta o mais importante: achar o troféu.";
		dialogues[1] = "Escuta, Simas: os criminosos sempre deixam rastros, mesmo os mais ousados. As escrivaninhas escondem papéis que podem ser cruciais.";
		dialogues[2] = "Já vi crimes internacionais e sei como eles funcionam. Esse aqui é diferente, mas segue a mesma lógica: orgulho e vaidade. O ladrão quer ser descoberto";
		dialogues[3] = "Presta atenção, Simas: ninguém entra aqui e some com um troféu sem deixar rastros. As escrivaninhas guardam papéis que podem entregar o culpado, é só você ter coragem de abrir e conferir.";

		
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
	
		 gp.ui.setNpcFace(this.faceImage, npcBeepIndex);
		//Faz coisas especificas do personagem
		
		super.speak();
	}
	
}
