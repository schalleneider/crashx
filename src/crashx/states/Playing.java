package crashx.states;

import crashx.game.CrashXGame;
import interlab.engine.core.Game;
import interlab.engine.core.GameState;

/**
 * Playing | Estado de execu��o do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Playing extends GameState{

	/**
	 * Limpeza necess�ria antes do t�rmino do estado.
	 * @param game Jogo em execu��o.
	 */
	public void cleanup(Game game) {
		
	}

	/**
	 * Inicializa o estado.
	 * @param game Jogo em execu��o.
	 */
	public void initialize(Game game) {
		((CrashXGame)game).enableCollisions(true);
	}

	/**
	 * Executado antes do inicio do ciclo do loop principal.
	 * @param game Jogo em execu��o.
	 * @param delta Delta.
	 */
	public void preStep(Game game, long delta) {
		
	}
	
	/**
	 * Executado ap�s o t�rmino do ciclo do loop principal.
	 * @param game Jogo em execu��o.
	 * @param delta Delta.
	 */
	public void postStep(Game game, long delta) {
		
	}
	
	/**
	 * Atualiza o estado dos objetos ligados a este estado.
	 * @param game Jogo em execu��o.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
	}
}
