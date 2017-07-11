package crashx.overlays;

import interlab.engine.io.graphics.Overlay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * LoseOverlay | Exibição do resultado para o jogador que perdeu.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class LoseOverlay implements Overlay {
		
	/**
	 * Construtor da classe.
	 */
	public LoseOverlay() {
	}

	/**
	 * Desenha o conteúdo a ser exibido.
	 * @param graphics Graphics onde o conteúdo será desenhado.
	 */
	public void draw(Graphics2D graphics) {
		
		Image border = Toolkit.getDefaultToolkit().getImage("crashx/resources/images/lose.png");
		graphics.drawImage(border, 0, 0, null);
		border = null;
	}
}
