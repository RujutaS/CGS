package menus;
import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;
import utilities.ImageLoader;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The StartMenu class is a specific type of Menu that
 * shows up before the user starts playing the game. It 
 * allows the user to pick his or her choice of game 
 * (single-player or multiplayer mode), as well as edit
 * certain game settings.
 *
 */
public class StartMenu extends Menu {

	public StartMenu() {
		super();
		doButtons();
	}
	
	private void doButtons() {
		this.addButton(new Button(165, 250, 200, 75, "Play", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
		this.addButton(new Button(415, 250, 200, 75, "Store", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		this.addButton(new Button(665, 250, 200, 75, "Scoreboard", Color.BLACK, GameScreen.LIGHT_BLUE, Color.CYAN, Color.BLACK));
		this.addButton(new Button(915, 250, 200, 75, "Quit", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Play")) {
			gameScreen.changeMenuMode("difficulty");
		} else if(buttonText.equals("Store")) {
			gameScreen.changeMenuMode("store"); 
		}else if(buttonText.equals("Scoreboard")) {
			gameScreen.changeMenuMode("scoreboard");
		} else if(buttonText.equals("Quit")) {
			gameScreen.changeMenuMode("quit");
		}
	}
	
	public void draw(PApplet drawer) {
//		drawer.background(ImageLoader.startImage);
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.fill(Color.black.getRGB());
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("FBLA-Dash", 640, 100);
		
		drawer.textSize(25);
		drawer.text("Instructions.", 640, 500);
	}
}
