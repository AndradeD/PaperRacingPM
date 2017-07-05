package grupoPM.projetoPaperRacing.Application;

import grupoPM.projetoPaperRacing.Model.Pista;

/**
 * Classe que lê o file e retorna a pista com os pontos válidos e obrigatórios
 * preenchidos.
 * */
public class LeitorXML {
	ReadXMLFile readXMLFile = new ReadXMLFile();

	public Pista loadFromFile(String filename) throws Exception {
		Pista pista = new Pista();

		pista = readXMLFile.ReadXmlFile(filename);
		return pista;
	}

}
