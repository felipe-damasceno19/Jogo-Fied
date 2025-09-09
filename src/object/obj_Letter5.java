package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter5 extends Entity{

		public obj_Letter5(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 5";
			ObjImage = setup("/objects/letter5");
			conteudo = "[" + name + "\nNa faculdade guardo encontros,\r\n"
					+ " monitorias sob meu comando.\r\n"
					+ " Quem procura pistas me encontra,\r\n"
					+ " sempre organizando, sempre orquestrando.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 4.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 350);
		}
}

