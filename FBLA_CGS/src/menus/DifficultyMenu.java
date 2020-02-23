package menus;
import java.awt.Color;
import java.nio.FloatBuffer;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
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
	
	private Button f, b, l, a;
	private boolean[] unlocked;
	
	public DifficultyMenu() {
		f = new Button(165, 225, 200, 75, "Future", Color.BLACK, Color.PINK, Color.RED, Color.BLACK);
		b = new Button(415, 325, 200, 75, "Business", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK);
		l = new Button(665, 425, 200, 75, "Leader", Color.BLACK, GameScreen.LIGHT_BLUE, Color.CYAN, Color.BLACK);
		a = new Button(915, 525, 200, 75, "America", Color.BLACK, Color.PINK, Color.RED, Color.BLACK);
		doButtons();
		unlocked = new boolean[3];
	}
	
	private void doButtons() {
		this.addButton(f);
		this.addButton(b);
		this.addButton(l);
		this.addButton(a);
		this.addButton(new Button(165, 550, 150, 50, "Back", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	}
	
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Future")) {
			gameScreen.changeMenuMode("f");
		} else if(buttonText.equals("Business")&&unlocked[0]) {
			gameScreen.changeMenuMode("b"); 
		} else if(buttonText.equals("Leader")&&unlocked[1]) {
			gameScreen.changeMenuMode("l");
		} else if(buttonText.equals("America")&&unlocked[2]) {
			gameScreen.changeMenuMode("a"); 
		} else if(buttonText.equals("Back")) {
			gameScreen.changeMenuMode("main");
		}
	}
	
	private void updateButtons(PApplet papplet) {
		GameScreen g = (GameScreen) papplet;
		int diff = g.getLevel();
		JSONObject json = papplet.loadJSONObject("data/levels.json");
		JSONArray levels = json.getJSONArray("levels");
		JSONObject two = (JSONObject)levels.get(4);
		JSONObject three = (JSONObject)levels.get(8);
		JSONObject four = (JSONObject)levels.get(12);
		
		unlocked[0] = two.getBoolean("unlocked");
		unlocked[1] = three.getBoolean("unlocked");
		unlocked[2] = four.getBoolean("unlocked");
		
		if(!unlocked[0]) {
			b.setFill(Color.LIGHT_GRAY);
			b.setHighlightFillColor(Color.LIGHT_GRAY);
		}
		if(!unlocked[1]) {
			l.setFill(Color.LIGHT_GRAY);
			l.setHighlightFillColor(Color.LIGHT_GRAY);
		}
		if(!unlocked[2]) {
			a.setFill(Color.LIGHT_GRAY);
			a.setHighlightFillColor(Color.LIGHT_GRAY);
		}
	}
	
	public void draw(PApplet drawer) {
//		drawer.background(ImageLoader.levelImage);
		updateButtons(drawer);
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Business Achievement Award Difficulty Select", 640, 100);
	}

}
