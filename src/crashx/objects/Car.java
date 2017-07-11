package crashx.objects;

import java.util.ArrayList;

import interlab.engine.core.GameObject;
import interlab.engine.io.graphics.Camera;
import interlab.engine.timer.TimerFactory;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * Car | Representação do objeto carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Car extends GameObject {
	
	/** Enumerador determinando o tipo de carro que deve ser usado geometricamente. */
	public enum CarType {
		/***/ TypeA, 
		/***/ TypeB
	}
	
	/** Velocidade máxima do carro. */
	public final double MAX_SPEED = 10.0;
	/** Aceleração do carro. */
	public final double ACCELERATION = 8;
	/** Coeficiente de desaceleração do carro. */
	public double FRICTION = 1;
	/** Coeficiente de rotação do carro. */
	public final double MAX_ROTATION = 2.0;
	
	/** Quantidade máxima de vida do carro. */
	private final double MAX_LIFE = 100.0;
	
	/** Tipo do carro. */
	private CarType type;
	
	/** Posição atual do carro. */
	private Point3d position;
	/** Orientação do carro. */
	private double orientation;
	/** Velocidade do carro */
	private double speed;
	
	/** Modo de posicionamento da câmera atual */
	private int cameraMode;
	/** Câmera de visão do carro */
	private Camera camera;
	/** Momento em que a câmera foi trocada de posição. */
	private long cameraChangeTime;
	
	/** Quantidade de pontos de vida do carro. */
	private double life;
	
	/** Lista das áreas de colisão da casa. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posição inicial do carro.
	 * @param camera câmera de visão do carro.
	 * @param orientation Orientação inicial do carro.
	 * @param type Tipo do carro.
	 */
	public Car(Point3d position, Camera camera, double orientation, CarType type) {
		super();
		this.speed = 0;
		this.type = type;
		this.camera = camera;
		this.cameraMode = 0;
		this.position = position;
		this.life = this.MAX_LIFE;
		this.orientation = orientation;
		this.bounds = new ArrayList<Bounds>();
		this.cameraChangeTime = TimerFactory.getTimer().currentTime();
		this.buildBounds();
	}
	
	/**
	 * Constrói as áreas de colisão da casa.
	 */
	private void buildBounds() {
		this.bounds.add(new BoundingBox(new Point3d(-9.75, -6.5, -4.3), new Point3d(9.75, 1.0, 4.3)));
	}

	/**
	 * Retorna a posição atual do carro.
	 * @return A posição atual do carro.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posição atual do carro.
	 * @param position Nova posição do carro.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna a orientação atual do carro.
	 * @return A orientação atual do carro.
	 */
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Atualiza a orientação atual do carro.
	 * @param orientation Nova orientação do carro.
	 */
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Retorna a velocidade atual do carro.
	 * @return A velocidade atual do carro.
	 */
	public double getSpeed() {
		return this.speed;
	}
	
	/**
	 * Atualiza a velocidade atual do carro.
	 * @param speed Nova velocidade do carro.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * Retorna o tipo do carro.
	 * @return O tipo do carro.
	 */
	public CarType getType() {
		return this.type;
	}
	
	/**
	 * Retorna o modo de posicionamento da câmera atual.
	 * @return O modo de posicionamento da câmera atual.
	 */
	public int getCameraMode() {
		return this.cameraMode;
	}
	
	/**
	 * Altera o modo de posicionamento da câmera atual.
	 */
	public void changeCameraMode() {
		if (TimerFactory.getTimer().currentTime() - cameraChangeTime > 300) {
			this.cameraMode = (this.cameraMode + 1) % 4;
			this.cameraChangeTime = TimerFactory.getTimer().currentTime();
		}
	}
	
	/**
	 * Retorna a câmera de visão do carro.
	 * @return A câmera de visão do carro.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Indica se o carro está em movimento.
	 * @return True se o carro estiver em movimento, false caso contrário.
	 */
	public boolean isMoving() {
		return this.speed > 0.1 || this.speed < -0.1;
	}
	
	/**
	 * Aplica dano ao carro. 
	 * @param factor Fator de dano a ser aplicado ao carro.
	 */
	public void damageCar(double factor) {
		this.life = this.life - Math.abs(factor) * 5; 
	}
	
	/**
	 * Indica se o carro está morto.
	 * @return True caso o carro esteja morto, false caso contrário.
	 */
	public boolean isDead() {
		return this.life <= 0;
	}
	
	/**
	 * Retorna a porcentagem de vida atual do carro.
	 * @return A porcentagem de vida atual do carro.
	 */
	public double getLifePercent() {
		return this.life / this.MAX_LIFE < 0 ? 0 : this.life / this.MAX_LIFE;
	}
	
	/**
	 * Retorna a lista das áreas de colisão ocupada pela casa.
	 * @return A lista das áreas de colisão ocupada pela casa.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
