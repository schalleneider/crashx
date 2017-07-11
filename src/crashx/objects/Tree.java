package crashx.objects;

import interlab.engine.core.GameObject;

import java.util.ArrayList;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * Tree | Representação da árvore do cenário do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Tree extends GameObject {
	
	/** Enumerador determinando o tipo de árvore que deve ser usado geometricamente. */
	public enum TreeType {
		/***/ TypeA,
		/***/ TypeB
	}
	
	/** Posição da árvore. */
	private Point3d position;
	
	/** Tipo da árvore. */
	private TreeType type;
	
	/** Lista das áreas de colisão da árvore. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posição da árvore.
	 * @param type Tipo da árvore.
	 */
	public Tree(Point3d position, TreeType type) {
		super();
		this.type = type;
		this.position = position;
		this.bounds = new ArrayList<Bounds>();
		this.buildBounds();
	}
	
	/**
	 * Constrói as áreas de colisão da árvore.
	 */
	private void buildBounds() {
		if (this.type.equals(TreeType.TypeA)) {
			this.bounds.add(new BoundingBox(new Point3d(-0.748, -1, -0.711), new Point3d(0.605, 1, 0.711)));
		} else if (this.type.equals(TreeType.TypeB)) {
			this.bounds.add(new BoundingBox(new Point3d(-0.426, -1, -0.405), new Point3d(0.344, 1, 0.405)));
		}
	}
	
	/**
	 * Retorna a posição da árvore.
	 * @return A posição da árvore.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posição da árvore.
	 * @param position Nova posição da árvore.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo da árvore.
	 * @return O tipo da árvore.
	 */
	public TreeType getType() {
		return this.type;
	}
	
	/**
	 * Retorna a lista das áreas de colisão ocupada pela árvore.
	 * @return A lista das áreas de colisão ocupada pela árvore.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
