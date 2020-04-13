package com.robinhood.game.model;

public class Player {

    private final String name;
    private final Archer archer;
    private Arrow arrow;

    Player(String name, String color) {
        this.name = name;
        this.archer = new Archer(color);
    }

    public Archer getArcher() {
        return archer;
    }

    public String getName(){
        return name;
    }
}
