package Screen;
import core.DrawingSurface;
import core.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Point;

import Screen.ScreenSwitcher;


import core.DrawingSurface;

/**
 * The screen that displays all of the achivements of the player
 * Such as numbers of chest, blocks, and bombs cracked
 * The number of tools obtained
 * The number of coins that the player got 
 */
public class StatsScreen extends Screen{
	private DrawingSurface surface;

	private Rectangle menuButton;

	private static int minutes, seconds, milSec, chestNum, bombNum, toolNum, coinNum, blockNum;
	private PImage background;
	private PImage backPic;

	/**
	 * Creates an object of type StatsScreen
	 * @param surface A surface that allows the MineScreen to be drawn to
	 */
	public StatsScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;
		menuButton = new Rectangle(10, 10, 50, 50);
		minutes = 0;
		seconds = 0;
		milSec = 0;
		chestNum = 0;
		bombNum = 0;
		toolNum = 1;
		coinNum = 0;
		blockNum = 0;
		background = new PImage();
		backPic = new PImage();
	}
	
	/**
	 * Draws this screen to the drawing surface
	 */
	public void draw() {
		surface.image(background, 0, 0, 800, 600);

		surface.stroke(0, 0, 0, 0);
		surface.fill(0, 0, 0, 0);
		menuButton.draw(surface);
		surface.image(backPic, 10, 10, 50, 50);
		
		surface.fill(255,230);
		surface.stroke(255);
		surface.strokeWeight(5);
		surface.rect(100, 80, 600, 100);//time
		surface.rect(100, 200, 290, 150);//coin
		surface.rect(410, 200, 290, 150);//tool
		surface.rect(100, 370, 186, 150);//block
		surface.rect(306, 370, 186, 150);//chest
		surface.rect(512, 370, 186, 150);//bomb
		
		surface.textAlign(surface.CENTER);
		surface.fill(0);
		surface.textSize(30);
		surface.text("Time Played: "+minutes+" mins  "+seconds+" secs", 100, 115, 600, 100);
		surface.text("Total Coins: $"+coinNum, 100, 260, 290, 150);
		surface.text("Tools Count: "+toolNum+"/10", 410, 260, 290, 150);
		surface.text("Blocks Mined: "+blockNum, 100, 415, 186, 150);
		surface.text("Chest Opened: "+chestNum, 306, 415, 186, 150);
		surface.text("Bombs Blown Up: "+bombNum, 512, 415, 186, 150);

	}
	
	/**
	 * sets up the images to be displayed in this screen
	 */
	public void setup() {
		background = surface.loadImage("img/StatsScreenBackground.png");
		backPic = surface.loadImage("img/backButton.png");

	}
	
	/**
	 * According to the location of the mouse click this screen can go back to the menu screen
	 */
	public void mousePressed() {
		
		Point unscaledMouse = surface.screenCoordinatesToDrawingCoordinates(new Point(surface.mouseX, surface.mouseY));

		if (menuButton.isPointInside(unscaledMouse.x, unscaledMouse.y)) {
			super.playSound(super.TAP);

			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
	
	/**
	 * Increase the time played by milliseconds 
	 */
	public void increaseTime() {
		milSec+=2;
		if(milSec >= 60) {
			milSec-=60;
			seconds++;
		}
		if(seconds >=60) {
			seconds -= 60;
			minutes++;
		}
	}
	
	/**
	 * Increase the number of total chests opened
	 */
	public void increaseChest() {
		chestNum++;
	}
	
	/**
	 * Increase the number of total bombs opened
	 */
	public void increaseBomb() {
		bombNum++;
	}
	
	/**
	 * Set the number of tools that the player has obtained
	 * @param t
	 */
	public void setTool(int t) {
		toolNum = t;
	}

	/**
	 * Increase the amount of coins by a specified mount
	 * @param c The amount of coins to increase
	 */
	public void increaseCoins(int c) {
		coinNum += c;
	}
	
	/**
	 * Increase the number of total blocks cracked
	 */
	public void increaseBlock() {
		blockNum++;
	}
	
	/**
	 * Returns the number of tools that the player obtained
	 * @return Number of tools that the player has
	 */
	public int getToolNum() {
		return toolNum;
	}

	
}