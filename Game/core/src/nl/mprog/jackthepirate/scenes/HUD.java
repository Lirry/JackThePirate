package nl.mprog.jackthepirate.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;
import nl.mprog.jackthepirate.screens.MenuScreen;
import nl.mprog.jackthepirate.screens.PlayScreen;


public class HUD implements Disposable{
    public Stage stage;
    private Viewport viewport;

    public static Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;

    public HUD(SpriteBatch sb){
        worldTimer = 0;
        timeCount = 0;
        score = 0;

        Skin skin = MenuScreen.skin;
        skin.getFont("default").getData().setScale(1,1);

        viewport = new FitViewport(MainActivity.V_WIDTH/2, MainActivity.V_HEIGHT/2, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);



        scoreLabel = new Label(String.format("%05d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt){
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
