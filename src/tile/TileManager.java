package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp; // Referência ao painel principal do jogo
	public Tile[] tile; // Array de tiles disponíveis (tipos diferentes: grama, parede, etc.)
	public int mapTileNum[][]; // Mapa do mundo armazenado como IDs de tiles (matriz de números)
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp; // Guarda a referência ao GamePanel
		
		// Lê o Tile data 
		InputStream is = getClass().getResourceAsStream("/maps/tileDataS1.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// PEGA OS CAMINHOS DOS TILE E A INFO DE COLISÃO
		String line;
		
		try {
			
			while((line = br.readLine()) != null){
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			} 
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		tile = new Tile[fileNames.size()]; // Inicializa o array de tiles com capacidade para 10 tipos
		getTileImage(); // Carrega as imagens dos tiles
		
		
		// Pega maxWorldCol & Row
		
		
		try {
			
			is = new FileInputStream("res/maps/sampleS1.txt");
			br = new BufferedReader(new InputStreamReader(is));
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			 
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Mapa baseado nas dimensões do mundo
			
			br.close();
			
		} catch(IOException e) {
			System.out.println("Exception:" + e);
		}
		
		
		loadMap("/maps/sampleS1.txt");
		
		
		// loadMap("/maps/world01.txt"); // Carrega o mapa a partir de um arquivo de texto
	}

	public void getTileImage() {
			
		
		for(int i = 0 ; i < fileNames.size(); i++) {
			
			String fileName;
			boolean collision;
			
			// Pega caminho do Arquivo
			fileName = fileNames.get(i);
			
			// Pega info colisão e transforma em Boleano
			if(collisionStatus.get(i).equals("true")) {
				collision = true;	
			} else {
				collision = false;
			}
			
			
			setup(i, fileName, collision);
			
		}
		
//			setup(0, "grass", false);
//			setup(1, "wall", true);
//			setup(2, "water", true);
//			setup(3, "earth", false);
//			setup(4, "tree", true);
//			setup(5, "sand", false);	
	}
	public void setup(int index, String imageName, boolean collision) { // Define um tile no array de tiles. Recebe: índice do tile, nome da imagem e se tem colisão
		
		UtilityTool uTool = new UtilityTool(); // Cria uma instância da classe UtilityTool, que será usada para redimensionar a imagem

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles_sala_01/"+ imageName));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		

	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Abre o arquivo do mapa
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Leitor de texto do arquivo
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) { // Para cada linha do mundo
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
				
					String[] numbers = line.split(" "); // Divide a linha em números (IDs dos tiles)

					int num = Integer.parseInt(numbers[col]); // Converte o número para inteiro
					
					mapTileNum[col][row] = num;
					col++; // Armazena o número (tipo do tile) na posição correta
				}
				if(col == gp.maxWorldCol) {
					
					col = 0;
					row++;
				}
			}

			br.close(); // Fecha o leitor após carregar o mapa
		
		} catch (Exception e) {
			
			e.printStackTrace(); // Em caso de erro na leitura do arquivo
		}
	}
	public void draw(Graphics2D g2) { // Método que desenha o mapa na tela, usando o objeto Graphics2D (g2)
		
		int worldCol = 0; // Começa na coluna 0 do mundo
		int worldRow = 0; // Começa na linha 0 do mundo
			
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) { // Enquanto ainda tiver colunas e linhas no mapa
			
			int tileNum = mapTileNum[worldCol][worldRow]; // Pega o número do tile (tipo de piso) na posição (coluna, linha)
			
			// Calcula onde o tile está no mundo
			int worldX = worldCol * gp.tileSize; // Posição X real no mundo
			int worldY = worldRow * gp.tileSize; // Posição Y real no mundo

			// Calcula onde desenhar o tile na tela (em relação ao jogador)
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			
			// Desenha o tile na posição calculada da tela
		
			
			worldCol++; // Vai pra próxima coluna
			
			// Se chegou ao final da linha, passa pra próxima linha
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0; // Volta pra primeira coluna
				worldRow++; // Vai pra próxima linha
			}
		}
	}

	
}

