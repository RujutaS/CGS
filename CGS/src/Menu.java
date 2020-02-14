



import java.awt.Point;
import java.awt.Rectangle;


public class Menu extends Screen {

	private DrawingSurface surface;
	
	//private Rectangle button;
	private Rectangle play, store, help;
	

	public Menu(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		//button = new Rectangle(800/2-100,600/2-50,200,100);
		store = new Rectangle (225, 300, 60, 60);
		play = new Rectangle (375, 300, 60, 60);
		help = new Rectangle (525, 300, 60, 60);
	
	}


	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		
		surface.rect(play.x, play.y, play.width, play.height, 10, 10, 10, 10);
		surface.rect(store.x, store.y, store.width, store.height, 10, 10, 10, 10); //the shape of the shape
		surface.rect(help.x, help.y, help.width, help.height, 10, 10, 10, 10); //the shape of the shape
	
		surface.textSize(14);
		
		surface.fill(0);
		String pText = "PLAY";
		String hText = "HELP";
		String sText = "STORE";
		
		float p = surface.textWidth(pText);
		float h = surface.textWidth(hText);
		float s = surface.textWidth(sText);
		
		surface.text(pText, play.x+play.width/2 - 14, play.y+play.height/2);
		surface.text(hText, help.x+help.width/2 - 14, help.y+help.height/2);
		surface.text(sText, store.x+store.width/2 - 16, store.y+store.height/2);
	
		
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
	}
	

}

