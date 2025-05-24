package entity;

import java.util.Random; // Importa a classe Random para gerar números aleatórios

import main.GamePanel;

// Define a classe NPC_OldMan que herda da classe Entity
public class NPC_OldMan extends Entity {

	// Construtor do NPC
	public NPC_OldMan(GamePanel gp) {
		super(gp); // Chama o construtor da superclasse (Entity) passando o GamePanel

		direction = "down"; // Define a direção inicial do NPC como "baixo"
		speed = 1; // Define a velocidade do NPC

		getImage(); // Carrega as imagens do NPC
		setDialogue();
	}

	// Método para carregar as imagens de animação do NPC
	public void getImage() {

		up1 = setup("/npc/oldman_up_1"); 
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1"); 
		down2 = setup("/npc/oldman_down_2"); 
		left1 = setup("/npc/oldman_left_1"); 
		left2 = setup("/npc/oldman_left_2"); 
		right1 = setup("/npc/oldman_right_1"); 
		right2 = setup("/npc/oldman_right_2"); 
		faceImage = setup("/npc/ismael_talking");
	}

	public void setDialogue() {
		
		dialogues[0] = "Esse jogo é maneirão";
		dialogues[1] = "Eu quero todos os pals";
		dialogues[2] = "Eu joguei todos os Resident Evil, TODOS!";
		dialogues[3] = "Bolsonaro";
		
	}
	
	// Define o comportamento do NPC (movimento aleatório)
	public void setAction() {

		actionLockCounter++; // Incrementa o contador de ações

		if (actionLockCounter == 120) { // A cada 120 frames (~2 segundos)
			Random random = new Random();
			int i = random.nextInt(100) + 1; // Gera um número aleatório de 1 a 100

			// Define a direção do NPC com base no número sorteado
			if (i <= 25) {
				direction = "up"; // 25% de chance de ir para cima
			}
			if (i > 25 && i <= 50) {
				direction = "down"; // 25% de chance de ir para baixo
			}
			if (i > 50 && i <= 75) {
				direction = "left"; // 25% de chance de ir para a esquerda
			}
			if (i > 75 && i <= 100) {
				direction = "right"; // 25% de chance de ir para a direita
			}

			actionLockCounter = 0; // Reinicia o contador de ações
		}
	}
	
	public void speak() {
		
		//Faz coisas especificas do personagem
		
		super.speak();
	}
	
}
