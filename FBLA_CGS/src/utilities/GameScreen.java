package utilities;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashSet;
import menus.*;
import objects.World;
import processing.core.PApplet;
import processing.data.JSONObject;

/**
 * 
 * @author Navaneet Kadaba
 * 
 *         The GameScreen class is the class that enables the GUI. It uses the
 *         Java Processing library in order to perform various actions such as
 *         drawing, moving, scrolling, and animating. Quite simply, GameScreen
 *         is the "backbone" of the game.
 *
 */
public class GameScreen extends PApplet {
	
	
	public final float ORIGINAL_WIDTH = 1280, ORIGINAL_HEIGHT = 720;
	public final static Color LIGHT_BLUE = new Color(135,206,235);
	
	private int diffNum, lvlNum, currentWidth, currentHeight;;
	private Menu currentMenu, startMenu, difficultyMenu, levelMenu, store, scoreboard;
	private Rectangle mouseP;
	
	private World world;
	private boolean[] keys;

	/**
	 * Creates the screen which contains the game
	 */
	public GameScreen() {
		startMenu = new StartMenu();
		difficultyMenu = new DifficultyMenu();
		levelMenu = new LevelMenu();
		scoreboard = new Scoreboard();
		store = new Store();
		mouseP = new Rectangle(0, 0, 1, 1);
		diffNum = 0;
		lvlNum = 0;	
		currentMenu = startMenu;
		currentWidth = width;
		currentHeight = height;
		keys = new boolean[1];
	}

	/**
	 * Sets up the game's screen.
	 */
	public void setup() {
		// surface.setResizable(true);
		this.frameRate(60);
		ImageLoader.loadAllImages(this);
		Store s = (Store) store;
		s.setup(this);
	}

	/**
	 * Sets the size of the screen.
	 */
	public void settings() {
		 size(1280, 720);
//		size(800, 600, FX2D);
		// size(800, 600, P2D);
//		 this.getSurface().setResizable(true);
		// fullScreen(P2D);
	}

	/**
	 * Draws the content that is displayed on the screen.
	 */
	public void draw() {

		if (width != currentWidth || height != currentHeight) {
			ImageLoader.resizeImages(this);
			currentWidth = width;
			currentHeight = height;
		}

		scale(width / ORIGINAL_WIDTH, height / ORIGINAL_HEIGHT);
		// System.out.println(width + " " + height);
		// System.out.println(displayWidth + " " + displayHeight);
		background(Color.WHITE.getRGB());
		
		if (currentMenu != null) {
			currentMenu.draw(this);
		} else {
			if (keys[0]) {
				world.jump();
			}
			world.draw(this);
			keys[0] = false;
		}
	}
	
	/**
	 * Determines what happens when a key is pressed on the keyboard.
	 */
	public void keyPressed() {
		if(currentMenu == null) {
			if (keyCode == UP) {
				keys[0] = true;
			} else if (key == 'r') {
				world.resetPlayer();
			}  else if (key == 'p') {
				world.pause();
			}
		}
	}
	
	/**
	 * Determines what happens when the mouse is pressed.
	 */
	public void mousePressed() {
		if (currentMenu != null) {
			String buttonText = currentMenu.checkIfButtonsPressed((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));

			if (buttonText == null) {
				return;
			}
			currentMenu.doButtonAction(buttonText, this);
		}
	}
	
	/**
	 * Determines what happens when the mouse is clicked.
	 */
	public void mouseClicked() {
		mouseP.setLocation((int) (mouseX / (width / ORIGINAL_WIDTH)), (int) (mouseY / (height / ORIGINAL_HEIGHT)));
		if(currentMenu == null) {
			world.mouseClicked(this, mouseX, mouseY);
		}
	}
	
	/**
	 * Determines what happens when the mouse is moved.
	 */
	public void mouseMoved() {
		if (currentMenu != null) {
			currentMenu.updateButtons((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
		}
	}

	/**
	 * Changes what is shown to the screen
	 * @param menumode what should be shown to the screen
	 */
	public void changeMenuMode(String menumode) {
		if(menumode.equals("main")) {
			currentMenu = startMenu;
		} else if (menumode.equals("difficulty")) {
			currentMenu = difficultyMenu;
		} else if (menumode.equals("store")) {
			currentMenu = store;
		} else if (menumode.equals("scoreboard")) {
			currentMenu = scoreboard;
		} else if(menumode.equals("f")) {
			lvlNum = 1;
			currentMenu = levelMenu;
		} else if(menumode.equals("b")) {
			lvlNum = 2;
			currentMenu = levelMenu;
		} else if(menumode.equals("l")) {
			lvlNum = 3;
			currentMenu = levelMenu;
		} else if(menumode.equals("a")) {
			lvlNum = 4;
			currentMenu = levelMenu;
		} else if(menumode.equals("1")) {
			diffNum = 1;
			currentMenu = null;
			makeWorld();
		} else if(menumode.equals("2")) {
			diffNum = 2;
			currentMenu = null;
			makeWorld();
		} else if(menumode.equals("3")) {
			diffNum = 3;
			currentMenu = null;
			makeWorld();
		} else if(menumode.equals("4")) {
			diffNum = 4;
			currentMenu = null;
			makeWorld();
		} else if (menumode.equals("quit")) {
			exit();
			System.exit(0);
		} else {
			currentMenu = null;
		}
		this.mouseMoved();
	}
	
	private void makeWorld() {
		Store s = (Store) store;
		world = new World(this, s.getSelectedImage(), lvlNum, diffNum);
	}
	
	/**
	 * Gives the level that the user selected.
	 * @return the level number that the user selected.
	 */
	public int getLevel() {
		return lvlNum;
	}
}
