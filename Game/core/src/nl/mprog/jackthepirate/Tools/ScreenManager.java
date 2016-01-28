package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The ScreenManager class is used to switch the different AbstractScreens, using the ScreenEnum
 * as a reference.
 *
 * Source: http://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/
 */

public class ScreenManager {
        private static ScreenManager instance;

        // Reference to game
        private Game game;

        private ScreenManager() {
            super();
        }

        // Get new instance
        public static ScreenManager getInstance() {
            if (instance == null) {
                instance = new ScreenManager();
            }
            return instance;
        }

        // Initialization with the game class
        public void initialize(Game game) {
            this.game = game;
        }

        // Show in the game the screen which enum type is received
        public void showScreen(ScreenEnum screenEnum, Object... params) {

            // Get current screen to dispose it
            Screen currentScreen = game.getScreen();

            // Show new screen
            AbstractScreen newScreen = screenEnum.getScreen();
            newScreen.buildStage();
            game.setScreen(newScreen);

            // Dispose previous screen
            if (currentScreen != null) {
                currentScreen.dispose();
            }
        }
    }

