package crashx.objects.collidables;

import javax.media.j3d.Bounds;

import interlab.engine.collision.ObjectBounds;
import interlab.engine.core.Collidable;
import crashx.objects.Wall;

/**
 * WallCollidable | Tratamento de colisões do muro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class WallCollidable extends Collidable {
	
	/** Objeto que irá colidir. */
	private Wall wall;
	
	/**
	 * Construtor da classe.
	 * @param wall Objeto a ser atualizado.
	 */
	public WallCollidable(Wall wall) {
		super();
		this.wall = wall;
		buildCollisionBounds();
	}
	
	/**
	 * Constrói os bounds de colisão do objeto.
	 */
	public void buildCollisionBounds() {
		ObjectBounds bounds = new ObjectBounds();
		Bounds[] list = new Bounds[this.wall.getBounds().size()];
		this.wall.getBounds().toArray(list);
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
