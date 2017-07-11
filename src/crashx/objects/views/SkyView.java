package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.Sky;
import crashx.objects.Sky.SkyType;

/**
 * SkyView | Representação geométrica do objeto céu, do cenário. Incorpora as luzes spot e ambiente do cenário.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class SkyView extends Viewable {

	/** Topo do grafo de cena da representação geométrica do céu. */
	private BranchGroup root;
	/** Grupo de transformação para atualização geométrica do céu. */
	private TransformGroup placement;
	
	/** Céu a ser representado. */
	private Sky sky;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(12, 8, 12);
	
	/**
	 * Construtor da classe.
	 * @param sky Céu a ser representado.
	 * @throws Exception Modelos não encontrados.
	 */
	public SkyView(Sky sky) throws Exception {
		super();
		this.sky = sky;
		this.build();
	}
	
	/**
	 * Constrói a representação geométrica do céu.
	 * @throws Exception Modelos não encontrados.
	 */
	private void build() throws Exception {
		
		String sky;		
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.sky.getType().equals(SkyType.TypeA)) {
	 		sky = "crashx/resources/models/world/sky.wrl";
	 	} else {
	 		sky = "";
	 	}
	 	
	 	BranchGroup skySceneGroup = objectLoader.load(sky).getSceneGroup();
	 	
	 	this.placement.addChild(skySceneGroup);
	 	this.root.addChild(this.placement);
	 	
	 	Transform3D scaleTransformer = new Transform3D();
	 	Transform3D positionTransformer = new Transform3D();
	 	
	 	scaleTransformer.setScale(this.scale);
	 	positionTransformer.setTranslation(new Vector3d(this.sky.getPosition()));
	 	
	 	scaleTransformer.mul(positionTransformer);
	 	
	 	this.placement.setTransform(scaleTransformer);
	 	
	 	BoundingSphere lightBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 500.0);
	 	
	 	Color3f spotColor = new Color3f(0.95f, 0.95f, 1.0f);
	 	Color3f ambientColor = new Color3f(0.4f, 0.4f, 0.5f);
	 	
	 	Point3f spotPositionOne = new Point3f(50.0f, 50.0f, 50.0f);
	 	Point3f spotPositionTwo = new Point3f(-50.0f, 50.0f, 50.0f);
	 	Point3f spotPositionThree = new Point3f(50.0f, 50.0f, -50.0f);
	 	Point3f spotPositionFour = new Point3f(-50.0f, 50.0f, -50.0f);
	 	
	 	Point3f spotAtenuation = new Point3f(0.0f, 0.040f, 0.0f);
	 	Vector3f spotDirection = new Vector3f(-1f, -1f, -1f);
	 	
	 	SpotLight spotOne = new SpotLight(true, spotColor, spotPositionOne, spotAtenuation, spotDirection, (float)Math.PI, 0.0f);
	 	SpotLight spotTwo = new SpotLight(true, spotColor, spotPositionTwo, spotAtenuation, spotDirection, (float)Math.PI, 0.0f);
	 	SpotLight spotThree = new SpotLight(true, spotColor, spotPositionThree, spotAtenuation, spotDirection, (float)Math.PI, 0.0f);
	 	SpotLight spotFour = new SpotLight(true, spotColor, spotPositionFour, spotAtenuation, spotDirection, (float)Math.PI, 0.0f);
	 	AmbientLight ambient = new AmbientLight(true, ambientColor);
	 	
	 	spotOne.setInfluencingBounds(lightBounds);
	 	spotTwo.setInfluencingBounds(lightBounds);
	 	spotThree.setInfluencingBounds(lightBounds);
	 	spotFour.setInfluencingBounds(lightBounds);
	 	ambient.setInfluencingBounds(lightBounds);
	 	
	 	this.root.addChild(spotOne);
	 	this.root.addChild(spotTwo);
	 	this.root.addChild(spotThree);
	 	this.root.addChild(spotFour);
	 	this.root.addChild(ambient);
	}
	
	/**
	 * Retorna a representação geométrica do céu.
	 * @return A representação geométrica do céu.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualização da representação geométrica do céu.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
	}
}