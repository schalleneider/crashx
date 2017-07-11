package crashx.objects;

import java.util.ArrayList;

import interlab.engine.core.GameObject;

import javax.media.j3d.BoundingBox;
import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;

/**
 * House | Representação da casa do cenário do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class House extends GameObject {
	
	/** Enumerador determinando o tipo de casa que deve ser usado geometricamente. */
	public enum HouseType {
		/***/ TypeA,
		/***/ TypeB
	}
	
	/** Posição da casa. */
	private Point3d position;
	
	/** Tipo da casa */
	private HouseType type;
	
	/** Lista das áreas de colisão da casa. */
	private ArrayList<Bounds> bounds;
	
	/**
	 * Construtor da classe.
	 * @param position Posição da casa.
	 * @param type Tipo da casa.
	 */
	public House(Point3d position, HouseType type) {
		super();
		this.type = type;
		this.position = position;
		this.bounds = new ArrayList<Bounds>();
		this.buildBounds();
	}
	
	/**
	 * Constrói as áreas de colisão da casa.
	 */
	private void buildBounds() {
		this.bounds.add(new BoundingBox(new Point3d(-13.0, -30.0, -9.0), new Point3d(13.0, 30.0, 9.0)));
		this.bounds.add(new BoundingBox(new Point3d(-4.5, -30.0, -0.0), new Point3d(4.5, 30.0, 11.0)));
	}

	/**
	 * Retorna a posição da casa.
	 * @return A posição da casa.
	 */
	public Point3d getPosition() {
		return this.position;
	}
	
	/**
	 * Atualiza a posição da casa.
	 * @param position Nova posição da casa.
	 */
	public void setPosition(Point3d position) {
		this.position = position;
	}
	
	/**
	 * Retorna o tipo da casa.
	 * @return O tipo da casa.
	 */
	public HouseType getType() {
		return this.type;
	}
	
	/**
	 * Retorna a lista das áreas de colisão ocupada pela casa.
	 * @return A lista das áreas de colisão ocupada pela casa.
	 */
	public ArrayList<Bounds> getBounds() {
		return this.bounds;
	}
}
