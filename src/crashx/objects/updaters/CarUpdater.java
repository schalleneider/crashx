package crashx.objects.updaters;

import interlab.engine.core.Game;
import interlab.engine.core.Updater;
import interlab.engine.io.input.InputAction;

import javax.vecmath.Point3d;

import crashx.game.CrashXGame;
import crashx.objects.Car;
import crashx.objects.collidables.CarCollidable;

/**
 * CarUpdater | Atualiza��o l�gica do carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CarUpdater extends Updater {
	
	/** Carro a ser atualizado. */
	private Car car;
	
	/** A��o de movimenta��o para a esquerda. */
	public final InputAction turnLeft;
	/** A��o de movimenta��o para a direita. */
	public final InputAction turnRight;
	/** A��o de movimenta��o para a frente. */
	public final InputAction moveForward;
	/** A��o de movimenta��o para tr�s. */
	public final InputAction moveBackward;
	
	/** A��o de mudan�a do posicionamento da c�mera. */
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
	 * Efetua a atualiza��o l�gica do carro.
	 * @param game Jogo em execu��o.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
		double turnSpeed = 0;
		double moveSpeed = 0;
		
		// Movimenta��o para a esquerda.
		if (this.turnLeft.getIntensity() > 0) {
			// Carro rotaciona somente com carro em movimento.
			if (this.car.isMoving()) {
				turnSpeed = +Math.toRadians(this.car.MAX_ROTATION) * (this.car.getSpeed() > 0 ? 1 : -1);
			}
		}
		
		// Movimenta��o para a direita.
		if (this.turnRight.getIntensity() > 0) {
			// Carro rotaciona somente com carro em movimento.
			if (this.car.isMoving()) {
				turnSpeed = -Math.toRadians(this.car.MAX_ROTATION) * (this.car.getSpeed() > 0 ? 1 : -1);
			}
		}
		
		// Movimenta��o para frente.
		if (this.moveForward.getIntensity() > 0) {
			// Acelera��o do carro.
			moveSpeed = this.car.ACCELERATION * delta;
		}
		
		// Movimenta��o para tr�s.
		if (this.moveBackward.getIntensity() > 0) {
			// Acelera��o do carro.
			moveSpeed = -this.car.ACCELERATION * delta;
		}
		
		// Altera��o do modo de posicionamento da c�mera.
		if (this.changeCameraPosition.getIntensity() > 0) {
			this.car.changeCameraMode();
		}
		
		// Classe respons�vel pelo tratamento de colis�es do carro.
		CarCollidable collidable = (CarCollidable)this.car.getCollidable();
		
		// Posi��o atual do carro - Inicio do movimento.
		Point3d position = this.car.getPosition();
		// Orienta��o atual do carro.
		double orientation = this.car.getOrientation();
		// Velocidade atual do carro.
		double speed = this.car.getSpeed();
		
		// Indicador de colis�o.
		boolean hasCollided = collidable == null ? false : collidable.getCollisionFlag();
		
		// Rotaciona o carro.
		orientation += turnSpeed;
		
		// Atualiza a posi��o caso o carro esteja em movimento.
		if (this.car.isMoving()) {
			
			// Posi��o 'X' do carro.
			position.x =
				position.x + (hasCollided  ? -3 : 1) *
				speed * delta * -Math.sin(orientation + Math.PI / 2) + (hasCollided ? -3 : 1) *
				.5 * (this.car.ACCELERATION - 3 * this.car.FRICTION) * -Math.sin(orientation + Math.PI / 2) * delta * delta;
			
			// Posi��o 'Z' do carro.
			position.z =
				position.z + (hasCollided ? -3 : 1) *
				speed * delta * -Math.cos(orientation + Math.PI / 2) + (hasCollided ? -3 : 1) *
				.5 * (this.car.ACCELERATION - 3 * this.car.FRICTION) * -Math.cos(orientation + Math.PI / 2) * delta * delta;
		}
		
		// Atualiza a velocidade do carro, levando em considera��o o coeficiente de atrito com o solo.
		speed += moveSpeed - this.car.FRICTION * delta;
		
		// Limita a velociade � velocidade m�xima permitida.
		if (speed > this.car.MAX_SPEED) {
			speed = this.car.MAX_SPEED;
		}
		
		// Limita a velocidade sempre positiva.
		if (speed < 0) {
			if (this.car.FRICTION > 0) this.car.FRICTION = -this.car.FRICTION;
		} else {
			if (this.car.FRICTION < 0) this.car.FRICTION = -this.car.FRICTION;
		}
		
		// Atualiza��o l�gica dos par�metros do carro de acordo com a colis�o.
		speed = hasCollided ? -(0.5 + speed / 3) : speed;
		
		// Atualiza��o l�gica dos par�metros do carro.
		this.car.setPosition(position);
		this.car.setOrientation(orientation);
		this.car.setSpeed(speed);
		
		// Atualiza��o das flags de colis�o.
		collidable.setCollisionFlag(false);
		
		// Se houve colis�o, muda de estado do jogo.
		if (hasCollided) {
			((CrashXGame)game).changeState(((CrashXGame)game).damageSufferedState);
		}
	}

}
