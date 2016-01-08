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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.scenes.HUD;
import nl.mprog.jackthepirate.sprites.Jack;


public class PlayScreen implements Screen {

    private MainActivity game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private HUD hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    //private Jack player;



    public PlayScreen(MainActivity game){

        // Initiates the game, camera, viewport and HUD
        this.game = game;
        gamecam = new OrthographicCamera();


        gameport = new FitViewport(MainActivity.V_WIDTH/ MainActivity.PPM, MainActivity.V_HEIGHT/ MainActivity.PPM, new OrthographicCamera());

        hud = new HUD(game.batch);

        // loading the map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1revised.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MainActivity.PPM);


        //initially set our gamecam to be centered correctly at the start of of map
        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2, 0);

        // Box2D world (graphics)
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        // New Jack
       // player = new Jack(world);


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        // The ground as an object is defined
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/MainActivity.PPM, (rect.getY() + rect.getHeight()/2) /MainActivity.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

//        // The feathers as an object is defined
//        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth()/2)/MainActivity.PPM, (rect.getY() + rect.getHeight()/2) /MainActivity.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }
//
//        // The platforms as an object is defined
//        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth()/2)/MainActivity.PPM, (rect.getY() + rect.getHeight()/2) /MainActivity.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
//        if (Gdx.input.isTouched()){
//            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& player.b2body.getLinearVelocity().x <= 2){
//            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&& player.b2body.getLinearVelocity().x <= -2){
//            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
//        }

    }

    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        //gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render the map
        renderer.render();

        // render the box2d
        b2dr.render(world, gamecam.combined);



        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
            gamecam.viewportWidth = width;
            gamecam.viewportHeight = height;
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

    }
}
