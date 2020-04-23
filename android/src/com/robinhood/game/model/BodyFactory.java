package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Singleton class representing the factory of the
 * Factory pattern, creating box2d Body-objects.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public final class BodyFactory {

    private static final BodyFactory INSTANCE = new BodyFactory();

    private BodyFactory() {}

    public static BodyFactory getInstance() {
        return INSTANCE;
    }

    public static Body getBody(String type, World world, float posX) {
        return getBody(type, world, posX, null);
    }

    public static Body getBody(String type, World world, float posX, String shot) {
        Body body;
        switch (type) {
            case "arrow":
                body = createArrowBody(world, posX, shot);
                break;
            case "ground":
                body = createGroundBody(world);
                break;
            default: // "player"
                body = createPlayerBody(world, posX);
        }

        return body;
    }

    private static Body createArrowBody(World world, float posX, String shot) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (new Vector2().fromString(shot).x > 0) {
            bodyDef.position.set(posX - 1, -4.65f);
        } else {
            bodyDef.position.set(posX + 1, -4.65f);
        }
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.1f, .01f);
        body.createFixture(shape, .2f);
        shape.dispose();

        return body;
    }

    private static Body createGroundBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -9);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(500, 3);
        body.createFixture(shape, 0);
        shape.dispose();

        return body;
    }

    private static Body createPlayerBody(World world, float posX) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, -5);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.25f, .7f);
        body.createFixture(shape, 5f);
        shape.dispose();

        return body;
    }
}
