package grupoPM.projetoPaperRacing.Model;

public interface AStarHeuristic {

	/**
	 * Interface que implementa o custo heurístico adicional pra determinada
	 * posição e a posição target.Controla a ordem que cada posição é pesquisada
	 * enquanto tenta encontrar o path para a localização target.Quanto menor o
	 * custo, maior a chance da posição ser pesquisada.
	 * 
	 * @param map
	 *            O mapa PaperMap.
	 * @param x
	 *            Posição x a se avaliar
	 * @param y
	 *            Posição y a se avaliar
	 * @param tx
	 *            Posição x target
	 * @param ty
	 *            Posição y target
	 * @return Retorna o custo associado à posição
	 */
	public float getCost(TileBasedMap map, int x, int y, int tx, int ty);
}