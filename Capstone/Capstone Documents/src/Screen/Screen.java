package Screen;

import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
/**
 * Ensures other screen subclasses have dimensions, and also allows each screen to have music and sound effects
 */
public abstract class Screen implements JayLayerListener{

	public final int SCREEN_WIDTH, SCREEN_HEIGHT;
	public final int PLAYER_APPEAR, MINE, BOMB, CHEST, TAP;
	private JayLayer sound;
/**
 * Creates a Screen object with dimensions width and height
 * @param width The width of the Screen
 * @param height The height of the Screen
 */
	public Screen(int width, int height) {
		this.SCREEN_WIDTH = width;
		this.SCREEN_HEIGHT = height;
		PLAYER_APPEAR = 0;
		MINE = 1;
		BOMB = 2;
		CHEST = 3;
		TAP = 4;
		String[] soundEffects = new String[]{"appearInMine.mp3","mining.mp3","openBomb.mp3","openChest.mp3", "tap.mp3"};
		sound=new JayLayer("audio/","audio/",false);
		sound.addSoundEffects(soundEffects);
		sound.addJayLayerListener(this);
	}
	
	/**
	 * Gets the width of the Screen
	 * @return SCREEN_WIDTH The width of the Screen
	 */
	public int getWidth() {
		return SCREEN_WIDTH;
	}
	
	/**
	 * Gets the height of the Screen
	 * @return SCREEN_HEIGHT The height of the Screen
	 */
	public int getHeight() {
		return SCREEN_HEIGHT;
	}

	/**
	 * Sets up the features of the screen
	 */
	public void setup() {
		
	}
	
	/**
	 * draws the screen on the drawing surface
	 */
	public void draw() {
		
	}
	
	/**
	 * acts according to the position of the mouse click
	 */
	public void mousePressed() {
		
	}

	/**
	 * acts according to the mouse movement
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * acts according to the mouse movement when it is dragged
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * acts when the mouse is released
	 */
	public void mouseReleased() {
		
	}
	
	/**
	 * Plays the sound effect at the specified index
	 * @param i The index of the sound effect
	 */
	public void playSound(int i) {
		sound.playSoundEffect(i);

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
