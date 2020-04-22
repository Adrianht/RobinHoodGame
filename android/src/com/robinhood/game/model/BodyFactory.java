package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
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
            fixtureDef.density = 1f;
        } else if (type.equals("arrow")) {
            fixtureDef.density = 0.5f;
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
                    bodyDef.position.set(posX - 2, -2);
                    bodyDef.angle = (float) Math.PI;
                } else {
                    bodyDef.position.set(posX + 2, -2);
                }
            } else {
                bodyDef.position.set(posX, -3);
            }
        }
        return bodyDef;
    }

    private static Shape createArrowShape() {
        ChainShape shape = new ChainShape();
        float[] arrowShapeCoordinatesX = {.3f, .4f, .08f, 1.3f,
                1.3f, 1.5f, 1.3f, 1.3f, .08f, .4f, .3f, 0f, .1f, 0f};
        float[] arrowShapeCoordinatesY = {0f, .1f, .1f, .1f,
                .05f, .15f, .25f, .2f, .2f, .2f, .3f, .3f, .15f, 0f};
        Vector2[] vertices = new Vector2[arrowShapeCoordinatesX.length];
        for (int i=0; i<arrowShapeCoordinatesX.length; i++) {
            vertices[i] = new Vector2(
                    arrowShapeCoordinatesX[i],
                    arrowShapeCoordinatesY[i]
            );
        }
        shape.createLoop(vertices);
        return shape;
    }

    private static Shape createPolygonShape(String type) {
        PolygonShape shape = new PolygonShape();
        if(type.equals("player")) {
            shape.setAsBox(1, 3);
        } else {
            shape.setAsBox(500, 3);
        }
        return shape;
    }
}
