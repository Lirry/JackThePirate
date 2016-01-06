package nl.mprog.jackthepirate.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nl.mprog.jackthepirate.MainActivity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MainActivity.V_WIDTH;
		config.height = MainActivity.V_HEIGHT;
		config.title = MainActivity.TITLE;
		new LwjglApplication(new MainActivity(), config);
	}
}
