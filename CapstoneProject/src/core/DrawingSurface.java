package core;

import Screen.Screen;

import java.awt.Point;
import java.util.ArrayList;

import Screen.InventoryScreen;
import Screen.MenuScreen;
import Screen.MineScreen;
import Screen.NormalMine;
import Screen.RichMine;
import Screen.ScreenSwitcher;
import Screen.StatsScreen;
import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
import processing.core.PApplet;
import sprite.Equipment;
import sprite.Gambledorf;

/**
 * A surface that can draw and display things and play sounds
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher, JayLayerListener {

	private ArrayList<Screen> screens;
	private Screen activeScreen;
	private ArrayList<Integer> keys;

	public float ratioX, ratioY;
	private JayLayer sound;

	/**
	 * Creates an object of type DrawingSurface
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		keys = new ArrayList<Integer>();
		
		
		String[] songs = new String[]{"song1.mp3"};

		sound=new JayLayer("audio/","audio/",false);
		sound.addPlayList();
		sound.addSongs(0,songs);
		sound.changePlayList(0);
		sound.addJayLayerListener(this);
		sound.nextSong();
		
	}
	
	/**
	 * Sets up the arraylist of screens
	 */
	public void setup() {
		Gambledorf character = new Gambledorf(null, 400, 42, null, this);

		StatsScreen stats = new StatsScreen(this);
		RichMine m2 = new RichMine(this, stats, character);

		NormalMine mine = new NormalMine(this, stats, character);

//		mine.setup();
		InventoryScreen inventory = new InventoryScreen(this, character, stats);

		MenuScreen menu = new MenuScreen(this, stats, mine, m2);

		screens.add(menu);
		screens.add(mine);
		screens.add(inventory);
		screens.add(stats);
		screens.add(m2);

		activeScreen = screens.get(0);
		
		for (Screen s : screens)
			s.setup();
		
		
	}

	/**
	 * Sets the size of the window displayed to 800x600
	 */
	public void settings() {
		setSize(800,600);
	}

	/**
	 * Draws the active screen and increase the time played
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.SCREEN_WIDTH;
		ratioY = (float)height/activeScreen.SCREEN_HEIGHT;

		push();
		scale(ratioX, ratioY);

		activeScreen.draw();

		pop();
		StatsScreen x = (StatsScreen)screens.get(3);
		x.increaseTime();
//		MineScreen x = (MineScreen)screens.get(1);
//		InventoryScreen y = (InventoryScreen)screens.get(2);
//		Equipment z = y.getSelectedPickaxe();
//		z.setHeight(40);
//		z.setWidth(40);
//		if()

	}

	public void keyPressed() {
		if (!keys.contains(keyCode))
			keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	/**
	 * Acts based on the key pressed
	 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(Integer.valueOf(keyCode));
		
		
		if(key == ' ') {
			MineScreen x = (MineScreen)screens.get(1);
			x.setShowButton(!x.getShowButton());
		}

	}
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	public void mousePressed() {
		activeScreen.mousePressed();
	}

	public void mouseMoved() {
		activeScreen.mouseMoved();
	}

	public void mouseDragged() {
		activeScreen.mouseDragged();
	}

	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	public Point drawingCoordinatesToScreenCoordinates(Point drawing) {
		return new Point((int)(drawing.getX()*ratioX), (int)(drawing.getY()*ratioY));
	}

	public Point screenCoordinatesToDrawingCoordinates(Point screen) {
		return new Point((int)(screen.getX()/ratioX) , (int)(screen.getY()/ratioY));
	}

	/**
	 * Switches between screens
	 * @param i The index of the screen in the arraylist to be switched to 
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
//		if(activeScreen instanceof MineScreen) {
//			sound.stopSong();
//		}
	}
	@Override
	public void musicStarted() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void musicStopped() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void playlistEnded() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void songEnded() {
		// TODO Auto-generated method stub
		
	}



}