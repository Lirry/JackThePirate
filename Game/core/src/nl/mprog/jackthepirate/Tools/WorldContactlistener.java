package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class WorldContactlistener implements ContactListener {

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
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
