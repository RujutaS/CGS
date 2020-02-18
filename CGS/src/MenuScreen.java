



import java.awt.Point;
import java.awt.Rectangle;


public class MenuScreen extends Screen {

	private DrawingSurface surface;
	
	//private Rectangle button;
	private Rectangle play, store, help, quit, scoreboard;
	

	public MenuScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;

		//button = new Rectangle(800/2-100,600/2-50,200,100);
		store = new Rectangle (225, 300, 60, 60);
		play = new Rectangle (375, 300, 60, 60);
		help = new Rectangle (525, 300, 60, 60);
	
		quit = new Rectangle (10, 10, 60, 60);
		scoreboard = new Rectangle (90, 10, 100, 60);
	}


	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		
		surface.rect(play.x, play.y, play.width, play.height, 10, 10, 10, 10);
		surface.rect(store.x, store.y, store.width, store.height, 10, 10, 10, 10); //the shape of the rectangle
		surface.rect(help.x, help.y, help.width, help.height, 10, 10, 10, 10); 
		
		surface.rect(quit.x, quit.y, quit.width, quit.height, 10, 10, 10, 10); 
		surface.rect(scoreboard.x, scoreboard.y, scoreboard.width, scoreboard.height, 10, 10, 10, 10); 
		
		
		surface.textSize(14);
		
		surface.fill(0);
		String pText = "PLAY";
		String hText = "HELP";
		String sText = "STORE";
		String qText = "QUIT";
		String sbText = "SCOREBOARD";
		
		float p = surface.textWidth(pText);
		float h = surface.textWidth(hText);
		float s = surface.textWidth(sText);
		float q = surface.textWidth(qText);
		float sb = surface.textWidth(sbText);
	
		
		surface.text(pText, play.x+play.width/2 - 14, play.y+play.height/2);
		surface.text(hText, help.x+help.width/2 - 14, help.y+help.height/2);
		surface.text(sText, store.x+store.width/2 - 16, store.y+store.height/2);
	
		surface.text(qText, quit.x+quit.width/2 - 14, quit.y+quit.height/2);
		surface.text(sbText, scoreboard.x+scoreboard.width/2 - 45, scoreboard.y+scoreboard.height/2);
		
		
		surface.textSize(50);
		
		surface.text("CGS", 350, 260);
		
		surface.popStyle();
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (play.contains(p)) {
			surface.switchScreen(ScreenSwitcher.GAMESCREEN);
		}
		if (help.contains(p)) {
			surface.switchScreen(ScreenSwitcher.HELPSCREEN);
		}
		if (store.contains(p)) {
			surface.switchScreen(ScreenSwitcher.STORESCREEN);
		}
		if (quit.contains(p)) {
			System.exit(0);
		}
		if (scoreboard.contains(p)) {
			surface.switchScreen(ScreenSwitcher.SCOREBOARDSCREEN);
		}
	}
	

}

