package nl.mprog.jackthepirate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The MainActivity is where the app starts. All the global variables are defined.
 * The MainMenu Screen is initialized from here. Also it contains a function to start a new game.
 */


public class MainActivity extends Game {

	public static SpriteBatch batch;
	public static Preferences prefs;

	// Size virtual screen and pixels per meter
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 1280;
	public static final float PPM = 16f;

	public static boolean onPlatform = false;
	public static boolean dead = false;
	public static boolean win = false;
	public static boolean noHighscores;
	public static boolean toggleMusic = true;

	public static boolean featherRedPicked = false;
	public static boolean featherBluePicked = false;
	public static boolean featherGreenPicked = false;

	public static boolean up_red = true;
	public static boolean down_red = false;
	public static boolean right_green = true;
	public static boolean left_green = false;
	public static boolean right_blue = true;
	public static boolean left_blue = false;

	public static String TITLE = "Jack the Pirate";



	@Override
	public void create () {
		// Set menu screen
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

		// Clearing the prefs
		prefs = Gdx.app.getPreferences("prefs");
		prefs.clear();

		// Set containers for the three highscores
		prefs.putString("highscore_1", "empty");
		prefs.putString("highscore_2", "empty");
		prefs.putString("highscore_3", "empty");
		prefs.flush();
	}

	public static void newGame(){
		// Start new game with spritebatch
		batch = new SpriteBatch();
		ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
	}



	@Override
	public void render () {
		super.render();
	}
}
