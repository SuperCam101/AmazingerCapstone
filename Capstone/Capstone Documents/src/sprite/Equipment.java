package sprite;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Represents the equipment that the Gambledorf uses, with each piece of equipment having a different mine power value, and rarities
 */
public class Equipment extends Sprite{
	private int miningPower;
	private String rarity;
//	private double luck; 
	private String name;
	
	/**
	 * Creates an object of type Equipment
	 */
	public Equipment(PImage img, int x, int y, int width, int height, int miningPower, String name, String rarity) {
		super(img, x, y, width, height);
		this.miningPower = miningPower;
//		this.luck = luck;
		this.name = name;
		this.rarity = rarity;
		
	}
	/**
	 * Gets the rarity of the equipment
	 * @return The rarity name of the equipment
	 */
	public String getRarity() {
		return rarity;
	}
	
	/**
	 * Gets the mining power of the equipment
	 * @return The number of the mining power of the equipment
	 */
	public int getMiningPower() {
		return miningPower;
	}
	
	/**
	 * Gets the name of the equipment
	 * @return The name of the equipment
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the mining power of the equipment
	 * @param miningPower the mining power of the equipment
	 */
	public void setMiningPower(int miningPower) {
		this.miningPower = miningPower;
	}
	
}
