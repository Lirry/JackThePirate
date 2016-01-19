package nl.mprog.jackthepirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;


import javax.sound.midi.MidiChannel;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.AbstractScreen;
import nl.mprog.jackthepirate.Tools.InteractiveObject;
import nl.mprog.jackthepirate.Tools.WorldContactlistener;
import nl.mprog.jackthepirate.scenes.HUD;
import nl.mprog.jackthepirate.sprites.Feather;
import nl.mprog.jackthepirate.sprites.Jack;
import nl.mprog.jackthepirate.sprites.Platform;
import nl.mprog.jackthepirate.sprites.PlatformGreen;


public class PlayScreen extends AbstractScreen implements Screen {

    private MainActivity game;
    private OrthographicCamera gamecam;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private float unitScale = 1 / 16f;

    private Jack player;
    private Platform platform;
    private PlatformGreen platform_green;


    private float accelX;
    boolean flip;



    public PlayScreen(MainActivity game){

        // Initiates the game
        this.game = game;

        // loading the map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1revisedmetveer.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        // loading gamecam
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false,3 ,12);
        gamecam.update();

        // loading hud
        hud = new HUD(game.batch);


        // Box2D world (graphics)
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        // New Jack
        player = new Jack(world);
        platform = new Platform(world);
        platform_green = new PlatformGreen(world);

        // get the z axis for controls
        accelX = Gdx.input.getAccelerometerX();

        // Setting contactlistener for collision
        world.setContactListener(new WorldContactlistener());

        // Setting flip for jack
        flip = player.isFlipX();


        // Bodydef for ground
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



        // The ground as an object is defined
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MainActivity.PPM, (rect.getY() + rect.getHeight() / 2) / MainActivity.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MainActivity.PPM, rect.getHeight() / 2 / MainActivity.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        // The feathers as an object is defined
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.DynamicBody;

            new Feather(world, map, rect);
        }

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched() && player.b2body.getLinearVelocity().y == 0 || Gdx.input.isTouched() && MainActivity.onPlatform){
            player.b2body.applyLinearImpulse(new Vector2(0, 9f), player.b2body.getWorldCenter(), true);
            MainActivity.onPlatform = false;
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        if(Gdx.input.getRoll()> 8 && player.b2body.getLinearVelocity().x <= 3 ){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            if (!flip) {
//                player.rotate(-5);
//                player.setOrigin(player.getWidth()/2, player.getHeight()/2);
                player.setFlip(true, false);
                flip = true;
            }
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        if(Gdx.input.getRoll()< -8 && player.b2body.getLinearVelocity().x >= -3){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            if (flip) {
                player.setFlip(false, false);
                flip = false;
            }
        }
        if (MainActivity.featherpicked != 10){
            if (platform.b2body.getPosition().y > 10) {
                platform.b2body.setLinearVelocity(0, -0.9f);
                Gdx.app.log("going down", "down");
            }
            else if (platform.b2body.getPosition().y <= 8){
                platform.b2body.setLinearVelocity(0, 0.9f);
                Gdx.app.log("going up", "up");
            }
            if (platform_green.b2body.getPosition().x > 11){
                platform_green.b2body.setLinearVelocity(-0.8f, 0);
            }
            else if (platform_green.b2body.getPosition().x <= 4){
                platform_green.b2body.setLinearVelocity(0.8f, 0);
            }
        }

    }




    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        player.update(dt);
        platform.update(dt);
        platform_green.update(dt);
        hud.update(dt);

        renderer.setView(gamecam);

        gamecam.position.x = player.b2body.getPosition().x;
        if(player.b2body.getPosition().y > 5){
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
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        platform.draw(game.batch);
        platform_green.draw(game.batch);
        game.batch.end();


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        //gameport.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
