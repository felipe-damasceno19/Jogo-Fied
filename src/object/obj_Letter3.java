package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter3 extends Entity{

		public obj_Letter3(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 3";
			ObjImage = setup("/objects/letter3");
			conteudo = "[" + name + "]\nEntre linhas de códigos escondo meu dom,\r\n"
					+ " técnicas ensino a cada lição.\r\n"
					+ " Se quiser achar o culpado, responda:\r\n"
					+ " quem faz da programação sua paixão?";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 3.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 350);
		}
}

