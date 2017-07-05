package grupoPM.projetoPaperRacing.Model;

/**
 * Interface a ser utilizada no PaperMap.
 */
public interface TileBasedMap {
	/**
	 * Pega a largura da Posição.
	 */
	public int getWidthInTiles();

	/**
	 * Pega a altura da Posição.
	 */
	public int getHeightInTiles();

	/**
	 * Verifica se a Posição está bloqueada.
	 */
	public boolean blocked(int x, int y);

	/**
	 * Método para pegar o custo entre duas Posições.
	 */
	public float getCost(int sx, int sy, int tx, int ty);

	/**
	 * Método para indicar que o PathFinder visitou determinada Posição.
	 */
	public void pathFinderVisited(int x, int y);
}
