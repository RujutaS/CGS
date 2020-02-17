package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

public class Platform extends Obstacle {
	
	private PApplet p;
	private Rect rect;
	private int width, height;
	
	public Platform(int x, int y, int w, int h, PApplet p) {
//		this.shape = p.createShape(p.RECT, x, y, w, h);
		rect = new Rect(x, y, w, h);
		
		width = w;
		height = h;
	}
	
	public void draw(PApplet p) {
		rect.draw(p);
	}
	
	public float[] getBounds() {
		float[] params = new float[4];
		params[0] = (float)rect.getX();
		params[1] = (float)rect.getY();
		params[2] = width;
		params[3] = height;
		
		return params;
	}

}
