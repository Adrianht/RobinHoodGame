package com.robinhood.game.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class representing the pool of the Object Pool
 * design pattern, holding arrow Entity-objects.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public final class ArrowEntityPool {

    private static final ArrowEntityPool INSTANCE = new ArrowEntityPool();

    private static final long expTime = 6000; // 6 seconds
    private static final HashMap<Entity, Long> available = new HashMap<>();
    private static Entity inUse;

    private ArrowEntityPool() {}

    public static ArrowEntityPool getInstance() {
        return INSTANCE;
    }

    public synchronized static Entity getObject() {
        long now = System.currentTimeMillis();
        if (!available.isEmpty() && inUse == null) {
            for (Map.Entry<Entity, Long> entry : available.entrySet()) {
                if (now - entry.getValue() > expTime) {
                    available.remove(entry.getKey());
                } else {
                    Entity arrowEntity = entry.getKey();
                    available.remove(entry.getKey());
                    inUse = arrowEntity;
                    return arrowEntity;
                }
            }
        }
        return createEntity();
    }

    private synchronized static Entity createEntity() {
        Entity newArrowEntity = new Entity();
        newArrowEntity.addComponent("arrowType");
        newArrowEntity.addComponent("box2dBody");
        inUse = newArrowEntity;
        return newArrowEntity;
    }

    public static void releaseObject(Entity usedArrowEntity) {
        usedArrowEntity.components.box2dBody.body = null;
        usedArrowEntity.components.arrowType.type = "Level1";
        usedArrowEntity.components.arrowType.damage = 10;
        available.put(usedArrowEntity, System.currentTimeMillis());
        inUse = null;
    }
}
