package nl.mprog.jackthepirate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.mprog.jackthepirate.screens.PlayScreen;
import nl.mprog.jackthepirate.screens.SplashScreen;

public class MainActivity extends Game {

	public SpriteBatch batch;

	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 1280;
	public static final float PPM = 16f;

	public static final String TITLE = "Jack the Pirate";


	@Override
	public void create () {

		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
}
