

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;


public class StoreScreen extends Screen {
	
	private int x, y;
	
	private DrawingSurface surface;
	private Rectangle back, quit;
	
	public StoreScreen(DrawingSurface surface) {
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
				surface.text("complete more levels to earn more coins", 550, 55);
				
				surface.textSize(20);
				surface.text("my coins: ", 550, 35);
				
				

				
				surface.textSize(50);
				surface.text("STORE", 325, 100);
				
				

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