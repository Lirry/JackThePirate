package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;



public abstract class SplashScreen implements Screen {

    private Sprite splashSprite;
    private SpriteBatch splashBatch;
    private long startTime;

    @Override
    public void show() {
        splashBatch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("jack.png"));
        splashSprite = new Sprite(texture);
        splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        startTime = TimeUtils.millis();

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



