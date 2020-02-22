package obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

/**
 * A class representing one of the answers inside of the question
 * 
 * @author Samarth Shah
 *
 */
public class Answer {

	private String answer;
	private boolean isCorrect;
	private int x, y, w,h;
	private Rect rect;
	
	/** Creates a new answer with the given parameters
	 * 
	 * @param a The String that has the actual answer
	 * @param c If this is the correct answer to the question
	 * @param x An int with the x value of the answer
	 * @param y An int with the y value of the answer
	 * @param w An int with the width value of the answer
	 * @param h An int with the height value of the answer
	 */
	public Answer(String a, boolean c, int x, int y, int w, int h) {
		answer = a;
		isCorrect = c;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		rect = new Rect(x, y, w, h);
	}
	
	/** Draws the answer
	 * 
	 * @param p The PApplet to draw onto
	 */
	public void draw(PApplet p) {
		p.pushStyle();
		p.noFill();
		p.rect(x, y, w, h);
		p.text(answer, x+10, y+60);
		p.popStyle();
	}
	
	/**
	 * 
	 * @return The answer string
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * 
	 * @return If this answer is correct
	 */
	public boolean isCorrect() {
		return isCorrect;
	}
	
	/**
	 * 
	 * @return A array of floats with the physical values used to do calculations with the position and size
	 */
	public float[] getBounds() {
		float[] params = new float[4];
		params[0] = (float)rect.getX();
		params[1] = (float)rect.getY();
		params[2] = w;
		params[3] = h;
		
		return params;
	}
}
