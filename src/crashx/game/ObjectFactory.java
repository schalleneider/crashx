package crashx.game;

import interlab.engine.core.GameObject;
import interlab.engine.io.graphics.Camera;

import javax.vecmath.Point3d;

import crashx.objects.Car;
import crashx.objects.Ground;
import crashx.objects.House;
import crashx.objects.Sky;
import crashx.objects.TitleScreen;
import crashx.objects.Tree;
import crashx.objects.Wall;
import crashx.objects.Car.CarType;
import crashx.objects.Ground.GroundType;
import crashx.objects.House.HouseType;
import crashx.objects.Sky.SkyType;
import crashx.objects.Tree.TreeType;
import crashx.objects.Wall.WallType;
import crashx.objects.collidables.CarCollidable;
import crashx.objects.collidables.HouseCollidable;
import crashx.objects.collidables.TreeCollidable;
import crashx.objects.collidables.WallCollidable;
import crashx.objects.updaters.CarUpdater;
import crashx.objects.updaters.TitleScreenUpdater;
import crashx.objects.views.CarView;
import crashx.objects.views.GroundView;
import crashx.objects.views.HouseView;
import crashx.objects.views.SkyView;
import crashx.objects.views.TreeView;
import crashx.objects.views.WallView;

/**
 * ObjectFactory | Fabrica de objectos.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class ObjectFactory {

	/** Inst�ncia da f�brica. */
	private static ObjectFactory instance;
	
	/**
	 * Construtor da classe. Impossibilita a instancia��o de fora dessa classe.
	 */
	private ObjectFactory() {}
	
	/**
	 * Retorna a inst�ncia da f�brica de objetos.
	 * @return A inst�ncia da f�brica de objetos.
	 */
	public static ObjectFactory getInstance() {
		if (ObjectFactory.instance == null) {
			ObjectFactory.instance = new ObjectFactory();
		}
		return ObjectFactory.instance;
	}
	
	/**
	 * Cria uma inst�ncia do objeto carro.
	 * @param position Posi��o inicial do carro.
	 * @param camera Camera de vis�o do carro.
	 * @param orientation Orienta��o do carro.
	 * @param type Tipo do carro.
	 * @return A inst�ncia criada.
	 */
	public GameObject createCar(Point3d position, Camera camera, double orientation, CarType type) {
		try {
			Car car = new Car(position, camera, orientation, type);
			car.setCollidable(new CarCollidable(car));
			car.setViewable(new CarView(car));
			car.setUpdater(new CarUpdater(car));
			return car;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia do objeto ch�o.
	 * @param position Posi��o do ch�o.
	 * @param type Tipo do ch�o.
	 * @return A inst�ncia criada.
	 */
	public GameObject createGround(Point3d position, GroundType type) {
		try {
			Ground ground = new Ground(position, type);
			ground.setViewable(new GroundView(ground));
			return ground;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia do objeto c�u.
	 * @param position Posi��o do c�u.
	 * @param type Tipo do c�u.
	 * @return A inst�ncia criada.
	 */
	public GameObject createSky(Point3d position, SkyType type) {
		try {
			Sky sky = new Sky(position, type);
			sky.setViewable(new SkyView(sky));
			return sky;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia do objeto casa.
	 * @param position Posi��o da casa.
	 * @param type Tipo da casa.
	 * @return A inst�ncia criada.
	 */
	public GameObject createHouse(Point3d position, HouseType type) {
		try {
			House house = new House(position, type);
			house.setCollidable(new HouseCollidable(house));
			house.setViewable(new HouseView(house));
			return house;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia do objeto muro.
	 * @param position Posi��o do muro.
	 * @param type Tipo do muro.
	 * @return A inst�ncia criada.
	 */
	public GameObject createWall(Point3d position, WallType type) {
		try {
			Wall wall = new Wall(position, type);
			wall.setCollidable(new WallCollidable(wall));
			wall.setViewable(new WallView(wall));
			return wall;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia do objeto �rvore.
	 * @param position Posi��o da �rvore.
	 * @param type Tipo da �rvore.
	 * @return A inst�ncia criada.
	 */
	public GameObject createTree(Point3d position, TreeType type) {
		try {
			Tree tree = new Tree(position, type);
			tree.setCollidable(new TreeCollidable(tree));
			tree.setViewable(new TreeView(tree));
			return tree;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cria uma inst�ncia da tela de t�tulo.
	 * @return A inst�ncia criada.
	 */
	public GameObject createTitleScreen() {
		try {
			TitleScreen screen = new TitleScreen();
			screen.setUpdater(new TitleScreenUpdater());
			return screen;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
