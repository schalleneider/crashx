package crashx;

import interlab.engine.io.input.Keyboard;
import crashx.game.CrashXGame;

/**
 * Main | Inicio da execução do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Main {
	
	/** Instância do jogo em execução. */
	private CrashXGame game;
	
	/**
	 * Inicia a execução do jogo.
	 */
	public void run() {
		this.game = new CrashXGame();
		this.game.setWindowParameters(1024, 512, "CrashX - The Massive Destruction Game!!!");
		this.game.initialize();
		this.game.getInput().registerDevice(Keyboard.getInstance());
		this.game.mainLoop(25, this.game.screenState);
	}
	
	/**
	 * Inicio da execução.
	 * @param args Parâmetros de execução.
	 */
	public static void main(String[] args) {
		try { 
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
