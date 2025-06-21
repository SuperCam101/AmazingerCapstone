package sprite;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * A tile inside a mine that, when mined, gives the player coins.
 * 
 */
public class Block extends Sprite{
	
	private int hp, coinVal;
	private boolean isClickable;
//	private int a;
	
	/**
	 * Creates a block at (x, y) with dimensions width x height that is visually represented in the window by img.
	 * The number of hits it takes to break the block is set by health and coinValue determines how many coins the player will receive.
	 * 
	 * @param img The image that visually represents the block in the window.
	 * @param x The x-coordinate of the block in the window.
	 * @param y The y-coordinate of the block in the window.
	 * @param width The width of the block.
	 * @param height The height of the block.
	 * @param health The durability of the block that makes it take more hits to mine.
	 * @param coinValue The number of coins the block will give the player when it is mined.
	 */
	public Block(PImage img, int x, int y, int width, int height, int health, int coinValue) {
		super(img, x, y, width, height);
		hp = health;
		coinVal = coinValue;
		isClickable = false;
//		a=0;
	}

	public void loseHealthPoints(Equipment e) {
		hp -= e.getMiningPower();
//		System.out.println("hp: "+hp+"\tMinePower: "+e.getMiningPower());
//		a+=255/(100/e.getMiningPower());
//		this.changeColor(100, 100, 100, a);
		//PLAN TO CHANGE ACCORDING TO THE ACTUAL IMG TO BE LOADED
	}
	
	public void giveCoins(Gambledorf gd) {
		gd.setCoins(gd.getCoins() + coinVal);
	}
	
//	public void draw(PApplet surface, boolean viewable) {
//		if(viewable)
//			surface.tint(0);
//		
//		super.draw(surface);
////		surface.noTint();
//	}
	
	public void setClickableBlock(boolean condition) {
		isClickable = condition;
	}
	
	public boolean clickable() {
		return isClickable;
	}
	
	public int getHealth() {
		return hp;
	}
	
	public int getCoinVal() {
		return coinVal;
	}
	
	public boolean isViewable(Block[][] blocks, int x, int y) {
		int count = 0;
		//From the gambledorf code for the cross
		//1. The location of this block in terms of the array
		for(int i = x-1; i<=x+1; i++) {
				if(i>-1 && i<blocks.length && blocks[i][y] == null && i!=x ) {
					return true;
				}
			}
		
		for (int j = y - 1; j <= y + 1; j++) {
				if (j>-1 && j<blocks[0].length && blocks[x][j] == null && j!=y) {
					return true;
				}
				
			}
		
		return false;
}
	}
	
