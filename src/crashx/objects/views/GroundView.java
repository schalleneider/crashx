package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.Ground;
import crashx.objects.Ground.GroundType;

/**
 * GroundView | Representação geométrica do objeto chão, do cenário.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class GroundView extends Viewable {

	/** Topo do grafo de cena da representação geométrica do chão. */
	private BranchGroup root;
	/** Grupo de transformação para atualização geométrica do chão. */
	private TransformGroup placement;
	
	/** Chão a ser representado. */
	private Ground ground;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(1.5, 1, 1.5);
	
	/**
	 * Construtor da classe.
	 * @param ground Chão a ser representado.
	 * @throws Exception Modelos não encontrados.
	 */
	public GroundView(Ground ground) throws Exception {
		super();
		this.ground = ground;
		this.build();
	}
	
	/**
	 * Constrói a representação geométrica do chão.
	 * @throws Exception Modelos não encontrados.
	 */
	private void build() throws Exception {
		
		String ground;
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.ground.getType().equals(GroundType.TypeA)) {
	 		ground = "crashx/resources/models/world/ground.wrl";
	 	} else {
	 		ground = "";
	 	}
	 	
	 	BranchGroup groundSceneGroup = objectLoader.load(ground).getSceneGroup();
	 	
	 	this.placement.addChild(groundSceneGroup);
	 	this.root.addChild(this.placement);
	 	
	 	Transform3D scaleTransformer = new Transform3D();
	 	Transform3D positionTransformer = new Transform3D();
	 	
	 	scaleTransformer.setScale(this.scale);
	 	positionTransformer.setTranslation(new Vector3d(this.ground.getPosition()));
	 	scaleTransformer.mul(positionTransformer);
	 	this.placement.setTransform(scaleTransformer);
	}
	
	/**
	 * Retorna a representação geométrica do chão.
	 * @return A representação geométrica do chão.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualização da representação geométrica do chão.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
	}
	
}