package crashx.objects;

import interlab.engine.core.GameObject;

import javax.vecmath.Point3d;

/**
 * Ground | Representa��o do ch�o do cen�rio do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Ground extends GameObject {
	
	/** Enumerador determinando o tipo de ch�o que deve ser usado geometricamente. */
	public enum GroundType {
		/***/ TypeA
	}
	
	/** Posi��o do ch�o. */
	private Point3d position;
	
	/** Tipo de ch�o. */
	private GroundType type;
	
	/**
	 * Construtor da classe.
	 * @param position Posi��o do ch�o.
	 * @param type Tipo do ch�o.
	 */
	public Ground(Point3d position, GroundType type) {
		super();
		this.type = type;
		this.position = position;
	}
	
	/**
	 * Retorna a posi��o do ch�o.
	 * @return A posi��o do ch�o.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posi��o do ch�o.
	 * @param position Nova posi��o do ch�o.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo do ch�o.
	 * @return O tipo do ch�o.
	 */
	public GroundType getType() {
		return this.type;
	}
}
