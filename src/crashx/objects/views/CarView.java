package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.Car;
import crashx.objects.Car.CarType;
import crashx.objects.updaters.CarUpdater;

/**
 * CarViewer | Representação geométrica do objeto carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CarView extends Viewable {

	/** Topo do grafo de cena da representação geométrica do carro. */
	private BranchGroup root;
	/** Grupo de transformação para atualização geométrica do carro. */
	private TransformGroup placement;
	
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira esquerda. */
	private TransformGroup backLeftWheelPositionPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira esquerda. */
	private TransformGroup backRightWheelPositionPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira esquerda. */
	private TransformGroup frontLeftWheelPositionPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira esquerda. */
	private TransformGroup frontRightWheelPositionPlacement;
	
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira esquerda. */
	private TransformGroup backLeftWheelRotationPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda traseira direita. */
	private TransformGroup backRightWheelRotationPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda frontal esquerda. */
	private TransformGroup frontLeftWheelRotationPlacement;
	/** Grupo de transformação para atualização geométrica de rotação da roda frontal direita. */
	private TransformGroup frontRightWheelRotationPlacement;
	/** Grupo de transformação para atualização geométrica de orientação da roda frontal esquerda. */
	private TransformGroup frontLeftWheelOrientationPlacement;
	/** Grupo de transformação para atualização geométrica de orientação da roda frontal direita. */
	private TransformGroup frontRightWheelOrientationPlacement;
	
	/** Carro a ser representado. */
	private Car car;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(0.05, 0.05, 0.05);
	
	/**
	 * Construtor da classe.
	 * @param car Carro a ser representado.
	 * @throws Exception Modelos não encontrados.
	 */
	public CarView(Car car) throws Exception {
		super();
		this.car = car;
		this.build();
	}
	
	/**
	 * Constrói a representação geométrica do carro.
	 * @throws Exception Modelos não encontrados.
	 */
	private void build() throws Exception {		
		
		String body, axis, wheel, capwheel, inside;
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.backLeftWheelPositionPlacement = new TransformGroup();
	 	this.backRightWheelPositionPlacement = new TransformGroup();
	 	this.frontLeftWheelPositionPlacement = new TransformGroup();
	 	this.frontRightWheelPositionPlacement = new TransformGroup();
	 	
	 	this.backLeftWheelRotationPlacement = new TransformGroup();
	 	this.backRightWheelRotationPlacement = new TransformGroup();
	 	this.frontLeftWheelRotationPlacement = new TransformGroup();
	 	this.frontRightWheelRotationPlacement = new TransformGroup();
	 	
	 	this.frontLeftWheelOrientationPlacement = new TransformGroup();
	 	this.frontRightWheelOrientationPlacement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	this.backLeftWheelPositionPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.backRightWheelPositionPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.frontLeftWheelPositionPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.frontRightWheelPositionPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	this.backLeftWheelRotationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.backRightWheelRotationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.frontLeftWheelRotationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.frontRightWheelRotationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	this.frontLeftWheelOrientationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	this.frontRightWheelOrientationPlacement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.car.getType().equals(CarType.TypeA)) {
	 		body = "crashx/resources/models/car/body01.wrl";
	 	} else if (this.car.getType().equals(CarType.TypeB)) {
	 		body = "crashx/resources/models/car/body02.wrl";
	 	} else {
	 		body = "";
	 	}
	 	
	 	axis = "crashx/resources/models/car/axis.wrl";
	 	wheel = "crashx/resources/models/car/wheel.wrl";
	 	capwheel = "crashx/resources/models/car/cap.wrl";
	 	inside = "crashx/resources/models/car/inside.wrl";
	 	
	 	BranchGroup bodySceneGroup					= objectLoader.load(body).getSceneGroup();
	 	BranchGroup axisSceneGroup					= objectLoader.load(axis).getSceneGroup();
	 	BranchGroup insideSceneGroup				= objectLoader.load(inside).getSceneGroup();
	 	BranchGroup frontRightWheelSceneGroup		= objectLoader.load(wheel).getSceneGroup();
	 	BranchGroup frontLeftWheelSceneGroup		= objectLoader.load(wheel).getSceneGroup();
	 	BranchGroup backRightWheelSceneGroup		= objectLoader.load(wheel).getSceneGroup();
	 	BranchGroup backLeftWheelSceneGroup			= objectLoader.load(wheel).getSceneGroup();
	 	BranchGroup frontRightCapWheelSceneGroup	= objectLoader.load(capwheel).getSceneGroup();
	 	BranchGroup frontLeftCapWheelSceneGroup		= objectLoader.load(capwheel).getSceneGroup();
	 	BranchGroup backRightCapWheelSceneGroup		= objectLoader.load(capwheel).getSceneGroup();
	 	BranchGroup backLeftCapWheelSceneGroup		= objectLoader.load(capwheel).getSceneGroup();
	 	
	 	this.backLeftWheelRotationPlacement.addChild(backLeftCapWheelSceneGroup);
	 	this.backLeftWheelRotationPlacement.addChild(backLeftWheelSceneGroup);
	 	
	 	this.backRightWheelRotationPlacement.addChild(backRightCapWheelSceneGroup);
	 	this.backRightWheelRotationPlacement.addChild(backRightWheelSceneGroup);
	 	
	 	this.frontLeftWheelRotationPlacement.addChild(frontLeftCapWheelSceneGroup);
	 	this.frontLeftWheelRotationPlacement.addChild(frontLeftWheelSceneGroup);
	 	
	 	this.frontRightWheelRotationPlacement.addChild(frontRightCapWheelSceneGroup);
	 	this.frontRightWheelRotationPlacement.addChild(frontRightWheelSceneGroup);
	 	
	 	this.frontLeftWheelOrientationPlacement.addChild(this.frontLeftWheelRotationPlacement);
	 	this.frontRightWheelOrientationPlacement.addChild(this.frontRightWheelRotationPlacement);
	 	
	 	this.backLeftWheelPositionPlacement.addChild(this.backLeftWheelRotationPlacement);
	 	this.backRightWheelPositionPlacement.addChild(this.backRightWheelRotationPlacement);
	 	this.frontLeftWheelPositionPlacement.addChild(this.frontLeftWheelOrientationPlacement);
	 	this.frontRightWheelPositionPlacement.addChild(this.frontRightWheelOrientationPlacement);
	 	
	 	this.placement.addChild(bodySceneGroup);
	 	this.placement.addChild(axisSceneGroup);
	 	this.placement.addChild(insideSceneGroup);
	 	
	 	this.placement.addChild(this.backLeftWheelPositionPlacement);
	 	this.placement.addChild(this.backRightWheelPositionPlacement);
	 	this.placement.addChild(this.frontLeftWheelPositionPlacement);
	 	this.placement.addChild(this.frontRightWheelPositionPlacement);
	 	
		this.root.addChild(this.placement);
	 	this.updateView(0);
	 	
	 	//this.placement.addChild(new Cylinder(0.1f, 200));
	}
	
	/**
	 * Retorna a representação geométrica do carro.
	 * @return A representação geométrica do carro.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualização da representação geométrica do carro.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
		// Transformers para posicionamento das rodas e calotas do carro.
		Transform3D backLeftWheelPositionTransformer = new Transform3D();
		Transform3D backRightWheelPositionTransformer = new Transform3D();
		Transform3D frontLeftWheelPositionTransformer = new Transform3D();
		Transform3D frontRightWheelPositionTransformer = new Transform3D();
		
		// Transformers para rotação mudança de orientação das rodas do carro.
		Transform3D wheelRotationTransformer = new Transform3D();
		Transform3D wheelOrientationTransformer = new Transform3D();
		
		// Transformers para escalamento e mudança de posição e orientação do carro.
		Transform3D carScaleTransformer = new Transform3D();
		Transform3D carPositionTransformer = new Transform3D();
		Transform3D carOrientationTransformer = new Transform3D();
		
		// Transformers para armazenamento da rotação atual aplicada às rodas do carro.
		Transform3D actualBackLeftWheelRotationPlacement = new Transform3D();
		Transform3D actualBackRightWheelRotationPlacement = new Transform3D();
		Transform3D actualFrontLeftWheelRotationPlacement = new Transform3D();
		Transform3D actualFrontRightWheelRotationPlacement = new Transform3D();
		
		// Posição e direção da câmera.
		Point3d cameraPosition = new Point3d();
		Point3d cameraLook = new Point3d();
		
		// Instância do updater do carro.
		CarUpdater updater = (CarUpdater)this.car.getUpdater();
		
		// Rotação e orientação são inicializadas com zero.
		wheelOrientationTransformer.rotY(0);
		wheelRotationTransformer.rotZ(0);
		
		// Transformações de rotação e orientação das rodas do carro somente podem ser feitas após criação do updater.
		if (updater != null) {
			
			// Roda deve ser orientada para a esquerda.
			if (updater.turnLeft.getIntensity() > 0) {
				wheelOrientationTransformer.rotY(+Math.PI / 8);
			}
			
			// Roda deve ser orientada para a direita.
			if (updater.turnRight.getIntensity() > 0) {
				wheelOrientationTransformer.rotY(-Math.PI / 8);
			}
			
			// Roda deve ser rotacionada de acordo com a velocidade do carro.
			if (this.car.isMoving()) {
				wheelRotationTransformer.rotZ(Math.toRadians(7 * this.car.getSpeed()));
			}
		}
		
		// Obtenção da rotação atual das rodas do carro.
		this.backLeftWheelRotationPlacement.getTransform(actualBackLeftWheelRotationPlacement);
		this.backRightWheelRotationPlacement.getTransform(actualBackRightWheelRotationPlacement);
		this.frontLeftWheelRotationPlacement.getTransform(actualFrontLeftWheelRotationPlacement);
		this.frontRightWheelRotationPlacement.getTransform(actualFrontRightWheelRotationPlacement);
		
		// Soma da rotação atual com a rotação a ser aplicada.
		actualBackLeftWheelRotationPlacement.mul(wheelRotationTransformer);
		actualBackRightWheelRotationPlacement.mul(wheelRotationTransformer);
		actualFrontLeftWheelRotationPlacement.mul(wheelRotationTransformer);
		actualFrontRightWheelRotationPlacement.mul(wheelRotationTransformer);
		
		// Posicionamento das rodas
		backLeftWheelPositionTransformer.setTranslation(new Vector3d(4.8, -4.0, 4.1));
		backRightWheelPositionTransformer.setTranslation(new Vector3d(4.8, -4.0, -4.1));
		frontLeftWheelPositionTransformer.setTranslation(new Vector3d(-6.3, -4.0, 4.1));
		frontRightWheelPositionTransformer.setTranslation(new Vector3d(-6.3, -4.0, -4.1));
		
		// Aplicação da transformação da orientação das rodas.
		this.frontLeftWheelOrientationPlacement.setTransform(wheelOrientationTransformer);
		this.frontRightWheelOrientationPlacement.setTransform(wheelOrientationTransformer);
		
		// Aplicação da transformação de rotação das rodas.
		this.backLeftWheelRotationPlacement.setTransform(actualBackLeftWheelRotationPlacement);
		this.backRightWheelRotationPlacement.setTransform(actualBackRightWheelRotationPlacement);
		this.frontLeftWheelRotationPlacement.setTransform(actualFrontLeftWheelRotationPlacement);
		this.frontRightWheelRotationPlacement.setTransform(actualFrontRightWheelRotationPlacement);
		
		// Aplicação da transformação de posicionamento das rodas.
		this.backLeftWheelPositionPlacement.setTransform(backLeftWheelPositionTransformer);
		this.backRightWheelPositionPlacement.setTransform(backRightWheelPositionTransformer);
		this.frontLeftWheelPositionPlacement.setTransform(frontLeftWheelPositionTransformer);
		this.frontRightWheelPositionPlacement.setTransform(frontRightWheelPositionTransformer);
		
		// Atualização do posicionamento e escala do carro.
		carScaleTransformer.setScale(this.scale);
		carPositionTransformer.setTranslation(new Vector3d(this.car.getPosition()));
		carOrientationTransformer.rotY(this.car.getOrientation());
		
		// Geração da matriz de transformação.
		carScaleTransformer.mul(carOrientationTransformer);
		carPositionTransformer.mul(carScaleTransformer);
		
		// Aplicação da transformação.
		this.placement.setTransform(carPositionTransformer);
		
		// Modo de posicionamento da câmera.
		switch (this.car.getCameraMode()) {
		
		// Câmera de visão traseira
		case 0:
			cameraPosition.x = this.car.getPosition().x + 2.2 * Math.cos(-this.car.getOrientation());
			cameraPosition.y = 1.2;
			cameraPosition.z = this.car.getPosition().z + 2.2 * Math.sin(-this.car.getOrientation());
			
			cameraLook.x = this.car.getPosition().x - 10 * Math.cos(-this.car.getOrientation());
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z - 10 * Math.sin(-this.car.getOrientation());
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(0, 1, 0));
			break;
			
		// Câmera de visão frontal
		case 1:
			cameraPosition.x = this.car.getPosition().x - 2.2 * Math.cos(-this.car.getOrientation());
			cameraPosition.y = 1.2;
			cameraPosition.z = this.car.getPosition().z - 2.2 * Math.sin(-this.car.getOrientation());
			
			cameraLook.x = this.car.getPosition().x + 10 * Math.cos(-this.car.getOrientation());
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z + 10 * Math.sin(-this.car.getOrientation());
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(0, 1, 0));
			break;

		// Câmera de visão áerea
		case 2:
			cameraPosition.x = this.car.getPosition().x;
			cameraPosition.y = 15.0;
			cameraPosition.z = this.car.getPosition().z;
			
			cameraLook.x = this.car.getPosition().x;
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z;
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(-1, 0, 0));
			break;
			
		// Câmera de visão interna
		case 3:
			cameraPosition.x = this.car.getPosition().x + 0.45 * Math.cos(-this.car.getOrientation());
			cameraPosition.y = 0.8;
			cameraPosition.z = this.car.getPosition().z + 0.45 * Math.sin(-this.car.getOrientation());
			
			cameraLook.x = this.car.getPosition().x - 10 * Math.cos(-this.car.getOrientation());
			cameraLook.y = 0.4;
			cameraLook.z = this.car.getPosition().z - 10 * Math.sin(-this.car.getOrientation());
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(0, 1, 0));
			break;
			
		}
		
		// Atualização dos bounds de colisão do carro
		if (this.car.getCollidable() != null) {
			this.car.getCollidable().getBounds().setTransform(carPositionTransformer);
		}
	}
}