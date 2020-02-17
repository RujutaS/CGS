package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

public class Ending extends Obstacle {
	
	private PApplet p;
	private Rect rect;
	private int width, height;

	public Ending(int x, int y, int w, int h, PApplet p) {
		rect = new Rect(x, y, w, h);
		
		width = w;
		height = h;
	}
	
	public void draw(PApplet p) {
		p.pushStyle();
		p.fill(255, 255, 0);
		p.rect((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight());
		p.popStyle();
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
