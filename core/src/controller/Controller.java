package controller;

import com.robinhood.game.RobinHood;

import model.Model;
import view.GameOverView;
import view.MenuView;

public class Controller {

    private Model model;
    private RobinHood game;

    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
    }

    public void userInput(String userInput) {
        model.stateChange(userInput);
        this.viewSelection(userInput);
    }

    //TODO: evaluate this method and if it should be in some kind of
    // arbitator or GameStateManager
    private void viewSelection(String change) {
        //TODO: calculate and decide view, how is a mystery :)
        if(change == "GIVE UP") {
            game.setView(new GameOverView(model, this));
        } else if (change == "TO MENU") {
            game.setView(new MenuView(model, this));
        }
    }

}
