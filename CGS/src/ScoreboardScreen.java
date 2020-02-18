

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;


public class ScoreboardScreen extends Screen {
	
	private int x, y;
	
	private DrawingSurface surface;
	private Rectangle quit, back;
	
	public ScoreboardScreen(DrawingSurface surface) {
		super(800, 600);
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
				
				surface.rect(back.x, back.y, back.width, back.height, 10, 10, 10, 10);
				surface.rect(quit.x, quit.y, quit.width, quit.height, 10, 10, 10, 10);
				
				surface.textSize(10);
				surface.fill(0);
				surface.text("BACK", 18, 35);
				surface.text("QUIT", 78, 35);

				surface.textSize(50);
				surface.text("SCOREBOARD", 230, 130);

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