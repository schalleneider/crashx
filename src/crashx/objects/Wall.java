package crashx.objects;

import interlab.engine.core.GameObject;

import java.util.ArrayList;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * Wall | Representa��o do muro do cen�rio do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Wall extends GameObject {
	
	/** Enumerador determinando o tipo de muro que deve ser usado geometricamente. */
	public enum WallType {
		/***/ TypeA
	}
	
	/** Posi��o do muro. */
	private Point3d position;
	
	/** Tipo do muro. */
	private WallType type;
	
	/** Lista das �reas de colis�o do muro. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posi��o do muro.
	 * @param type Tipo do muro.
	 */
	public Wall(Point3d position, WallType type) {
		super();
		this.type = type;
		this.position = position;
		this.bounds = new ArrayList<Bounds>();
		this.buildBounds();
	}
	
	/**
	 * Constr�i as �reas de colis�o do muro.
	 */
	private void buildBounds() {
		this.bounds.add(new BoundingBox(new Point3d(15.925, -1, -16.0), new Point3d(16.075, 1, 16)));
		this.bounds.add(new BoundingBox(new Point3d(-16, -1, -16.075), new Point3d(16, 1, -15.925)));
		this.bounds.add(new BoundingBox(new Point3d(-16.075, -1, -16), new Point3d(-15.925, 1, 16)));
		this.bounds.add(new BoundingBox(new Point3d(-16, -1, 15.925), new Point3d(16, 1, 16.075)));
	}
	
	/**
	 * Retorna a posi��o do muro.
	 * @return A posi��o do muro.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posi��o do muro.
	 * @param position Nova posi��o do muro.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo do muro.
	 * @return O tipo do muro.
	 */
	public WallType getType() {
		return this.type;
	}
	
	/**
	 * Retorna a lista das �reas de colis�o ocupada pelo muro.
	 * @return A lista das �reas de colis�o ocupada pelo muro.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
