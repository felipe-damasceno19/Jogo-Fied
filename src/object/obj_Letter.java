package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter extends Entity{

	public obj_Letter(GamePanel gp) {
		super(gp);
		
		name = "Anotação sala 1";
		ObjImage = setup("/objects/letter");
		description = "[" + name + "]\nUma carta misteriosa.";
		conteudo = "[" + name + "]\nO paciente tem evitado discussões \nprolongadas sobre sua rotina \nfora da instituição. Demonstra um \ncansaço crônico, mas se recusa a \nfalar sobre a origem exata. \r\n"
				+ "\r\n"
				+ "Durante a sessão, comentou que \n“a cabeça não desliga nem quando \nacaba o turno”. Quando questionei \nse se referia ao trabalho aqui ou em \noutro lugar, ele desviou o olhar. \r\n"
				+ "\r\n"
				+ "Apresenta sinais claros de privação \nde descanso. Há sobreposição de \nturnos em sua rotina semanal. ";
		imagemCarta = setup("/notes/nota_01");
	}
}
