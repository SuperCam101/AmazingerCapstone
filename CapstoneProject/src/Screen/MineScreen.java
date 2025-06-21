package Screen;


import java.awt.Point;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import core.Rectangle;
import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
import processing.core.PImage;
import sprite.Block;
import sprite.Bomb;
import sprite.Chest;
import sprite.Equipment;
import sprite.Gambledorf;
import sprite.Sprite;

/**
 * The screen where the player is able to mine blocks 
 * Can get new tools when clicked on chest
 * Can lose coins when clicked on bomb
 * Can navigate to different screens
 */
public class MineScreen extends Screen {
	private DrawingSurface surface;

	private Block[][] blocks;
	private Rectangle[] borders;
	private Rectangle menuButton;
	private Rectangle toolButton;
	private Rectangle reloadMineButton;
	private Rectangle rightBorder, leftBorder, topBorder, bottomBorder;
	private int coins;
	private boolean showButton;
	private PImage toTool, blockPic, chestPic, bombPic, background, gambledorfLeft, gambledorfRight, reloadMineButtonPic;

	private Gambledorf character;
	
	private StatsScreen stats;
	private PImage backPic;
	private PImage reloadPic;



	/**
	 * Creates an object of type MineScreen
	 * @param surface A surface that allows the MineScreen to be drawn to
	 * @param stats A StatsScreen object that is shared among classes
	 * @param g The character that is drawn onto the screen, shared among all classes
	 */
	public MineScreen(DrawingSurface surface, StatsScreen stats, Gambledorf g) {
		//Setup of drawing surface
		super(800, 600);
		showButton = true;
		this.surface = surface;
		toTool = new PImage();
		blockPic = new PImage();
		chestPic = new PImage();
		bombPic = new PImage();
		background = new PImage();
		gambledorfLeft = new PImage();
		gambledorfRight = new PImage();
		
		//Creation of borders
		leftBorder = new Rectangle(-20, 0, 20, SCREEN_HEIGHT);
		rightBorder = new Rectangle(SCREEN_WIDTH, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		topBorder = new Rectangle(0, -20, SCREEN_WIDTH, 20);
		bottomBorder = new Rectangle(0, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);

		//Creation of buttons
		menuButton = new Rectangle(10, 10, 50, 50);
		toolButton = new Rectangle(740, 540, 50, 50);
		reloadMineButton = new Rectangle(680, 5, 100, 100);
		// Implementation of the blocks and borders arrays
		blocks = new Block[12][16];//50x50 box
		borders = new Rectangle[] {leftBorder, rightBorder, topBorder, bottomBorder};	
		
		character = g;
		
		this.stats = stats;
		backPic = new PImage();
		reloadPic = new PImage();
	}
	
	/**
	 * Draw the MineScreen components: mine blocks, character
	 */
	public void draw() {
//		surface.background(255);
		surface.image(background, 0, 0, 800, 600);
		leftBorder.draw(surface);
		
		surface.fill(0,0,0,0);
		menuButton.draw(surface);

//		PImage backPic = new PImage();
		surface.image(backPic, 10, 10, 50, 50);

		surface.image(reloadPic, 680, 5, 100, 100);
//		if(character.getCoins()<0)
//		{
//			surface.text("YOURE IN DEBT MINE QUICKER", 120, 10);
////			surface.text
//		}
		//Character draw
		
		character.draw(surface);
		drawBlocks(character.getX() + character.getWidth()/2, character.getY() + character.getHeight()/2);
		character.getEquipment().setHeight(40);
		character.getEquipment().setWidth(40);
		character.getEquipment().setX(character.getX());
		character.getEquipment().setY(character.getY());
		character.getEquipment().draw(surface);
		character.getEquipment().setHeight(100);
		character.getEquipment().setWidth(100);

			
		if(showButton) {
			toolButton.draw(surface);
			surface.image(toTool, 740, 540, 50, 50);


		}
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
			character.goLeft();
//			character.setImage(gambledorfLeft);
			
	}
		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
			character.goRight();
//			character.setImage(gambledorfRight);
	}
		if (surface.isPressed(KeyEvent.VK_UP)) {
			character.jump();
			
		}
//		if(character.getImage().equals(gambledorfLeft)) {
//			character.getEquipment().setX(character.getX()-20);
//			character.getEquipment().setY(character.getY()-5);
//		} else {
//			character.getEquipment().setX(character.getX()+5);
//			character.getEquipment().setY(character.getY()-5);
//		}
		character.act(blocks);
		character.act(borders);
		surface.textSize(50);
		surface.fill(255);
		surface.text("Coins: "+ character.getCoins(), 75, 50);
//		surface.rect(0,0,100,100);
//		character.getCenter().x
		
		
	}
	
	/**
	 * Sets if the MineScreen should show the tool button or not
	 * @param x The boolean that tells the screen if it should show the tool button
	 */
	public void setShowButton(boolean x){
		showButton = x;
	}
	
	/**
	 * Returns the boolean that says if the MineScreen should show the tool button or not
	 * @return The boolean that says if the MineScreen should show the tool button or not
	 */
	public boolean getShowButton() {
		return showButton;
	}
	
	/**
	 * Returns the tool button
	 * @return The tool button
	 */
	public Rectangle getToolBu(){
		return toolButton;
	}
	
	/**
	 * According to the location of the mouse click, the MineScreen will
	 * either change the screen, reload the mine, or remove mine blocks accordingly
	 */
	public void mousePressed() {
		//HERE
		Point unscaledMouse = surface.screenCoordinatesToDrawingCoordinates(new Point(surface.mouseX, surface.mouseY));
		
		if (menuButton.isPointInside(unscaledMouse.x,unscaledMouse.y)) {
			super.playSound(super.TAP);
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);

		}
		
		if (reloadMineButton.isPointInside(unscaledMouse.x,unscaledMouse.y)) {
			super.playSound(super.TAP);

			reloadMine();
		}
		
//		if(showButton) {
//			if (toolButton.isPointInside(unscaledMouse.x,unscaledMouse.y)) {
//				super.playSound(super.TAP);
//
//				surface.switchScreen(ScreenSwitcher.INVENTORY_SCREEN);
//				InventoryScreen.changeLastScreen(ScreenSwitcher.MINE_SCREEN);
//
//			}
//
//		}
		setLastScreen(unscaledMouse, showButton);
		
		for(int i = 0; i<blocks.length; i++) {
			for(int j = 0; j<blocks[0].length; j++) {
				if(blocks[i][j]!=null&& blocks[i][j].isPointInside(unscaledMouse.x,unscaledMouse.y)&& blocks[i][j].clickable()) {
					blocks[i][j].loseHealthPoints(character.getEquipment());
					if(blocks[i][j].getHealth()<=0) {
						if(blocks[i][j] instanceof Bomb) {
							stats.increaseBomb();
							stats.increaseBlock();
							
							if(character.getEquipment().getName().equals("BombPickaxe")) {
								blocks[i][j]=null;

							}
							else {
								playSound(BOMB);
								Bomb hi = (Bomb)blocks[i][j];
								hi.act(blocks, i, j, character);
								blocks[i][j]=null;

							}
						}
						else if(blocks[i][j] instanceof Chest) {
							if(character.getCoins() >= 100) {
								stats.increaseChest();
								stats.increaseBlock();
								addInven(i, j);
							}
						}
						else /*(blocks[i][j] instanceof Block)*/ {
							playSound(super.MINE);
							stats.increaseBlock();
							stats.increaseCoins(blocks[i][j].getCoinVal());
							blocks[i][j].giveCoins(character);
							blocks[i][j]=null;

						}
					}
				}
					
			}
		}
	}
	
	public void setLastScreen(Point unscaledMouse, boolean showButton)
	{

	}
	
	public void addInven(int i, int j) {

	}
	public void bombAct(int i, int j) {

	}
	
	/**
	 * Returns the mine block 2D array
	 * @return the mine block 2D array
	 */
	public Block[][] getBlocks() {
		return blocks;
	}
	
	/**
	 * Sets the features of the character
	 */
	public void spawnNewGambledorf() {
//		sound.playSoundEffect(0);
		

		character.setImage(gambledorfLeft);
		// character.setEquipment(t);
		coins = character.getCoins();

	}
	
	/**
	 * Sets the features of all of the mine block, normal block, chest, bomb
	 */
	public void spawnNewBlocks() {
		//Setting the values in the array for blocks and borders
		for(int i = 0; i<blocks.length; i++) {
			for(int j = 0; j<blocks[0].length; j++) {
				if(i==0 || i==1)
					blocks[i][j] = null;
				else
					blocks[i][j] = newBlock(i, j);
				
			}
		}
		
		//Chests
		for(int x = 0; x<30; x++) {
			int i = (int)(Math.random()*12);
			int j = (int)(Math.random()*16);
			if(!(blocks[i][j] instanceof Chest) && i!=0 && i!=1 && i!=2)
				blocks[i][j] = new Chest(chestPic, 50*j, 50*i, 50, 50);
		}
		
		//Bombs
		for(int x = 0; x<20; x++) {
			int i = (int)(Math.random()*12);
			int j = (int)(Math.random()*16);
			if(!(blocks[i][j] instanceof Chest) && !(blocks[i][j] instanceof Bomb)&& i!=0 && i!=1 && i!=2)
				blocks[i][j] = newBomb(i, j);

		}
	}
	
	
	public Block newBlock(int i, int j) {
		return null;
	}
	public Bomb newBomb(int i, int j) {
		return null;
	}
//	public Chest newChest(int i, int j) {
//		return null;
//	}
	
	/**
	 * sets up the images that are displayed in the drawing surface
	 */
	public void setup() {
		toTool = surface.loadImage("img/ToInventoryScreenButton.png");
		chestPic = setChestPic();
		bombPic = setBombPic();
		blockPic = setBlockPic();
		background = setBackgroundPic();
		gambledorfLeft = surface.loadImage("img/GambledorfLeft.png");
		gambledorfRight = surface.loadImage("img/GambledorfRight.png");
		backPic = surface.loadImage("img/backButton.png");
		reloadPic = surface.loadImage("img/ReloadMineButton.png");

		spawnNewGambledorf();
		spawnNewBlocks();
	}
	public PImage setChestPic() {
		return null;
	}
	public PImage setBombPic() {
		return null;
	}

	public PImage setBlockPic() {
		return null;
	}
	public PImage setBackgroundPic() {
		return null;

	}
	
	/**
	 * Returns the image of the block
	 * @return the image of the block
	 */
	public PImage getBlock() {
		return blockPic;
	}
	
	/**
	 * Returns the image of the chest
	 * @return the image of the chest
	 */
	public PImage getBomb() {
		return bombPic;
	}
	
//	public PImage getChest() {
//		return chestPic;
//	}

	
	public boolean isOutside(int i, int j){
		
		if(blocks[i][j].isPointInside(character.getX(),  character.getY())) {
			return true;
		}
		
		
		return false;
	}
	
	/**
	 * Draws the list of blocks to the screen and blacks certain blocks out if their aren't viewable
	 * @param x The x position of the character
	 * @param y The y position of the character
	 */
	public void drawBlocks(double x, double y) {
		//Work on this for optimization
		for(int i = 0; i<blocks.length; i++) {
			for(int j = 0; j<blocks[0].length; j++) {
				if(blocks[i][j]!=null) {
					if(blocks[i][j].isViewable(blocks, i, j)) {
						blocks[i][j].colorChange(false);
						blocks[i][j].draw(surface);
					}
					else {
						blocks[i][j].colorChange(true);
						blocks[i][j].draw(surface);
						
					}

				}
			}
		}
		
		
		
		//Drawing the blocks to the screen
		Rectangle[][] ah = new Rectangle[12][16];
		for(int i = 0; i<ah.length; i++) {
			for(int j = 0; j<ah[i].length; j++) {
				ah[i][j]=new Rectangle(50*j, 50*i, 50, 50);
			}
		}
				
		int row = 0, col = 0;
		
		//Sets up the color and state of the blocks
		for(int i = 0; i<ah.length; i++) {
			for(int j = 0; j<ah[i].length; j++) {
				if(i!=0 && i!=1) {
					if(blocks[i][j]!=null) {
						surface.fill(70);
						blocks[i][j].draw(surface);
						blocks[i][j].setClickableBlock(false);

					}
				}					
				if(ah[i][j].isPointInside(x,  y)) {
					row = i;
					col = j;
				}

			}
		}
//		surface.noTint();
//		
//		//Detecting and setting the color of adjacent locations of the character Gambledorf
//		for(int i = row-1; i<=row+1; i++) {
//			if((i!= row) && i>-1 && col>-1 && i<blocks.length && col<blocks[i].length) {
//				if(blocks[i][col] != null) {
//					blocks[i][col].setClickableBlock(true);
//					surface.fill(200);
//					blocks[i][col].draw(surface);
//				}
//			}
//		}
		
//		for (int j = col - 1; j <= col + 1; j++) {
//			if (j != col && row > -1 && j > -1 && row < blocks.length && j < blocks[row].length) {
//				if (blocks[row][j] != null) {
//					blocks[row][j].setClickableBlock(true);
//					surface.fill(200);
//					blocks[row][j].draw(surface);
//				}
//			}
//		}
		
		//Just in case we would need to swtich back to the adjacent plus diagonals block detection system
		
//		for(int ii = 0; ii<blocks.length; ii++) {
//			for(int j = 0; j<blocks[i].length; j++) {
//				blocks[ii][j] = null;
//			}
//		}
//
//		
		for(int i = row-1; i<=row+1; i++) {
			for(int j = col-1; j<= col+1; j++) {
				if((i!= row || j!=col) && i>-1 && j>-1 && i<blocks.length && j<blocks[i].length) {
					if(blocks[i][j] != null&& blocks[i][j].isViewable(blocks, i, j)) {
						blocks[i][j].setClickableBlock(true);

					}
				}
			}
		}
		
		
	}
	
	/**
	 * Returns the character
	 * @return The character
	 */
	public Gambledorf getPlayer() {
		return character;
	}

	/**
	 * Reloads the mine by resetting the blocks and putting the character back to its start position
	 */
	public void reloadMine() {
		spawnNewBlocks();
		character.setX(400);
		character.setY(50);
		character.getEquipment().setX(400);
		character.getEquipment().setY(50);
	}

}
