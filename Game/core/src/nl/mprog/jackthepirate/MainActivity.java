package nl.mprog.jackthepirate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.mprog.jackthepirate.screens.PlayScreen;

public class MainActivity extends Game {

	public SpriteBatch batch;

	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 900;

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
