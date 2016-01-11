package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import javax.swing.plaf.nimbus.State;

import nl.mprog.jackthepirate.MainActivity;


public class Jack extends Sprite {

    public World world;
    public Body b2body;
    public Texture jackSprite;

    private boolean right;

    public Jack(World world){
        this.world = world;
        defineJack();
        jackSprite = new Texture("jackmedium.png");
        setBounds(0, 0, 16/MainActivity.PPM, 16/MainActivity.PPM);
        setRegion(jackSprite);
        right = true;
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if (b2body.getLinearVelocity().x < 0 && right) {
            setFlip(true, false);
            right = false;
        }
        else if (b2body.getLinearVelocity().x < 0 && !right) {
            setFlip(true, false);
            right = true;
        }
    }


    public void defineJack(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MainActivity.PPM, 32 / MainActivity.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ MainActivity.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
