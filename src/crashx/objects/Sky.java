package crashx.objects;

import interlab.engine.core.GameObject;

import javax.vecmath.Point3d;

/**
 * Sky | Representa��o do c�u do cen�rio do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Sky extends GameObject {
	
	/** Enumerador determinando o tipo de c�u que deve ser usado geometricamente. */
	public enum SkyType {
		/***/ TypeA
	}
	
	/** Posi��o do c�u. */
	private Point3d position;
	
	/** Tipo do c�u. */
	private SkyType type;
	
	/**
	 * Construtor da classe.
	 * @param position Posi��o do c�u.
	 * @param type Tipo do c�u.
	 */
	public Sky(Point3d position, SkyType type) {
		super();
		this.type = type;
		this.position = position;
	}
	
	/**
	 * Retorna a posi��o do c�u.
	 * @return A posi��o do c�u.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posi��o do c�u.
	 * @param position Nova posi��o do c�u.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo do c�u.
	 * @return O tipo do c�u.
	 */
	public SkyType getType() {
		return this.type;
	}
}
