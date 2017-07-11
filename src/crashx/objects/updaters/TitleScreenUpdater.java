package crashx.objects.updaters;

import interlab.engine.core.Game;
import interlab.engine.core.Updater;
import interlab.engine.io.input.InputAction;
import crashx.states.Setup;

/**
 * TitleScreenUpdater | Atualização lógica da tela de título.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class TitleScreenUpdater extends Updater {
	
	/** Ação de início do jogo. */
	public final InputAction startGame;
	
	/**
	 * Construtor da classe.
	 */
	public TitleScreenUpdater() {
		super();
		this.startGame = new InputAction(1, 0, "START");
	}
	
	/**
	 * Efetua a atualização lógica da tela de título.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
				
		// Início do jogo.
		if (this.startGame.getIntensity() > 0) {
			game.changeState(new Setup());
		}
	}

}
