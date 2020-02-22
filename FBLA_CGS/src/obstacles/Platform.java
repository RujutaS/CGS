package obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

/** A class that represents a platform as an obstacle
 * 
 * @author Samarth Shah
 *
 */
public class Platform extends Obstacle {
	
	private Rect rect;
	private int width, height;
	
	/** Creates a new platform with the given parameters
	 * 
	 * @param x An int with the x value of the platform
	 * @param y An int with the y value of the platform
	 * @param w An int with the width value of the platform
	 * @param h An int with the height value of the platform
	 */
	public Platform(int x, int y, int w, int h) {
		rect = new Rect(x, y, w, h);
		
		width = w;
		height = h;
	}
	
	/** Draws the platform
	 * 
	 * @param p The PApplet to draw onto
	 */
	public void draw(PApplet p) {
		rect.draw(p);
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
