package Screen;
import core.DrawingSurface;
import core.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.Point;
import java.util.ArrayList;

import Screen.ScreenSwitcher;
import sprite.Equipment;
import sprite.Gambledorf;
import sprite.Sprite;

/**
 * A screen where the tools of player is displayed
 * The player can see and equip tools by clicking on the tool the mouse hovered over
 */
public class InventoryScreen extends Screen{

	private DrawingSurface surface;
	private Rectangle backButton;
	private static int lastScreen;
	private boolean discoveredGreatPickaxe, discoveredBananaPickaxe, discoveredBaguettePickaxe, discoveredKermitPickaxe;
	private boolean discoveredRainbow, discoveredBomb, discoveredStonks, discoveredDoge, discoveredGold;
	private Equipment hiPickaxe, greatPickaxe, bananaPickaxe, baguettePickaxe, kermitPickaxe;
	private Equipment rainbowPickaxe, bombPickaxe, stonksPickaxe, dogePickaxe, goldPickaxe;
	private ArrayList<Equipment> inventory;
	private PImage backPic, rainbowBackground;
//	private Equipment selectedPickaxe;

	private static ArrayList<Equipment> equipmentObtained;
	private ArrayList<Rectangle> places;
	private Rectangle background;
	
	private Gambledorf player;
	private int box1X, box2X, box3X, box4X, box5X;
	
	private StatsScreen stats;

	/**
	 * Creates an InventoryScreen objects 
	 * @param surface A surface that allows the InventoryScreen to be drawn to
	 * @param player The character that is drawn onto the screen, shared among all classes
	 * @param stats A StatsScreen object that is shared among classes
	 */
	public InventoryScreen(DrawingSurface surface, Gambledorf player, StatsScreen stats) {
		super(800, 600);
		this.surface = surface;
		this.player = player;
		backButton = new Rectangle(10, 10, 50, 50);
		inventory = new ArrayList<Equipment>();
		places = new ArrayList<Rectangle>();
		equipmentObtained = new ArrayList<Equipment>();
		int xPos = (800-500)/6;

		discoveredGreatPickaxe = false;
		discoveredBananaPickaxe = false;
		discoveredBaguettePickaxe = false;
		discoveredKermitPickaxe = false;
		discoveredRainbow = false;
		discoveredBomb = false;
		discoveredStonks = false;
		discoveredDoge = false;
		discoveredGold = false;

		PImage backPic = new PImage();		
		PImage rainbowBackground = new PImage();

		for(int x = 0; x<5; x++) {
			places.add(new Rectangle(xPos, 175, 100, 100));
			xPos+= 100+(800-500)/6;
		}
		xPos = (800-500)/6;

		for(int x = 0; x<5; x++) {
			places.add(new Rectangle(xPos, 325, 100, 100));
			xPos+= 100+(800-500)/6;

		}
		
		box1X = (800-500)/6;
		box2X = 152+(800-500)/6;
		box3X = 297+(800-500)/6;
		box4X = 452+(800-500)/6;
		box5X = 597+(800-500)/6;
		
		this.stats = stats;
	}
	
	/**
	 * Draws the InventoryScreen to the drawing surface and also the tools that the player found on the to drawing surface
	 * Also shows the tool information when the mouse goes to the tool
	 */
	public void draw() {
		Point unscaledMouse = surface.screenCoordinatesToDrawingCoordinates(new Point(surface.mouseX, surface.mouseY));
		stats.setTool(inventory.size());

		surface.background(255);
		surface.fill(0, 0, 0, 0);
		background.draw(surface);
		surface.stroke(0, 0, 0, 0);
		backButton.draw(surface);
		surface.image(backPic, 10, 10, 50, 50);
//		surface.fill(255);
		surface.strokeWeight(5);
		surface.stroke(0);
		for(int i=0; i<places.size(); i++) {
			if(i==0) {
				surface.fill(240);
			}
			if(i==1) {
				surface.fill(100,100,255);
			}
			if(i==2) {
				surface.fill(255,0,255);
			}
			if(i==3) {
				surface.fill(255,255,0);
			}
			if(i==4) {
				surface.fill(0,255,0);
			}
			
			if(i==5) {
				surface.fill(255,255,0);
			}		
			
			if(i==7) {
				surface.fill(0,255,0);
			}
			if(i==8) {
				surface.fill(255,255,200);
			}
			if(i==9) {
				surface.fill(50,50,50);
			}
			places.get(i).draw(surface);
			surface.image(rainbowBackground, 3*(100+(800-500))/6, 325, 100, 100);
		}
		for(int i = 0; i<inventory.size(); i++) {
			if(inventory.get(i)!=null)
			{
				if(inventory.get(i).getName().equals("hi")) {
					inventory.get(i).setX(box1X);
					inventory.get(i).setY(175);

				}
				else if(inventory.get(i).getName().equals("GreatPickaxe")) {
					inventory.get(i).setX(box2X);
					inventory.get(i).setY(175);

				}
				else if(inventory.get(i).getName().equals("BananaPickaxe")) {
					inventory.get(i).setX(box3X);
					inventory.get(i).setY(175);

				}
				else if(inventory.get(i).getName().equals("BaguettePickaxe")) {
					inventory.get(i).setX(box4X);
					inventory.get(i).setY(175);

				}
				else if(inventory.get(i).getName().equals("KermitPickaxe")) {
					inventory.get(i).setX(box5X);
					inventory.get(i).setY(175);

				}
				else if(inventory.get(i).getName().equals("GoldPickaxe")) {
					inventory.get(i).setX(box1X);
					inventory.get(i).setY(325);

				}

				else if(inventory.get(i).getName().equals("RainbowPickaxe")) {
					inventory.get(i).setX(box2X);
					inventory.get(i).setY(325);

				}
				else if(inventory.get(i).getName().equals("StonksPickaxe")) {
					inventory.get(i).setX(box3X);
					inventory.get(i).setY(325);

				}
				else if(inventory.get(i).getName().equals("DogePickaxe")) {
					inventory.get(i).setX(box4X);
					inventory.get(i).setY(325);

				}
				else if(inventory.get(i).getName().equals("BombPickaxe")) {
					inventory.get(i).setX(box5X);
					inventory.get(i).setY(325);

				}
				
				
				
				
				inventory.get(i).draw(surface);


			}
		}
		
		//For hiPickaxe
		if(places.get(0).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(hiPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+hiPickaxe.getName()+"\nRarity: "+hiPickaxe.getRarity()+"\nMining Power: "+hiPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );

		}
		
			//For greatPickaxe
		else if(places.get(1).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(greatPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+greatPickaxe.getName()+"\nRarity: "+greatPickaxe.getRarity()+"\nMining Power: "+greatPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
			
			//For bananaPickaxe
		else if(places.get(2).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(bananaPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+bananaPickaxe.getName()+"\nRarity: "+bananaPickaxe.getRarity()+"\nMining Power: "+bananaPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
			}
			//For baguettePickaxe
		else if(places.get(3).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(baguettePickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+baguettePickaxe.getName()+"\nRarity: "+baguettePickaxe.getRarity()+"\nMining Power: "+baguettePickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
			
			//For kermitPickaxe
		else if(places.get(4).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(kermitPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+kermitPickaxe.getName()+"\nRarity: "+kermitPickaxe.getRarity()+"\nMining Power: "+kermitPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
		
		//For bombPickaxe
		else if(places.get(9).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(bombPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+bombPickaxe.getName()+"\nRarity: "+bombPickaxe.getRarity()+"\nMining Power: "+bombPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
		//For dogePickaxe
		else if(places.get(8).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(dogePickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+dogePickaxe.getName()+"\nRarity: "+dogePickaxe.getRarity()+"\nMining Power: "+dogePickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}	
		//For stonksPickaxe
		else if(places.get(7).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(stonksPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+stonksPickaxe.getName()+"\nRarity: "+stonksPickaxe.getRarity()+"\nMining Power: "+stonksPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
		
		//For rainbowPickaxe
		else if(places.get(6).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(rainbowPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+rainbowPickaxe.getName()+"\nRarity: "+rainbowPickaxe.getRarity()+"\nMining Power: "+rainbowPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}
		//For goldPickaxe
		else if(places.get(5).isPointInside(unscaledMouse.x,unscaledMouse.y) && inventory.contains(goldPickaxe)) {
			surface.fill(200);
			surface.strokeWeight(3);
			surface.rect(unscaledMouse.x,unscaledMouse.y, 200, 80);
			surface.fill(0);
			surface.textSize(20);
			surface.text("Name: "+goldPickaxe.getName()+"\nRarity: "+goldPickaxe.getRarity()+"\nMining Power: "+goldPickaxe.getMiningPower(),unscaledMouse.x+5,unscaledMouse.y+5, 200, 80 );
		}




		
		if(!inventory.contains(hiPickaxe)) {
			inventory.add(hiPickaxe);
		}
		if(!inventory.contains(goldPickaxe) && equipmentObtained.size()==5) {
			inventory.add(goldPickaxe);
		}
				
		for(int i = 0; i < equipmentObtained.size(); i++) {
			if(equipmentObtained.get(i)!=null) {				
				if(equipmentObtained.get(i).getName()=="TheGreatPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredGreatPickaxe = true;
				}
					
				if(equipmentObtained.get(i).getName()=="BananaPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredBananaPickaxe = true;
				}
					
				if(equipmentObtained.get(i).getName()=="BaguettePickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredBaguettePickaxe = true;
				}
					
				if(equipmentObtained.get(i).getName()=="KermitPickaxe"&&!inventory.contains(equipmentObtained.get(i))) { 
					discoveredKermitPickaxe = true;
				}
				if(equipmentObtained.get(i).getName()=="BombPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredBomb = true;
				}
				if(equipmentObtained.get(i).getName()=="DogePickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredDoge = true;
				}
				if(equipmentObtained.get(i).getName()=="StonksPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredStonks = true;
				}
				if(equipmentObtained.get(i).getName()=="RainbowPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredRainbow= true;
				}
				if(equipmentObtained.get(i).getName()=="GoldPickaxe"&&!inventory.contains(equipmentObtained.get(i))) {
					discoveredGold= true;
				}


			}

			
		}
		
		if(discoveredGreatPickaxe && !inventory.contains(greatPickaxe)) {
			inventory.add(greatPickaxe);
		}
		
		else if(discoveredBananaPickaxe && !inventory.contains(bananaPickaxe)) {
			inventory.add(bananaPickaxe);
		}
		
		else if(discoveredBaguettePickaxe && !inventory.contains(baguettePickaxe)) {
			inventory.add(baguettePickaxe);
		}
		
		else if(discoveredKermitPickaxe && !inventory.contains(kermitPickaxe)) {
			inventory.add(kermitPickaxe);
		}
		
		else if(discoveredBomb && !inventory.contains(bombPickaxe)) {
			inventory.add(bombPickaxe);
		}
		
		else if(discoveredDoge && !inventory.contains(dogePickaxe)) {
			inventory.add(dogePickaxe);
		}
		
		else if(discoveredStonks && !inventory.contains(stonksPickaxe)) {
			inventory.add(stonksPickaxe);
		}
		
		else if(discoveredRainbow && !inventory.contains(rainbowPickaxe)) {
			inventory.add(rainbowPickaxe);
		}
		else if(discoveredGold && !inventory.contains(goldPickaxe)) {
			inventory.add(goldPickaxe);
		}


	}
	
	/**
	 * Sets up the unique features of all the tools: sets their look and properties
	 */
	public void setup() {
		backPic = surface.loadImage("img/backButton.png");
		rainbowBackground = surface.loadImage("img/rainbowPickaxeBackground.png");
		background = new Sprite(surface.loadImage("img/InventoryScreenBackground.png"),0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
		hiPickaxe = new Equipment(surface.loadImage("img/Pickaxe.png"), box1X, 175, 100, 100, 25, "hi", "Common");
//		this.selectedPickaxe = hiPickaxe;

//		inventory.add(hiPickaxe);
		greatPickaxe = 	new Equipment(surface.loadImage("img/TheGreatPickaxe.png"), box2X, 175, 100, 100, 25, "GreatPickaxe", "uncommon");
//		inventory.add(greatPickaxe);
		bananaPickaxe = new Equipment(surface.loadImage("img/BananaPickaxe.png"), box3X, 175, 100, 100, 34, "BananaPickaxe", "rare");
//		inventory.add(bananaPickaxe);
		baguettePickaxe = new Equipment(surface.loadImage("img/BaguettePickaxe.png"), box4X, 175, 100, 100, 50, "BaguettePickaxe", "legendary");
//		inventory.add(baguettePickaxe);
		kermitPickaxe = new Equipment(surface.loadImage("img/KermitPickaxe.png"), box5X, 175, 100, 100, 100, "KermitPickaxe", "kermit");
//		inventory.add(kermitPickaxe);
		goldPickaxe = new Equipment(surface.loadImage("img/GoldPickaxe.png"), box1X, 325, 100, 100, 100, "GoldPickaxe", "Goldy");
		rainbowPickaxe = new Equipment(surface.loadImage("img/rainbowPickaxe.png"), box2X, 325, 100, 100, 125, "RainbowPickaxe", "Rainbowy");
		stonksPickaxe = new Equipment(surface.loadImage("img/StonksPickaxe.png"), box3X, 325, 100, 100, 167, "StonksPickaxe", "Rich");
		dogePickaxe = new Equipment(surface.loadImage("img/DogePickaxe.png"), box4X, 325, 100, 100, 250, "DogePickaxe", "Heavenly");
		bombPickaxe = new Equipment(surface.loadImage("img/BombPickaxe.png"), box5X, 325, 100, 100, 500, "BombPickaxe", "Bomb");
	}
	
	/**
	 * Detects mouse activity and act according to where the mouse clicked. Sets the character's current
	 * tool when the player clicked on a tool
	 */
	public void mousePressed() {
		Point unscaledMouse = surface.screenCoordinatesToDrawingCoordinates(new Point(surface.mouseX, surface.mouseY));

		if (backButton.isPointInside(unscaledMouse.x, unscaledMouse.y)) {
			super.playSound(super.TAP);

			surface.switchScreen(lastScreen);
		}
		
		else if(places.get(0).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(hiPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(hiPickaxe);
		}
		else if(places.get(1).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(greatPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(greatPickaxe);
		}
		
		//For bananaPickaxe
		else if(places.get(2).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(bananaPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(bananaPickaxe);
		}
		//For baguettePickaxe
		else if(places.get(3).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(baguettePickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(baguettePickaxe);
		}
		
		//For kermitPickaxe
		else if(places.get(4).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(kermitPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(kermitPickaxe);
		}
		
		//For goldPickaxe
		else if(places.get(5).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(goldPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(goldPickaxe);
		}
		
		//For rainbowPickaxe
		else if(places.get(6).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(rainbowPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(rainbowPickaxe);
		}
		//For stonksPickaxe
		else if(places.get(7).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(stonksPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(stonksPickaxe);
		}
		//For dogePickaxe
		else if(places.get(8).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(dogePickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(dogePickaxe);
		}
		//For bombPickaxe
		else if(places.get(9).isPointInside(unscaledMouse.x, unscaledMouse.y) && inventory.contains(bombPickaxe)) {
			super.playSound(super.TAP);

			player.setEquipment(bombPickaxe);
		}


	}
	
	/**
	 * Remembers the index of the screen that led to InventoryScreen so that
	 * when the player clicks the BackButton, it will direct the player
	 * back to the right screen
	 * @param screen The index of the screen in the screen arraylist that exist within the DrawingSurface
	 */
	public static void changeLastScreen(int screen) {
		lastScreen = screen;
	}
	
	/**
	 * Adds an equipment to the list of equipments that the player found
	 * @param input The new equipment that the player found and is to be added to the list
	 */
	public static void addToInventory(Equipment input) {
		equipmentObtained.add(input);
	}
	
	

}