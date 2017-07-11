package crashx.states;

import java.awt.event.KeyEvent;

import crashx.game.CrashXGame;
import crashx.game.ObjectFactory;
import crashx.objects.updaters.TitleScreenUpdater;
import crashx.overlays.TitleScreenOverlay;
import interlab.engine.core.Game;
import interlab.engine.core.GameObject;
import interlab.engine.core.GameState;
import interlab.engine.io.input.Keyboard;
import interlab.engine.io.sound.GameAudio;

/**
 * Screen | Estado de exibi��o da tela de t�tulo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Screen extends GameState{

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

		GameAudio.getInstance().loopPlayMusic("TITLE");
		
		GameObject screen = ObjectFactory.getInstance().createTitleScreen();
		
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_ENTER),	((TitleScreenUpdater)screen.getUpdater()).startGame);
		
		((CrashXGame)game).addObject(screen);
		((CrashXGame)game).getView(0).setOverlay(new TitleScreenOverlay());
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
