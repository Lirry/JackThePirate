package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import nl.mprog.jackthepirate.MainActivity;


public class Jack extends Sprite {
    public World world;
    public Body b2body;

    public Jack(World world){
        this.world = world;
        defineJack();
    }

    public void defineJack(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MainActivity.PPM, 32 / MainActivity.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MainActivity.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
