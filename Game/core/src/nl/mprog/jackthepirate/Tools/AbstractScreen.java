package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The Abstract-Screen class is used for defining other AbstractScreens.
 * This way all the AbstractScreens get called in the same matter.
 */

public abstract class AbstractScreen extends Stage implements Screen {

    // Setting camera for the AbstractScreen
    protected AbstractScreen() {
        super( new StretchViewport(320.0f, 240.0f, new OrthographicCamera()) );
    }
    public abstract void buildStage();

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw screen
        super.act(delta);
        super.draw();
    }

    @Override
    public void show() {
        // Set that the screen can handle input for buttons
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        // Resize if necessary
        getViewport().update(width, height, true);
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}