package samarthshah.objects;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import samarthshah.obstacles.Obstacle;

public class Level {
	private ArrayList<Obstacle> obstacles, onScreenObstacles;
	private PImage backgroundImage;
	private int speed;

	
	public Level(ArrayList<Obstacle> obstacles, PImage background, int width, int x) {
		this.obstacles = obstacles;
		backgroundImage = background;
		
		onScreenObstacles = new ArrayList<Obstacle>();
		
		for (Obstacle o: obstacles) {
			float[] params = o.getBounds();

			
			if (!(params[0] + params[2] < x || params[0] > x + width)) {
				onScreenObstacles.add(o);
			}
		}
	}
	
	public void draw(PApplet p) {
		if (backgroundImage != null) {
			p.image(backgroundImage, 0, 0);
		}
		
		p.translate(speed, 0);
		
		for (Obstacle o : onScreenObstacles) {
			o.draw(p);
		}
	}
	
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}
}
