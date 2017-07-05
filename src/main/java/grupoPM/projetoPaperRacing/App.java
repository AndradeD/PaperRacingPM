package grupoPM.projetoPaperRacing;

import grupoPM.projetoPaperRacing.Application.FactoryPista;
import grupoPM.projetoPaperRacing.Application.LeitorXML;
import grupoPM.projetoPaperRacing.Application.RedutorCaminhoPista;
import grupoPM.projetoPaperRacing.Model.MapLoader;
import grupoPM.projetoPaperRacing.Model.Pista;
import grupoPM.projetoPaperRacing.Model.Posicao;

import java.util.ArrayList;

/**
 * Classe de aplicação que inicializa a pista , executa o redutor e renderiza o
 * mapa.
 */
public class App {
	/**
	 * Função Principal da aplicação.
	 */
	public static void main(String[] args) throws Exception {
		Pista pista = InitializePista("jp");
		if (pista != null) {
			ArrayList<Posicao> posicoes = GetMelhoresPosicoes(pista);
			MapLoader mapLoader = new MapLoader();
			mapLoader.LoadMapaRender(posicoes, pista);
		}
		System.out.println("OK");
	}

	/**
	 * Função que inicializa a pista chamando o leitor de XML e retorna a pista
	 * com todos os valores x e y válidos e exigidos.
	 */
	public static Pista InitializePista(String inicialPais) throws Exception {
		FactoryPista factory = new FactoryPista();
		String link = factory.GetLinkPista(inicialPais);
		Pista pista = null;
		if (link != "") {
			LeitorXML leitor = new LeitorXML();
			pista = leitor.loadFromFile(link);
			pista.setUrlPista(link);
		}
		return pista;
	}

	/**
	 * Função que recebe a pista e seus valores x e y e aplica o algoritmo para
	 * retornar as melhores posições para finalizar a pista.
	 */
	public static ArrayList<Posicao> GetMelhoresPosicoes(Pista pista) {
		RedutorCaminhoPista redutorCaminhoPista = new RedutorCaminhoPista();
		ArrayList<Posicao> melhoresPosicoes = redutorCaminhoPista
				.findMelhorCaminho(pista);

		for (Posicao posicao : melhoresPosicoes) {
			System.out.println("X: " + posicao.getY());
			System.out.println("Y: " + posicao.getX());
			System.out.println("_______________");
		}
		return melhoresPosicoes;
	}

}
