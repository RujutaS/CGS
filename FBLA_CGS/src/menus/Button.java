package menus;
import java.awt.Color;
import processing.core.PApplet;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The Button class is a convenient element of GUI that 
 * enables the user in a game to choose their game mode,
 * adjust the game settings, pause, and quit. 
 *
 */
public class Button {

	private float x, y, width, height;
	private String text;
	private Color edgeColor, fillColor, highlightFillColor, textColor, currentColor;

	/**
	 * Creates a button
	 * @param x the x coordinate of the button
	 * @param y the y coordinate of the button
	 * @param width the width of the button
	 * @param height the width of the button
	 * @param text the text of the button
	 * @param edgeColor the color of the edge of the button
	 * @param fillColor the background color of the button
	 * @param highlightFillColor the color of the button when the mouse is hovering over the button
	 * @param textColor the color of the text in the button
	 */
	public Button(float x, float y, float width, float height, String text, Color edgeColor, Color fillColor,
			Color highlightFillColor, Color textColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.edgeColor = edgeColor;
		this.fillColor = fillColor;
		this.highlightFillColor = highlightFillColor;
		this.textColor = textColor;
		currentColor = fillColor;
	}
	
	/**
	 * Draws the button to the screen
	 * @param drawer the PApplet the button is drawn to 
	 */
	public void draw(PApplet drawer) {
		drawer.rectMode(PApplet.CORNER);
		drawer.fill(currentColor.getRGB());
		drawer.stroke(edgeColor.getRGB());
		drawer.rect(x, y, width, height, 10);
		drawer.fill(textColor.getRGB());
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text(text, x + width / 2, y + height / 2);
	}

	/**
	 * Determines is the mouse is hovering over the button
	 * @param mouseX the x coordinate of the button
	 * @param mouseY the y coordinate of the button
	 * @return true if the mouse is hovering over the button
	 */
	public boolean mouseOver(int mouseX, int mouseY) {
		return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
	}
	
	/**
	 * Changes the background color of the button 
	 * @param fill the new background color
	 */
	public void setFill( Color fill ) 
	{
		fillColor  = fill;
	}
	
	/**
	 * Changes the highlight color of the button 
	 * @param highlight the new highlight color
	 */
	public void setHighlightFillColor(Color highlight) {
		highlightFillColor = highlight;
	}

	/**
	 * Gives the text of the button
	 * @return the text of the button
	 */
	public String getText() {
		return text;
	}

	/**
	 * Updates the color of the button based on the location of the mouse
	 * @param mouseX the x coordinate of the mouse
	 * @param mouseY the y coordinate of the mouse
	 */
	public void update(int mouseX, int mouseY) {
		if (mouseOver(mouseX, mouseY)) {
			currentColor = highlightFillColor;
		} else {
			currentColor = fillColor;
		}
	}

}
