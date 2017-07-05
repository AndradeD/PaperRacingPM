package grupoPM.projetoPaperRacing.Application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory do projeto que recebe a inicial do país e retorna o link da pista
 * correspondente a inicial.
 * */
public class FactoryPista {

	String filePath = "src/main/java/grupoPM/projetoPaperRacing/Resources/Tracks/";
	Map<String, String> map = new HashMap<String, String>();

	public FactoryPista() {
		map.put("br", filePath + "br/track.xml");
		map.put("be", filePath + "be/track.xml");
		map.put("hu", filePath + "hu/track.xml");
		map.put("it", filePath + "it/track.xml");
		map.put("jp", filePath + "jp/track.xml");
		map.put("uk", filePath + "uk/track.xml");
	}
	
	/**
	 * Retorna o link da pista passando pelo factory, analisa o dicionário e retorna o link da inicial do país.
	 * */
	public String GetLinkPista(String inicialPista) throws IOException {
		String linkPista = new File(map.get(inicialPista.toLowerCase()))
				.getCanonicalPath();
		return linkPista;
	}

}
