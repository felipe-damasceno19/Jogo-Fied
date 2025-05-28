package main;

import javax.swing.JFrame; // Importa a classe JFrame para criar uma janela

public class Main {

	public static JFrame window;
	
	public static void main(String[] args) {
		
		window = new JFrame(); // Cria uma nova janela
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação de fechar o programa ao fechar a janela
		window.setResizable(false); // Impede que o usuário redimensione a janela
		window.setTitle("Jogo FIED"); // Define o título da janela

		
		GamePanel gamePanel = new GamePanel(); // Cria um painel do jogo
		window.add(gamePanel); // Adiciona o painel do jogo à janela
		
		gamePanel.config.loadConfig();
		if(gamePanel.fullScreenOn == true) {
			window.setUndecorated(true);
		}
		
		window.pack(); // Ajusta o tamanho da janela automaticamente baseado no conteúdo
		
		window.setLocationRelativeTo(null); // Centraliza a janela na tela
		window.setVisible(true); // Torna a janela visível
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}