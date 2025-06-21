package sprite;

import java.awt.geom.Point2D.Double;
import java.util.List;

import core.DrawingSurface;
import core.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * A interactable character that can move up, down, left, and right, and mine blocks. 
 */
public class Gambledorf extends Sprite {
	public static final int WIDTH = 30;
	public static final int HEIGHT = 40;
	private int coins;
	private Equipment tool;
	private int initialJumps, maxJumps;
	private double xVel, yVel;
	private PImage normalL, normalR, kermitL, kermitR, monkeyL, monkeyR;
	private DrawingSurface surface;
	/**
	 * Creates a Gambledorf object with image, location, equipment, and surface to be drawn on
	 * @param img The image of the Gambledorf
	 * @param x The X location of the Gambledorf
	 * @param y The Y location of the Gambledorf
	 * @param t The equipment that Gambledorf will hold
	 * @param surface The drawing surface to be drawn on 
	 */
	public Gambledorf(PImage img, int x, int y, Equipment t, DrawingSurface surface) {
		super(img, x, y, WIDTH, HEIGHT);
		xVel = 0;
		yVel = 0;
		this.coins = 0;
		tool = t;
		initialJumps = 0;
		maxJumps = 1;
		normalL = surface.loadImage("img/GambledorfLeft.png");
		normalR = surface.loadImage("img/GambledorfRight.png");
		kermitL = surface.loadImage("img/kermitDorfLeft.png");
		kermitR = surface.loadImage("img/kermitDorfRight.png");
		monkeyL = surface.loadImage("img/monkeyDorfLeft.png");
		monkeyR = surface.loadImage("img/monkeyDorfRight.png");
		
		this.surface=surface;
	}
	// METHODS

	/**
	 * Makes Gambledorf walk left or right across the window
	 * 
	 * @param dir -1 for left, 1 for right
	 */
	public void walk(int dir) {
		xVel+=(double)dir/2;
		if(xVel > 5)
			xVel+= -3;
		else if (xVel < -5)
			xVel+= 3;
	}

	/**
	 * Makes Gambledorf jump up
	 */
	public void jump() {
		if(initialJumps<maxJumps) {
		if (yVel > -2.5)
			yVel = yVel - 4;
		initialJumps++;
		}
	}

	/**
	 * Makes Gambledorf do everything that he should do without any keys being pressed
	 * (such as fall to the ground) This involves: - applying forces to his
	 * velocities - applying his velocities to his position - checking for
	 * collisions
	 * 
	 * @param blocks Blocks that mario could collide with
	 */
	public void act(Block[][] blocks) {

		yVel += 0.1;
		xVel *= 0.9;
		
		setX(getX()+xVel);
		
//		System.out.println(xVel);
		
		for (Block[] s : blocks) {
			for (Block b : s) {
				if(b!=null)
				if (b.isTouching(this)) {
//					x -= xVel;
//					System.out.println("qwert");
					setX(getX() - xVel);
					xVel = 0;
//					System.out.println(b.isTouching(this));
				}
			}
		}
		setY(getY()+yVel);

		for (Block[] s : blocks) {
			for (Block b : s) {
				if(b!=null)
				if (b.isTouching(this)) {
//					y -= yVel;
					setY(getY() - yVel);
					yVel = 0;
					initialJumps=0;
				}
			}
		}

	}

	/**
	 * Makes Gambledorf do everything that he should do without any keys being pressed
	 * (such as fall to the ground) This involves: - applying forces to his
	 * velocities - applying his velocities to his position - checking for
	 * collisions
	 * 
	 * @param borders Other sprites that mario could collide with (the platforms)
	 */
	public void act(Rectangle[] borders) {
		
		for (Rectangle b : borders) {
				if(b!=null)
				if (b.isTouching(this)) {
					setX(getX() - xVel);
					xVel = 0;
				}
		}
		for (Rectangle b : borders) {
				if(b!=null)
				if (b.isTouching(this)) {
					setY(getY() - yVel);
					yVel = 0;
					initialJumps = 0;
				}
		}

	}
	
	/**
	 * Makes Gambledorf move left across the window
	 */
	public void goLeft() {
		this.walk(-1);
		this.getEquipment().setX(this.getX()-20);
		this.getEquipment().setY(this.getY()-5);

		if(tool.getName().equals("BananaPickaxe")) {
			this.setImage(monkeyL);
		}
		else if(tool.getName().equals("KermitPickaxe")) {
			this.setImage(kermitL);

		}
		else {
			this.setImage(normalL);

		}
	}

	/**
	 * Makes Gambledorf move right across the window
	 */
	public void goRight() {
		this.walk(1);
		this.getEquipment().setX(this.getX()+5);
		this.getEquipment().setY(this.getY()-5);
		if(tool.getName().equals("BananaPickaxe")) {
			this.setImage(monkeyR);
		}
		else if(tool.getName().equals("KermitPickaxe")) {
			this.setImage(kermitR);

		}
		else {
			this.setImage(normalR);

		}


	}
	/**
	 * Uses PApplet to create a Gambledorf object on the screen with a tool object
	 */
	public void draw() {
		super.draw(surface);
		tool.draw(surface);
	}
	
	/**
	 * Gets the amount of coins Gambledorf has
	 * @return coins The amount of coins Gambledorf has
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Changes the amount of coins that Gambledorf has
	 * @param coinSet The amount of coins that Gambledorf's amount will be set to
	 */
	public void setCoins(int coinSet) {
		this.coins = coinSet;
	}

	/**
	 * Changes the equipment that Gambledorf has
	 * @param equipmentSelect The equipment Gambledorf will hold and use
	 */
	public void setEquipment(Equipment equipmentSelect) {
		tool = equipmentSelect;
	}
	
	/**
	 * Gets the equipment Gambledorf has
	 * @return tool The equipment Gambledorf has and is using
	 */
	public Equipment getEquipment() {
		return tool;
	}
	
	/**
	 * Resets Gambledorf's jumps
	 */
	public void resetJumps() {
		initialJumps=0;
	}
}
