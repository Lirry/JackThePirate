package nl.mprog.jackthepirate.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import nl.mprog.jackthepirate.MainActivity;
import nl.mprog.jackthepirate.Tools.InteractiveObject;


public class Feather extends InteractiveObject {
    public Feather(World world, TiledMap map, Rectangle bounds) {
        super(world,map, bounds);
        fixture.setUserData("feather");
        setCategoryFilter(MainActivity.FEATHER_BIT);
    }

    @Override
    public void onFeatherPickup() {
        Gdx.app.log("Feather", "Collision YEAAAAAH");
        setCategoryFilter(MainActivity.DESTROYED_BIT);
    }
}
