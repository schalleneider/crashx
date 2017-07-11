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

	/** Instância da fábrica. */
	private static ObjectFactory instance;
	
	/**
	 * Construtor da classe. Impossibilita a instanciação de fora dessa classe.
	 */
	private ObjectFactory() {}
	
	/**
	 * Retorna a instância da fábrica de objetos.
	 * @return A instância da fábrica de objetos.
	 */
	public static ObjectFactory getInstance() {
		if (ObjectFactory.instance == null) {
			ObjectFactory.instance = new ObjectFactory();
		}
		return ObjectFactory.instance;
	}
	
	/**
	 * Cria uma instância do objeto carro.
	 * @param position Posição inicial do carro.
	 * @param camera Camera de visão do carro.
	 * @param orientation Orientação do carro.
	 * @param type Tipo do carro.
	 * @return A instância criada.
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
	 * Cria uma instância do objeto chão.
	 * @param position Posição do chão.
	 * @param type Tipo do chão.
	 * @return A instância criada.
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
	 * Cria uma instância do objeto céu.
	 * @param position Posição do céu.
	 * @param type Tipo do céu.
	 * @return A instância criada.
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
	 * Cria uma instância do objeto casa.
	 * @param position Posição da casa.
	 * @param type Tipo da casa.
	 * @return A instância criada.
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
	 * Cria uma instância do objeto muro.
	 * @param position Posição do muro.
	 * @param type Tipo do muro.
	 * @return A instância criada.
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
	 * Cria uma instância do objeto árvore.
	 * @param position Posição da árvore.
	 * @param type Tipo da árvore.
	 * @return A instância criada.
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
	 * Cria uma instância da tela de título.
	 * @return A instância criada.
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
