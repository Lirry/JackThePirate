package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;
import nl.mprog.jackthepirate.scenes.HUD;


public class HighscoreScreen extends AbstractScreen {



    private Preferences prefs;
    public Stage stage;
    public Skin skin;
    private Texture texture;
    private TextButton highscore_1;
    private TextButton highscore_2;
    private TextButton highscore_3;
    private String highscore_string;



    public HighscoreScreen() {
        super();
    }


    public void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2,2);
        skin = new Skin();
        skin.add("default", font);


        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/12, Pixmap.Format.RGBA8888);
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
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events


        if (MainActivity.prefs != null) {
            if (MainActivity.prefs.getString("highscore") != null) {
                    highscore_string = MainActivity.prefs.getString("highscore");
                    Gdx.app.log("highscore not null", highscore_string);
            }
        }

        texture = new Texture("backgroundmenunieuw.png");
        TextureRegion textureRegion = new TextureRegion(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image background = new Image(textureRegion);
        stage.addActor(background);
        Table table = new Table();
        table.setFillParent(true);


        createBasicSkin();
        TextButton backButton = new TextButton("Back", MenuScreen.skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, Gdx.graphics.getHeight() / 8);
        table.addActor(backButton);

        highscore_1 = new TextButton("No Score Yet!", skin);
        highscore_1.setPosition(Gdx.graphics.getWidth() / 2 - highscore_1.getWidth() / 2, Gdx.graphics.getHeight() / 8 + 350);
        highscore_2 = new TextButton("No Score Yet!", skin);
        highscore_2.setPosition(Gdx.graphics.getWidth() / 2 - highscore_1.getWidth() / 2, Gdx.graphics.getHeight() / 8 + 300);
        highscore_3 = new TextButton("No Score Yet!", skin);
        highscore_3.setPosition(Gdx.graphics.getWidth() / 2 - highscore_1.getWidth() / 2, Gdx.graphics.getHeight() / 8 + 250);


        if(highscore_string != null) {
            if (MainActivity.prefs.getString("highscore") != null) {
                if (MainActivity.prefs.getString("highscore_1").contentEquals("empty")) {
                    highscore_string = MainActivity.prefs.getString("highscore");
                    MainActivity.prefs.putString("highscore_1", highscore_string);
                    MainActivity.prefs.flush();
                } else if (MainActivity.prefs.getString("highscore_2").contentEquals("empty")) {
                    highscore_string = MainActivity.prefs.getString("highscore");
                    MainActivity.prefs.putString("highscore_2", highscore_string);
                    MainActivity.prefs.flush();
                } else if (MainActivity.prefs.getString("highscore_3").contentEquals("empty")) {
                    highscore_string = MainActivity.prefs.getString("highscore");
                    MainActivity.prefs.putString("highscore_3", highscore_string);
                    MainActivity.prefs.flush();
                }
                if(!MainActivity.prefs.getString("highscore_1").contentEquals("empty")){
                    highscore_1.setText("first place: " + MainActivity.prefs.getString("highscore_1"));
                }
                if(!MainActivity.prefs.getString("highscore_2").contentEquals("empty")){
                    highscore_2.setText("second place: " + MainActivity.prefs.getString("highscore_2"));
                }
                if(!MainActivity.prefs.getString("highscore_3").contentEquals("empty")){
                    highscore_3.setText("third place: " + MainActivity.prefs.getString("highscore_3"));
                }
            }
            highscore_string = null;
            Gdx.app.log("highscore removed", MainActivity.prefs.getString("highscore"));
        }

            table.addActor(highscore_1);
            table.addActor(highscore_2);
            table.addActor(highscore_3);
            stage.addActor(table);
        }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
