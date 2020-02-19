package samarthshah.surface;

import processing.core.PApplet;
import processing.core.PImage;
import samarthshah.objects.World;


public class DrawingSurface extends PApplet {
	
	World world;
	boolean[] keys;

	public DrawingSurface() {
		keys = new boolean[1];
	}
	
	public void setup() {
		
		PImage playerImage = this.loadImage("jerry.jpg");

		world = new World(this, playerImage, 1, 3);
	}
	
	public void draw() {	
		if (keys[0]) {
			world.jump();
		}
		world.draw(this);
		keys[0] = false;
	}
	
	
	public void keyPressed() {
		if (keyCode == UP) {
			keys[0] = true;
		} else if (key == 'r') {
			world.resetPlayer();
		}  else if (key == 'p') {
			world.pause();
		}
	}
	
	public void mouseClicked() {
		world.mouseClicked(mouseX, mouseY);
	}
	
	
}

