package grupoPM.projetoPaperRacing.Application;

import grupoPM.projetoPaperRacing.Model.Pista;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe que implementa a leitura do arquivo xml.
 * */
public class ReadXMLFile {

	/**
	 * Lê o arquivo xml recebido e retorna uma pista com todos os pontos válidos
	 * e exigidos do arquivo.
	 **/
	public Pista ReadXmlFile(String filePath) {

		Pista pista = new Pista();
		try {

			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			NodeList nListPasses = doc.getElementsByTagName("point");

			/**
			 * Pega todos os passes do xml que são as posições obrigatórias.
			 */
			for (int temp = 0; temp < nListPasses.getLength(); temp++) {

				Node nNode = nListPasses.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					int x = Integer.parseInt(eElement.getAttribute("x"));
					int y = Integer.parseInt(eElement.getAttribute("y"));

					pista.addPosicao(pista.getPosicoesObrigatorias(), x, y);
				}
			}

			/**
			 * Pega todos os points do xml que são os pontos válidos da pista.
			 */
			NodeList nListLine = doc.getElementsByTagName("line");
			for (int temp = 0; temp < nListLine.getLength(); temp++) {
				Node nNode = nListLine.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				Element eElement = (Element) nNode;
				System.out.println("points : " + eElement.getAttribute("y"));
				Element eElementColumn = (Element) nNode;
				String[] listaElementosX = eElementColumn.getTextContent()
						.split("\n\t");
				if (listaElementosX.length > 1) {
					for (String Elemento : listaElementosX) {
						if (!Elemento.trim().isEmpty()) {
							int x = Integer.parseInt(Elemento.trim());
							int y = Integer
									.parseInt(eElement.getAttribute("y"));

							pista.addPosicao(pista.getPosicoesValidas(), x, y);
						}
					}
				}
			}

			/**
			 * Leitura de elementos básicos de tamanho da pista
			 */
			Element elemento = (Element) doc.getElementsByTagName("rows").item(
					0);
			pista.setHeightTotal(Integer.parseInt(elemento.getTextContent()));
			elemento = (Element) doc.getElementsByTagName("columns").item(0);
			pista.setWidthTotal(Integer.parseInt(elemento.getTextContent()));
			elemento = (Element) doc.getElementsByTagName("borderHeight").item(
					0);
			pista.setBorderHeight(Integer.parseInt(elemento.getTextContent()));
			elemento = (Element) doc.getElementsByTagName("borderWidth")
					.item(0);
			pista.setBorderWidth(Integer.parseInt(elemento.getTextContent()));
			elemento = (Element) doc.getElementsByTagName("firstColumnWidth")
					.item(0);
			pista.setColumnWidth(Integer.parseInt(elemento.getTextContent()));
			elemento = (Element) doc.getElementsByTagName("firstRowHeight")
					.item(0);
			pista.setRowHeight(Integer.parseInt(elemento.getTextContent()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pista;
	}
}
