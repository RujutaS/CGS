package samarthshah.obstacles;

import processing.core.PApplet;
import processing.core.PShape;
import samarthshah.shapes.Shape;

public abstract class Obstacle {
//	protected PShape shape;
	protected Shape shape;
	
//	public PShape getBounds() {
//		return shape;
//	}
	
	public abstract float[] getBounds();
	
	public abstract void draw(PApplet p);
	
	
//	public void draw(PApplet p) {
//		p.shape(shape);
//	}
//	
//	public Shape getShape( ) {
//		return shape;
//	}
}
