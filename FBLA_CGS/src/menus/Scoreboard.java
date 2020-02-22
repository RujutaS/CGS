package menus;

import java.awt.Color;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import processing.core.PApplet;
import utilities.GameScreen;

public class Scoreboard extends Menu {
	
	JSONParser parser;
	int[] highScores;

	public Scoreboard() {
		this.addButton(new Button(540, 525, 200, 75, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	
		highScores = new int[16];
		
		try {
			parser = new JSONParser();

			JSONObject jsonObject = (JSONObject)parser.parse(new FileReader("data/levels.json"));
			JSONArray levelsArray = (JSONArray) jsonObject.get("levels");
			
			for (int i = 0; i < levelsArray.size(); i++) {
				JSONObject level = (JSONObject)levelsArray.get(i);
				highScores[i] = Math.toIntExact((long)level.get("high score"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		drawer.text("Scoreboard", 640, 50);
		drawer.textSize(20);
		for (int i = 0; i < 16; i++) {
			drawer.text("" + highScores[i], 640, 100 + 20 * i);
		}

	}

}

