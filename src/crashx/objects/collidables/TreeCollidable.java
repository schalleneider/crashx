package crashx.objects.collidables;

import interlab.engine.collision.ObjectBounds;
import interlab.engine.core.Collidable;

import javax.media.j3d.Bounds;

import crashx.objects.Tree;

/**
 * TreeCollidable | Tratamento de colis�es da casa.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class TreeCollidable extends Collidable {
	
	/** Objeto que ir� colidir. */
	private Tree tree;
	
	/**
	 * Construtor da classe.
	 * @param tree Objeto a ser atualizado.
	 */
	public TreeCollidable(Tree tree) {
		super();
		this.tree = tree;
		buildCollisionBounds();
	}
	
	/**
	 * Constr�i os bounds de colis�o do carro.
	 */
	public void buildCollisionBounds() {
		ObjectBounds bounds = new ObjectBounds();
		Bounds[] list = new Bounds[this.tree.getBounds().size()];
		this.tree.getBounds().toArray(list);
		bounds.setBounds(list);
		this.setBounds(bounds);
	}
	
	/**
	 * Retorna a prioridade do objeto no tratamento de colis�es.
	 * @return A prioridade do objeto no tratamento de colis�es.
	 */
	public int getCollisionHandlingPriority() {
		return 0;
	}
	
	/**
	 * Trata a colis�o entre este objeto e outro qualquer.
	 * @param collidable O objeto com o qual este colidiu.
	 */
	public void handleCollision(Collidable collidable) {
		
	}
}
