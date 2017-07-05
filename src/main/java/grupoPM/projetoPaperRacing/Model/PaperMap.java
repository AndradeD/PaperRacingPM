package grupoPM.projetoPaperRacing.Model;

public class PaperMap implements TileBasedMap {
	/**
	 * A largura total de todos os pontos da imagem.
	 */
	private static int WIDTH;
	/**
	 * A altura total de todos os pontos da imagem.
	 */
	private static int HEIGHT;
	/**
	 * Inicializa as unidades com o total de largura/altura.
	 */
	private int[][] units;
	/**
	 * Indica se uma determinada unidade foi visitada durante a busca.
	 */
	private boolean[][] visited;
	/**
	 * Inicializa a pista com o total de largura/altura.
	 */
	private int[][] pistaPaperMap;
	/**
	 * Indica que a posição está fora das posições válidas.
	 */
	public static final int FORAPISTA = 0;
	public static final int CARRO = 1;

	/**
	 * Cria uum novo mapa com uma configuração de início do carro.
	 */
	public PaperMap(int xCarroInicial, int yCarroInicial,Pista pista) {
		WIDTH = pista.getWidthTotal();
		HEIGHT = pista.getHeightTotal();
		units = new int[WIDTH][HEIGHT];
		visited = new boolean[WIDTH][HEIGHT];
		pistaPaperMap = new int[WIDTH][HEIGHT];
		units[xCarroInicial][yCarroInicial] = CARRO;
	}

	/**
	 * Preenche todas as áreas válidas da pista e inicializa a pista com o tipo
	 * do carro para preencher áreas válidas.
	 */
	public void fillAreaValida(int x, int y) {
		for (int xp = x; xp < WIDTH; xp++) {
			for (int yp = y; yp < HEIGHT; yp++) {
				pistaPaperMap[xp][yp] = 1;
			}
		}
	}

	/**
	 * Limpar as áreas visitadas pelo pathFinder.
	 */
	public void clearVisited() {
		for (int x = 0; x < getWidthInTiles(); x++) {
			for (int y = 0; y < getHeightInTiles(); y++) {
				visited[x][y] = false;
			}
		}
	}

	/**
	 * Verifica se determinado ponto foi visitado.
	 */
	public boolean visited(int x, int y) {
		return visited[x][y];
	}

	/**
	 * Pega a unidade de determinada localização(se é a pista ou não).
	 */
	public int getUnit(int x, int y) {
		return pistaPaperMap[x][y];
	}

	/**
	 * Seta a unidade que está na localização.
	 */
	public void setUnit(int x, int y, int unit) {
		units[x][y] = unit;
	}

	/**
	 * Verifica se o caminho está bloqueado.
	 */
	public boolean blocked(int x, int y) {
		if (getUnit(x, y) != 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Pega o custo entre duas posições.
	 */
	public float getCost(int sx, int sy, int tx, int ty) {
		return 1;
	}

	/**
	 * Pega a altura das Unidades.
	 */
	public int getHeightInTiles() {
		return HEIGHT;
	}

	/**
	 * Pega a largura das Unidades.
	 */
	public int getWidthInTiles() {
		return WIDTH;
	}

	/**
	 * Seta que o pathFinder já visitou determinada posição.
	 */
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}

}
