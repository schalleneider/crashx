package crashx.states;

import crashx.game.CrashXGame;
import interlab.engine.core.Game;
import interlab.engine.core.GameState;
import interlab.engine.io.sound.GameAudio;

/**
 * DamageSuffered | Estado de dano sofrido aos carros.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class DamageSuffered extends GameState{

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
		GameAudio.getInstance().playSoundEffect("HIT");
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
		// Verifica se algum dos carros morreu após a colisão. Se sim, encerra o jogo, senão continua o jogo atual.
		if (((CrashXGame)game).getCar(0).isDead() || ((CrashXGame)game).getCar(1).isDead()) {
			((CrashXGame)game).changeState(((CrashXGame)game).endGameState);
		} else {
			((CrashXGame)game).changeState(((CrashXGame)game).playingState);
		}
	}
	
	/**
	 * Atualiza o estado dos objetos ligados a este estado.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
	}
}
