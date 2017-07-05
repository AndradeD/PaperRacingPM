package grupoPM.projetoPaperRacing.Model;

import java.util.ArrayList;

public class Path {
	/**
	 * Lista de todos os steps que constroem o path.
	 */
	public ArrayList<Step> steps = new ArrayList<Step>();

	/**
	 * Cria um path vazio.
	 */
	public Path() {

	}

	/**
	 * Pega o tamanho do path.
	 */
	public int getLength() {
		return steps.size();
	}

	/**
	 * Pega o step de acordo com o index do path.
	 */
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}

	/**
	 * Pega a coordenada x do step em um determinado index.
	 */
	public int getX(int index) {
		return getStep(index).x;
	}

	/**
	 * Pega a coordenada y do step em um determinado index.
	 */
	public int getY(int index) {
		return getStep(index).y;
	}

	/**
	 * Adiciona um step ao path ao final da lista.
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x, y));
	}

	/**
	 * Adiciona um step ao path no início da lista.
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}

	/**
	 * Checa se um path contém determinado step.
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x, y));
	}

	/**
	 * Um step do Path.
	 */
	public class Step {
		private int x;
		private int y;

		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}

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
		 * Multiplica x e y.
		 */
		public int hashCode() {
			return x * y;
		}

		/**
		 * Verifica se um step é igual ao outro.
		 */
		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;

				return (o.x == x) && (o.y == y);
			}

			return false;
		}
	}
}
