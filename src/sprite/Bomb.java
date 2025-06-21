package sprite;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/** 
 * A type of block that when mined, causes the player to lose
 * coins and removes adjacent blocks in the mine.
 */
public class Bomb extends Block{

	private int exRad, coinsTake;
	
	/**
	 * Creates a bomb at (x, y) in the window with dimensions width x height.
	 * Visually represented in the window by img and health sets how resistant the bomb is to
	 * being mined and how many coins it will take from the player when it is mined.
	 * 
	 * @param img The image that visually represents the block in the window.
	 * @param x The x-coordinate of the bomb block in the window.
	 * @param y The y-coordinate of the bomb block in the window.
	 * @param width The width of the bomb.
	 * @param height The height of the bomb.
	 * @param health The durability of the bomb that makes it take more hits to mine.
	 * @param coinsTake The number of coins the bomb will take away from the player.
	 */
	public Bomb(PImage img, int x, int y, int width, int height, int health, int coinsTake) {
		super(img, x, y, width, height, health, 0);
		exRad = 1;
		this.coinsTake = coinsTake;
	}
	
	/**
	 * Detonates the bomb which removes adjacent blocks in the mine and causes the player to lose coins.
	 * 
	 * @param block The block containing a bomb that the player mined.
	 * @param i The row in the mine in which the bomb is located.
	 * @param j The column in the mine in which the bomb is located.
	 * @param gd The Gambledorf object that will lose coins.
	 */
	public void blowUp(Block[][] block, int i, int j, Gambledorf gd) {
		for(int y = i-1; y<=i+1; y++) {
			for(int x = j-1; x<=j+1; x++) {
				if((y!=i && x==j) || (x!=j && y==i)) {
					if((y>=0 && y<block.length && x>=0 && x<block[0].length)) { //check if this is inside list
						if(block[y][x] != null && block[y][x] instanceof Bomb) {//check if neighbor is null and instanceof bomb
							Bomb hi = (Bomb)block[y][x];
							block[i][j] = null;
							hi.act(block, y, x, gd);
						}
						block[y][x] = null;
					}

				}
			}
		}
	}
	public void act(Block[][] block, int i, int j, Gambledorf gd) {
		this.blowUp(block, i, j, gd);
		this.takeCoins(gd);
	}
	
	public void takeCoins(Gambledorf gd) {
			gd.setCoins(gd.getCoins()-coinsTake);
	}
//	public void draw(PApplet surface) {
//		surface.fill(255, 0, 0);
//		surface.rect((float) super.getX(), (float) super.getY(), (float) 50, (float) 50);
//
//	}
	
}
