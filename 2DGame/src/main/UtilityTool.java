package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // Cria uma nova imagem com o tamanho desejado e o mesmo tipo da original
		Graphics2D g2 = scaledImage.createGraphics();  // Cria um objeto gráfico para desenhar na nova imagem
		g2.drawImage(original, 0, 0, width, height, null); // Desenha a imagem original dentro da nova, ajustando para o novo tamanho
		g2.dispose();  // Libera os recursos usados pelo objeto gráfico (boa prática)
		
		return scaledImage;
		

	}
}
