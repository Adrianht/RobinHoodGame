package com.robinhood.game.model;

/*
     Entity in ECS
     Holds a identificator and a component
     with inner classes relavant for this entiy
 */
public class Entity {

    final Components component;

    public Entity() {
        this.component = new Components();
    }

    // Creates the correct inner class instance within the
    //  component related to this entity
    public void addComponent(String componentName) {
        switch(componentName) {
            case "arrowType":
                this.component.new ArrowType();
                break;
            case "energy":
                this.component.new Energy();
                break;
            case "hp":
                this.component.new HitPoints();
                break;
            case "name":
                this.component.new PlayerName();
                break;
            case "pos":
                this.component.new Position();
                break;
            case "playernr":
                this.component.new PlayerNr();
                break;
            case "actor":
                this.component.new GameActor();
                break;
            case "turf":
                this.component.new Turf();
                break;
            case "turn":
                this.component.new Turn();
                break;
            default:
                //
                break;
        }
    }

    // FIXME: method should remove a inner component class
    public void removeComponent(String componentName) {

        /*
        TODO:
            something like this this.component[componentName] = null;
         */

    }

}
