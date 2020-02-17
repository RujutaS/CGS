

public interface ScreenSwitcher {
	public static final int SCREEN1 = 0;
	public static final int GAMESCREEN = 1;
	public static final int HELPSCREEN = 2;
	public static final int STORESCREEN = 3;
	public static final int SCOREBOARDSCREEN = 4;
	
	
	public void switchScreen(int i);
}
