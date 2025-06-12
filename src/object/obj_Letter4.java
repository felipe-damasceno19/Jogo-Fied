package object;

import entity.Entity;
import main.GamePanel;

public class obj_Letter4 extends Entity{

		public obj_Letter4(GamePanel gp) {
			super(gp);
			
			name = "Anotação Sala 4";
			ObjImage = setup("/objects/letterS");
			conteudo = "[" + name + "\nO paciente demonstrou sensibilidade incomum\n"+
					"a críticas sutis. Quando mencionei a\n"+
					"possibilidade de um afastamento temporário,\n"+
					"ele reagiu com pânico.\n"+

					"Disse: Parar agora significaria deixar tudo\n"+
					"escapar. A frase sugere possível mecanismo\n"+
					"de controle obsessivo.\n"+

					"Comentou em tom informal: Se eu tivesse\n"+
					"um carro, já teria sumido daqui faz tempo.\n"+
					"(Possível informação falsa: não está claro\n"+
					"se foi sarcasmo ou literal.)\n"+

					"Fez uma referência quase nostálgica a um\n"+
					"lugar cheio de neblina e ladeiras, onde o\n"+
					"silêncio era mais pesado que o vento\n"+
					"Afirmou ter sonhos recorrentes em que\n"+
					"retorna para esse local, mas sempre acorda\n"+
					"antes de chegar.\n";
					
					description = "[" + name + "]\nUma carta misteriosa encontrada \nna sala 4.";
			imagemCarta = gp.setupImg("/notes/nota_01", 300, 400);
		}
}

