package grupoPM.projetoPaperRacing.Model;

import java.util.ArrayList;

/**
 * Classe da pista na qual terá todas as posições,tamanho da primeira
 * coluna,primeira row, url da pista.
 */
public class Pista {

	private ArrayList<Posicao> posicoesObrigatorias;
	private ArrayList<Posicao> posicoesValidas;
	private ArrayList<Posicao> melhoresPosicoes;
	private String UrlPista;
	private int WidthTotal;
	private int HeightTotal;
	private int BorderWidth;
	private int BorderHeight;
	private int ColumnWidth;
	private int RowHeight;

	public Pista() {
		setPosicoesObrigatorias(new ArrayList<Posicao>());
		setPosicoesValidas(new ArrayList<Posicao>());
		setMelhoresPosicoes(new ArrayList<Posicao>());
	}

	/**
	 * Adiciona a Posição com valor x e y como parâmetros para a lista de
	 * posições.
	 */
	public void addPosicao(ArrayList<Posicao> listaPosicoes, int valorX,
			int valorY) {
		Posicao posicao = new Posicao(valorX, valorY);
		listaPosicoes.add(posicao);
	}

	/**
	 * Pega a altura da primeira row.
	 */
	public int getFirstRowHeight() {
		return RowHeight;
	}

	/**
	 * Pega a largura da primeira coluna.
	 */
	public int getfirstColumnWidth() {
		return ColumnWidth;
	}

	/**
	 * Pega a largura da borda.
	 */
	public int getborderWidth() {
		return BorderWidth;
	}

	/**
	 * Pega a altura da borda.
	 */
	public int getborderHeight() {
		return BorderHeight;
	}

	/**
	 * Pega a lista de posições obrigatórias que são posições onde há
	 * checkpoints.
	 */
	public ArrayList<Posicao> getPosicoesObrigatorias() {
		return posicoesObrigatorias;
	}

	/**
	 * Seta a lista de posições obrigatórias.
	 */
	public void setPosicoesObrigatorias(ArrayList<Posicao> posicoesObrigatorias) {
		this.posicoesObrigatorias = posicoesObrigatorias;
	}

	/**
	 * Pega a lista de posições válidas.
	 */
	public ArrayList<Posicao> getPosicoesValidas() {
		return posicoesValidas;
	}

	/**
	 * Seta a lista de posições válidas.
	 */
	public void setPosicoesValidas(ArrayList<Posicao> posicoesValidas) {
		this.posicoesValidas = posicoesValidas;
	}

	/**
	 * Pega a Url da pista.
	 */
	public String getUrlPista() {
		return UrlPista;
	}

	/**
	 * Seta a Url da pista.
	 */
	public void setUrlPista(String urlPista) {
		UrlPista = urlPista;
	}

	/**
	 * Pega a lista de melhores posições.
	 */
	public ArrayList<Posicao> getMelhoresPosicoes() {
		return melhoresPosicoes;
	}

	/**
	 * Seta a lista de melhores posições.
	 */
	public void setMelhoresPosicoes(ArrayList<Posicao> melhoresPosicoes) {
		this.melhoresPosicoes = melhoresPosicoes;
	}

	/**
	 * Pega a largura total.
	 */
	public int getWidthTotal() {
		return WidthTotal;
	}

	/**
	 * Seta a largura total.
	 */
	public void setWidthTotal(int widthTotal) {
		WidthTotal = widthTotal;
	}

	/**
	 * Pega a largura da borda.
	 */
	public int getBorderWidth() {
		return BorderWidth;
	}

	/**
	 * Seta a largura da borda.
	 */
	public void setBorderWidth(int borderWidth) {
		BorderWidth = borderWidth;
	}

	/**
	 * Pega a altura da borda.
	 */
	public int getBorderHeight() {
		return BorderHeight;
	}

	/**
	 * Pega a altura da borda.
	 */
	public void setBorderHeight(int borderHeight) {
		BorderHeight = borderHeight;
	}

	/**
	 * Pega a largura da coluna.
	 */
	public int getColumnWidth() {
		return ColumnWidth;
	}

	/**
	 * Seta a largura da coluna.
	 */
	public void setColumnWidth(int columnWidth) {
		ColumnWidth = columnWidth;
	}

	/**
	 * Pega a algura da row.
	 */
	public int getRowHeight() {
		return RowHeight;
	}

	/**
	 * Seta a altura da row.
	 */
	public void setRowHeight(int rowHeight) {
		RowHeight = rowHeight;
	}

	/**
	 * Pega a altura total.
	 */
	public int getHeightTotal() {
		return HeightTotal;
	}

	/**
	 * Seta a altura total.
	 */
	public void setHeightTotal(int heightTotal) {
		HeightTotal = heightTotal;
	}

}
