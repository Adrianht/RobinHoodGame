package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
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
        BodyDef bodyDef = createBodyDef(type, posX, shot);
        Body body = world.createBody(bodyDef);

        Shape shape;
        if(type.equals("arrow")) {
            shape = createArrowShape();
        } else {
            shape = createPolygonShape(type);
        }

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        if(type.equals("ground")) {
            fixtureDef.density = 0f;
        } else if (type.equals("player")) {
            fixtureDef.density = 5f;
        } else if (type.equals("arrow")) {
            fixtureDef.density = 0.2f;
            fixtureDef.friction = 0.7f;
            fixtureDef.restitution = 0.3f;
        }
        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    private static BodyDef createBodyDef(String type, float posX, String shot) {
        BodyDef bodyDef = new BodyDef();
        if (type.equals("ground")) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, -9);
        } else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            if (type.equals("arrow")) {
                if (new Vector2().fromString(shot).x > 0) {
                    bodyDef.position.set(posX - 1, -4.65f);
                } else {
                    bodyDef.position.set(posX + 1, -4.65f);
                }
            } else {
                bodyDef.position.set(posX, -5);
            }
        }
        return bodyDef;
    }

    private static Shape createArrowShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.1f, .01f);
        return shape;
    }

    private static Shape createPolygonShape(String type) {
        PolygonShape shape = new PolygonShape();
        if(type.equals("player")) {
            shape.setAsBox(.25f, .7f);
        } else {
            shape.setAsBox(500, 3);
        }
        return shape;
    }
}
