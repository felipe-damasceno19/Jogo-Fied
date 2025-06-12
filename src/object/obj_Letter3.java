package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter3 extends Entity{

		public obj_Letter3(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 3";
			ObjImage = setup("/objects/letter3");
			conteudo = "[" + name + "]\nPela primeira vez, notei um pequeno sinal\n" +
					"de instabilidade dissociativa. O paciente\n" +
					"referiu-se a si mesmo como “ele” brevemente,\n" +
					"depois se corrigiu. Quando perguntei quem\n" +
					"era “ele”, respondeu:\n" +
					"“Às vezes eu acho que tem alguém vivendo\n" +
					"no meu lugar.”\n" +
					"A fala foi seguida de silêncio prolongado.\n" +
					"O cansaço extremo estava visível em sua\n" +
					"expressão.\n" +
					"Uma observação curiosa: ele se referiu ao\n" +
					"uso de óculos como algo recente, “para tentar\n" +
					"focar nas coisas que importam”.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 3.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 400);
		}
}

