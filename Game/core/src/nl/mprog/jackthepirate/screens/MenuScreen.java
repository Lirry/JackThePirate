package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;


public class MenuScreen extends AbstractScreen{

    private Sprite splashSprite;
    private SpriteBatch splashBatch;
    public Stage stage;
    private Viewport viewport;



    @Override
    public void show() {
        splashBatch = new SpriteBatch();
        viewport = new FitViewport(MainActivity.V_WIDTH/2, MainActivity.V_HEIGHT/2, new OrthographicCamera());
        stage = new Stage(viewport, splashBatch);
        Texture texture = new Texture(Gdx.files.internal("jackmediumhq.png"));
        splashSprite = new Sprite(texture);
        splashSprite.setSize(100, 100);
        splashSprite.setPosition(300, 500);

        Table table = new Table();
        table.top();
        table.setFillParent(true);




    }

    public MenuScreen() {
        super();
    }

    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        splashBatch.begin();
        splashSprite.draw(splashBatch);
        splashBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashBatch.dispose();
    }
}
