package obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Triangle;

/** A class representing a spike as an obstacle that would cause the player to die instantly
 *  
 * @author Samarth Shah
 *
 */
public class Spike extends Obstacle {
	
	private Triangle tri;
	private boolean up;
	
	/** Creates a new spike with the given parameters
	 * 
	 * @param x An int with the x value of the spike
	 * @param y An int with the y value of the spike
	 * @param l An int with the length of the sides of the spike
	 * @param up If the spike is facing upward
	 */
	public Spike(int x, int y, int l, boolean up) {
		tri = new Triangle(x, y, l, up);
		this.up = up;
	}

	/**
	 * 
	 * @return The bounds of the spike in terms of the x and y values of its points
	 */
	public float[] getBoundsPoints() {
		float[] bounds = new float[7];
		
		bounds[0] = (float)tri.getX();
		bounds[1] = (float)tri.getY();
		bounds[2] = (float)(tri.getX() + tri.getLength());
		bounds[3] = (float)tri.getY();
		bounds[4] = (float)(tri.getX() + tri.getLength()/2);
		
		if (up) {
			bounds[5] = (float)(tri.getY() - (tri.getLength()/2*Math.sqrt(3)));
			bounds[6] = 1;
		} else {
			bounds[5] = (float)(tri.getY() + (tri.getLength()/2*Math.sqrt(3)));
			bounds[6] = 0;
		}

		return bounds;
	}
	
	/**
	 * 
	 * @return A array of floats with the physical values used to do calculations with the position and size
	 */
	@Override
	public float[] getBounds() {
		float[] bounds = new float[4];
		
		bounds[0] = (float)tri.getX();
		bounds[1] = (float)tri.getY();
		bounds[2] = (float)tri.getLength();
		
		if (up) {
			bounds[3] = 1;
		} else {
			bounds[3] = 0;
		}

		return bounds;
	}

	/** Draws the Spike
	 * 
	 * @param p The PApplet to draw onto
	 */
	@Override
	public void draw(PApplet p) {
		tri.draw(p);
	}
}
