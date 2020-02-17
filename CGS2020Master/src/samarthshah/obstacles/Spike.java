package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Triangle;

public class Spike extends Obstacle {
	
	PApplet p;
	Triangle tri;
	boolean up;
	
	public Spike(int x, int y, int l, boolean up, PApplet p) {
		this.p = p;
		tri = new Triangle(x, y, l, up);
		this.up = up;
	}

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

	@Override
	public void draw(PApplet p) {
		tri.draw(p);
	}
}
