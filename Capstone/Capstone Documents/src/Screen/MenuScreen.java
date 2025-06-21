package Screen;

import core.DrawingSurface;
import core.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
import sprite.Equipment;

import java.awt.Point;

import Screen.ScreenSwitcher;

/**
 * The starting screen where the player can start the game and go to the other screens.
 * Displays all of the screens the player can go to.
 */
public class MenuScreen extends Screen{
	private DrawingSurface surface;

	private Rectangle mineButton;
	private Rectangle toolButton;
	private Rectangle statsButton;
	private Rectangle mine2Button;
	private PImage mineBu, toolBu, statBu, mine2Bu;
	private StatsScreen stats;
	private NormalMine m1;
	private RichMine m2;
	PImage background;
	/**
	 * Creates an object of type MenuScreen
	 * @param surface A surface that allows the MenuScreen to be drawn to
	 * @param stats A StatsScreen object that is shared among classes
	 * @param m1 A NormalMine object that is shared among classes
	 * @param m2 A RichMine object that is shared among classes
	 */
	public MenuScreen(DrawingSurface surface, StatsScreen stats, NormalMine m1, RichMine m2) {
		super(800, 600);
		this.surface = surface;
//		int 800 = this.getWidth();
//		int 600 = this.getHeight();
//		System.out.println(800+" "+600);
		toolButton = new Rectangle(40, 320, 225, 225);
		mineButton = new Rectangle(290, 285, 225, 180);//h/6+h/4
		statsButton = new Rectangle(540, 315, 275, 235);
		mine2Button = new  Rectangle(305,445,195,130);
		toolBu = new PImage();
		mineBu = new PImage();
		statBu = new PImage();
		mine2Bu = new PImage();
		background = new PImage();

		this.stats = stats;
		this.m1 = m1;
		this.m2 = m2;
	}
	
	/**
	 * Draws the MenuScreen to the drawing surface
	 * Displays the background and the buttons
	 */
	public void draw() {
		surface.image(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		surface.fill(255, 255, 255, 0);
		surface.stroke(255, 255, 255, 0);
		mineButton.draw(surface);
		toolButton.draw(surface);
		statsButton.draw(surface);

		surface.image(toolBu, 40, 320, 225, 225);
		surface.image(mineBu, 290, 285, 225, 180);
		surface.image(statBu, 540, 315, 275, 235);
		surface.image(mine2Bu, 305,445,195,130);

	}
	
	/**
	 * sets up the images that are displayed in the drawing surface 
	 */
	public void setup() {
		background = surface.loadImage("img/MenuScreenBackground.png");
		mineBu = surface.loadImage("img/MineButtonImage.png");
		toolBu = surface.loadImage("img/ToolsButtonImage.png");
		statBu = surface.loadImage("img/StatsButtonImage.png");
		mine2Bu = surface.loadImage("img/Mine2ButtonImage.png");
	}
	
	/**
	 * Changes the screen displayed according to the place that the mouse clicked
	 */
	public void mousePressed() {
		Point unscaledMouse = surface.screenCoordinatesToDrawingCoordinates(new Point(surface.mouseX, surface.mouseY));

		
		if (mineButton.isPointInside(unscaledMouse.x, unscaledMouse.y)) {
			super.playSound(super.TAP);
			m1.reloadMine();

			surface.switchScreen(ScreenSwitcher.MINE_SCREEN);
		}
		if (toolButton.isPointInside(unscaledMouse.x, unscaledMouse.y)) {
			super.playSound(super.TAP);

			surface.switchScreen(ScreenSwitcher.INVENTORY_SCREEN);
			InventoryScreen.changeLastScreen(ScreenSwitcher.MENU_SCREEN);
		}
		if (statsButton.isPointInside(unscaledMouse.x, unscaledMouse.y)) {
			super.playSound(super.TAP);

			surface.switchScreen(ScreenSwitcher.STATS_SCREEN);
		}
		if (mine2Button.isPointInside(unscaledMouse.x, unscaledMouse.y) && stats.getToolNum()>=5) {
			super.playSound(super.TAP);
			m2.reloadMine();
				InventoryScreen.addToInventory(new Equipment(surface.loadImage("img/GoldPickaxe.png"), 50, 50, 40, 40, 100, "GoldPickaxe", "Goldy"));
			surface.switchScreen(ScreenSwitcher.MINE_SCREEN_2);
			
		}

	}

}