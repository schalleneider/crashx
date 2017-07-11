package crashx.overlays;

import interlab.engine.io.graphics.Overlay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * WinOverlay | Exibição do resultado para o jogador que ganhou.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class WinOverlay implements Overlay {
		
	/**
	 * Construtor da classe.
	 */
	public WinOverlay() {
	}

	/**
	 * Desenha o conteúdo a ser exibido.
	 * @param graphics Graphics onde o conteúdo será desenhado.
	 */
	public void draw(Graphics2D graphics) {
		
		Image border = Toolkit.getDefaultToolkit().getImage("crashx/resources/images/win.png");
		graphics.drawImage(border, 0, 0, null);
		border = null;
	}
}
