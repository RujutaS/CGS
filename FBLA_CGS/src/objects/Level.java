package objects;

import java.util.ArrayList;

import obstacles.Obstacle;
import processing.core.PApplet;

/**
 * This is the class that holds the data for the level that the player is trying to finish. The level is stored as a list of obstacles like platforms and spikes.
 *
 * @author Samarth Shah
 * 
 *
 */
public class Level {
	private ArrayList<Obstacle> obstacles, onScreenObstacles;
	private int speed;


	/** Creates a new level
	 * 
	 * @param obstacles The array of obstacles that make up the level
	 * @param width An int with the width of the window
	 * @param x In int with how much the window has scrolled through the level so far
	 */
	public Level(ArrayList<Obstacle> obstacles, int width, int x) {
		this.obstacles = obstacles;

		onScreenObstacles = new ArrayList<Obstacle>();

		for (Obstacle o: obstacles) {
			float[] params = o.getBounds();

			if (!(params[0] + params[2] < (x * -1) || params[0] > width - x)) {
				onScreenObstacles.add(o);
			}
		}
	}

	/** Draws the level's obstacles
	 * 
	 * @param p The PApplet to draw onto
	 */
	public void draw(PApplet p) {

		p.translate(speed, 0);

		for (Obstacle o : onScreenObstacles) {
			o.draw(p);
		}
	}

	/**
	 * 
	 * @return The current obstacles that are on the screen at that moment
	 */
	public ArrayList<Obstacle> getObstacles() {
		return onScreenObstacles;
	}
	

	/**
	 * 
	 * @return The all obstacles that are in the level
	 */
	public ArrayList<Obstacle> getAllObstacles() {
		return obstacles;
	}


	/** Refreshes what obstacles are currently on the screen
	 * 
	 * @param width An int with the width of the window
	 * @param x In int with how much the window has scrolled through the level so far
	 */
	public void refresh(int x, int width) {
		onScreenObstacles = new ArrayList<Obstacle>();

		for (Obstacle o: obstacles) {
			float[] params = o.getBounds();


			if (!(params[0] + params[2] < (x * -1) || params[0] > width - x)) {
				onScreenObstacles.add(o);
			}
		}
	}
}
