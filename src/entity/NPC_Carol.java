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
		
		dialogues[0] = "Eu fui a primeira a ver... o corpo. Achei que fosse só mais um dia comum. Entrei na enfermaria e... Os olhos dela ainda estavam abertos.";
		dialogues[1] = "Desde aquele dia, não consigo passar perto do pátio sem me arrepiar. E pensar que ele estava sempre por ali, como se nada fosse...";
		dialogues[2] = "Eu li parte das anotações que ela deixou... Havia algo sombrio nelas, mas o nome do culpado estava apagado.";
		dialogues[3] = "Na Itália, dizem: 'Il diavolo si nasconde nei dettagli'... O diabo se esconde nos detalhes. E olha... aqui tem muito detalhe estranho.";

		
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
