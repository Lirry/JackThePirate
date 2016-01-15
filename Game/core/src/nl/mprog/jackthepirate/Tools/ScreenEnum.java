package nl.mprog.jackthepirate.Tools;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.screens.HighscoreScreen;
import nl.mprog.jackthepirate.screens.HowToScreen;
import nl.mprog.jackthepirate.screens.MenuScreen;
import nl.mprog.jackthepirate.screens.PlayScreen;


public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen() {
            return new MenuScreen();
        }
    },
    HOW_TO {
        public AbstractScreen getScreen() {
            return new HowToScreen();
        }
    },
    HIGHSCORES {
        public AbstractScreen getScreen() {
            return new HighscoreScreen();
        }
    },
    GAME{
        public AbstractScreen getScreen(){
                return new PlayScreen(new MainActivity());
            }
    };


    public abstract AbstractScreen getScreen();
}
