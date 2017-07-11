package crashx.states;

import interlab.engine.collision.SimpleCollisionManager;
import interlab.engine.collision.Subspace;
import interlab.engine.core.Game;
import interlab.engine.core.GameObject;
import interlab.engine.core.GameState;
import interlab.engine.io.input.Keyboard;
import interlab.engine.io.sound.GameAudio;

import java.awt.event.KeyEvent;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;


import crashx.game.CrashXGame;
import crashx.game.ObjectFactory;
import crashx.objects.Car.CarType;
import crashx.objects.Ground.GroundType;
import crashx.objects.House.HouseType;
import crashx.objects.Sky.SkyType;
import crashx.objects.Tree.TreeType;
import crashx.objects.Wall.WallType;
import crashx.objects.updaters.CarUpdater;
import crashx.overlays.LoadingOverlay;
import crashx.overlays.ScoreOverlay;

/**
 * Setup | Estado de configuração do jogo.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class Setup extends GameState{

	/**
	 * Limpeza necessária antes do término do estado.
	 * @param game Jogo em execução.
	 */
	public void cleanup(Game game) {
		
	}

	/**
	 * Inicializa o estado.
	 * @param game Jogo em execução.
	 */
	public void initialize(Game game) {
		
		GameAudio.getInstance().stopMusic("TITLE");
		GameAudio.getInstance().loopPlayMusic("BACK");
		
		((CrashXGame)game).enableCollisions(false);
		
		// Divide a tela de jogo em 2
		((CrashXGame)game).setSplitScreen(true, false);
		
		// Carrega imagem de loading
		((CrashXGame)game).getView(1).setOverlay(new LoadingOverlay());
		
		// Cria os objetos do jogo.
		GameObject ground = ObjectFactory.getInstance().createGround(new Point3d(0, 0, 0), GroundType.TypeA);
		GameObject wall = ObjectFactory.getInstance().createWall(new Point3d(0, 0.2, 0), WallType.TypeA);
		GameObject sky = ObjectFactory.getInstance().createSky(new Point3d(0, 0, 0), SkyType.TypeA);
		
		GameObject house01 = ObjectFactory.getInstance().createHouse(new Point3d(0, 0.9, -15.0), HouseType.TypeA);
		GameObject house02 = ObjectFactory.getInstance().createHouse(new Point3d(0, 0.9, +15.0), HouseType.TypeB);
		
		GameObject car01 = ObjectFactory.getInstance().createCar(new Point3d(0, 0.55, -11.5), ((CrashXGame)game).getCamera(0), +Math.PI / 2, CarType.TypeA);
		GameObject car02 = ObjectFactory.getInstance().createCar(new Point3d(0, 0.55, +11.5), ((CrashXGame)game).getCamera(1), -Math.PI / 2, CarType.TypeB);

		GameObject tree01 = ObjectFactory.getInstance().createTree(new Point3d(15, 0, 1), TreeType.TypeA);
		GameObject tree02 = ObjectFactory.getInstance().createTree(new Point3d(7, 0, 2), TreeType.TypeA);
		GameObject tree03 = ObjectFactory.getInstance().createTree(new Point3d(12, 0, -7), TreeType.TypeA);
		GameObject tree04 = ObjectFactory.getInstance().createTree(new Point3d(0, 0, 5), TreeType.TypeA);
		GameObject tree05 = ObjectFactory.getInstance().createTree(new Point3d(-10, 0, -10), TreeType.TypeA);
		GameObject tree06 = ObjectFactory.getInstance().createTree(new Point3d(-5, 0, -10), TreeType.TypeA);
		GameObject tree07 = ObjectFactory.getInstance().createTree(new Point3d(-8, 0, 8), TreeType.TypeA);
		GameObject tree08 = ObjectFactory.getInstance().createTree(new Point3d(-10, 0, -3), TreeType.TypeA);
		GameObject tree09 = ObjectFactory.getInstance().createTree(new Point3d(8, 0, 8), TreeType.TypeA);
		GameObject tree10 = ObjectFactory.getInstance().createTree(new Point3d(10, 0, -3), TreeType.TypeA);
		
		// Adiciona a instância dos carros à classe Game.
		((CrashXGame)game).addCar(car01);
		((CrashXGame)game).addCar(car02);
		
		// Configura as teclas com as ações do carro 1.		
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_A),	((CarUpdater)car01.getUpdater()).turnLeft);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_D),	((CarUpdater)car01.getUpdater()).turnRight);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_W),	((CarUpdater)car01.getUpdater()).moveForward);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_S),	((CarUpdater)car01.getUpdater()).moveBackward);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_1),	((CarUpdater)car01.getUpdater()).changeCameraPosition);
		
		// Configura as teclas com as ações do carro 2.
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_LEFT),	((CarUpdater)car02.getUpdater()).turnLeft);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_RIGHT),	((CarUpdater)car02.getUpdater()).turnRight);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_UP),	((CarUpdater)car02.getUpdater()).moveForward);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_DOWN),	((CarUpdater)car02.getUpdater()).moveBackward);
		((CrashXGame)game).getInput().bind(Keyboard.getInstance().getSensor(KeyEvent.VK_0),		((CarUpdater)car02.getUpdater()).changeCameraPosition);
		
		// Cria a área de colisão.
		SimpleCollisionManager collisionManager = ((CrashXGame)game).getCollider();
		BoundingSphere worldBounds = new BoundingSphere(new Point3d(0, 0, 0), 50);
		collisionManager.addSubspace(new Subspace(worldBounds));
		
		// Adiciona os objetos ao jogo.
		game.addObject(ground);
		game.addObject(wall);
		game.addObject(sky);
		
		game.addObject(house01);
		game.addObject(house02);
		
		game.addObject(car01);
		game.addObject(car02);
		
		game.addObject(tree01);
		game.addObject(tree02);
		game.addObject(tree03);
		game.addObject(tree04);
		game.addObject(tree05);
		game.addObject(tree06);
		game.addObject(tree07);
		game.addObject(tree08);
		game.addObject(tree09);
		game.addObject(tree10);
		
		// Adiciona a pontuação dos jogadores.
		((CrashXGame)game).getView(0).setOverlay(new ScoreOverlay(car01));
		((CrashXGame)game).getView(1).setOverlay(new ScoreOverlay(car02));
	}

	/**
	 * Executado antes do inicio do ciclo do loop principal.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void preStep(Game game, long delta) {
		
	}
	
	/**
	 * Executado após o término do ciclo do loop principal.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void postStep(Game game, long delta) {
		((CrashXGame)game).changeState(((CrashXGame)game).playingState);
	}
	
	/**
	 * Atualiza o estado dos objetos ligados a este estado.
	 * @param game Jogo em execução.
	 * @param delta Delta.
	 */
	public void update(Game game, float delta) {
		
	}
}
