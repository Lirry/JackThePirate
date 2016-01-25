package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.screens.PlayScreen;


public class WorldContactlistener implements ContactListener {

    /**
     *
     * Comment Section
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "jack" || fixB.getUserData() == "jack"){
            Fixture playerCol = fixA.getUserData() == "jack" ? fixA : fixB;
            Fixture feather = playerCol == fixA ? fixB : fixA;
            if (feather.getUserData() instanceof InteractiveObject){
                Gdx.app.log("Working till here", "second if loop");
                ((InteractiveObject) feather.getUserData()).onFeatherPickup();
            }
        }
        if (fixA.getUserData() == "platform" || fixB.getUserData() == "platform") {
            MainActivity.onPlatform = true;
        }
        if (fixA.getUserData() == "spike" || fixB.getUserData() == "spike"){
            Gdx.app.log("DEAD BABY", "DEAD");
            MainActivity.dead = true;
        }
        if (fixA.getUserData() == "parrot" || fixB.getUserData() == "parrot"){
            MainActivity.win = true;
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
