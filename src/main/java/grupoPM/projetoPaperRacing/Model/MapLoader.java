package grupoPM.projetoPaperRacing.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MapLoader {

	/**
	 * Função que faz o load do Mapa , renderiza e desenha as melhores posições.
	 */
	public void LoadMapaRender(final ArrayList<Posicao> posicoes, Pista pista)
			throws IOException {

		JFrame frame = buildFrame();

		final BufferedImage image = ImageIO.read(new File(pista.getUrlPista()
				.replace("track.xml", "track.png")));

		JPanel pane = new JPanel() {
			/**
			 * Serial Version necessária para executar o JPanel.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				double scaleFactor = Math.min(
						1d,
						getScaleFactorToFit(new Dimension(image.getWidth(),
								image.getHeight()), getSize()));

				int scaleWidth = (int) Math.round(image.getWidth()
						* scaleFactor);
				int scaleHeight = (int) Math.round(image.getHeight()
						* scaleFactor);

				Image scaled = image.getScaledInstance(scaleWidth, scaleHeight,
						Image.SCALE_SMOOTH);

				int width = getWidth() - 1;
				int height = getHeight() - 1;

				int x = (width - scaled.getWidth(this)) / 2;
				int y = (height - scaled.getHeight(this)) / 2;

				g.drawImage(scaled, x, y, this);
				g.setColor(Color.black);
				for (Posicao posicao : posicoes) {
					g.fillRect(posicao.getX() * 12, posicao.getY() * 12, 4, 4);
				}
				g.dispose();
			}
		};
		pane.repaint();
		frame.add(pane);
	}

	/**
	 * Inicializa o Jframe e seta as configurações básicas.
	 */
	private static JFrame buildFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		return frame;
	}

	/**
	 * Faz o cálculo do scale na imagem da pista para melhor visualização no
	 * Jpanel.
	 */
	public static double getScaleFactor(int iMasterSize, int iTargetSize) {
		double dScale = 1;
		if (iMasterSize > iTargetSize) {
			dScale = (double) iTargetSize / (double) iMasterSize;
		} else {
			dScale = (double) iTargetSize / (double) iMasterSize;
		}
		return dScale;
	}

	/**
	 * Faz o fit da imagem para melhor visualização no Jpanel.
	 */
	public static double getScaleFactorToFit(Dimension original, Dimension toFit) {
		double dScale = 1d;
		if (original != null && toFit != null) {
			double dScaleWidth = getScaleFactor(original.width, toFit.width);
			double dScaleHeight = getScaleFactor(original.height, toFit.height);

			dScale = Math.min(dScaleHeight, dScaleWidth);
		}
		return dScale;
	}
}
