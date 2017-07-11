package crashx.objects.views;

import interlab.engine.core.Viewable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import crashx.objects.Tree;
import crashx.objects.Tree.TreeType;

/**
 * TreeView | Representação geométrica do objeto árvore, do cenário.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class TreeView extends Viewable {

	/** Topo do grafo de cena da representação geométrica da árvore. */
	private BranchGroup root;
	/** Grupo de transformação para atualização geométrica da árvore. */
	private TransformGroup placement;
	
	/** Árvore a ser representada. */
	private Tree tree;
	
	/** Vetor com a escala utilizada, para cada coordenada. */
	private Vector3d scale = new Vector3d(0.4, 0.4, 0.4);
	
	/**
	 * Construtor da classe.
	 * @param tree Árvore a ser representada.
	 * @throws Exception Modelos não encontrados.
	 */
	public TreeView(Tree tree) throws Exception {
		super();
		this.tree = tree;
		this.build();
	}
	
	/**
	 * Constrói a representação geométrica da árvore.
	 * @throws Exception Modelos não encontrados.
	 */
	private void build() throws Exception {		
		
		String tree;	
		VrmlLoader objectLoader = new VrmlLoader();
	 	
		this.root = new BranchGroup();
	 	this.placement = new TransformGroup();
	 	
	 	this.root.setCapability(BranchGroup.ALLOW_DETACH);
	 	this.placement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 	
	 	if (this.tree.getType().equals(TreeType.TypeA)) {
	 		tree = "crashx/resources/models/world/tree01.wrl";
	 	} else if (this.tree.getType().equals(TreeType.TypeB)) {
	 		tree = "crashx/resources/models/world/tree02.wrl";
	 	} else {
	 		tree = "";
	 	}
	 	
	 	BranchGroup houseSceneGroup = objectLoader.load(tree).getSceneGroup();
	 	
	 	this.placement.addChild(houseSceneGroup);
	 	this.root.addChild(this.placement);
	 	
	 	Transform3D scaleTransformer = new Transform3D();
	 	Transform3D positionTransformer = new Transform3D();
	 	
	 	// Atualização do posicionamento e escala da árvore.
		scaleTransformer.setScale(this.scale);
		positionTransformer.setTranslation(new Vector3d(this.tree.getPosition()));
		
		// Geração da matriz de transformação.
		positionTransformer.mul(scaleTransformer);
		
		// Aplicação da transformação.
		this.placement.setTransform(positionTransformer);
		
		this.updateView(0);
	}
	
	/**
	 * Retorna a representação geométrica da árvore.
	 * @return A representação geométrica da árvore.
	 */
	public BranchGroup getView() {
		return this.root;
	}

	/**
	 * Atualização da representação geométrica da árvore.
	 * @param delta Delta.
	 */
	public void updateView(long delta) {
		// Atualização dos bounds de colisão da árvore
		if (this.tree.getCollidable() != null) {
			
			Transform3D scaleTransformer = new Transform3D();
			Transform3D positionTransformer = new Transform3D();
			
			scaleTransformer.setScale(this.scale);
		 	positionTransformer.setTranslation(new Vector3d(this.tree.getPosition()));
		 	positionTransformer.mul(scaleTransformer);	
			
			this.tree.getCollidable().getBounds().setTransform(positionTransformer);
		}
	}
}