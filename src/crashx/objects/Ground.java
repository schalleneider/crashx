package crashx.objects;

import interlab.engine.core.GameObject;

import javax.vecmath.Point3d;

/**
 * Ground | Representação do chão do cenário do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Ground extends GameObject {
	
	/** Enumerador determinando o tipo de chão que deve ser usado geometricamente. */
	public enum GroundType {
		/***/ TypeA
	}
	
	/** Posição do chão. */
	private Point3d position;
	
	/** Tipo de chão. */
	private GroundType type;
	
	/**
	 * Construtor da classe.
	 * @param position Posição do chão.
	 * @param type Tipo do chão.
	 */
	public Ground(Point3d position, GroundType type) {
		super();
		this.type = type;
		this.position = position;
	}
	
	/**
	 * Retorna a posição do chão.
	 * @return A posição do chão.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posição do chão.
	 * @param position Nova posição do chão.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo do chão.
	 * @return O tipo do chão.
	 */
	public GroundType getType() {
		return this.type;
	}
}
