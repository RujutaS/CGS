package samarthshah.objects;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import samarthshah.obstacles.Ending;
import samarthshah.obstacles.Obstacle;
import samarthshah.obstacles.Platform;
import samarthshah.obstacles.Question;
import samarthshah.obstacles.Spike;
import samarthshah.surface.DrawingSurface;

public class World {

	private Player player;
	private Level level;
	private int x, speed;
	private PApplet p;
	private DrawingSurface surface;
	private boolean running, won;
	private int questionNumber;
	private PImage heart, background;
	private String question;
	private Color c1, c2;
	private Rectangle2D.Double button1, button2;
	private JSONParser parser;


	public World(PApplet applet, DrawingSurface sur, Color c1, Color c2, PImage playerImage, int levelNumber, int difficulty) {
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
		surface = sur;
		running = true;
		won = false;
		questionNumber = 1;
		x = 0;
		this.c1 = c1;
		this.c2 = c2;

		button1 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 80, p.width/4, 70);
		button2 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 190, p.width/4, 70);

		heart = applet.loadImage("heart.png");

		player = new Player(100, 100, difficulty, playerImage, 30, 30, this);

				try {
					parser = new JSONParser();
					ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
					int length = 0;
		
					JSONObject jsonObject = (JSONObject)parser.parse(new FileReader("res/level" + levelNumber + ".json"));
					JSONArray obstaclesArray = (JSONArray) jsonObject.get("obstacles");
					
					for (int i = 0; i < obstaclesArray.size(); i++) {
						JSONObject obstacle = (JSONObject) obstaclesArray.get(i);
						String type = (String)obstacle.get("type");
						
						if (type.equals("Platform")) {
							long x = (long)obstacle.get("x");
							long y = (long)obstacle.get("y");
							long w = (long)obstacle.get("w");
							long h = (long)obstacle.get("h");
		
							Platform p = new Platform(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h), applet);
							obstacles.add(p);
						} else if (type.equals("Ending")) {
							long x = (long)obstacle.get("x");
							long y = (long)obstacle.get("y");
							long w = (long)obstacle.get("w");
							long h = (long)obstacle.get("h");
		
							Ending e = new Ending(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h), applet);
							obstacles.add(e);
							length = Math.toIntExact(x)+Math.toIntExact(w);
						} else if (type.equals("Spike")) {
							long x = (long)obstacle.get("x");
							long y = (long)obstacle.get("y");
							long l = (long)obstacle.get("l");
							boolean up = (boolean)obstacle.get("up");
		
							Spike s = new Spike(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(l), up, applet);
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
		
							Question q = new Question(question, a, Math.toIntExact(c), Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(w), Math.toIntExact(h), applet);
							obstacles.add(q);
						} else if (type.equals("Background")) {
							this.background = applet.loadImage((String)obstacle.get("path"));
							background.resize(applet.width, applet.height);
						}
					}
				
					level = new Level(obstacles, null, length, 0);
					
		
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

	public void draw(PApplet p) {
		p.background(background);
		
		button1 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 80, p.width/4, 70);
		button2 = new Rectangle2D.Double(p.width/2-x, PConstants.BOTTOM + 190, p.width/4, 70);

		p.text(question, p.width/2 - 200, 20);
		for (int i = 0; i < player.getLives(); i++) {
			p.image(heart, p.width-100 + 30*i, 5, 20, 20);
		}


		if (running && !won) {
			if (player.getX() >= surface.displayWidth/2) {
				x -= speed;
			}
			player.act(level.getObstacles());
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

	public void jump() {
		player.jump();
	}

	public void resetWorld() {
		x = 0;
	}

	public void resetPlayer() {
		player.reset();
		x = 0;
		questionNumber = 0;
		this.nextQuestion();
	}

	public void pause( ) {
		running = !running;
	}

	public void win() {
		won = true;
	}

	public void nextQuestion() {		
		questionNumber++;

		ArrayList<Obstacle> obstacles = level.getObstacles();

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

	public void mouseClicked(int x, int y) {
		if (!running || won) {
			if (button1.contains(x - this.x + button1.width/2, y + button1.getHeight()/2)) {

			} else if (button2.contains(x - this.x + button1.width/2, y + button1.getHeight()/2)) {
				System.exit(0);
			}
		}
	}


}
