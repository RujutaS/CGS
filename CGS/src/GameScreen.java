

import java.awt.Point;
import java.awt.event.*;


import java.awt.Rectangle;


public class GameScreen extends Screen {
	
	private int x, y;
	
	private DrawingSurface surface;
	private Rectangle back, quit, future, business, leader, america;
	
	public GameScreen(DrawingSurface surface) {
		super(800, 600); //800, 600
		this.surface = surface;
		
		x = 30;
		y = 30;
	}
	
	public void draw() {
		
		// Draw stuff
		
		surface.pushStyle();
		
		surface.background(255);   // Clear the screen with a white background
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();

		
		
		surface.fill(255);
		
		back = new Rectangle (10, 10, 40, 40);
		quit = new Rectangle (70, 10, 40, 40);
		future = new Rectangle(130, 200, 100, 50);
		business = new Rectangle(280, 300, 100, 50);
		leader = new Rectangle(430, 400, 100, 50);
		america = new Rectangle (580, 500, 100, 50);
		
		surface.rect(back.x, back.y, back.width, back.height, 10, 10, 10, 10);
		surface.rect(quit.x, quit.y, quit.width, quit.height, 10, 10, 10, 10);
		surface.rect(future.x, future.y, future.width, future.height, 10, 10, 10, 10);
		surface.rect(business.x, business.y, business.width, business.height, 10, 10, 10, 10);
		surface.rect(leader.x, leader.y, leader.width, leader.height, 10, 10, 10, 10);
		surface.rect(america.x, america.y, america.width, america.height, 10, 10, 10, 10);
		
		
		surface.textSize(10);
		surface.fill(0);
		surface.text("BACK", 18, 35);
		surface.text("QUIT", 78, 35);
		
		surface.textSize(15);
		surface.text("FUTURE", 150, 230);
		surface.text("BUSINESS", 295, 330);
		surface.text("LEADER", 450, 430);
		surface.text("AMERICA", 600, 530);
		
		surface.textSize(20);
		surface.text("SELECT A BUSINESS ACHIEVEMENT AWARD LEVEL TO PLAY", 130, 100);
		
		

		surface.popStyle();

		
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p)) {
			surface.switchScreen(ScreenSwitcher.SCREEN1);
		}
		if (quit.contains(p)) {
			System.exit(0);
		}
		
	}
	
	
	
}