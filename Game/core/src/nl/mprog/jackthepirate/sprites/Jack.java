package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;


import nl.mprog.jackthepirate.MainActivity;


public class Jack extends Sprite {

    public World world;
    public Body b2body;
    public Texture jackSprite;


    public Jack(World world){
        this.world = world;
        defineJack();
        jackSprite = new Texture("jackmediumhq.png");
        setBounds(0, 0, 16/MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(jackSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }


    public void defineJack(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MainActivity.PPM, 32 / MainActivity.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // defining the shape of the sprite to interact with the ground
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ MainActivity.PPM);

        fdef.filter.categoryBits = MainActivity.JACK_BIT;
        fdef.filter.maskBits = MainActivity.DEFAULT_BIT | MainActivity.FEATHER_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        // defining the fixtures of jack to interact with other objects
        CircleShape collisionShape = new CircleShape();
        shape.setRadius(13 / MainActivity.PPM);
        fdef.shape = collisionShape;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("jack");
    }
}
