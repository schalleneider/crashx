package crashx.states;

import crashx.game.CrashXGame;
import crashx.overlays.LoseOverlay;
import crashx.overlays.TiedOverlay;
import crashx.overlays.WinOverlay;
import interlab.engine.core.Game;
import interlab.engine.core.GameState;

/**
 * EndGame | Estado de fim de jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class EndGame extends GameState{

	/** Indicador de fim de jogo */
	private boolean hasEnd = false;
	
	/**
	 * Limpeza necessária antes do término do estado.
	 * @param game Jogo em execução.
	 */
	public void cleanup(Game game) {
		
	}

	/**
	 * Inicializa o estado.
	 * @param game Jogo em execução.
	 */
	public void initialize(Game game) {
		if (!this.hasEnd) {
			if (((CrashXGame)game).getCar(0).isDead()) {
				if (((CrashXGame)game).getCar(1).isDead()) {
					((CrashXGame)game).getView(0).setOverlay(new TiedOverlay());
					((CrashXGame)game).getView(1).setOverlay(new TiedOverlay());
				} else {
					((CrashXGame)game).getView(0).setOverlay(new LoseOverlay());
					((CrashXGame)game).getView(1).setOverlay(new WinOverlay());
				}
			} else {
				((CrashXGame)game).getView(0).setOverlay(new WinOverlay());
				((CrashXGame)game).getView(1).setOverlay(new LoseOverlay());
			}
			this.hasEnd = true;
		}
	}

	/**
	 * Executado antes do inicio do ciclo do loop principal.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void preStep(Game game, long delta) {
		
	}
	
	/**
	 * Executado após o término do ciclo do loop principal.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void postStep(Game game, long delta) {
		
	}
	
	/**
	 * Atualiza o estado dos objetos ligados a este estado.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
	}
}
