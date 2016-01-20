package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import nl.mprog.jackthepirate.MainActivity;


public class PlatformBlue extends Sprite {
    public World world;
    public Body b2body;
    public Texture platformSprite;


    public PlatformBlue(World world){
        this.world = world;
        definePlatform();
        platformSprite = new Texture("platform_blue.png");
        setBounds(0, 0, 40/ MainActivity.PPM, 12/MainActivity.PPM);
        setRegion(platformSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
    }


    public void definePlatform(){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(4, 17);
        bdef.type = BodyDef.BodyType.KinematicBody;
        shape.setAsBox(1.25f, 0.45f);
        b2body = world.createBody(bdef);

        // defining the shape of the sprite to interact with the ground
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("platform_blue");
        b2body.createFixture(fdef);
    }
}
