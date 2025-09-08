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
		
		dialogues[0] = "Simas, você conseguiu! Energia de volta. Isso é sinal de que podemos avançar. Mas o troféu ainda nos falta.";
		dialogues[1] = "No sítio Oiticicas, onde moro, aprendi que todo segredo tem seu tempo de ser revelado. As pistas nas escrivaninhas são o caminho.";
		dialogues[2] = "Esse ladrão não quer apenas roubar. Ele quer ser lembrado. Cada papel escondido é uma confissão disfarçada.";
		dialogues[3] = "Como amante da festa do Arroz, eu não posso deixar isso assim. Traga as pistas, junte as informações e mostre à ONINTA quem realmente está por trás desse crime.";
		
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
