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
 * WallView | Representa��o geom�trica do objeto muro, do cen�rio.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class WallView extends Viewable {

	/** Topo do grafo de cena da representa��o geom�trica do muro. */
	private BranchGroup root;
	/** Grupo de transforma��o para atualiza��o geom�trica do muro. */
	private TransformGroup placement;
	
	/** Muro a ser representado. */
	private Wall wall;
	
	/**
	 * Construtor da classe.
	 * @param wall Muro a ser representado.
	 * @throws Exception Modelos n�o encontrados.
	 */
	public WallView(Wall wall) throws Exception {
		super();
		this.wall = wall;
		this.build();
	}
	
	/**
	 * Constr�i a representa��o geom�trica do muro.
	 * @throws Exception Modelos n�o encontrados.
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
	 * Retorna a representa��o geom�trica do muro.
	 * @return A representa��o geom�trica do muro.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualiza��o da representa��o geom�trica do muro.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		
	}
	
}