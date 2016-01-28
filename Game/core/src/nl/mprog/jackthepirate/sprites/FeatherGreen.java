package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import nl.mprog.jackthepirate.MainActivity;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The green feather is defined using Box2D.
 */

public class FeatherGreen extends Sprite {
    public World world;
    public Body b2body;
    public Texture featherSprite;


    public FeatherGreen(World world){
        // Place feather in world context
        this.world = world;
        definePlatform();

        // Use png file for sprite
        featherSprite = new Texture("feather_green.png");

        // Set size
        setBounds(0, 0, 16/ MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(featherSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void definePlatform(){
        // Bodydefinition for collision
        BodyDef bdef = new BodyDef();
        CircleShape shape = new CircleShape();
        FixtureDef fdef = new FixtureDef();

        // Position in the world
        bdef.position.set(12, 1.5f);

        // Set solid circle as collison body
        bdef.type = BodyDef.BodyType.StaticBody;
        shape.setRadius(4 / MainActivity.PPM);
        b2body = world.createBody(bdef);

        // defining the shape of the sprite to interact with the ground
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("feather_green");
        b2body.createFixture(fdef);
    }
}
