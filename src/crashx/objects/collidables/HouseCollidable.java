package crashx.objects.collidables;

import javax.media.j3d.Bounds;

import interlab.engine.collision.ObjectBounds;
import interlab.engine.core.Collidable;
import crashx.objects.House;

/**
 * HouseCollidable | Tratamento de colisões da casa.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class HouseCollidable extends Collidable {
	
	/** Objeto que irá colidir. */
	private House house;
	
	/**
	 * Construtor da classe.
	 * @param house Objeto a ser atualizado.
	 */
	public HouseCollidable(House house) {
		super();
		this.house = house;
		buildCollisionBounds();
	}
	
	/**
	 * Constrói os bounds de colisão do carro.
	 */
	public void buildCollisionBounds() {
		ObjectBounds bounds = new ObjectBounds();
		Bounds[] list = new Bounds[this.house.getBounds().size()];
		this.house.getBounds().toArray(list);
		bounds.setBounds(list);
		this.setBounds(bounds);
	}
	
	/**
	 * Retorna a prioridade do objeto no tratamento de colisões.
	 * @return A prioridade do objeto no tratamento de colisões.
	 */
	public int getCollisionHandlingPriority() {
		return 0;
	}
	
	/**
	 * Trata a colisão entre este objeto e outro qualquer.
	 * @param collidable O objeto com o qual este colidiu.
	 */
	public void handleCollision(Collidable collidable) {
		
	}
}
