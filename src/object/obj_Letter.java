package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter extends Entity{

	public obj_Letter(GamePanel gp) {
		super(gp);
		
		name = "Anotação Sala 1";
		ObjImage = setup("/objects/letter");
		description = "[" + name + "]\nUma carta misteriosa.";
		conteudo = "[" + name + "]\nO paciente tem evitado discussões\n" +
				"prolongadas sobre sua rotina fora da\n" +
				"instituição. Demonstra um cansaço\n" +
				"crônico, mas se recusa a falar sobre a\n" +
				"origem exata.\n" +
				"Durante a sessão, comentou que “a cabeça\n" +
				"não desliga nem quando acaba o turno”.\n" +
				"Quando questionei se se referia ao trabalho\n" +
				"aqui ou em outro lugar, ele desviou o olhar.\n" +
				"Apresenta sinais claros de privação de\n" +
				"descanso. Há sobreposição de turnos em\n" +
				"sua rotina semanal.";
		imagemCarta = gp.setupImg("/notes/nota_01", 300, 400);
	}
}
