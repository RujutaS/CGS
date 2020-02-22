package objects;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import obstacles.Ending;
import obstacles.Obstacle;
import obstacles.Platform;
import obstacles.Question;
import obstacles.Spike;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import utilities.GameScreen;

/**
 * The class that holds the player and the level and manages how they interact, in essence, the game runner
 * 
 * @author Samarth Shah
 *
 */
public class World {

	private Player player;
	private Level level;
	private int x, speed, levelNumber, difficulty, ending, coinsWon;
	private PApplet p;
	private boolean running, won;
	private int questionNumber;
	private PImage heart, background;
	private String question;
	private Color c1, c2;
	private Rectangle2D.Double button1, button2;
	private JSONParser parser;


	/** Creates a new world with the given parameters
	 * 
	 * @param applet The PApplet that the world is in
	 * @param playerImage The image to be used for the player
	 * @param levelNumber The level number that was selected by the player
	 * @param difficulty The difficulty that was selected by the player
	 */
	public World(PApplet applet, PImage playerImage, int levelNumber, int difficulty) {
		
		
		p = applet;
		if (difficulty == 1) {
			speed = 3;
		} else if (difficulty == 2) {
			speed = 4;
		} else if (difficulty == 3) {
			speed = 5;
		} else if (difficulty == 4) {
			speed = 6;
		}
		running = true;
		won = false;
		questionNumber = 1;
		x = 0;
		this.levelNumber = levelNumber;
		this.difficulty = difficulty;

		button1 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 80, p.width/4, 70);
		button2 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 190, p.width/4, 70);

		heart = applet.loadImage("heart.png");

		player = new Player(100, 100, difficulty, playerImage, 30, 30, this);

		//Creates the world by getting the data from a json file based off the difficulty and level number
		try {
			parser = new JSONParser();
			ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

			JSONObject jsonObject = (JSONObject)parser.parse(new FileReader("data/levels/level" + levelNumber + difficulty + ".json"));

			JSONArray color1Array = (JSONArray) jsonObject.get("color1");
			c1 = new Color(Math.toIntExact((long)color1Array.get(0)), Math.toIntExact((long)color1Array.get(1)), Math.toIntExact((long)color1Array.get(2)));

			JSONArray color2Array = (JSONArray) jsonObject.get("color2");
			c2 = new Color(Math.toIntExact((long)color2Array.get(0)), Math.toIntExact((long)color2Array.get(1)), Math.toIntExact((long)color2Array.get(2)));


			JSONArray obstaclesArray = (JSONArray) jsonObject.get("obstacles");

			for (int i = 0; i < obstaclesArray.size(); i++) {
				JSONObject obstacle = (JSONObject) obstaclesArray.get(i);
				String type = (String)obstacle.get("type");

				if (type.equals("Platform")) {
					long x = (long)obstacle.get("x");
					long y = (long)obstacle.get("y");
					long w = (long)obstacle.get("w");
					long h = (long)obstacle.get("h");

					Platform p = new Platform(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h));
					obstacles.add(p);
				} else if (type.equals("Ending")) {
					long x = (long)obstacle.get("x");
					long y = (long)obstacle.get("y");
					long w = (long)obstacle.get("w");
					long h = (long)obstacle.get("h");

					Ending e = new Ending(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h));
					obstacles.add(e);
					ending = Math.toIntExact(x) + Math.toIntExact(w);
				} else if (type.equals("Spike")) {
					long x = (long)obstacle.get("x");
					long y = (long)obstacle.get("y");
					long l = (long)obstacle.get("l");
					boolean up = (boolean)obstacle.get("up");

					Spike s = new Spike(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(l), up);
					obstacles.add(s);
				} else if (type.equals("Question")) {
					String question = (String)obstacle.get("q");

					String[] a = new String[3];
					JSONArray questionArray = (JSONArray)obstacle.get("a");
					for (int j = 0; j < questionArray.size(); j++) {
						a[j] = (String)questionArray.get(j);
					}

					long c = (long)obstacle.get("c");
					long x = (long)obstacle.get("x");
					long y = (long)obstacle.get("y");
					long w = (long)obstacle.get("w");
					long h = (long)obstacle.get("h");

					Question q = new Question(question, a, Math.toIntExact(c), Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h));
					obstacles.add(q);
				} else if (type.equals("Background")) {
					this.background = applet.loadImage((String)obstacle.get("path"));
					background.resize(applet.width, applet.height);
				}
			}

			level = new Level(obstacles, applet.width, 0);


			for (Obstacle o : obstacles) {
				if (o.getClass().getName().equals("samarthshah.obstacles.Question")) {
					Question questionObject = (Question)(o);
					question = questionObject.getQuestion();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Draws the world and everything in it
	 * 
	 * @param p The PApplet to draw onto
	 */
	public void draw(PApplet p) {
		p.background(background);

		button1 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 80, p.width/4, 70);
		button2 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 190, p.width/4, 70);

		p.pushStyle();
		p.textSize(15);
		p.textAlign(PConstants.CENTER, PConstants.BOTTOM);
		p.text(question, p.width/2 , 25);
		p.popStyle();
		for (int i = 0; i < player.getLives(); i++) {
			p.image(heart, p.width-100 + 30*i, 5, 20, 20);
		}

		level.refresh(x, p.width);

		if (running && !won) {
			ArrayList<Obstacle> obstacles = level.getObstacles();
			if (player.getX() >= p.width/2 && ending + x > p.width) {
				x -= speed;
			}
			player.act(obstacles);
		}

		p.translate(x, 0);

		p.pushStyle();
		p.fill(c1.getRed(), c1.getGreen(), c1.getBlue());
		p.stroke(c2.getRed(), c2.getGreen(), c2.getBlue());


		player.draw(p);
		level.draw(p);

		p.popStyle();

		if (won) {
			p.pushStyle();

			p.fill(100, 200);
			p.rect(-x, 0, p.width, p.height);

			p.fill(255);
			p.textSize(40);
			p.textAlign(PConstants.CENTER, PConstants.BOTTOM);
			p.text("You Won", p.width/2 - x, 100);

			p.fill(200);
			p.rectMode(PConstants.CENTER);
			p.rect((float)button1.getX(), (float)button1.getY(), (float)button1.getWidth(), (float)button1.getHeight());
			p.fill(0);
			p.text("Main Menu", p.width/2-x, PConstants.BOTTOM + 100);

			p.fill(200);
			p.rect((float)button2.getX(), (float)button2.getY(), (float)button2.getWidth(), (float)button2.getHeight());
			p.fill(0);
			p.text("Exit Game", p.width/2-x, PConstants.BOTTOM + 210);

			p.fill(255);
			p.textSize(40);
			p.textAlign(PConstants.CENTER, PConstants.BOTTOM);
			p.text("You have earned " + coinsWon + " Coins", p.width/2 - x, 500);


			p.popStyle();
		} else if (!running) {
			p.pushStyle();

			p.fill(100, 200);
			p.rect(-x, 0, p.width, p.height);

			p.fill(255);
			p.textSize(40);
			p.textAlign(PConstants.CENTER, PConstants.BOTTOM);
			p.text("Game Paused", p.width/2 - x, 100);

			p.fill(200);
			p.rectMode(PConstants.CENTER);
			p.rect((float)button1.getX(), (float)button1.getY(), (float)button1.getWidth(), (float)button1.getHeight());
			p.fill(0);
			p.text("Main Menu", p.width/2-x, PConstants.BOTTOM + 100);

			p.fill(200);
			p.rect((float)button2.getX(), (float)button2.getY(), (float)button2.getWidth(), (float)button2.getHeight());
			p.fill(0);
			p.text("Exit Game", p.width/2-x, PConstants.BOTTOM + 210);

			p.popStyle();
		}
	}

	/** Makes the player jump
	 * 
	 */
	public void jump() {
		player.jump();
	}

	/** Resets the scroll of the level
	 * 
	 */
	public void resetWorld() {
		x = 0;
		questionNumber = 0;
		this.nextQuestion();
	}

	/** Resets the player and the world to its original place
	 * 
	 */
	public void resetPlayer() {
		player.reset();
		x = 0;
		questionNumber = 0;
		this.nextQuestion();
	}

	/** Pauses the game
	 * 
	 */
	public void pause( ) {
		running = !running;
	}

	/** Called when the player has reached the end of the level
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void win() {
		won = true;

		try {
			JSONObject coinsJsonObject = (JSONObject)parser.parse(new FileReader("data/store.json"));
			int coins = Math.toIntExact((long)coinsJsonObject.get("coins"));
			int coinsEarned = 10 + 5 * (2 + player.getLives());
			coinsWon = coinsEarned;
			coinsJsonObject.put("coins", coins + coinsEarned);

			try (FileWriter file = new FileWriter("data/store.json")) {
				file.write(coinsJsonObject.toJSONString());
			}

			JSONObject levelsJSONObject = (JSONObject)parser.parse(new FileReader("data/levels.json"));
			JSONArray levelsArray = (JSONArray) levelsJSONObject.get("levels");

			for (int i = 0; i < levelsArray.size(); i++) {
				JSONObject levelJSONObject = (JSONObject)(levelsArray.get(i));
				if (levelJSONObject.get("number").toString().equals("" + levelNumber + difficulty)) {
					int highScore = Math.toIntExact((long)levelJSONObject.get("high score"));
					if (highScore < coinsEarned) {
						levelJSONObject.put("high score", coinsEarned);
					}
					JSONObject nextLevel = (JSONObject)levelsArray.get(i+1);
					nextLevel.put("unlocked", true);
				}
			}

			try (FileWriter file = new FileWriter("data/levels.json")) {
				file.write(levelsJSONObject.toJSONString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Changes the question in the top bar to the next one
	 * 
	 */
	public void nextQuestion() {	
				
		questionNumber++;

		ArrayList<Obstacle> obstacles = level.getAllObstacles();

		int i = 1;

		for (Obstacle o : obstacles) {
			if (o.getClass().getName().equals("samarthshah.obstacles.Question")) {

				if (i == questionNumber) {
					Question questionObject = (Question)(o);
					question = questionObject.getQuestion();
					break;
				}

				i++;

			}
		}

	}

	/** Checks if the mouse button was clicked on one of the buttons
	 * 
	 * @param g The window
	 * @param x The x value of the click
	 * @param y THe y value of the click
	 */
	public void mouseClicked(GameScreen g, int x, int y) {
		if (!running || won) {
			if (button1.contains(x - this.x + button1.width/2, y + button1.getHeight()/2)) {
				g.changeMenuMode("main");
			} else if (button2.contains(x - this.x + button1.width/2, y + button1.getHeight()/2)) {
				System.exit(0);
			}
		}
	}


}
