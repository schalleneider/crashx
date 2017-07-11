package crashx.objects;

import interlab.engine.core.GameObject;

import java.util.ArrayList;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * Tree | Representa��o da �rvore do cen�rio do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Tree extends GameObject {
	
	/** Enumerador determinando o tipo de �rvore que deve ser usado geometricamente. */
	public enum TreeType {
		/***/ TypeA,
		/***/ TypeB
	}
	
	/** Posi��o da �rvore. */
	private Point3d position;
	
	/** Tipo da �rvore. */
	private TreeType type;
	
	/** Lista das �reas de colis�o da �rvore. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posi��o da �rvore.
	 * @param type Tipo da �rvore.
	 */
	public Tree(Point3d position, TreeType type) {
		super();
		this.type = type;
		this.position = position;
		this.bounds = new ArrayList<Bounds>();
		this.buildBounds();
	}
	
	/**
	 * Constr�i as �reas de colis�o da �rvore.
	 */
	private void buildBounds() {
		if (this.type.equals(TreeType.TypeA)) {
			this.bounds.add(new BoundingBox(new Point3d(-0.748, -1, -0.711), new Point3d(0.605, 1, 0.711)));
		} else if (this.type.equals(TreeType.TypeB)) {
			this.bounds.add(new BoundingBox(new Point3d(-0.426, -1, -0.405), new Point3d(0.344, 1, 0.405)));
		}
	}
	
	/**
	 * Retorna a posi��o da �rvore.
	 * @return A posi��o da �rvore.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posi��o da �rvore.
	 * @param position Nova posi��o da �rvore.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo da �rvore.
	 * @return O tipo da �rvore.
	 */
	public TreeType getType() {
		return this.type;
	}
	
	/**
	 * Retorna a lista das �reas de colis�o ocupada pela �rvore.
	 * @return A lista das �reas de colis�o ocupada pela �rvore.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
