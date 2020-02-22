package samarthshah.objects;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import samarthshah.obstacles.Answer;
import samarthshah.obstacles.Obstacle;
import samarthshah.obstacles.Platform;
import samarthshah.obstacles.Question;
import samarthshah.shapes.Rect;

/** 
 * The class that holds the data for the player in the game
 * 
 * @author Samarth Shah
 *
 */
@SuppressWarnings("serial")
public class Player extends Rectangle2D.Double {

	private int lives;
	private boolean onASurface;
	private final int initialX, initialY;
	private double vx, vy, ay;
	private PImage skin;
	private boolean answeredQuestion;
	private World world;
	private boolean isRocket;

	/** Creates a new player with the given parameters
	 * 
	 * @param x An int with the initial x value of the player
	 * @param y An int with the initial y value of the player
	 * @param difficulty An int with the difficulty selected, changing how fast the player moves
	 * @param image An image that is the selected player of the player
	 * @param w An int with the width of the player
	 * @param h An int with the height of the player
	 * @param world The world that the player is in
	 */
	public Player(int x, int y, int difficulty, PImage image, int w, int h, World world) {		
		super(x, y, w, h);

		if (difficulty == 1) {
			vx = 3;
		} else if (difficulty == 2) {
			vx = 4;
		} else if (difficulty == 3) {
			vx = 5;
		} else if (difficulty == 4) {
			vx = 6;
		}
		ay = 0.7/5*vx;
		onASurface = false;
		initialX = x;
		initialY = y;
		lives = 3;
		vy = 0;
		skin = image;
		answeredQuestion = false;
		this.world = world;
		isRocket = false;
	}

	/** Draws the player
	 * 
	 * @param p The papplet to draw onto
	 */
	public void draw(PApplet p) {

		p.rect((int)x, (int)y, (int)super.width, (int)super.height);
		if (skin != null) {
			p.image(skin, (int)x, (int)y, (int)width, (int)height);
		}
	}

	/** Checks for collisions and acts based off that, which could be making the user restart or be on a platform
	 * 
	 * @param obstacles An arraylist with all of the obstacles that the player could have collided with
	 */
	public void act(ArrayList<Obstacle> obstacles) {		
		
		if (lives <= 0) {
			reset();
		}
		
		ay = 0.7;

		vy += ay;
		double y2 = y + vy;

		boolean inQuestion = false;
		boolean cancelY = false;

		//A new rectangle with where the player with travel in the y direction in this frame to check if it collides with anything
		Rectangle2D.Double strechY = new Rectangle2D.Double(x,Math.min(y,y2),width,height+Math.abs(vy));

		onASurface = false;
		isRocket = false;

		//If the player is moving downward used to check if it is on the ground or hitting its head
		if (vy > 0) {

			Obstacle standingSurface = null;
			for (Obstacle o : obstacles) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						onASurface = true;
						standingSurface = o;
						vy = 0;
					}
				} else if (o.getClass().getName().equals("samarthshah.obstacles.Spike")) {
					float[] params = (o.getBounds());

					if (params[3] == 1) {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], (params[1] - width), params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1] - params[2], width, params[2]);	

						if (rect.intersects(strechY) || rect2.intersects(strechY)) {
							reset();
							return;
						}
					} else {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1], width, params[2]);	

						if (rect.intersects(strechY) || rect2.intersects(strechY)) {
							reset();
							return;
						}
					}


				} else if (o.getClass().getName().equals("samarthshah.obstacles.Question")) {
					Question q = (Question)o;

					Platform[] dividers = q.getDividers();
					ArrayList<Platform> dividerList = new ArrayList<Platform>();

					for (Platform p : dividers) {
						dividerList.add(p);
					}
					if(this.checkPlatforms(dividerList)) {
						cancelY = true;
					}
					
					Rect[] rects = q.getOuters();
					Rectangle2D.Double rect1 = new Rectangle2D.Double(rects[0].getX(), rects[0].getY(), rects[0].getWidth(), rects[0].getHeight());
					Rectangle2D.Double rect2 = new Rectangle2D.Double(rects[1].getX(), rects[1].getY(), rects[1].getWidth(), rects[1].getHeight());
					
					if (rect1.intersects(strechY) || rect2.intersects(strechY)) {
						isRocket = true;
					}

					Answer[] answers = q.getAnswers();

					for (Answer a: answers) {
						float[] params = a.getBounds();
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);
						if (rect.intersects(strechY)) {

							inQuestion = true;

							if (!answeredQuestion) {

								answeredQuestion = true;

								if (!a.isCorrect()) {
									this.loseLife();
								}
							}
						}
					}
				}  else if (o.getClass().getName().equals("samarthshah.obstacles.Ending")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						world.win();
					}
				}
			}
			if (standingSurface != null) {
				float[] params = standingSurface.getBounds();
				y2 = params[1]-height;
			}
		} else if (vy < 0) {
			Obstacle headSurface = null;
			for (Obstacle o : obstacles) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						headSurface = o;
						vy = 0;
					}
				} else if (o.getClass().getName().equals("samarthshah.obstacles.Spike")) {
					float[] params = (o.getBounds());

					if (params[3] == 1) {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], (params[1] - width), params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1] - params[2], width, params[2]);	

						if (rect.intersects(strechY) || rect2.intersects(strechY)) {
							reset();
							return;
						}
					} else {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1], width, params[2]);	

						if (rect.intersects(strechY) || rect2.intersects(strechY)) {
							reset();
							return;
						}
					}


				} else if (o.getClass().getName().equals("samarthshah.obstacles.Question")) {
					Question q = (Question)o;

					Platform[] dividers = q.getDividers();
					ArrayList<Platform> dividerList = new ArrayList<Platform>();

					for (Platform p : dividers) {
						dividerList.add(p);
					}
					if(this.checkPlatforms(dividerList)) {
						cancelY = true;
					}
					
					Rect[] rects = q.getOuters();
					Rectangle2D.Double rect1 = new Rectangle2D.Double(rects[0].getX(), rects[0].getY(), rects[0].getWidth(), rects[1].getHeight());
					Rectangle2D.Double rect2 = new Rectangle2D.Double(rects[1].getX(), rects[1].getY(), rects[1].getWidth(), rects[1].getHeight());
					
					if (rect1.intersects(strechY) || rect2.intersects(strechY)) {
						isRocket = true;
					}

					Answer[] answers = q.getAnswers();

					for (Answer a: answers) {
						float[] params = a.getBounds();
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);
						if (rect.intersects(strechY)) {
							
							inQuestion = true;

							if (!answeredQuestion) {

								answeredQuestion = true;

								if (!a.isCorrect()) {
									this.loseLife();
								}
							}
						}
					}
				} else if (o.getClass().getName().equals("samarthshah.obstacles.Ending")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						world.win();
					}
				}
				if (headSurface != null) {
					float[] params = headSurface.getBounds();
					y2 = params[1] + params[3];
				}
			}
		}

		if (Math.abs(vy) < .5)
			vy = 0;

		double x2 = x + vx;

		//A new rectangle with where the player with travel in the x direction in this frame to check if it collides with anything
		Rectangle2D.Double strechX = new Rectangle2D.Double(x2,y2,width+Math.abs(vx),height);

		if (vx > 0) {
			Obstacle rightSurface = null;
			for (Obstacle o : obstacles) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {

					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechX)) {
						rightSurface = o;
					}
				} else if (o.getClass().getName().equals("samarthshah.obstacles.Spike")) {
					float[] params = (o.getBounds());

					if (params[3] == 1) {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], (params[1] - width), params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1] - params[2], width, params[2]);	

						if (rect.intersects(strechX) || rect2.intersects(strechX)) {
							reset();
							return;
						}
					} else {
						double width = params[2]/10;
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], width);
						Rectangle2D.Double rect2 = new Rectangle2D.Double(params[0] + params[2]/2 - width/2, params[1], width, params[2]);	

						if (rect.intersects(strechX) || rect2.intersects(strechX)) {
							reset();
							return;
						}
					}

				} else if (o.getClass().getName().equals("samarthshah.obstacles.Question")) {
					Question q = (Question)o;

					Platform[] dividers = q.getDividers();
					ArrayList<Platform> dividerList = new ArrayList<Platform>();

					for (Platform p : dividers) {
						dividerList.add(p);
					}
					this.checkPlatforms(dividerList);
					
					Rect[] rects = q.getOuters();
					Rectangle2D.Double rect1 = new Rectangle2D.Double(rects[0].getX(), rects[0].getY(), rects[0].getWidth(), rects[1].getHeight());
					Rectangle2D.Double rect2 = new Rectangle2D.Double(rects[1].getX(), rects[1].getY(), rects[1].getWidth(), rects[1].getHeight());
					
					if (rect1.intersects(strechX) || rect2.intersects(strechX)) {
						isRocket = true;
					}

					Answer[] answers = q.getAnswers();

					for (Answer a: answers) {
						float[] params = a.getBounds();
						Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);
						if (rect.intersects(strechX)) {
							

							inQuestion = true;

							if (!answeredQuestion) {

								answeredQuestion = true;

								if (!a.isCorrect()) {
									this.loseLife();
								}
							}
						}
					}
				} else if (o.getClass().getName().equals("samarthshah.obstacles.Ending")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechX)) {
						world.win();
					}
				}
			}
			if (rightSurface != null) {
				reset();
				return;
			}
		}
		
		if (!cancelY) {
			y = y2;
		}

		x = x2;
		
		//Moves onto the next question once the last one has been answered
		if (!inQuestion && answeredQuestion) {
			answeredQuestion = false;
			world.nextQuestion();
		}
	}
	
	/** Similar to checking collisions but specifically for platforms inside of question classes
	 * The main difference is that there is no change in y to make sure that the change in y is not done twice every time the draw method is called
	 * Only checks if the player hits the side of the platforms which would result in the player dying
	 * 
	 * @param platforms The array of platforms that are in a questions
	 * @return true if the player is on a platform so there should be no change in y
	 */
	private boolean checkPlatforms(ArrayList<Platform> platforms) {
		
		boolean cancelOtherYs = false;
		
		double y2 = y + vy;
		Rectangle2D.Double strechY = new Rectangle2D.Double(x,Math.min(y,y2),width,height+Math.abs(vy));
		
		if (vy > 0) {

			Obstacle standingSurface = null;
			for (Platform o : platforms) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						onASurface = true;
						standingSurface = o;
						vy = 0;
					}
				
				}
			}
			if (standingSurface != null) {
				float[] params = standingSurface.getBounds();
				y2 = params[1]-height;
				cancelOtherYs = true;
			}
		} else if (vy < 0) {
			Obstacle headSurface = null;
			
			for (Platform o : platforms) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {
					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechY)) {
						headSurface = o;
						vy = 0;
					}
				}
				if (headSurface != null) {
					float[] params = headSurface.getBounds();
					y2 = params[1] + params[3];
					cancelOtherYs = true;
				}
			}
		}

		double x2 = x + vx;

		Rectangle2D.Double strechX = new Rectangle2D.Double(x2,y2,width+Math.abs(vx),height);

		if (vx > 0) {
			Obstacle rightSurface = null;
			for (Platform o : platforms) {

				if (o.getClass().getName().equals("samarthshah.obstacles.Platform")) {

					float[] params = (o.getBounds());
					Rectangle2D.Double rect = new Rectangle2D.Double(params[0], params[1], params[2], params[3]);

					if (rect.intersects(strechX)) {
						rightSurface = o;
					}
				}
				if (rightSurface != null) {
					reset();
					return false;
				}
			}
		}

		y = y2;
		
		return cancelOtherYs;
	}

	/** Makes the player jump if it on a surface or in a question area
	 * 
	 */
	public void jump() {
		if (onASurface || isRocket) {
			vy = -10*(ay/0.7);
		}
	}

	/**
	 * 
	 * @return The number of lives that the player has
	 */
	public int getLives() {
		return lives;
	}

	/** Resets the player after it dies
	 * 
	 */
	public void reset() {
		x = initialX;
		y = initialY;
		vy = 0;
		ay = 0.7/5*vx;
		lives = 3;
		world.resetWorld();
	}

	/**
	 * @return The x value of the player
	 */
	public double getX() {
		return x;
	}
	
	/** Makes the player lose a life
	 * 
	 */
	private void loseLife() {
		lives--;
	}
}
