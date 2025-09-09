package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter4 extends Entity{

		public obj_Letter4(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 4";
			ObjImage = setup("/objects/letter4");
			conteudo = "[" + name + "\nNão são meus olhos, mas me revelam,\r\n"
					+ " nas lentes que nunca me deixam só.\r\n"
					+ " Se quiser saber quem eu sou,\r\n"
					+ " olhe bem: comigo a visão é melhor.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 4.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 350);
		}
}

