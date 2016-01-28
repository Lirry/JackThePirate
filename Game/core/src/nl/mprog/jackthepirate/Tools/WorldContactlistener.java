package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import nl.mprog.jackthepirate.MainActivity;

/**
 * Lirry Pinter
 * 10565051
 * lirry.pinter@gmail.com
 *
 * The WorldContactListener class is used to detect all sorts of collisions within the game.
 * It is a pre-made class from LibGDX.
 */



public class WorldContactlistener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        // Initialize two fixtures for collision detecting
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // If one of the fixtures is the platform, switch boolean
        if (fixA.getUserData() == "platform" || fixB.getUserData() == "platform" ||
                fixA.getUserData() == "platform_blue" || fixB.getUserData() == "platform_blue" ||
                fixA.getUserData() == "platform_green" || fixB.getUserData() == "platform_green" ) {
            MainActivity.onPlatform = true;
        }
        // If one of the fixtures is the spike, switch boolean
        if (fixA.getUserData() == "spike" || fixB.getUserData() == "spike"){
            MainActivity.dead = true;
        }
        // If one of the fixtures is the parrot, switch boolean
        if (fixA.getUserData() == "parrot" || fixB.getUserData() == "parrot"){
            MainActivity.win = true;
        }
        // If one of the fixtures are the feathers, switch booleans
        if (fixA.getUserData() == "feather_red" || fixB.getUserData() == "feather_red"){
            MainActivity.featherRedPicked = true;
            MainActivity.featherBluePicked = false;
            MainActivity.featherGreenPicked = false;
        }
        if (fixA.getUserData() == "feather_blue" || fixB.getUserData() == "feather_blue"){
            MainActivity.featherBluePicked = true;
            MainActivity.featherGreenPicked = false;
            MainActivity.featherRedPicked = false;
        }
        if (fixA.getUserData() == "feather_green" || fixB.getUserData() == "feather_green"){
            MainActivity.featherGreenPicked = true;
            MainActivity.featherBluePicked = false;
            MainActivity.featherRedPicked = false;
        }


    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
