package utilities;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The ImageLoader class is a static class that
 * provides a convenient and centralized way to 
 * load image (png) files. This helps in executing
 * the program faster, as well as eliminating the
 * issue of lagging.
 *
 */
public class ImageLoader {
	
	public static PImage mario, luigi, yoshi;
	public static PImage[] characters;
	
	/**
	 * Loads all images beforehand to prevent lag during the game.
	 * @param p The PApplet which will load the images
	 */
	public static void loadAllImages(PApplet p) {
		mario = p.loadImage("mario.png");
		mario.resize(1280, 720);	
		luigi = p.loadImage("luigi.png");
		luigi.resize(1280, 720);	
		yoshi = p.loadImage("yoshi.png");
		yoshi.resize(1280, 720);
		characters = new PImage[] {mario, luigi, yoshi};
	}
	
	/**
	 * Makes sure that images are updated when the window is resized.
	 * @param p The PApplet which will resize the images
	 */
	public static void resizeImages(PApplet p){
		mario.resize(p.width, p.height);
		luigi.resize(p.width, p.height);
		yoshi.resize(p.width, p.height);
	}

}
