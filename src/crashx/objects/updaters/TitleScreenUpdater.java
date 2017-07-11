package crashx.objects.updaters;

import interlab.engine.core.Game;
import interlab.engine.core.Updater;
import interlab.engine.io.input.InputAction;
import crashx.states.Setup;

/**
 * TitleScreenUpdater | Atualiza��o l�gica da tela de t�tulo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class TitleScreenUpdater extends Updater {
	
	/** A��o de in�cio do jogo. */
	public final InputAction startGame;
	
	/**
	 * Construtor da classe.
	 */
	public TitleScreenUpdater() {
		super();
		this.startGame = new InputAction(1, 0, "START");
	}
	
	/**
	 * Efetua a atualiza��o l�gica da tela de t�tulo.
	 * @param game Jogo em execu��o.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
				
		// In�cio do jogo.
		if (this.startGame.getIntensity() > 0) {
			game.changeState(new Setup());
		}
	}

}
