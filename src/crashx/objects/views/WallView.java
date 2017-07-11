package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.Wall;
import crashx.objects.Wall.WallType;

/**
 * WallView | Representação geométrica do objeto muro, do cenário.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class WallView extends Viewable {

	/** Topo do grafo de cena da representação geométrica do muro. */
	private BranchGroup root;
	/** Grupo de transformação para atualização geométrica do muro. */
	private TransformGroup placement;
	
	/** Muro a ser representado. */
	private Wall wall;
	
	/**
	 * Construtor da classe.
	 * @param wall Muro a ser representado.
	 * @throws Exception Modelos não encontrados.
	 */
	public WallView(Wall wall) throws Exception {
		super();
		this.wall = wall;
		this.build();
	}
	
	/**
	 * Constrói a representação geométrica do muro.
	 * @throws Exception Modelos não encontrados.
	 */
	private void build() throws Exception {
		
		String wall;
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.wall.getType().equals(WallType.TypeA)) {
	 		wall = "crashx/resources/models/world/wall.wrl";
	 	} else {
	 		wall = "";
	 	}
	 	
	 	BranchGroup wallSceneGroup = objectLoader.load(wall).getSceneGroup();
	 	
	 	this.placement.addChild(wallSceneGroup);
	 	this.root.addChild(this.placement);
	 	
	 	Transform3D positionTransformer = new Transform3D();
	 	positionTransformer.setTranslation(new Vector3d(this.wall.getPosition()));	 	
	 	this.placement.setTransform(positionTransformer);
	}
	
	/**
	 * Retorna a representação geométrica do muro.
	 * @return A representação geométrica do muro.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualização da representação geométrica do muro.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
	}
	
}