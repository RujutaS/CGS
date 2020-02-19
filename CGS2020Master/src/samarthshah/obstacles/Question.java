package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

/** A class that represents a question as an obstacle that would need to be answered by the player by going through an answer to pick it
 * 
 * @author Samarth Shah
 *
 */
public class Question extends Obstacle {

	private String question;
	private Answer[] answers;
	private Platform[] dividers;
	private Rect timeBefore, timeAfter;
	
	/** Creates a new question with the given parameters
	 * 
	 * @param q A string with the question
	 * @param a A array of strings that are each a possible answer to the question
	 * @param c Which of the answer strings is the correct answer
	 * @param x An int with the x value of the question obstacle
	 * @param y An int with the y value of the question obstacle
	 * @param width An int with the width value of the question obstacle
	 * @param height An int with the height value of the question obstacle
	 */
	public Question(String q, String[] a, int c, int x, int y, int width, int height) {
		
		answers = new Answer[3];
		question = q;
		
		for (int i = 0; i < 3; i++) {
			
			Answer answer;
			
			timeBefore = new Rect(x, y, width/3, height);
			timeAfter = new Rect(x + width/3*2, y, width/3, height);

			
			if (i == 0) {
				answer = new Answer(a[i], i == c, x + width/3, y, width/3, height/3);
			} else if (i == 1) {
				answer = new Answer(a[i], i == c, x + width/3, height/3 + y, width/3, height/3);
			} else {
				answer = new Answer(a[i], i == c, x + width/3, height/3*2 + y, width/3, height/3);
			}
			
			answers[i] = answer;
		}
		
		Platform p1 = new Platform(x + width/3, height/3 + y, width/3, 10);
		Platform p2 = new Platform(x + width/3, height/3*2 + y, width/3, 10);
		
		dividers = new Platform[2];
		dividers[0] = p1;
		dividers[1] = p2;

	}
	
	/** Draws the Question and all of its parts, including the answers and the dividers between the answers
	 * 
	 * @param p The PApplet to draw onto
	 * 
	 */
	public void draw(PApplet p) {
		for (Answer a: answers) {
			a.draw(p);
		}
		
		for (Platform plat: dividers) {
			plat.draw(p);
		}
		
		p.pushStyle();
		p.noFill();
		p.rect((float)timeBefore.getX(), (float)timeBefore.getY(), (float)timeBefore.getWidth(), (float)timeBefore.getHeight());
		p.rect((float)timeAfter.getX(), (float)timeAfter.getY(), (float)timeAfter.getWidth(), (float)timeAfter.getHeight());
		p.popStyle();
	}
	
	/**
	 * 
	 * @return A string with the question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * 
	 * @return A array with the possible answers
	 */
	public Answer[] getAnswers() {
		return answers;
	}
	
	/**
	 * 
	 * @return A array with the platforms that separate the answers
	 */
	public Platform[] getDividers() {
		return dividers;
	}
	
	/**
	 * 
	 * @return The two rectangles where the player can jump infinitely to reach the top answer
	 */
	public Rect[] getOuters() {
		Rect[] rects = new Rect[2];
		rects[0] = timeBefore;
		rects[1] = timeAfter;
		return rects;
	}

	/**
	 * 
	 * @return A array of floats with the physical values used to do calculations with the position and size
	 */
	@Override
	public float[] getBounds() {
		float[] params = new float[4];
		params[0] = (float)timeBefore.getX();
		params[1] = (float)0;
		params[2] = (float)timeBefore.getWidth()*3;
		params[3] = (float)timeBefore.getHeight();
		
		return params;
	}
}
