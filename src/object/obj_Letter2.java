package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter2 extends Entity{

		public obj_Letter2(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 2";
			ObjImage = setup("/objects/letterS");
			conteudo = "[" + name + "]\nDe longe vim, de terras serranas,\r\n"
					+ " num distrito chamado Oiticicas.\r\n"
					+ " Quem fala de mim recorda Viçosa,\r\n"
					+ " como se fosse escrita em pistas.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 2.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 350);
		}
}

