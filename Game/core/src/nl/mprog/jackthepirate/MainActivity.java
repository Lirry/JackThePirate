package nl.mprog.jackthepirate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;
public class MainActivity extends Game {

	public static SpriteBatch batch;

	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 1280;
	public static final float PPM = 16f;

	public static int featherpicked = 3;
	public static boolean onPlatform = false;
	public static boolean dead = false;
	public static boolean win = false;
	public static boolean fromMenu;

	public static final short DEFAULT_BIT = 1;
	public static final short JACK_BIT = 2;
	public static final short FEATHER_BIT = 4;
	public static final short SPIKE_BIT = 8;
	public static final short DESTROYED_BIT = 32;
	public static final String TITLE = "Jack the Pirate";
	public static Preferences prefs;




	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
		prefs = Gdx.app.getPreferences("prefs");
		prefs.clear();
		prefs.putString("highscore_1", "empty");
		prefs.putString("highscore_2", "empty");
		prefs.putString("highscore_3", "empty");
		prefs.flush();
	}

	public static void newGame(){
		batch = new SpriteBatch();
		ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
	}



	@Override
	public void render () {
		super.render();
	}
}
