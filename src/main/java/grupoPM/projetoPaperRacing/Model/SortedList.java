package grupoPM.projetoPaperRacing.Model;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList {
	/**
	 * Lista de Elementos do Array.
	 */
	@SuppressWarnings("rawtypes")
	private ArrayList list = new ArrayList();

	/**
	 * Pega o primeiro elemento da lista.
	 */
	public Object first() {
		return list.get(0);
	}

	/**
	 * Esvazia a lista.
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * Adiciona um elemento na lista e dá um sort.
	 */
	@SuppressWarnings("unchecked")
	public void add(Object o) {
		list.add(o);
		Collections.sort(list);
	}

	/**
	 * Remove o elemento da lista.
	 */
	public void remove(Object o) {
		list.remove(o);
	}

	/**
	 * Pega o número de elementos da lista.
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Checa se um elemento está na lista.
	 */
	public boolean contains(Object o) {
		return list.contains(o);
	}
}