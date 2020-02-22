package menus;

import java.util.ArrayList;
import processing.core.PApplet;
import utilities.GameScreen;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The Menu class is responsible for providing certain 
 * menus such as the StartMenu, PauseMenu, and DeathMenu 
 * to the user, which are responsible for giving the user
 * a specific list of options.
 *
 */
public abstract class Menu{
	
	private ArrayList<Button> buttons;
	
	/**
	 * Creates the menu screen
	 */
	public Menu() {
		 buttons = new ArrayList<Button>();
	}
	
	/**
	 * Draws all the buttons in the menu
	 * @param drawer the PApplet to draw the buttons to
	 */
	public void draw(PApplet drawer) {
		//drawer.background(Color.WHITE.getRGB());
		for(Button b:buttons) {
			b.draw(drawer);
		}
	}
	
	/**
	 * Adds a button to the menu
	 * @param b the button to add
	 */
	public void addButton(Button b) {
		buttons.add(b);
	}
	
	/**
	 * Removes a button to the menu
	 * @param b the button to remove
	 */
	public void removeButton(Button b) {
		buttons.remove(b);
	}
	
	/**
	 * Controls the hover state of the button
	 * @param mouseX the x coordinate of the mouse
	 * @param mouseY the y coordinate of the mouse
	 * @return the text of the button the mouse is hovering over, or null if no button is there
	 */
	public String checkIfButtonsPressed(int mouseX, int mouseY) {
		for(Button b:buttons) {
			if (b.mouseOver(mouseX, mouseY)) {
				return b.getText();
			}
		}
		return null;
	}
	
	/**
	 * Updates all the buttons' colors
	 * @param mouseX the x coordinate of the mouse
	 * @param mouseY the y coordinate of the mouse
	 */
	public void updateButtons(int mouseX, int mouseY) {
		for(Button b:buttons) {
			b.update(mouseX, mouseY);
		}
	}
	
	/**
	 * Assigns the action taken when a button is clicked
	 * @param buttonText the text of the button chosen
	 * @param gameScreen the whole screen of the game
	 */
	public abstract void doButtonAction(String buttonText, GameScreen gameScreen);
}
