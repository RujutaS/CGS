package menus;

import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;

/**
 * 
 * @author Navaneet Kadaba
 * 
 *         This Menu is displayed before the user can play the game so that he
 *         or she can choose one of the three levels offered.
 *
 */
public class LevelMenu extends Menu {

	public LevelMenu() {
		doButtons();
	}

	private void doButtons() {
		this.addButton(new Button(40, 350, 200, 75, "1", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
		this.addButton(new Button(290, 350, 200, 75, "2", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		this.addButton(new Button(540, 350, 200, 75, "3", Color.BLACK, GameScreen.LIGHT_BLUE, Color.CYAN, Color.BLACK));
		this.addButton(new Button(790, 350, 200, 75, "4", Color.BLACK, Color.PINK, Color.RED, Color.BLACK));
		this.addButton(new Button(1040, 350, 200, 75, "5", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
		this.addButton(new Button(165, 550, 150, 50, "Back", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLACK));
	}

	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if (buttonText.equals("1")) {
			gameScreen.changeMenuMode("1");
		} else if (buttonText.equals("2")) {
			gameScreen.changeMenuMode("2");
		} else if (buttonText.equals("3")) {
			gameScreen.changeMenuMode("3");
		} else if (buttonText.equals("4")) {
			gameScreen.changeMenuMode("4");
		} else if (buttonText.equals("Back")) {
			gameScreen.changeMenuMode("difficulty");
		}
	}

	public void draw(PApplet drawer) {
		// drawer.background(ImageLoader.levelImage);
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Roboto", 20));
		drawer.textSize(40);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		String level;
		GameScreen g = (GameScreen) drawer;
		switch (g.getLevel()) {
		case 0:
			level = "Future Level Select";
			break;
		case 1:
			level = "Business Level Select";
			break;
		case 2:
			level = "Leader Level Select";
			break;
		case 3:
			level = "America Level Select";
			break;
		default:
			level = "Level Select";
		}
		drawer.text(level, 640, 100);
		
		drawer.fill(255);
		drawer.pushMatrix();
		drawer.translate(140, 250);
		drawer.rotate(drawer.frameCount / 69.0f);
		star(drawer, 0, 0, 30, 70, 5);
		drawer.popMatrix();
	}

	private void star(PApplet drawer, float x, float y, float radius1, float radius2, int npoints) {
		drawer.pushMatrix();
		float angle = PApplet.TWO_PI / npoints;
		float halfAngle = angle / 2.0f;
		drawer.beginShape();
		for (float a = 0; a < PApplet.TWO_PI; a += angle) {
			float sx = x + PApplet.cos(a) * radius2;
			float sy = y + PApplet.sin(a) * radius2;
			drawer.vertex(sx, sy);
			sx = x + PApplet.cos(a + halfAngle) * radius1;
			sy = y + PApplet.sin(a + halfAngle) * radius1;
			drawer.vertex(sx, sy);
		}
		drawer.endShape(PApplet.CLOSE);
		drawer.popMatrix();
	}

}
