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
 * The red platform is defined using Box2D.
 */

public class Platform extends Sprite {
    public World world;
    public Body b2body;
    public Texture platformSprite;


    public Platform(World world){
        // Place platform in world context
        this.world = world;
        definePlatform();

        // Use png file for sprite
        platformSprite = new Texture("platform_red.png");

        // Set size
        setBounds(0, 0, 40/ MainActivity.PPM, 12/MainActivity.PPM);
        setRegion(platformSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void definePlatform(){
        // Bodydefinition for collision
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        // Position in the world
        bdef.position.set(11, 7);

        // Box shape for collision
        bdef.type = BodyDef.BodyType.KinematicBody;
        shape.setAsBox(1.25f, 0.45f);
        b2body = world.createBody(bdef);

        // defining the shape of the sprite to interact with the ground
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("platform");
        b2body.createFixture(fdef);
    }
}
