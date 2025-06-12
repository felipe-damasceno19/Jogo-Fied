package object;

import entity.Entity;
import main.GamePanel;

public class obj_Caderno extends Entity{

	public obj_Caderno(GamePanel gp) {
		super(gp);
		
		name = "Caderno de Anotação";
		ObjImage = setup("/objects/caderno");
		description = "[" + name + "]\nCaderno com as pistas do caso.";
		conteudo = "";
		imagemCarta = gp.setupImg("/objects/Caderno", 300, 400);
	}
}
