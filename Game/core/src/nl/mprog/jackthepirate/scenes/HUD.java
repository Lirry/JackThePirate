package nl.mprog.jackthepirate.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.mprog.jackthepirate.MainActivity;


public class HUD {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownlabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;

    public HUD(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MainActivity.V_WIDTH, MainActivity.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownlabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));
        levelLabel = new Label("level 1", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(timeLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);
    }

}
