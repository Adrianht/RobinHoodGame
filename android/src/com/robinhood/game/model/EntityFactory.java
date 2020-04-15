package com.robinhood.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

// simplified BodyFactory from box2d repo
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
        entity.addComponent("box2d");
        entity.component.box2dBody.body = world.createBody(bodyDef);

        // Shape - long and narrow
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 3);
        entity.component.box2dBody.body.createFixture(shape, 0.0f);
        shape.dispose();

        return entity;
    }

    public Entity createPlayer(String username, int posX, int i) {
        Entity entity = new Entity();
        entity.addComponent("turn");
        if(i == 0) {
            entity.component.turn.turn = true;
        }
        entity.addComponent("playernr");
        entity.component.playernr.nr = i;
        entity.addComponent("energy");
        entity.addComponent("hp");
        entity.addComponent("name");
        entity.component.name.name = username;
        entity.addComponent("pos");
        //entity.component.pos.x = startPositions[i][0];
        //entity.component.pos.y = startPositions[i][1];

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX, -3);
        entity.addComponent("box2d");
        entity.component.box2dBody.body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 3);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        // Create the 'physical' body in the world typ render
        entity.component.box2dBody.body.createFixture(shape, 0.0f);
        shape.dispose();

        /*
        entity.addComponent("actor");
        switch(playerColor[i]) {
            case "RED":
                entity.component.actor.sprite = new Sprite(new Texture("redarcher.png"));
                break;
            case "BLUE":
                entity.component.actor.sprite = new Sprite(new Texture("bluearcher.png"));
                break;
            default:
                entity.component.actor.sprite = new Sprite(new Texture("redarcher.png"));
                break;
        }
        entity.component.actor.sprite.setSize(200, 200);

         */
        return entity;
    }

    public Entity newArrow() {
        Entity entity = new Entity();
        //entity.addComponent("pos");
        //entity.component.pos.x = 30;
        //entity.component.pos.y = 200;
        //entity.addComponent("actor");
        //entity.component.actor.sprite = new Sprite(new Texture("arrow.png"));
        //entity.component.actor.sprite.setSize(100, 100);

        entity.addComponent("box2d");
        BodyDef arrowBodyDef = new BodyDef();
        arrowBodyDef.position.x = 0;
        arrowBodyDef.position.y = 0;

        // Definition sticky for arrows
        arrowBodyDef.type = BodyDef.BodyType.DynamicBody;
        arrowBodyDef.fixedRotation = false;
        entity.component.box2dBody.body = world.createBody(arrowBodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(.5f,.2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.3f;
        entity.component.box2dBody.body.createFixture(fixtureDef);
        polygonShape.dispose();

        entity.addComponent("arrowType");

        return entity;
    }


}
