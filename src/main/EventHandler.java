package main;


public class EventHandler {

	GamePanel gp; // Referência ao painel principal do jogo
	EventRect eventRect[][][]; // Matriz de áreas de evento para cada tile do mundo
	
	int previousEventX, previousEventY; // Posição do último evento ativado
	boolean canTouchEvent = true; // Flag que determina se o player pode ativar outro evento
	
	public EventHandler(GamePanel gp) {
		
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; // Inicializa a matriz de eventos com base no tamanho do mundo
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();   // Cria uma nova área de evento para cada tile
			eventRect[map][col][row].x = 23;				 // Define posição X relativa dentro do tile
			eventRect[map][col][row].y = 23; 			 // Define posição Y relativa dentro do tile
			eventRect[map][col][row].width = 30; 			 // Largura da área de detecção
			eventRect[map][col][row].height = 30; 		 // Altura da área de detecção
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x; // Armazena valor original para reset
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++; // Avança para o próximo tile na coluna
			if(col == gp.maxWorldCol) { // Quando termina a linha, vai para a próxima
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent() {
		
		// CHECA SE O PLAYER ESTÁ A MAIS DE 1 TILE DE DISTÂNCIA DO ÚLTIMO EVENTO
		int xDistance = Math.abs(gp.player.worldX - previousEventX); // Distância horizontal
		int yDistance = Math.abs(gp.player.worldY - previousEventY); // Distância vertical
		int distance = Math.max(xDistance, yDistance); // Considera a maior das distâncias
		
		if(distance > gp.tileSize) { // Se o player se afastou o suficiente
			canTouchEvent = true; // Permite ativar novos eventos
		}
		
		if(canTouchEvent == true) { // Se pode ativar eventos
			
			// Checa se o player colidiu com algum evento e executa se sim
			if(hit(0, 27, 15, "right") == true) {damagePit( gp.dialogueState);} // Evento de buraco
			else if(hit(0, 23, 7, "up") == true) {healingPool(gp.dialogueState);} // Evento de cura
			else if(hit(0, 58, 36, "any") == true){teleport(1,32,25);} 
			else if(hit(1, 45, 25, "any") == true){teleport(0,63,52);} 
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		// Atualiza as posições da área sólida do jogador e do retângulo de evento para posição absoluta no mundo
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
		eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
		
		// Verifica colisão entre a área do jogador e o retângulo de evento
		if(map == gp.currentMap) {
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				// Verifica se a direção do jogador é a exigida ou se qualquer direção serve
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					// Armazena a posição onde o evento foi ativado
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			// Reseta as posições dos retângulos para seus valores padrões
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		
		return hit; // Retorna se houve colisão válida com o evento
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState; // Altera o estado do jogo para mostrar diálogo
		gp.ui.currentDialogue = "Você caiu em um buraco"; // Mensagem para o jogador
		gp.player.life -= 1; // Aplica dano ao jogador
		//eventRect[col][row].eventDone = true; // (Comentado) Marcar evento como concluído, se quiser que só ocorra uma vez
		canTouchEvent = false; // Impede ativação imediata de outro evento
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) { // Só ativa o evento se o jogador pressionar ENTER
			
			gp.gameState = gameState; // Muda para estado de diálogo
			gp.ui.currentDialogue = "Você bebeu a água, vida recuperada!"; // Mensagem para o jogador
			gp.player.life = gp.player.maxLife; // Restaura a vida do jogador
		}
	}
	
	public void teleport(int map, int col, int row) {
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		System.out.println("oii");
		//gp.playSE(01);
	}
}
