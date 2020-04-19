package com.robinhood.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for Systems inner classes.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
class SystemsHelpers {

    static List<Entity> findPlayers(List<Entity> entities) {
        List<Entity> players = new ArrayList<>();
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null) {
                players.add(entity);
            }
        }
        return players;
    }

    static Entity findActivePlayer(List<Entity> entities) {
        Entity activePlayer = null;
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null &&
                    entity.components.playerInfo.isMyTurn) {
                activePlayer = entity;
            }
        }
        return activePlayer;
    }

    static Entity findActiveArrow(List<Entity> entities) {
        Entity activeArrow = null;
        for(Entity entity: entities) {
            if (entity.components.arrowType != null) {
                activeArrow = entity;
            }
        }
        return activeArrow;
    }
}
