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
 * Jack is defined using Bod2D
 */


public class Jack extends Sprite {

    public World world;
    public Body b2body;
    public Texture jackSprite;


    public Jack(World world){
        // Place Jack in world context
        this.world = world;
        defineJack();

        // Use png file for sprite
        jackSprite = new Texture("jackmediumhq.png");

        // Set size
        setBounds(0, 0, 16/MainActivity.PPM, 20/MainActivity.PPM);
        setRegion(jackSprite);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }


    public void defineJack(){
        // Bodydefinition for collision
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MainActivity.PPM, 32 / MainActivity.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // define shape of the sprite to interact with the ground
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8.8f / MainActivity.PPM);

        // Create bodyfixture for collision
        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.createFixture(fdef).setUserData("jack");
    }
}
