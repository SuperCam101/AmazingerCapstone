package Screen;
/**
 * Changes the screen from MenuScreen, StatsScreen, NormalMine, RichMine, and InventoryScreen.
 */
public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int MINE_SCREEN = 1;
	public static final int INVENTORY_SCREEN = 2;
	public static final int STATS_SCREEN = 3;
	public static final int MINE_SCREEN_2 = 4;
	
	/**
	 * Switches between screens
	 * @param i The screen to be switched to
	 */
	public void switchScreen(int i);

}
