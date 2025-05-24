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
