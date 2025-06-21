package sprite;

import core.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *	Represents any object that moves and is affected by gravity
 */
public class Sprite extends Rectangle {

	// FIELDS
	private PImage image;
	private int w, h;
	private boolean tint;
//	private float r, g, b, a;
	
/**
 * Creates a Sprite object with a location and dimensions
 * @param x The X location of the Sprite
 * @param y The Y location of the Sprite
 * @param w The width of the Sprite
 * @param h The height of the Sprite
 */
	public Sprite(int x, int y, int w, int h) {
		this(null, x, y, w, h);
	}
/**
 * Creates a Sprite object with an image, location, and dimensions
 * @param img The image of the Sprite
 * @param x The X location of the Sprite
 * @param y The Y location of the Sprite
 * @param w The width of the Sprite
 * @param h The height of the Sprite
 */
	public Sprite(PImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
		image = img;
		this.w = w;
		this.h = h;
		tint = false;
//		r= 0;
//		g=0;
//		b=0;
//		a=0;
	}

	/**
	 * Moves the sprite to x and y location given in the parameter
	 * @param x X location for the Sprite to be moved to
	 * @param y Y location for the Sprite to be moved to
	 */
	public void moveToLocation(double x, double y) {
		super.setX(x);
		super.setY(y);
	}

	/**
	 * Moves the sprite by x and y amount given in the parameter
	 * @param x X amount for the Sprite to be moved by
	 * @param y Y amount for the Sprite to be moveed by
	 */
	public void moveByAmount(double x, double y) {
		super.setX(super.getX() + x);
		super.setY(super.getY() + y);
	}

	
	 /**
	  * Allows the Sprite to be resized by the window
	  * @param windowWidth The width of the window
	  * @param windowHeight The height of the window
	  */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		double x = super.getX();
		double y = super.getY();

		setX(Math.min(x, windowWidth - w));
		setY(Math.min(y, windowHeight - h));
		setX(Math.max(0, x));
		setY(Math.max(0, y));

//		x = Math.min(x,windowWidth-w);
//		y = Math.min(y,windowHeight-h);
//		x = Math.max(0,x);
//		y = Math.max(0,y);

	}
/**
 * Uses PApplet to create a Sprite object on the screen
 * @param p The surface that the Sprite object is drawn on
 */
	public void draw(PApplet p) {
		if (image != null) {
			if(tint)
				p.tint(150,150,150);
			p.image(image, (float) super.getX(), (float) super.getY(), (float) w, (float) h);
			p.noTint();
		}
		else {
//			g.fill(r, g, b);
			p.rect((float) super.getX(), (float) super.getY(), (float) w, (float) h);
		}
		
	}
	
//	public void changeColor(int r, int g, int b) {
////		tint = true;
//		this.r = r;
//		this.g = g;
//		this.b = b;
////		this.a = a;
//	}

/**
 * Sets whether the Sprite would be tinted or not
 * @param color Holds true if Sprite is tinted while false is when Sprite is not tinted
 */
	public	void colorChange(boolean color) {
		tint = color;
	}
	
	/**
	 * Sets the Sprite to an image
	 * @param i Taken in as a PImage, is what the Sprite is displayed as
	 */
	public void setImage (PImage i) {
		image = i;
	}
	
	/**
	 * Gets the image of the Sprite
	 * @return image The image of the Sprite
	 */
	public PImage getImage() {
		return image;
	}
	
	/**
	 * Sets the width of the Sprite
	 * @param ww The width of the Sprite
	 */
	public void setWidth(int ww) {
		w= ww;
	}
	
	/**
	 * Sets the height of the Sprite
	 * @param hh The height of the Sprite
	 */
	public void setHeight(int hh) {
		h= hh;
	}

}
