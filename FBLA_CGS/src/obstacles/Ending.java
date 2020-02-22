package obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

/**
 * A class with the obstacle that represents the end of the level
 * 
 * @author Samarth Shah
 *
 */
public class Ending extends Obstacle {
	
	private Rect rect;
	private int width, height;

	/** Creates a new Ending with the given parameters
	 * 
	 * @param x An int with the x value of the ending
	 * @param y An int with the y value of the ending
	 * @param w An int with the width value of the ending
	 * @param h An int with the height value of the ending
	 */
	public Ending(int x, int y, int w, int h) {
		rect = new Rect(x, y, w, h);
		
		width = w;
		height = h;
	}
	
	/** Draws the ending
	 * 
	 * @param p The PApplet to draw onto
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		p.fill(255, 255, 0);
		p.rect((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight());
		p.popStyle();
	}
	
	/**
	 * 
	 * @return A array of floats with the physical values used to do calculations with the position and size
	 */
	public float[] getBounds() {
		float[] params = new float[4];
		params[0] = (float)rect.getX();
		params[1] = (float)rect.getY();
		params[2] = width;
		params[3] = height;
		
		return params;
	}


}
