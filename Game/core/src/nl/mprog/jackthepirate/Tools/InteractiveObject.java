package nl.mprog.jackthepirate.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


import nl.mprog.jackthepirate.MainActivity;



public abstract class InteractiveObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public InteractiveObject(World world, TiledMap map, Rectangle bounds ){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / MainActivity.PPM, (bounds.getY() + bounds.getHeight() / 2) / MainActivity.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / MainActivity.PPM, bounds.getHeight() / 2 / MainActivity.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onFeatherPickup();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int) (body.getPosition().x * MainActivity.PPM / 16), (int) (body.getPosition().y * MainActivity.PPM / 16));
    }



}
