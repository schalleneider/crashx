package crashx.objects.updaters;

import interlab.engine.core.Game;
import interlab.engine.core.Updater;
import interlab.engine.io.input.InputAction;

import javax.vecmath.Point3d;

import crashx.game.CrashXGame;
import crashx.objects.Car;
import crashx.objects.collidables.CarCollidable;

/**
 * CarUpdater | Atualização lógica do carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CarUpdater extends Updater {
	
	/** Carro a ser atualizado. */
	private Car car;
	
	/** Ação de movimentação para a esquerda. */
	public final InputAction turnLeft;
	/** Ação de movimentação para a direita. */
	public final InputAction turnRight;
	/** Ação de movimentação para a frente. */
	public final InputAction moveForward;
	/** Ação de movimentação para trás. */
	public final InputAction moveBackward;
	
	/** Ação de mudança do posicionamento da câmera. */
	public final InputAction changeCameraPosition;
	
	/**
	 * Construtor da classe.
	 * @param car Objeto a ser atualizado.
	 */
	public CarUpdater(Car car) {
		super();
		this.car = car;
		this.turnLeft = new InputAction(1, 0, "LEFT");
		this.turnRight = new InputAction(2, 0, "RIGHT");
		this.moveForward = new InputAction(3, 0, "FORWARD");
		this.moveBackward = new InputAction(4, 0, "BACKWARD");
		this.changeCameraPosition = new InputAction(5, 0, "CHANGECAM");
	}
	
	/**
	 * Efetua a atualização lógica do carro.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
		double turnSpeed = 0;
		double moveSpeed = 0;
		
		// Movimentação para a esquerda.
		if (this.turnLeft.getIntensity() > 0) {
			// Carro rotaciona somente com carro em movimento.
			if (this.car.isMoving()) {
				turnSpeed = +Math.toRadians(this.car.MAX_ROTATION) * (this.car.getSpeed() > 0 ? 1 : -1);
			}
		}
		
		// Movimentação para a direita.
		if (this.turnRight.getIntensity() > 0) {
			// Carro rotaciona somente com carro em movimento.
			if (this.car.isMoving()) {
				turnSpeed = -Math.toRadians(this.car.MAX_ROTATION) * (this.car.getSpeed() > 0 ? 1 : -1);
			}
		}
		
		// Movimentação para frente.
		if (this.moveForward.getIntensity() > 0) {
			// Aceleração do carro.
			moveSpeed = this.car.ACCELERATION * delta;
		}
		
		// Movimentação para trás.
		if (this.moveBackward.getIntensity() > 0) {
			// Aceleração do carro.
			moveSpeed = -this.car.ACCELERATION * delta;
		}
		
		// Alteração do modo de posicionamento da câmera.
		if (this.changeCameraPosition.getIntensity() > 0) {
			this.car.changeCameraMode();
		}
		
		// Classe responsável pelo tratamento de colisões do carro.
		CarCollidable collidable = (CarCollidable)this.car.getCollidable();
		
		// Posição atual do carro - Inicio do movimento.
		Point3d position = this.car.getPosition();
		// Orientação atual do carro.
		double orientation = this.car.getOrientation();
		// Velocidade atual do carro.
		double speed = this.car.getSpeed();
		
		// Indicador de colisão.
		boolean hasCollided = collidable == null ? false : collidable.getCollisionFlag();
		
		// Rotaciona o carro.
		orientation += turnSpeed;
		
		// Atualiza a posição caso o carro esteja em movimento.
		if (this.car.isMoving()) {
			
			// Posição 'X' do carro.
			position.x =
				position.x + (hasCollided  ? -3 : 1) *
				speed * delta * -Math.sin(orientation + Math.PI / 2) + (hasCollided ? -3 : 1) *
				.5 * (this.car.ACCELERATION - 3 * this.car.FRICTION) * -Math.sin(orientation + Math.PI / 2) * delta * delta;
			
			// Posição 'Z' do carro.
			position.z =
				position.z + (hasCollided ? -3 : 1) *
				speed * delta * -Math.cos(orientation + Math.PI / 2) + (hasCollided ? -3 : 1) *
				.5 * (this.car.ACCELERATION - 3 * this.car.FRICTION) * -Math.cos(orientation + Math.PI / 2) * delta * delta;
		}
		
		// Atualiza a velocidade do carro, levando em consideração o coeficiente de atrito com o solo.
		speed += moveSpeed - this.car.FRICTION * delta;
		
		// Limita a velociade à velocidade máxima permitida.
		if (speed > this.car.MAX_SPEED) {
			speed = this.car.MAX_SPEED;
		}
		
		// Limita a velocidade sempre positiva.
		if (speed < 0) {
			if (this.car.FRICTION > 0) this.car.FRICTION = -this.car.FRICTION;
		} else {
			if (this.car.FRICTION < 0) this.car.FRICTION = -this.car.FRICTION;
		}
		
		// Atualização lógica dos parâmetros do carro de acordo com a colisão.
		speed = hasCollided ? -(0.5 + speed / 3) : speed;
		
		// Atualização lógica dos parâmetros do carro.
		this.car.setPosition(position);
		this.car.setOrientation(orientation);
		this.car.setSpeed(speed);
		
		// Atualização das flags de colisão.
		collidable.setCollisionFlag(false);
		
		// Se houve colisão, muda de estado do jogo.
		if (hasCollided) {
			((CrashXGame)game).changeState(((CrashXGame)game).damageSufferedState);
		}
	}

}
