package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter extends Entity{

	public obj_Letter(GamePanel gp) {
		super(gp);
		
		name = "Anotação Sala 1";
		ObjImage = setup("/objects/letter");
		description = "[" + name + "]\nUma carta misteriosa.";
		conteudo = "[" + name + "]\nSou visto onde o arroz vira festa,\r\n"
				+ " e a tradição se veste de alegria.\r\n"
				+ " Procure quem nunca esquece essa data,\r\n"
				+ " mesmo quando a noite esfria.";
		imagemCarta = gp.setupImg("/notes/nota_01", 300, 350);
	}
}
