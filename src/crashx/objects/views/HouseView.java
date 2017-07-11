package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.House;
import crashx.objects.House.HouseType;

/**
 * HouseView | Representa��o geom�trica do objeto casa, do cen�rio.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class HouseView extends Viewable {

	/** Topo do grafo de cena da representa��o geom�trica da casa. */
	private BranchGroup root;
	/** Grupo de transforma��o para atualiza��o geom�trica da casa. */
	private TransformGroup placement;
	
	/** Casa a ser representada. */
	private House house;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(0.15, 0.15, 0.15);
	
	/**
	 * Construtor da classe.
	 * @param house Casa a ser representada.
	 * @throws Exception Modelos n�o encontrados.
	 */
	public HouseView(House house) throws Exception {
		super();
		this.house = house;
		this.build();
	}
	
	/**
	 * Constr�i a representa��o geom�trica da casa.
	 * @throws Exception Modelos n�o encontrados.
	 */
	private void build() throws Exception {		
		
		String house;	
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.house.getType().equals(HouseType.TypeA)) {
	 		house = "crashx/resources/models/world/house01.wrl";
	 	} else if (this.house.getType().equals(HouseType.TypeB)) {
	 		house = "crashx/resources/models/world/house02.wrl";
	 	} else {
	 		house = "";
	 	}
	 	
	 	BranchGroup houseSceneGroup = objectLoader.load(house).getSceneGroup();
	 	
	 	this.placement.addChild(houseSceneGroup);
	 	this.root.addChild(this.placement);
	 	
	 	Transform3D scaleTransformer = new Transform3D();
	 	Transform3D positionTransformer = new Transform3D();
	 	Transform3D orientationTransformer = new Transform3D();
	 	
	 	// Atualiza��o do posicionamento e escala da casa.
		scaleTransformer.setScale(this.scale);
		positionTransformer.setTranslation(new Vector3d(this.house.getPosition()));
		
		if (this.house.getType().equals(HouseType.TypeA)) {
			orientationTransformer.rotY(0);
	 	} else if (this.house.getType().equals(HouseType.TypeB)) {
	 		orientationTransformer.rotY(Math.PI);
	 	} else {
	 		orientationTransformer.rotY(0);
	 	}
		
		// Gera��o da matriz de transforma��o.
		scaleTransformer.mul(orientationTransformer);
		positionTransformer.mul(scaleTransformer);
		
		// Aplica��o da transforma��o.
		this.placement.setTransform(positionTransformer);
		
		this.updateView(0);
	}
	
	/**
	 * Retorna a representa��o geom�trica da casa.
	 * @return A representa��o geom�trica da casa.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualiza��o da representa��o geom�trica da casa.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		// Atualiza��o dos bounds de colis�o da casa
		if (this.house.getCollidable() != null) {
			
			Transform3D scaleTransformer = new Transform3D();
		 	Transform3D positionTransformer = new Transform3D();
		 	Transform3D orientationTransformer = new Transform3D();
		 	
		 	// Atualiza��o do posicionamento e escala da casa.
			scaleTransformer.setScale(this.scale);
			positionTransformer.setTranslation(new Vector3d(this.house.getPosition()));
			
			if (this.house.getType().equals(HouseType.TypeA)) {
				orientationTransformer.rotY(0);
		 	} else if (this.house.getType().equals(HouseType.TypeB)) {
		 		orientationTransformer.rotY(Math.PI);
		 	} else {
		 		orientationTransformer.rotY(0);
		 	}
			
			// Gera��o da matriz de transforma��o.
			scaleTransformer.mul(orientationTransformer);
			positionTransformer.mul(scaleTransformer);
			
			this.house.getCollidable().getBounds().setTransform(positionTransformer);
		}
	}
}