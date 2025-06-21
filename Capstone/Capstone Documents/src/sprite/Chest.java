package sprite;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Block that when mined has a random chance to give the player
 * a piece of equipment out of a pool of different equipment.
 * 
 */
public class Chest extends Block{
	
	private Equipment[][] possibleEquipment;
	private String[] names;
	
	/**
	 * Creates a chest at (x, y) in the window with dimensions width x height that is
	 * visually represented in the window by img.
	 * 
	 * @param img The image that visually represents the chest in the window.
	 * @param x The x-coordinate of the chest in the window.
	 * @param y The y-coordinate of the chest in the window.
	 * @param w The width of the chest.
	 * @param h The height of the chest.
	 */
	public Chest(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h, 0, 0);
		
	}


//	public Equipment gamble(Gambledorf c) {
//		String n = names[(int)(Math.random()*names.length)];
//		Equipment e;
//		if(c.getCoins()<100)
//			e = null;
//		else {
//			c.setCoins(c.getCoins()-100);
//			
//			if(n.equals(names[0])) 
//				e = new Equipment(surface.loadImage("img/Gambledorf.png"), (int)c.getX(), (int)c.getY(), 50, 50, 5, 2, n);
//			else if(n.equals(names[1]))
//				e = new Equipment(10, 2, n);
//			else
//				e = new Equipment(50, 2, n);
//
//		}
//		
//		
//		return e;
//	}

	public Equipment gamble(Gambledorf c, PApplet surface, int mineNum) {
		double chance = Math.random();
		Equipment e;
		if(mineNum == 1) {

			if(c.getCoins()<100)
				e = null;
			else {
				
				c.setCoins(c.getCoins()-100);
	//			System.out.println(chance);
					if(chance>0&&chance<=0.1)
						e = new Equipment(surface.loadImage("img/KermitPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 100, "KermitPickaxe", "Kermit");
					else if(chance>0.1&&chance<=0.25)
						e = new Equipment(surface.loadImage("img/BaguettePickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 50, "BaguettePickaxe", "Legendary");
					else if(chance>0.25&&chance<=0.5)
						e = new Equipment(surface.loadImage("img/BananaPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 34, "BananaPickaxe", "Rare");
					else if(chance>0.5&&chance<1)
						e = new Equipment(surface.loadImage("img/TheGreatPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 25, "TheGreatPickaxe", "Uncommon");
					else
						e = null;
	
				}
		}
		

		else{

			if(c.getCoins()<500)
				e = null;
			else {
				
				c.setCoins(c.getCoins()-500);
				if(chance>0&&chance<=0.1)
					e = new Equipment(surface.loadImage("img/BombPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 500, "BombPickaxe", "Bomb");
	
				else if(chance>0.1&&chance<=0.25)
					e = new Equipment(surface.loadImage("img/DogePickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 250, "DogePickaxe", "Heavenly");
	
				else if(chance>0.25&&chance<=0.5)
					e = new Equipment(surface.loadImage("img/StonksPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 167, "StonksPickaxe", "Rich");
				
				else if(chance>0.5&&chance<1)
					e = new Equipment(surface.loadImage("img/rainbowPickaxe.png"), (int)c.getX(), (int)c.getY(), 50, 50, 125, "RainbowPickaxe", "Rainbowy");
				else
					e = null;
			}
		}

		return e;
	}
//	public void draw(PApplet surface) {
//		surface.fill(0, 255, 0);
//		surface.rect((float) super.getX(), (float) super.getY(), (float) 50, (float) 50);
//
//}


}
