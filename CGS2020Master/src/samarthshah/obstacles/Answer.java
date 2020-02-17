package samarthshah.obstacles;

import processing.core.PApplet;
import samarthshah.shapes.Rect;

public class Answer {

	private String answer;
	private boolean isCorrect;
	private int x, y, w,h;
	private Rect rect;
	
	public Answer(String a, boolean c, int x, int y, int w, int h) {
		answer = a;
		isCorrect = c;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		rect = new Rect(x, y, w, h);
	}
	
	public void draw(PApplet p) {
		p.pushStyle();
		p.noFill();
		p.rect(x, y, w, h);
		p.text(answer, x+10, y+60);
		p.popStyle();
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public float[] getBounds() {
		float[] params = new float[4];
		params[0] = (float)rect.getX();
		params[1] = (float)rect.getY();
		params[2] = w;
		params[3] = h;
		
		return params;
	}
}
