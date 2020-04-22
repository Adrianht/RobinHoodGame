package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class EntityFactory {

    private final World world;

    EntityFactory (World world) {
        this.world = world;
    }

    public Entity createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -9);

        Entity entity = new Entity();
        entity.addComponent("box2dBody");
        entity.components.box2dBody.body = world.createBody(bodyDef);

        // Shape - long and narrow
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(500, 3);
        entity.components.box2dBody.body.createFixture(shape, 0.0f);
        shape.dispose();

        return entity;
    }

    public Entity createPlayer(String username, int posX, int index) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, -3);

        Entity entity = new Entity();
        entity.addComponent("playerInfo");
        entity.components.playerInfo.username = username;
        entity.components.playerInfo.index = index;
        if(index == 0) {
            entity.components.playerInfo.isPlayersTurn = true;
        }

        entity.addComponent("box2dBody");
        entity.components.box2dBody.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 3);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        entity.components.box2dBody.body.createFixture(shape, 0.0f);
        shape.dispose();

        return entity;
    }

    public Entity createBow(int posX, int i) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX+1, -3);

        Entity entity = new Entity();
        entity.addComponent("bow");
        entity.components.box2dBody.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        entity.components.box2dBody.body = world.createBody(bodyDef);
        shape.dispose();

        return entity;
    }

    public Entity newArrow() {
        BodyDef arrowBodyDef = new BodyDef();
        arrowBodyDef.position.set(0, 0);
        arrowBodyDef.type = BodyDef.BodyType.DynamicBody;
        arrowBodyDef.fixedRotation = true;

        Entity entity = new Entity();
        entity.addComponent("arrowType");
        entity.addComponent("box2dBody");
        entity.components.box2dBody.body = world.createBody(arrowBodyDef);
        ChainShape chainShape = new ChainShape();
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
        chainShape.createLoop(vertices);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.3f;
        entity.components.box2dBody.body.createFixture(fixtureDef);
        chainShape.dispose();

        // Orient arrow in direction

        return entity;
    }

}
