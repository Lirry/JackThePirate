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
 * The parrot is defined using Box2D
 */

public class Parrot extends Sprite {
    public World world;
    public Body b2body;
    public Texture parrotSprite;

    public Parrot(World world){
        // Place parrot in world context
        this.world = world;
        defineParrot();

        // Use png file for sprite
        parrotSprite = new Texture("parrot.png");

        // Set size
        setBounds(0, 0, 14/ MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(parrotSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void defineParrot(){
        // Bodydefinition for collision
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        // Set position in world
        bdef.position.set(12.5f , 20.5f);

        // Set solid circle as collison body
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);
        shape.setRadius(8/ MainActivity.PPM);

        // defining the shape of the sprite to interact with the ground
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("parrot");
        b2body.createFixture(fdef);
    }
}
