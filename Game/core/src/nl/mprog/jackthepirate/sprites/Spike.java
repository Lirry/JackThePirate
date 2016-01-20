package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.InteractiveObject;


public class Spike extends Sprite {
    public World world;
    public Body b2body;
    public Texture spikeSprite;


    public Spike(World world){
        this.world = world;
        defineSpike();
        spikeSprite = new Texture("spike.png");
        setBounds(0, 0, 16/ MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(spikeSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void defineSpike(){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        float verticles[] = new float[]{
                -0.5f, -0.5f,
                0.5f, -0.5f,
                0, 0.5f
        };


        bdef.position.set(11, 1.5f);
        bdef.type = BodyDef.BodyType.StaticBody;
        shape.set(verticles);
        b2body = world.createBody(bdef);

        // defining the shape of the sprite to interact with the ground
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("spike");
        b2body.createFixture(fdef);
    }
}
