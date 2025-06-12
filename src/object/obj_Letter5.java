package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter5 extends Entity{

		public obj_Letter5(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 5";
			ObjImage = setup("/objects/letterS");
			conteudo = "[" + name + "\nO paciente apresenta sinais claros de\n"
					+ "esgotamento psíquico e fragmentação da\n"
					+ "identidade.\n"
					
					+ "Relatou lapsos de memória e negou falas\n"
					+ "anteriores. Disse: “Não lembro do que fiz\n"
					+ "depois das aulas.”\n"
					
					+ "Sempre que pressionado, busca locais abertos,\n"
					+ "evitando salas fechadas.\n"
					
					+ "Tenta conciliar duas rotinas distintas.\n"
					+ "O corpo está exausto; a mente, instável.\n"
					
					+ "Evita falar do passado. Ao mencionar sua\n"
					+ "“cidade anterior”, comentou: “Tudo começou\n"
					+ "a piorar depois que desci a serra.”\n"
					+ "A identidade do paciente segue em sigilo.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 4.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 400);
		}
}

