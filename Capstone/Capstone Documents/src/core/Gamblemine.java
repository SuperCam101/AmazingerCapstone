package core;
import processing.core.PApplet;

/**
 * Helps set up the gamblemine game
 */
public class Gamblemine {

	/**
	 * The main method which helps set up the program.
	 * @param args
	 */
	public static void main(String[] args) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		drawing.windowResizable(true);

	}


}
