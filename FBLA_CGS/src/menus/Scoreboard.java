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
		this.addButton(new Button(540, 575, 200, 75, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	
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
		
		drawer.textSize(30);
		drawer.text("Future", 640, 125);
		drawer.text("Business", 640, 225);
		drawer.text("Leader", 640, 325);
		drawer.text("America", 640, 425);
		
		drawer.textSize(25);
		
		
		for(int i = 1;i<=4;i++) {
			for(int j = 1;j<=4;j++) {
				drawer.text("Level "+j+": "+highScores[(j-1)*4+i-1], 265 + 250*(j-1), 175 + 100*(i-1));
			}
		}
	}

}

