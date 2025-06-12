package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter2 extends Entity{

		public obj_Letter2(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 2";
			ObjImage = setup("/objects/letterS");
			conteudo = "[" + name + "]\nO sujeito tem demonstrado comportamento\n "
					+ "defensivo quando o assunto envolve mobilidade\n"
					+ "e deslocamento. Afirma “não depender de\n"
					+ "ninguém”, mas evita falar diretamente\n"
					+ "sobre seu meio de transporte.\n"
					+ "Relatou dificuldade para manter compromissos\n"
					+ "durante o dia por conta de um “emprego mais\n"
					+ "exigente”, o que, segundo ele, acaba afetando\n"
					+ "sua concentração durante a noite.\n"
					+ "Disse que se sente \\\"preso entre dois mundos\\\"\n"
					+ "mas não deixou claro se era em sentido literal\n"
					+ "ou emocional.\n"
					+ "Ele parece carregar o peso de algo antigo nos\n"
					+ "ombros, mas tenta esconder atrás daquela franja\n"
					+ "escura sempre caindo no rosto — quase como se\n"
					+ "não quisesse ser visto por completo.";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 2.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 400);
		}
}

