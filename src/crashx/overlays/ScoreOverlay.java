package crashx.overlays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import crashx.objects.Car;

import interlab.engine.core.GameObject;
import interlab.engine.io.graphics.Overlay;

/**
 * ScoreOverlay | Exibição da quantidade de vida dos carros.
 *
 * @author Willians Schallemberger Schneider
 * @version 1.00
 *
 */
public class ScoreOverlay implements Overlay {
	
	/** Instância do carro cujo dados serão exibidos. */
	private Car car;
	
	/**
	 * Construtor da classe.
	 * @param car Instância do carro cujo dados serão exibidos.
	 */
	public ScoreOverlay(GameObject car) {
		this.car = (Car)car;
	}

	/**
	 * Desenha o conteúdo a ser exibido.
	 * @param graphics Graphics onde o conteúdo será desenhado.
	 */
	public void draw(Graphics2D graphics) {
		
		Point startBar = new Point(80, 22);
		Dimension dimensionBar = new Dimension((int)(411 * this.car.getLifePercent()), 10);
		
		Image border = Toolkit.getDefaultToolkit().getImage("crashx/resources/images/border.png");
		graphics.drawImage(border, 0, 0, null);
		border = null;
		
		int startColorRed	= (int)(150 * ( 1 - this.car.getLifePercent()));
		int startColorGreen	= (int)(150 * (this.car.getLifePercent()));
		int startColorBlue	= 0;
		
		int endColorRed		= (int)(255 * ( 1 - this.car.getLifePercent()));
		int endColorGreen	= (int)(255 * (this.car.getLifePercent()));
		int endColorBlue	= 0;
		
		Color startColor = new Color(startColorRed, startColorGreen, startColorBlue);
		Color endColor = new Color(endColorRed, endColorGreen, endColorBlue);
		
		GradientPaint paint = new GradientPaint(startBar.x, startBar.y + (int)dimensionBar.height / 2, startColor, startBar.x + dimensionBar.width, startBar.y + (int)dimensionBar.height / 2, endColor);
		
		graphics.setPaint(paint);
		graphics.fillRect(startBar.x, startBar.y, dimensionBar.width, dimensionBar.height);
		
		/* Debug - Parâmetros do carro. *
		graphics.setColor(new Color(255, 255, 255));
		graphics.drawString("Position [" + this.car.getPosition().x + ", " + this.car.getPosition().y + ", " + this.car.getPosition().z + "]", 20, 60);
		graphics.drawString("Orientation [" + Math.toDegrees(this.car.getOrientation()) + "]", 20, 80);
		/**/
	}
}
