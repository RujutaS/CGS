package menus;
import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;
import utilities.GameScreen;
import utilities.ImageLoader;


/**
 * 
 * @author Navaneet Kadaba & Rujuta Swadi
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
		drawer.textSize(50);
		drawer.fill(Color.black.getRGB());
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("BAA Dash", 640, 100);

		PImage logo = drawer.loadImage("fbla.png");
		PImage hhs = drawer.loadImage("hhs.png");
		PImage mario = drawer.loadImage("mario.png");
		
		logo.resize(150, 0);
		hhs.resize(150, 0);
		mario.resize(150, 0);
		
		drawer.image(logo, 50, 30);
		drawer.image(hhs, 120, 90);
		drawer.image(mario, 900, 500);
		
		drawer.textSize(15);
		drawer.triangle(1140, 680, 1150, (float)(680 - Math.sqrt(300)), 1160, 680);
		drawer.text("*to jump", 1150, 690);
		
		drawer.textSize(18);
		drawer.text("read the questions at the top", 640, 450);
		drawer.text("jump* to the correct answer", 640, 480);
		drawer.text("jump* to avoid spikes", 640, 510);
		drawer.text("receive coins & increase score by completeing levels", 640, 540);
		drawer.text("lose a life for an incorrect answer", 640, 570);
		drawer.text("buy new characters with coins", 640, 600);
		drawer.textSize(25);
		drawer.text("press PLAY to begin!", 640, 630);


	}




}
