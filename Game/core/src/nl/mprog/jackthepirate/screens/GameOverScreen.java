package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * If the player is dead, the GameOverScreen prompts the player if they want to try again or want
 * to return to the main menu.
 */


public class GameOverScreen extends AbstractScreen{
    public Stage stage;
    public Skin skin;


    // Create a basic skin
    public void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2, 2);
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/2,100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.FIREBRICK);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Create a textbox style
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    public GameOverScreen() {
        super();
    }

    @Override
    public void buildStage() {
    }

    @Override
    public void render(float delta) {
        // Clears the screen
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draws the stage on the screen
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        // Create a new stage and that it can handle events
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        // Creat a texture for background
        Texture texture = new Texture("gameoverbackground.png");
        TextureRegion textureRegion = new TextureRegion(texture,Gdx.graphics.getWidth()
                ,Gdx.graphics.getHeight());
        Image background = new Image(textureRegion);
        stage.addActor(background);

        // Create a table
        Table table = new Table();
        table.top();
        table.setFillParent(true);


        // Create buttons with basic skin
        createBasicSkin();
        TextButton tryAgainkButton = new TextButton("TRY AGAIN?", skin);
        TextButton menuButton = new TextButton("MAIN MENU", skin);

        tryAgainkButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
            }
        });

        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });

        // Setting positions of buttons, adding to the table and stage
        menuButton.setPosition(Gdx.graphics.getWidth() / 2 - menuButton.getWidth() / 2
                , Gdx.graphics.getHeight() / 4);
        tryAgainkButton.setPosition(Gdx.graphics.getWidth()/2 - menuButton.getWidth()/2
                , Gdx.graphics.getHeight()/4 + 100);
        table.addActor(tryAgainkButton);
        table.addActor(menuButton);
        stage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
