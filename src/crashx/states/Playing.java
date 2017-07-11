package crashx.states;

import crashx.game.CrashXGame;
import interlab.engine.core.Game;
import interlab.engine.core.GameState;

/**
 * Playing | Estado de execução do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Playing extends GameState{

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
		((CrashXGame)game).enableCollisions(true);
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
