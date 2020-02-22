package utilities;

import java.awt.Dimension;
import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The Main class is from where the program starts executing.
 * It has a GameScreen that controls the rest of the game actions
 * and conditions.
 *
 */

public class Main {

	/**
	 * The main method which starts the program.
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		
		GameScreen drawing = new GameScreen();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
				
//		window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		window.setSize(1280, 720);


		window.setMinimumSize(new Dimension(1280, 720));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
		
	}
}