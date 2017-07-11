package crashx.overlays;

import interlab.engine.io.graphics.Overlay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * TiedOverlay | Exibição do resultado para o jogador, no caso de empate.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class TiedOverlay implements Overlay {
		
	/**
	 * Construtor da classe.
	 */
	public TiedOverlay() {
	}

	/**
	 * Desenha o conteúdo a ser exibido.
	 * @param graphics Graphics onde o conteúdo será desenhado.
	 */
	public void draw(Graphics2D graphics) {
		
		Image border = Toolkit.getDefaultToolkit().getImage("crashx/resources/images/tied.png");
		graphics.drawImage(border, 0, 0, null);
		border = null;
	}
}
