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

		direction = "down"; // Define a direção inicial do NPC como "baixo"
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
		
		dialogues[0] = "Silas, você sabia que o Rio é a cidade com a maior quantidade de praias no mundo? Eu, como boa carioca, posso te garantir que sou especialista em escolher a melhor praia dependendo do clima!";
		dialogues[1] = "Outro dia, eu estava pensando... você já experimentou aquele 'açaí com granola' que o pessoal vende na rua? Aqui nos EUA, eles chamam isso de 'superfood', mas, pra gente, é só comida de rua mesmo, né?";
		dialogues[2] = "Eu sou suspeita, mas eu adoro a mistura de culturas que tem no Rio. Você já reparou que a gente consegue ouvir funk, samba, rock e até música clássica nas ruas? É tipo uma trilha sonora da diversidade!";
		dialogues[3] = "Ah, e falando em diversidade... Sabia que em algumas partes da Europa, o conceito de 'saudade' é meio difícil de entender? Acho que é porque lá as pessoas não têm aquele ‘feeling’ de quem sente falta de um calor humano carioca!";
		dialogues[4] = "Você já foi a alguma reunião pedagógica que parecia mais uma sessão de terapia? Eu juro que, às vezes, a gente acaba mais cansado do que se tivesse dado aula o dia inteiro!";
		dialogues[5] = "Silas, sério... você já passou por aquele momento em que você tenta resolver um problema e acaba criando outro? Eu já fiz isso tantas vezes que até tenho um 'checklist de como não fazer as coisas'!";
		dialogues[6] = "Eu sou apaixonada pela minha experiência na ONG, Silas. Trabalhar com causas sociais me fez entender muito sobre o ser humano, sabe? Às vezes, a gente precisa sair da nossa bolha para realmente enxergar o mundo.";
		dialogues[7] = "Eu morava nos EUA e tinha uma rotina tão diferente. Uma coisa que me marcou foi como lá as pessoas valorizam demais a pontualidade, mas sempre com uma pitada de ‘calma, tudo vai dar certo’. Aqui no Brasil, é mais ‘cheguei, tô chegando’!";
		dialogues[8] = "Eu nunca vou esquecer da viagem à Europa, Silas. O choque de cultura foi enorme! Mas, sério, as melhores lembranças que eu trouxe de lá foram as conversas com as pessoas. Elas realmente têm uma visão de mundo tão diferente!";
		dialogues[9] = "Silas, você já reparou como o tempo passa rápido? Parece que foi ontem que eu estava organizando minha mala para viajar para os EUA e agora cá estou, com histórias de praia, viagens e muitas risadas para contar!";

		
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
