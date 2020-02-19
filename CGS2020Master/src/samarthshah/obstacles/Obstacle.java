package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Shape;

/** An abstract class used as the basis for all of the obstacles
 * 
 * @author Samarth Shah
 *
 */
public abstract class Obstacle {
	protected Shape shape;

	/**
	 * 
	 * @return A array of floats with the physical values used to do calculations with the position and size
	 */
	public abstract float[] getBounds();
	
	/** Draws the obstacles
	 * 
	 * @param p The PApplet to draw onto
	 */
	public abstract void draw(PApplet p);
}
