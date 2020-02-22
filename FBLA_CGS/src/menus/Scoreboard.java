package menus;

import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;
import utilities.ImageLoader;

public class Scoreboard extends Menu {

	public Scoreboard() {
		this.addButton(new Button(540, 525, 200, 75, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if (buttonText.equals("Main Menu")) {
			gameScreen.changeMenuMode("main");
		}
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Scoreboard", 640, 100);

	}

}

