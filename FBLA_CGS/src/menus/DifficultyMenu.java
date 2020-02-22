package menus;
import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * This Menu is displayed before the user can play the game
 * so that he or she can choose one of the three levels 
 * offered.
 *
 */
public class DifficultyMenu extends Menu {
	
	public DifficultyMenu() {
		doButtons();
	}
	
	private void doButtons() {
		this.addButton(new Button(165, 225, 200, 75, "Future", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
		this.addButton(new Button(415, 325, 200, 75, "Business", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		this.addButton(new Button(665, 425, 200, 75, "Leader", Color.BLACK, GameScreen.LIGHT_BLUE, Color.CYAN, Color.BLACK));
		this.addButton(new Button(915, 525, 200, 75, "America", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
		this.addButton(new Button(165, 550, 150, 50, "Back", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	}
	
	
	
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Future")) {
			gameScreen.changeMenuMode("f");
		} else if(buttonText.equals("Business")) {
			gameScreen.changeMenuMode("b"); 
		} else if(buttonText.equals("Leader")) {
			gameScreen.changeMenuMode("l");
		} else if(buttonText.equals("America")) {
			gameScreen.changeMenuMode("a"); 
		} else if(buttonText.equals("Back")) {
			gameScreen.changeMenuMode("main");
		}
	}
	
	public void draw(PApplet drawer) {
//		drawer.background(ImageLoader.levelImage);
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Business Achievement Award Difficulty Select", 640, 100);
	}

}
