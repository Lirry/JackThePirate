package nl.mprog.jackthepirate.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.screens.MenuScreen;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
* The HUD displays the time for the player.
*/

public class HUD implements Disposable{

    public Stage stage;
    private Viewport viewport;

    public static Integer worldTimer;
    private float timeCount;

    Label scoreLabel;
    Label timeLabel;

    public HUD(SpriteBatch sb){
        // Timer and second
        worldTimer = 0;
        timeCount = 0;

        // Using same skin as main menu
        Skin skin = MenuScreen.skin;
        skin.getFont("default").getData().setScale(1,1);

        // Viewport and stage initiated
        viewport = new FitViewport(MainActivity.V_WIDTH/2, MainActivity.V_HEIGHT/2,
                new OrthographicCamera());
        stage = new Stage(viewport, sb);

        // Table initiated
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        // Setting the labels for the table
        scoreLabel = new Label(String.format("%05d", worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));

        // Adding the labels to the table
        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX();

        // Adding table to the stage
        stage.addActor(table);
    }

    public void update(float dt){
        /**
         * The update method contains the counting of the delta time, thus setting the timer.
         * 1 second is 10 points.
         */
        timeCount += dt*10;
        if (timeCount >= 1){
            worldTimer++;
            scoreLabel.setText(String.format("%05d", worldTimer));
            timeCount -= 1;
        }

    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
