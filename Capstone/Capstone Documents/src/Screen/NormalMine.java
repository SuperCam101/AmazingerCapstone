package Screen;

import java.awt.Point;

import core.DrawingSurface;
import processing.core.PImage;
import sprite.Block;
import sprite.Bomb;
import sprite.Chest;
import sprite.Equipment;
import sprite.Gambledorf;

/**
 * A normal mine that extends from MineScreen and that sets its unique characteristics
 */
public class NormalMine extends MineScreen{
	private DrawingSurface surface;

	private StatsScreen stats;

	private Gambledorf g;

	/**
	 * Creates an object of type NormalMine
	 * @param surface A surface that allows the NormalMine to be drawn to
	 * @param stats A StatsScreen object that is shared among classes
	 * @param g The character that is drawn onto the screen, shared among all classes
	 */
	public NormalMine(DrawingSurface surface, StatsScreen stats, Gambledorf g) {
		super(surface, stats, g);
		this.stats = stats;
		this.surface = surface;
		this.g = g;

	}
	
	/**
	 * Sets this screen as the last screen that was displayed before displaying the inventory class
	 * @param unscaledMouse The point where the mouse clicks
	 * @param showButton The boolean that tells the screen if it should show the tool button 
	 */
	public void setLastScreen(Point unscaledMouse, boolean showButton)
	{
		if(showButton) {
			if (super.getToolBu().isPointInside(unscaledMouse.x,unscaledMouse.y)) {
				super.playSound(super.TAP);
	
				surface.switchScreen(ScreenSwitcher.INVENTORY_SCREEN);
				InventoryScreen.changeLastScreen(ScreenSwitcher.MINE_SCREEN);
	
			}
		}

	}
	
	/**
	 * Adds a random tool to the inventory list of the InventoryScreen
	 * The tools added are tools that can only be found the NormalMine
	 * @param i, the row of the chest location
	 * @param j, the column of the chest location
	 */
	public void addInven(int i, int j) {
		Chest bye = (Chest)super.getBlocks()[i][j];
		Equipment e = bye.gamble(g, surface, 1);
		InventoryScreen.addToInventory(e);

		if(e != null) {
			super.getBlocks()[i][j] = null;
			super.playSound(super.CHEST);

		}


	}

	/**
	 * Adds on to the features of the character after the super class method is processed
	 */
	public void spawnNewGambledorf() {
		super.spawnNewGambledorf();
		Equipment t = new Equipment(surface.loadImage("img/Pickaxe.png"), 50, 50, 40, 40, 25, "Tool", "common");
		g.setEquipment(t);
	}
	
	/**
	 * Return a new block objects 
	 * @param i, the row of the block location
	 * @param j, the column of the block location
	 */
	public Block newBlock(int i, int j) {
		return new Block(super.getBlock(), 50*j, 50*i, 50, 50, 100, 50);
	}
	
	/**
	 * Return a new bomb objects 
	 * @param i, the row of the bomb location
	 * @param j, the column of the bomb location
	 */
	public Bomb newBomb(int i, int j) {
		return new Bomb(super.getBomb(), 50*j, 50*i, 50, 50, 0, 150);
	}
	
//	/**
//	 * Return a new chest objects 
//	 * @param i, the row of the chest location
//	 * @param j, the column of the chest location
//	 */
//	public Chest newChest(int i, int j) {
//		return new Chest(super.getChest(), 50*j, 50*i, 50, 50);
//	}


	/**
	 * sets the image of the chest
	 */
	public PImage setChestPic() {
		return surface.loadImage("img/Chest.png");
	}
	
	/**
	 * sets the image of the bomb
	 */
	public PImage setBombPic() {
		 return surface.loadImage("img/Bomb.png");
	}

	/**
	 * sets the image of the block
	 */
	public PImage setBlockPic() {
		return surface.loadImage("img/Block.png");
	}
	
	/**
	 * sets the image of the background
	 */
	public PImage setBackgroundPic() {
		return surface.loadImage("img/MineScreenBackground.png");

	}

}
