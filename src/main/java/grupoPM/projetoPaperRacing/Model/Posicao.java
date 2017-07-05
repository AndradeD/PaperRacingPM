package grupoPM.projetoPaperRacing.Model;

/**
 * Classe de posição x,y.
 */
public class Posicao {

	private int x;
	private int y;

	/**
	 * Pega o x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Pega o y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Seta o x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Seta o y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Inicializa Posição vazio.
	 */
	public Posicao() {
	}

	/**
	 * Inicializa Posição já com valores x e y.
	 */
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Soma duas posições x,y.
	 */
	public void SomaPosicoes(Posicao a) {
		this.setX(this.getX() + a.getX());
		this.setY(this.getY() + a.getY());
	}
}
