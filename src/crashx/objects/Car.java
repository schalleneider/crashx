package crashx.objects;

import java.util.ArrayList;

import interlab.engine.core.GameObject;
import interlab.engine.io.graphics.Camera;
import interlab.engine.timer.TimerFactory;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * Car | Representa��o do objeto carro.
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
	
	/** Velocidade m�xima do carro. */
	public final double MAX_SPEED = 10.0;
	/** Acelera��o do carro. */
	public final double ACCELERATION = 8;
	/** Coeficiente de desacelera��o do carro. */
	public double FRICTION = 1;
	/** Coeficiente de rota��o do carro. */
	public final double MAX_ROTATION = 2.0;
	
	/** Quantidade m�xima de vida do carro. */
	private final double MAX_LIFE = 100.0;
	
	/** Tipo do carro. */
	private CarType type;
	
	/** Posi��o atual do carro. */
	private Point3d position;
	/** Orienta��o do carro. */
	private double orientation;
	/** Velocidade do carro */
	private double speed;
	
	/** Modo de posicionamento da c�mera atual */
	private int cameraMode;
	/** C�mera de vis�o do carro */
	private Camera camera;
	/** Momento em que a c�mera foi trocada de posi��o. */
	private long cameraChangeTime;
	
	/** Quantidade de pontos de vida do carro. */
	private double life;
	
	/** Lista das �reas de colis�o da casa. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posi��o inicial do carro.
	 * @param camera c�mera de vis�o do carro.
	 * @param orientation Orienta��o inicial do carro.
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
	 * Constr�i as �reas de colis�o da casa.
	 */
	private void buildBounds() {
		this.bounds.add(new BoundingBox(new Point3d(-9.75, -6.5, -4.3), new Point3d(9.75, 1.0, 4.3)));
	}

	/**
	 * Retorna a posi��o atual do carro.
	 * @return A posi��o atual do carro.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posi��o atual do carro.
	 * @param position Nova posi��o do carro.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna a orienta��o atual do carro.
	 * @return A orienta��o atual do carro.
	 */
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Atualiza a orienta��o atual do carro.
	 * @param orientation Nova orienta��o do carro.
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
	 * Retorna o modo de posicionamento da c�mera atual.
	 * @return O modo de posicionamento da c�mera atual.
	 */
	public int getCameraMode() {
		return this.cameraMode;
	}
	
	/**
	 * Altera o modo de posicionamento da c�mera atual.
	 */
	public void changeCameraMode() {
		if (TimerFactory.getTimer().currentTime() - cameraChangeTime > 300) {
			this.cameraMode = (this.cameraMode + 1) % 4;
			this.cameraChangeTime = TimerFactory.getTimer().currentTime();
		}
	}
	
	/**
	 * Retorna a c�mera de vis�o do carro.
	 * @return A c�mera de vis�o do carro.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Indica se o carro est� em movimento.
	 * @return True se o carro estiver em movimento, false caso contr�rio.
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
	 * Indica se o carro est� morto.
	 * @return True caso o carro esteja morto, false caso contr�rio.
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
	 * Retorna a lista das �reas de colis�o ocupada pela casa.
	 * @return A lista das �reas de colis�o ocupada pela casa.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
