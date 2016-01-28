package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import nl.mprog.jackthepirate.MainActivity;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The spike is defined using Box2D.
 */


public class SpikeTwo extends Sprite {
    public World world;
    public Body b2body;
    public Texture spikeSprite;


    public SpikeTwo(World world){
        // Place spike in world
        this.world = world;
        defineSpike();

        // Use png file fot sprite
        spikeSprite = new Texture("beterespike.png");

        // Set size
        setBounds(0, 0, 16/ MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(spikeSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void defineSpike(){
        // Bodydefinition for collision
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        // Verticles for triangular body shape
        float verticles[] = new float[]{
                0, -0.5f,
                0.2f, -0.5f,
                0.05f, 0.5f
        };

        // Position in world
        bdef.position.set(10.5f, 1.5f);

        // Set body for collision
        bdef.type = BodyDef.BodyType.StaticBody;
        shape.set(verticles);
        b2body = world.createBody(bdef);

        // Create shape using body and fixture definitions
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("spike");
        b2body.createFixture(fdef);
    }
}
