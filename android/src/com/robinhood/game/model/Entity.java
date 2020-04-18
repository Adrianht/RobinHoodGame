package com.robinhood.game.model;

/**
 * Class representing Entity in Entity-Component-System.
 * Holds a Components object with inner classes relevant to this entity.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Entity {

    // TODO-Ola: swap to components everywhere (NB! also outside this file)
    public final Components component = new Components();

    // Add component to this entity
    public void addComponent(String componentName) {
        componentAction(componentName, true);
    }

    // Remove component from this entity
    public void removeComponent(String componentName) {
        componentAction(componentName, false);
    }

    // TODO: fix better case-names after fixed classnames in Components
    // Finds and execute action on correct component
    private void componentAction(String componentName, boolean isAddAction) {
        switch(componentName) {
            case "arrowType":
                if (isAddAction) {
                    this.component.new ArrowType();
                } else {
                    this.component.arrowtype = null;
                }
                break;
            case "energy":
                if (isAddAction) {
                    this.component.new Energy();
                } else {
                    this.component.energy = null;
                }
                break;
            case "hp":
                if (isAddAction) {
                    this.component.new HitPoints();
                } else {
                    this.component.hp = null;
                }
                break;
            case "name":
                if (isAddAction) {
                    this.component.new PlayerName();
                } else {
                    this.component.name = null;
                }
                break;
            case "playernr":
                if (isAddAction) {
                    this.component.new PlayerNr();
                } else {
                    this.component.playernr = null;
                }
                break;
            case "box2d":
                if (isAddAction) {
                    this.component.new Box2dBody();
                } else {
                    this.component.box2dBody = null;
                }
                break;
            case "turn":
                if (isAddAction) {
                    this.component.new Turn();
                } else {
                    this.component.turn = null;
                }
                break;
            default:
                // no action
                break;
        }
    }
}
