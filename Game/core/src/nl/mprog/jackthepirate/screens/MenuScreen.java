package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;


// The skin-building method comes from http://www.tempoparalelo.com/blog/?p=76


public class MenuScreen extends AbstractScreen{

    public Stage stage;
    public Skin skin;
    private Texture texture;

    public void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(100, 50, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.BROWN);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }



    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events


        texture = new Texture("backgroundmenunieuw.png");
        TextureRegion textureRegion = new TextureRegion(texture,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        textureRegion.setRegionWidth(720);
        textureRegion.setRegionHeight(1280);
        Image background = new Image(textureRegion);
        stage.addActor(background);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        createBasicSkin();
        TextButton newGameButton = new TextButton("NEW GAME", skin);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainActivity.newGame();
            }
        });
        TextButton howToButton = new TextButton("HOW TO PLAY", skin);
        howToButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.HOW_TO);
            }
        });

        TextButton highScoresButton = new TextButton("HIGHSCORES", skin);
        highScoresButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.HIGHSCORES);
            }
        });
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        howToButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8,(Gdx.graphics.getHeight()/2) -75);
        highScoresButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/8,(Gdx.graphics.getHeight()/2) -150);

        table.add(newGameButton).growX();
        table.row();
        table.add(howToButton);
        table.row();
        table.add(highScoresButton);

        stage.addActor(table);
//        stage.addActor(newGameButton);
//        stage.addActor(howToButton);
//        stage.addActor(highScoresButton);



    }


    public MenuScreen() {
        super();
    }


    @Override
    public void buildStage() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
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
        stage.dispose();
    }
}
