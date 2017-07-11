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
 * CarViewer | Representa��o geom�trica do objeto carro.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class CarView extends Viewable {

	/** Topo do grafo de cena da representa��o geom�trica do carro. */
	private BranchGroup root;
	/** Grupo de transforma��o para atualiza��o geom�trica do carro. */
	private TransformGroup placement;
	
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira esquerda. */
	private TransformGroup backLeftWheelPositionPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira esquerda. */
	private TransformGroup backRightWheelPositionPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira esquerda. */
	private TransformGroup frontLeftWheelPositionPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira esquerda. */
	private TransformGroup frontRightWheelPositionPlacement;
	
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira esquerda. */
	private TransformGroup backLeftWheelRotationPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda traseira direita. */
	private TransformGroup backRightWheelRotationPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda frontal esquerda. */
	private TransformGroup frontLeftWheelRotationPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de rota��o da roda frontal direita. */
	private TransformGroup frontRightWheelRotationPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de orienta��o da roda frontal esquerda. */
	private TransformGroup frontLeftWheelOrientationPlacement;
	/** Grupo de transforma��o para atualiza��o geom�trica de orienta��o da roda frontal direita. */
	private TransformGroup frontRightWheelOrientationPlacement;
	
	/** Carro a ser representado. */
	private Car car;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(0.05, 0.05, 0.05);
	
	/**
	 * Construtor da classe.
	 * @param car Carro a ser representado.
	 * @throws Exception Modelos n�o encontrados.
	 */
	public CarView(Car car) throws Exception {
		super();
		this.car = car;
		this.build();
	}
	
	/**
	 * Constr�i a representa��o geom�trica do carro.
	 * @throws Exception Modelos n�o encontrados.
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
	 * Retorna a representa��o geom�trica do carro.
	 * @return A representa��o geom�trica do carro.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualiza��o da representa��o geom�trica do carro.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
		// Transformers para posicionamento das rodas e calotas do carro.
		Transform3D backLeftWheelPositionTransformer = new Transform3D();
		Transform3D backRightWheelPositionTransformer = new Transform3D();
		Transform3D frontLeftWheelPositionTransformer = new Transform3D();
		Transform3D frontRightWheelPositionTransformer = new Transform3D();
		
		// Transformers para rota��o mudan�a de orienta��o das rodas do carro.
		Transform3D wheelRotationTransformer = new Transform3D();
		Transform3D wheelOrientationTransformer = new Transform3D();
		
		// Transformers para escalamento e mudan�a de posi��o e orienta��o do carro.
		Transform3D carScaleTransformer = new Transform3D();
		Transform3D carPositionTransformer = new Transform3D();
		Transform3D carOrientationTransformer = new Transform3D();
		
		// Transformers para armazenamento da rota��o atual aplicada �s rodas do carro.
		Transform3D actualBackLeftWheelRotationPlacement = new Transform3D();
		Transform3D actualBackRightWheelRotationPlacement = new Transform3D();
		Transform3D actualFrontLeftWheelRotationPlacement = new Transform3D();
		Transform3D actualFrontRightWheelRotationPlacement = new Transform3D();
		
		// Posi��o e dire��o da c�mera.
		Point3d cameraPosition = new Point3d();
		Point3d cameraLook = new Point3d();
		
		// Inst�ncia do updater do carro.
		CarUpdater updater = (CarUpdater)this.car.getUpdater();
		
		// Rota��o e orienta��o s�o inicializadas com zero.
		wheelOrientationTransformer.rotY(0);
		wheelRotationTransformer.rotZ(0);
		
		// Transforma��es de rota��o e orienta��o das rodas do carro somente podem ser feitas ap�s cria��o do updater.
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
		
		// Obten��o da rota��o atual das rodas do carro.
		this.backLeftWheelRotationPlacement.getTransform(actualBackLeftWheelRotationPlacement);
		this.backRightWheelRotationPlacement.getTransform(actualBackRightWheelRotationPlacement);
		this.frontLeftWheelRotationPlacement.getTransform(actualFrontLeftWheelRotationPlacement);
		this.frontRightWheelRotationPlacement.getTransform(actualFrontRightWheelRotationPlacement);
		
		// Soma da rota��o atual com a rota��o a ser aplicada.
		actualBackLeftWheelRotationPlacement.mul(wheelRotationTransformer);
		actualBackRightWheelRotationPlacement.mul(wheelRotationTransformer);
		actualFrontLeftWheelRotationPlacement.mul(wheelRotationTransformer);
		actualFrontRightWheelRotationPlacement.mul(wheelRotationTransformer);
		
		// Posicionamento das rodas
		backLeftWheelPositionTransformer.setTranslation(new Vector3d(4.8, -4.0, 4.1));
		backRightWheelPositionTransformer.setTranslation(new Vector3d(4.8, -4.0, -4.1));
		frontLeftWheelPositionTransformer.setTranslation(new Vector3d(-6.3, -4.0, 4.1));
		frontRightWheelPositionTransformer.setTranslation(new Vector3d(-6.3, -4.0, -4.1));
		
		// Aplica��o da transforma��o da orienta��o das rodas.
		this.frontLeftWheelOrientationPlacement.setTransform(wheelOrientationTransformer);
		this.frontRightWheelOrientationPlacement.setTransform(wheelOrientationTransformer);
		
		// Aplica��o da transforma��o de rota��o das rodas.
		this.backLeftWheelRotationPlacement.setTransform(actualBackLeftWheelRotationPlacement);
		this.backRightWheelRotationPlacement.setTransform(actualBackRightWheelRotationPlacement);
		this.frontLeftWheelRotationPlacement.setTransform(actualFrontLeftWheelRotationPlacement);
		this.frontRightWheelRotationPlacement.setTransform(actualFrontRightWheelRotationPlacement);
		
		// Aplica��o da transforma��o de posicionamento das rodas.
		this.backLeftWheelPositionPlacement.setTransform(backLeftWheelPositionTransformer);
		this.backRightWheelPositionPlacement.setTransform(backRightWheelPositionTransformer);
		this.frontLeftWheelPositionPlacement.setTransform(frontLeftWheelPositionTransformer);
		this.frontRightWheelPositionPlacement.setTransform(frontRightWheelPositionTransformer);
		
		// Atualiza��o do posicionamento e escala do carro.
		carScaleTransformer.setScale(this.scale);
		carPositionTransformer.setTranslation(new Vector3d(this.car.getPosition()));
		carOrientationTransformer.rotY(this.car.getOrientation());
		
		// Gera��o da matriz de transforma��o.
		carScaleTransformer.mul(carOrientationTransformer);
		carPositionTransformer.mul(carScaleTransformer);
		
		// Aplica��o da transforma��o.
		this.placement.setTransform(carPositionTransformer);
		
		// Modo de posicionamento da c�mera.
		switch (this.car.getCameraMode()) {
		
		// C�mera de vis�o traseira
		case 0:
			cameraPosition.x = this.car.getPosition().x + 2.2 * Math.cos(-this.car.getOrientation());
			cameraPosition.y = 1.2;
			cameraPosition.z = this.car.getPosition().z + 2.2 * Math.sin(-this.car.getOrientation());
			
			cameraLook.x = this.car.getPosition().x - 10 * Math.cos(-this.car.getOrientation());
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z - 10 * Math.sin(-this.car.getOrientation());
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(0, 1, 0));
			break;
			
		// C�mera de vis�o frontal
		case 1:
			cameraPosition.x = this.car.getPosition().x - 2.2 * Math.cos(-this.car.getOrientation());
			cameraPosition.y = 1.2;
			cameraPosition.z = this.car.getPosition().z - 2.2 * Math.sin(-this.car.getOrientation());
			
			cameraLook.x = this.car.getPosition().x + 10 * Math.cos(-this.car.getOrientation());
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z + 10 * Math.sin(-this.car.getOrientation());
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(0, 1, 0));
			break;

		// C�mera de vis�o �erea
		case 2:
			cameraPosition.x = this.car.getPosition().x;
			cameraPosition.y = 15.0;
			cameraPosition.z = this.car.getPosition().z;
			
			cameraLook.x = this.car.getPosition().x;
			cameraLook.y = 0.0;
			cameraLook.z = this.car.getPosition().z;
			
			this.car.getCamera().lookAt(cameraPosition, cameraLook, new Vector3d(-1, 0, 0));
			break;
			
		// C�mera de vis�o interna
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
		
		// Atualiza��o dos bounds de colis�o do carro
		if (this.car.getCollidable() != null) {
			this.car.getCollidable().getBounds().setTransform(carPositionTransformer);
		}
	}
}