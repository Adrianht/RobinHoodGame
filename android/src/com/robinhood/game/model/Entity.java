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

    public final Components components = new Components();

    public void addComponent(String componentName) {
        componentAction(componentName, true);
    }

    public void removeComponent(String componentName) {
        componentAction(componentName, false);
    }

    private void componentAction(String componentName, boolean isAddAction) {
        switch(componentName) {
            case "arrowType":
                if (isAddAction) {
                    this.components.new ArrowType();
                } else {
                    this.components.arrowType = null;
                }
                break;
            case "box2dBody":
                if (isAddAction) {
                    this.components.new Box2dBody();
                } else {
                    this.components.box2dBody = null;
                }
                break;
            case "playerInfo":
                if (isAddAction) {
                    this.components.new PlayerInfo();
                } else {
                    this.components.playerInfo = null;
                }
                break;
            default:
                // no action
                break;
        }
    }
}
