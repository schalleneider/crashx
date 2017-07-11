package crashx.objects.collidables;

import interlab.engine.collision.ObjectBounds;
import interlab.engine.core.Collidable;

import javax.media.j3d.Bounds;

import crashx.objects.Car;

/**
 * CarCollidable | Tratamento de colis�es do carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CarCollidable extends Collidable {

	/** Flag que indica que o objeto relacionado colidiu.  */
	private boolean hasCollided;

	/** Carro que ir� colidir. */
	private Car car;
	
	/**
	 * Construtor da classe.
	 * @param car Objeto a ser atualizado.
	 */
	public CarCollidable(Car car) {
		super();
		this.car = car;
		this.hasCollided = false;
		buildCollisionBounds();
	}
	
	/**
	 * Constr�i os bounds de colis�o do carro.
	 */
	public void buildCollisionBounds() {
		ObjectBounds bounds = new ObjectBounds();
		Bounds[] list = new Bounds[this.car.getBounds().size()];
		this.car.getBounds().toArray(list);
		bounds.setBounds(list);
		this.setBounds(bounds);
	}
	
	/**
	 * Retorna a prioridade do carro no tratamento de colis�es.
	 * @return A prioridade do carro no tratamento de colis�es.
	 */
	public int getCollisionHandlingPriority() {
		return 1;
	}
	
	/**
	 * Trata a colis�o entre o carro e um outro objeto qualquer.
	 * @param collidable O objeto com o qual o carro colidiu.
	 */
	public void handleCollision(Collidable collidable) {
		
		// Carro colidiu com carro.
		if (collidable instanceof CarCollidable) {
			
			this.hasCollided = true;
			((CarCollidable)collidable).setCollisionFlag(true);
			
			this.car.damageCar(((CarCollidable)collidable).getCar().getSpeed() / 2);
			((CarCollidable)collidable).getCar().damageCar(this.car.getSpeed() / 2);
		}

		// Carro colidiu com casa.
		if (collidable instanceof HouseCollidable) {
			this.hasCollided = true;
			this.car.damageCar(this.car.getSpeed() / 5);
		}
		
		// Carro colidiu com muro.
		if (collidable instanceof WallCollidable) {
			this.hasCollided = true;
			this.car.damageCar(this.car.getSpeed() / 5);
		}
		
		// Carro colidiu com �rvore.
		if (collidable instanceof TreeCollidable) {
			this.hasCollided = true;
			this.car.damageCar(this.car.getSpeed() / 3);
		}
	}
	
	/**
	 * Altera o indicador de colis�o do objeto.
	 * @param value O novo valor a ser aplicado ao indicador.
	 */
	public void setCollisionFlag(boolean value) {
		this.hasCollided = value;
	}
	
	/**
	 * Retorna o indicador de colis�o do objeto.
	 * @return O indicador de colis�o do objeto.
	 */
	public boolean getCollisionFlag() {
		return this.hasCollided;
	}
	
	/**
	 * Retorna o carro associado com o objeto de colis�o.
	 * @return O carro associado com o objeto de colis�o.
	 */
	public Car getCar() {
		return this.car;
	}
}
