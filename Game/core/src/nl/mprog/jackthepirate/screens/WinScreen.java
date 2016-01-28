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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;


/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * If Jack wins the game, the Winscreen offers the player a few buttons,
 * either starting a new game or going back to the main-menu.
 */


public class WinScreen extends AbstractScreen{

    public Stage stage;
    public static Skin skin;

    // Create a basic skin
    public void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3, 3);
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(100, 50, Pixmap.Format.RGBA4444);
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

    }



    @Override
    public void show() {

        // Create a stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events


        // Create texture for background
        Texture texture = new Texture("winbackground.png");
        TextureRegion textureRegion = new TextureRegion(texture,Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        textureRegion.setRegionWidth(720);
        textureRegion.setRegionHeight(1280);
        Image background = new Image(textureRegion);
        stage.addActor(background);

        // Create table
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        // Create buttons
        createBasicSkin();
        TextButton newGameButton = new TextButton("  PLAY AGAIN? ", skin);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainActivity.newGame();
            }
        });
        TextButton howToButton = new TextButton("MAIN MENU", skin);
        howToButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 ,
                Gdx.graphics.getHeight()/2);
        howToButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8,
                (Gdx.graphics.getHeight()/2) -75);

        // Add buttons to table
        table.add(newGameButton);
        table.row();
        table.add(howToButton);


        // Add table to stage
        stage.addActor(table);



    }


    public WinScreen() {
        super();
    }


    @Override
    public void buildStage() {
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw stage
        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        // Resize if necessary
        stage.getViewport().update(width, height, true);

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
        // Dispose data
        stage.dispose();
    }
}
