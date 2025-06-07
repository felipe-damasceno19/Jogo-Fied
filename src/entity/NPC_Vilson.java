package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random; // Importa a classe Random para gerar números aleatórios

import javax.imageio.ImageIO;

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_Vilson extends Entity {

	// Construtor do NPC
	public NPC_Vilson(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		direction = "down"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {
	    try {
	        BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream("/npc/sprite_wilson_walk_idle.png"));

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

	        faceImage = setup("/npc/wilson_talking");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void setDialogue() {
		
		dialogues[0] = "No dia do crime, estacionei meu Gol perto da entrada. Não vi ninguém, mas tava aquele silêncio estranho... como se algo já tivesse acontecido.";
		dialogues[1] = "Minha esposa acha que tô paranoico, mas eu tenho prestado atenção. Tem gente aqui que carrega um cansaço que não é só físico.";
		dialogues[2] = "Outro dia ouvi um comentário estranho no corredor. Sobre alguém que ‘trabalha demais pra ainda parecer normal’. Achei que fosse piada...";
		dialogues[3] = "O problema é que ninguém presta atenção no que parece comum demais.";
		
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
