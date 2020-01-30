import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import processing.core.PApplet;


public class ScoreboardPanel extends PApplet{
	
	
	private final float DRAWING_WIDTH = 1000, DRAWING_HEIGHT = 500;
	
	public void printScoreboard() {
		System.out.println("scoreboard panel");
		
		
	}
	
	public void draw()
	{
		background(255);
		pushMatrix();
		pushStyle();
		
		
		
		
		float scaleW = width / DRAWING_WIDTH;
		float scaleH = height / DRAWING_HEIGHT;
		scale(scaleW,scaleH);
		//flightSim.draw(this);
		popStyle();
		popMatrix();
	}
}