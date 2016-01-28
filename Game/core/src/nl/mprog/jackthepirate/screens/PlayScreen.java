package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.ResetGame;
import nl.mprog.jackthepirate.Tools.ScreenEnum;
import nl.mprog.jackthepirate.Tools.ScreenManager;
import nl.mprog.jackthepirate.Tools.WorldContactlistener;
import nl.mprog.jackthepirate.scenes.HUD;
import nl.mprog.jackthepirate.sprites.FeatherBlue;
import nl.mprog.jackthepirate.sprites.FeatherGreen;
import nl.mprog.jackthepirate.sprites.FeatherRed;
import nl.mprog.jackthepirate.sprites.Jack;
import nl.mprog.jackthepirate.sprites.Parrot;
import nl.mprog.jackthepirate.sprites.Platform;
import nl.mprog.jackthepirate.sprites.PlatformBlue;
import nl.mprog.jackthepirate.sprites.PlatformGreen;
import nl.mprog.jackthepirate.sprites.Spike;
import nl.mprog.jackthepirate.sprites.SpikeThree;
import nl.mprog.jackthepirate.sprites.SpikeTwo;

/**
 *
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The PlayScreen is where the entire game-engine works. It takes a map, initializes all the sprites
 * and the world. Movement, win and death of Jack is specified.
 */


public class PlayScreen extends AbstractScreen implements Screen {

    public MainActivity game;
    private OrthographicCamera gamecam;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private float unitScale = 1 / 16f;

    private Jack player;
    private Platform platform;
    private PlatformGreen platform_green;
    private PlatformBlue platform_blue;
    private Spike spike;
    private SpikeTwo spike_two;
    private SpikeThree spike_three;
    private Parrot parrot;
    private FeatherRed feather_red;
    private FeatherBlue feather_blue;
    private FeatherGreen feather_green;


    private float accelX;
    boolean flip;
    public static Music music;



    public PlayScreen(MainActivity game){

        // Start music if toggled
        if(MainActivity.toggleMusic) {
            music = Gdx.audio.newMusic(Gdx.files.internal("pirates.mp3"));
            music.setVolume(0.2f);
            music.play();
        }

        // Initiates the game
        this.game = game;

        // loading the map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tiledmap_level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        // loading gamecam
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 4, 14);
        gamecam.update();

        // loading hud
        hud = new HUD(MainActivity.batch);


        // load box2d world
        world = new World(new Vector2(0, -10), true);

        // load sprites
        player = new Jack(world);
        platform = new Platform(world);
        platform_green = new PlatformGreen(world);
        platform_blue = new PlatformBlue(world);
        spike = new Spike(world);
        spike_two = new SpikeTwo(world);
        spike_three = new SpikeThree(world);
        parrot = new Parrot(world);
        feather_red = new FeatherRed(world);
        feather_blue = new FeatherBlue(world);
        feather_green = new FeatherGreen(world);


        // get the z axis for controls
        accelX = Gdx.input.getAccelerometerX();

        // Set contactlistener for collision
        world.setContactListener(new WorldContactlistener());

        // Set flip for jack
        flip = player.isFlipX();


        // Bodydef for ground
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        // The ground as an object is defined, from the tiled-map.
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MainActivity.PPM, (rect.getY()
                    + rect.getHeight() / 2) / MainActivity.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MainActivity.PPM, rect.getHeight()
                    / 2 / MainActivity.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
    }


    @Override
    public void show() {
    }

    public void handleInput(float dt) {

        // Jack jumps
        if (Gdx.input.isTouched() && player.b2body.getLinearVelocity().y == 0 ||
                Gdx.input.isTouched() && MainActivity.onPlatform) {
            Music jump = Gdx.audio.newMusic(Gdx.files.internal("popsound.wav"));
            jump.play();
            player.b2body.applyLinearImpulse(new Vector2(0, 9f),
                    player.b2body.getWorldCenter(), true);
            MainActivity.onPlatform = false;
        }
        // Jack goes right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//        if(Gdx.input.getRoll()> 8 && player.b2body.getLinearVelocity().x <= 3 ){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0),
                    player.b2body.getWorldCenter(), true);
            if (!flip) {
                player.setFlip(true, false);
                flip = true;
            }
        }
        // Jack goes left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//        if(Gdx.input.getRoll()< -8 && player.b2body.getLinearVelocity().x >= -3){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),
                    player.b2body.getWorldCenter(), true);
            if (flip) {
                player.setFlip(false, false);
                flip = false;
            }
        }

        if (MainActivity.featherRedPicked) {
            // Remove feather
            feather_red.b2body.setActive(false);
            feather_red.setBounds(0, 0, 0, 0);

            // Other feathers get spawned again
            feather_blue.b2body.setActive(true);
            feather_blue.setBounds(0, 0, 16 / MainActivity.PPM, 16 / MainActivity.PPM);

            feather_green.b2body.setActive(true);
            feather_green.setBounds(0, 0, 16 / MainActivity.PPM, 16 / MainActivity.PPM);
        }

        // Move platform
        if(MainActivity.featherRedPicked) {
            if (MainActivity.up_red) {
                platform.b2body.setLinearVelocity(0, 0.9f);
                if (platform.b2body.getPosition().y >= 11) {
                    MainActivity.up_red = false;
                    MainActivity.down_red = true;
                }
            }
            if (MainActivity.down_red) {
                platform.b2body.setLinearVelocity(0, -0.9f);
                if (platform.b2body.getPosition().y <= 7) {
                    MainActivity.down_red = false;
                    MainActivity.up_red = true;
                }
            }
        }
        else{
            platform.b2body.setLinearVelocity(0,0);
        }

        if (MainActivity.featherGreenPicked){
            // Remove feather
            feather_green.b2body.setActive(false);
            feather_green.setBounds(0,0,0,0);

            // Other feathers get spawned again
            feather_red.b2body.setActive(true);
            feather_red.setBounds(0, 0, 16 / MainActivity.PPM, 16 / MainActivity.PPM);

            feather_blue.b2body.setActive(true);
            feather_blue.setBounds(0, 0, 16 / MainActivity.PPM, 16 / MainActivity.PPM);
        }
        // Move platform
        if(MainActivity.featherGreenPicked){
            if (MainActivity.right_green) {
                    platform_green.b2body.setLinearVelocity(0.9f, 0);
                if(platform_green.b2body.getPosition().x >= 10){
                    MainActivity.right_green = false;
                    MainActivity.left_green = true;
                }
            } if (MainActivity.left_green) {
                platform_green.b2body.setLinearVelocity(-0.9f, 0);
                if(platform_green.b2body.getPosition().x <= 4){
                    MainActivity.left_green = false;
                    MainActivity.right_green = true;
                }
            }
        }
        else {
            platform_green.b2body.setLinearVelocity(0, 0);
        }

        if (MainActivity.featherBluePicked){
            // Remove feather
            feather_blue.b2body.setActive(false);
            feather_blue.setBounds(0, 0, 0, 0);

            // Other feathers get spawned again
            feather_red.b2body.setActive(true);
            feather_red.setBounds(0, 0, 16/ MainActivity.PPM, 16 / MainActivity.PPM);

            feather_green.b2body.setActive(true);
            feather_green.setBounds(0, 0, 16/ MainActivity.PPM, 16/MainActivity.PPM);

       }
        // Move platform
        if (MainActivity.featherBluePicked){
            if (MainActivity.right_blue) {
                platform_blue.b2body.setLinearVelocity(0.9f, 0);
                if(platform_blue.b2body.getPosition().x >= 5){
                MainActivity.right_blue = false;
                MainActivity.left_blue = true;
            }
            }
            if (MainActivity.left_blue) {
                platform_blue.b2body.setLinearVelocity(-0.9f, 0);
                if(platform_blue.b2body.getPosition().x <= 2){
                    MainActivity.left_blue = false;
                    MainActivity.right_blue = true;
                }
            }
        }
        else {
            platform_blue.b2body.setLinearVelocity(0, 0);
        }


    }




    public void update(float dt){
        // Update the world, based on delta time
        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        player.update(dt);
        platform.update(dt);
        platform_green.update(dt);
        platform_blue.update(dt);
        spike.update(dt);
        spike_two.update(dt);
        spike_three.update(dt);
        parrot.update(dt);
        feather_red.update(dt);
        feather_blue.update(dt);
        feather_green.update(dt);
        hud.update(dt);
        renderer.setView(gamecam);

        // Let camera follow player
        gamecam.position.x = player.b2body.getPosition().x;
        if(player.b2body.getPosition().y > 7){
            gamecam.position.y = player.b2body.getPosition().y;
        }
        gamecam.update();


    }

    @Override
    public void buildStage() {

    }


    @Override
    public void render(float delta) {
        update(delta);



        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render the map
        renderer.setView(gamecam);
        renderer.render();

        // render the box2d
        MainActivity.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // Set gamecam with HUD
        MainActivity.batch.setProjectionMatrix(gamecam.combined);

        // Load all the assest from spritebatch
        MainActivity.batch.begin();
        player.draw(MainActivity.batch);
        platform.draw(MainActivity.batch);
        platform_green.draw(MainActivity.batch);
        platform_blue.draw(MainActivity.batch);
        spike.draw(MainActivity.batch);
        spike_two.draw(MainActivity.batch);
        spike_three.draw(MainActivity.batch);
        parrot.draw(MainActivity.batch);
        feather_red.draw(MainActivity.batch);
        feather_blue.draw(MainActivity.batch);
        feather_green.draw(MainActivity.batch);
        MainActivity.batch.end();


        // If Jack is dead
        if (MainActivity.dead){
            Music dead = Gdx.audio.newMusic
                    (Gdx.files.internal("musical_piano_strings_stab_minor.mp3"));
            dead.play();
            MainActivity.dead = false;

            // Reset game variables
            ResetGame.ResetGame();

            // Set game over screen
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
        }
        // If Jack wins
        if (MainActivity.win){
            // Play win-music
            Music win = Gdx.audio.newMusic(Gdx.files.internal("cartoon_parrot_squawk.mp3"));
            win.play();

            // Reset game variables
            ResetGame.ResetGame();

            // Put highscore in prefs
            MainActivity.prefs.putString("highscore", HUD.worldTimer.toString());
            MainActivity.prefs.flush();

            // New winscreen
            ScreenManager.getInstance().showScreen(ScreenEnum.WIN);

            // Set booleans
            MainActivity.win = false;
            MainActivity.noHighscores = true;
        }

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
        // Dispose before exit
        map.dispose();
        renderer.dispose();
        world.dispose();
        hud.dispose();
        if(MainActivity.toggleMusic) {
            music.dispose();
        }
    }
}
