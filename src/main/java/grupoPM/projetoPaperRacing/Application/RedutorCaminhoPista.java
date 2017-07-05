package grupoPM.projetoPaperRacing.Application;

import grupoPM.projetoPaperRacing.Model.ClosestHeuristic;
import grupoPM.projetoPaperRacing.Model.PaperMap;
import grupoPM.projetoPaperRacing.Model.Path;
import grupoPM.projetoPaperRacing.Model.Path.Step;
import grupoPM.projetoPaperRacing.Model.PathFinder;
import grupoPM.projetoPaperRacing.Model.Pista;
import grupoPM.projetoPaperRacing.Model.Posicao;
import grupoPM.projetoPaperRacing.Model.SortedList;

import java.util.ArrayList;

/**
 * Classe que implementa o algoritmo PathFinder, faz os cálculos e retorna as
 * melhores posições x e y para a pista.
 * */
public class RedutorCaminhoPista implements PathFinder {

	/**
	 * Nós fechados já considerados como totalmente buscados no mapa que ficam
	 * em uma lista.
	 */
	private ArrayList<Node> closed = new ArrayList<Node>();
	/**
	 * Nós abertos ainda não considerados como totalmente buscados no mapa que
	 * ficam em uma lista.
	 */
	private SortedList open = new SortedList();

	/**
	 * O classe da imagem do mapa que tá sendo usado.
	 */
	private PaperMap map;
	/**
	 * O tamanho máximo de busca que o algoritmo utilizará antes de desistir de
	 * procurar.
	 */
	private int maxSearchDistance = 200;

	/**
	 * Array de todos os nós do mapa.
	 */
	private Node[][] nodes;
	/**
	 * Classe que aplica a heurística aplicada para determinar qual nó deve
	 * pesquisar primeiro.
	 */
	private ClosestHeuristic ClosestHeuristic = new ClosestHeuristic();
	/**
	 * Lista de posições válidas Pista.
	 */
	private ArrayList<Posicao> posicoesValidasPista = new ArrayList<Posicao>();

	/**
	 * Função que encontra o melhor caminho usando o algoritmo PathFinder.
	 */
	public ArrayList<Posicao> findMelhorCaminho(Pista pista) {
		ArrayList<Posicao> posicoesValidas = pista.getPosicoesValidas();
		ArrayList<Posicao> posicoesExigidas = pista.getPosicoesObrigatorias();
		posicoesValidasPista = posicoesValidas;

		Posicao posicaoAtual = posicoesExigidas.get(0);
		map = new PaperMap(posicaoAtual.getX(), posicaoAtual.getY(),pista);

		for (Posicao posicao : pista.getPosicoesValidas()) {
			map.fillAreaValida(posicao.getX(), posicao.getY());
		}

		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		open = new SortedList();
		closed = new ArrayList<Node>();

		for (int x = 0; x < map.getWidthInTiles(); x++) {
			for (int y = 0; y < map.getHeightInTiles(); y++) {
				nodes[x][y] = new Node(x, y);
			}
		}

		/**
		 * Pra cada uma das posições exigidas exceto a última, verifica de um em
		 * um, a posição atual e o próximo checkpoint e adiciona no path, o
		 * melhor caminho.
		 */
		for (int i = 1; i < posicoesExigidas.size(); i++) {
			Path path = findPath(posicoesExigidas.get(i - 1).getX(),
					posicoesExigidas.get(i - 1).getY(), posicoesExigidas.get(i)
							.getX(), posicoesExigidas.get(i).getY());
			ArrayList<Posicao> posicoesEncontradas = new ArrayList<Posicao>();
			if (path != null) {
				for (Step step : path.steps) {
					Posicao posicao = new Posicao();
					posicao.setX(step.getX());
					posicao.setY(step.getY());
					posicoesEncontradas.add(posicao);
				}
				for (Posicao posicao : posicoesEncontradas) {
					pista.getMelhoresPosicoes().add(posicao);
				}
			}

		}

		int ultimaPosicao = posicoesExigidas.size() - 1;

		/**
		 * Para o último checkpoint, aplica o algoritmo uma ultima vez até a
		 * posição inicial do carrinho e adiciona o melhor caminho ao path.
		 */
		Posicao posicaoFinal = posicoesExigidas.get(0);
		Path lastPath = findPath(posicaoFinal.getX(), posicaoFinal.getY(),
				posicoesExigidas.get(ultimaPosicao).getX(), posicoesExigidas
						.get(ultimaPosicao).getY());
		ArrayList<Posicao> posicoesUltimoCheck = new ArrayList<Posicao>();
		if (lastPath != null) {
			for (Step step : lastPath.steps) {
				Posicao posicao = new Posicao();
				posicao.setX(step.getX());
				posicao.setY(step.getY());
				posicoesUltimoCheck.add(0, posicao);
			}
			for (Posicao posicao : posicoesUltimoCheck) {
				pista.getMelhoresPosicoes().add(posicao);
			}
		}

		return pista.getMelhoresPosicoes();
	}

	/**
	 * Encontra o melhor caminho para o carro, dado determinado ponto sx,sy
	 * atual até o próximo checkpoint tx,ty.
	 */
	public Path findPath(int sx, int sy, int tx, int ty) {

		/**
		 * Verifica se o destino já foi avaliado para evitar que ele seja
		 * avaliado repetidamente.
		 */
		boolean eAvaliado = false;

		if (map.blocked(tx, ty)) {
			return null;
		}

		/**
		 * O grupo closed nulo.O grupo aberto com o node inicial que ainda será
		 * verificado.
		 */
		nodes[sx][sy].cost = 0;
		nodes[sx][sy].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);

		/**
		 * O parent do target é nulo, ou seja, não sabemos ainda de que forma
		 * chegar lá.
		 */
		nodes[tx][ty].parent = null;

		/**
		 * Enquanto não atingirmos a profundidade máxima, procura próximo nó.
		 */
		int maxDepth = 0;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {

			/**
			 * Retira o primeiro node da lista de abertos.
			 */
			Node current = getFirstInOpen();
			if (current == nodes[tx][ty]) {
				break;
			}

			removeFromOpen(current);
			addToClosed(current);

			/**
			 * Procura os vizinhos do nó atual.
			 */
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {

					/**
					 * Se for o nó atual.
					 */
					if ((x == 0) && (y == 0)) {
						continue;
					}

					/**
					 * Determina a localização do vizinho e avalia.
					 */
					int xp = x + current.x;
					int yp = y + current.y;

					if (isValidLocation(sx, sy, xp, yp)) {

						/**
						 * Percorre posições válidas.
						 */
						for (Posicao posicao : posicoesValidasPista) {

							if (eAvaliado) {
								break;
							}
							Posicao posicaoAtual = new Posicao();
							posicaoAtual.setX(xp);
							posicaoAtual.setY(yp);

							/**
							 * Se a posição a se avaliar é uma posição válida.
							 */
							if (posicao.getX() == posicaoAtual.getX()
									&& posicao.getY() == posicaoAtual.getY()) {
								eAvaliado = true;
								float nextStepCost = current.cost
										+ getMovementCost(current.x, current.y,
												xp, yp);
								Node neighbour = nodes[xp][yp];
								map.pathFinderVisited(xp, yp);

								/**
								 * Se o custo atual for menor que o custo do
								 * vizinho para o próximo step, pega esse
								 * vizinho e remove da lista de open e closed.
								 */
								if (nextStepCost < neighbour.cost) {
									if (inOpenList(neighbour)) {
										removeFromOpen(neighbour);
									}
									if (inClosedList(neighbour)) {
										removeFromClosed(neighbour);
									}
								}

								/**
								 * Se o node não tiver sido processado e
								 * descartado,reseta o custo pro custo corrente
								 * e adiciona como um próximo step para a open
								 * list.
								 */
								if (!inOpenList(neighbour)
										&& !(inClosedList(neighbour))) {
									neighbour.cost = nextStepCost;
									neighbour.heuristic = ClosestHeuristic
											.getCost(map, xp, yp, tx, ty);
									maxDepth = Math.max(maxDepth,
											neighbour.setParent(current));
									addToOpen(neighbour);
								}
							}
						}
						eAvaliado = false;
					}
				}
			}
		}

		/**
		 * Se não for encontrado nenhum parent pro node target, então, não foi
		 * possível encontrar um path, retorna null.
		 */
		if (nodes[tx][ty].parent == null) {
			return null;
		}

		/**
		 * Se o parent não é nulo, cria-se um path e adiciona os nodes
		 * encontrados ao path.
		 */
		Path path = new Path();
		Node target = nodes[tx][ty];
		while (target != nodes[sx][sy]) {
			path.prependStep(target.x, target.y);
			target = target.parent;
		}
		path.prependStep(sx, sy);

		return path;
	}

	public class Node implements Comparable<Object> {
		/**
		 * Coordenada x do Node.
		 */
		private int x;
		/**
		 * Coordenada y do Node.
		 */
		private int y;
		/**
		 * Custo de caminho pro Node.
		 */
		private float cost;
		/**
		 * O pai do Node, que é a forma como o Node atual foi encontrado.
		 */
		private Node parent;
		/**
		 * O custo de heurística pro Node Atual.
		 */
		private float heuristic;
		/**
		 * A profundidade de busca pro Node atual.
		 */
		private int depth;

		/**
		 * Cria um novo node.
		 */
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Seta o pai do Node que nos leva ao node atual e retorna a
		 * profundidade atualizada.
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;

			return depth;
		}

		/**
		 * Compara um nó a outro.
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;

			float f = heuristic + cost;
			float of = o.heuristic + o.cost;

			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Pega o primeiro da lista de Nodes.
	 */
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}

	/**
	 * Adiciona um node a lista de abertos.
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}

	/**
	 * Checa se o node está na lista de abertos.
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}

	/**
	 * Remove um node da lista de abertos.
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}

	/**
	 * Adiciona um node na lista de fechados.
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}

	/**
	 * Checa se o node está na lista de fechados.
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}

	/**
	 * Remove um nó da lista de fechados.
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}

	/**
	 * Checa se uma determinada posição é válida pra movimento.
	 * 
	 * @param sx
	 *            Coordenada x atual.
	 * @param sy
	 *            Coordenada y atual.
	 * @param x
	 *            Coordenada x que se quer checar.
	 * @param y
	 *            Coordenada y que se quer checar.
	 * @return True Se a localização for válida pro carrinho.
	 */
	protected boolean isValidLocation(int sx, int sy, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles())
				|| (y >= map.getHeightInTiles());

		if ((!invalid) && ((sx != x) || (sy != y))) {
			invalid = map.blocked(x, y);
		}

		return !invalid;
	}

	/**
	 * Pega o custo de movimento de acordo com a localização.
	 * 
	 * @param sx
	 *            Coordenada x da posição que se quer analisar o custo.
	 * @param sy
	 *            Coordenada y da posição que se quer analisar o custo.
	 * @param tx
	 *            Coordenada x da localização alvo.
	 * @param ty
	 *            Coordenada y da localização alvo.
	 * @return O custo de movimento para aquela posição.
	 */
	public float getMovementCost(int sx, int sy, int tx, int ty) {
		return map.getCost(sx, sy, tx, ty);
	}

	/**
	 * Pega o custo heurístico para localização dada.Determina a ordem na qual
	 * as localizações serão processadas.
	 * 
	 * @param x
	 *            Coordenada x possível que se possa testar.
	 * @param y
	 *            Coordenada y possível que se possa testar.
	 * @param tx
	 *            Coordenada x da posição obrigatória(alvo) que se quer chegar.
	 * @param ty
	 *            Coordenada y da posição obrigatória(alvo) que se quer chegar.
	 * @return O custo heurístico entre as duas posições.
	 */
	public float getHeuristicCost(int x, int y, int tx, int ty) {
		return ClosestHeuristic.getCost(map, x, y, tx, ty);
	}

}
