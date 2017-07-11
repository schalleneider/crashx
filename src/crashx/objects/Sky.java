package crashx.objects;

import interlab.engine.core.GameObject;

import javax.vecmath.Point3d;

/**
 * Sky | Representação do céu do cenário do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Sky extends GameObject {
	
	/** Enumerador determinando o tipo de céu que deve ser usado geometricamente. */
	public enum SkyType {
		/***/ TypeA
	}
	
	/** Posição do céu. */
	private Point3d position;
	
	/** Tipo do céu. */
	private SkyType type;
	
	/**
	 * Construtor da classe.
	 * @param position Posição do céu.
	 * @param type Tipo do céu.
	 */
	public Sky(Point3d position, SkyType type) {
		super();
		this.type = type;
		this.position = position;
	}
	
	/**
	 * Retorna a posição do céu.
	 * @return A posição do céu.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posição do céu.
	 * @param position Nova posição do céu.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo do céu.
	 * @return O tipo do céu.
	 */
	public SkyType getType() {
		return this.type;
	}
}
