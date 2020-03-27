package model;

public class Player {

    private final String name;
    private final Archer archer;
    private Arrow arrow;
    private int energyPoints;

    Player(String name, String color) {
        this.name = name;
        this.archer = new Archer(color);
        this.energyPoints = 20;
    }

    public Archer getArcher() {
        return archer;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    // For each time it's a players turn again, it will get 10 more energy points
    public void  increaseEnergyPoints() {
        energyPoints = energyPoints + 10;
    }

    // Called from Model.move() when the player has moved, could also be done directly in Model
    public void setEnergyPoints(int updatedEnergyPoints) {
        this.energyPoints = updatedEnergyPoints;
    }
}
