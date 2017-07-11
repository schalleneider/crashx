package crashx.game;

import java.util.ArrayList;

import crashx.objects.Car;
import crashx.states.DamageSuffered;
import crashx.states.EndGame;
import crashx.states.Playing;
import crashx.states.Screen;
import crashx.states.Setup;
import interlab.engine.core.GameObject;
import interlab.engine.framework.SinglePlayerGame;
import interlab.engine.io.sound.GameAudio;

/**
 * Game | Gerenciamento do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CrashXGame extends SinglePlayerGame {
		
	/** Estado de exibição da tela de título do jogo. */
	public Screen screenState;
	/** Estado de configuração do jogo. */
	public Setup setupState;
	/** Estado de execução do jogo. */
	public Playing playingState;
	/** Estado de dano sofrido em um dos carros do jogo. */
	public DamageSuffered damageSufferedState;
	/** Estado de fim de jogo. */
	public EndGame endGameState;
	
	/** Lista com os carros do jogo. */
	private ArrayList<Car> cars;
	
	/**
	 * Construtor da classe.
	 */
	public CrashXGame() {
		this.cars = new ArrayList<Car>();
		this.screenState = new Screen();
		this.setupState = new Setup();
		this.playingState = new Playing();
		this.damageSufferedState = new DamageSuffered();
		this.endGameState = new EndGame();
		
		GameAudio.getInstance().addAudioClipMusic("BACK", "crashx/resources/audio/paranoid.mid");
		GameAudio.getInstance().addAudioClipMusic("TITLE", "crashx/resources/audio/blacksabbath.mid");
		GameAudio.getInstance().addAudioClipSoundEffect("HIT", "crashx/resources/audio/hit.wav");
	}
	
	/**
	 * Inicializa o jogo.
	 */
	public void initialize() {
		super.initialize();
	}
	
	/**
	 * Obtém um dos carros do jogo em execução.
	 * @param index Índice do carro a ser retornado.
	 * @return O carro de índice informado.
	 */
	public Car getCar(int index) {
		return this.cars.get(index);
	}
	
	/**
	 * Adiciona um carro à lista de carros existentes no jogo.
	 * @param car Carro a ser adicionado.
	 */
	public void addCar(GameObject car) {
		this.cars.add((Car)car);
	}
}
