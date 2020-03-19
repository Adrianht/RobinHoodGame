package view;

import controller.Controller;
import model.Model;


// TODO: adapt from other view-classes
public class GameView {

    Controller controller;
    Model model;

    public GameView(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;

        /*
         TODO:
         Use/create methods in model to extract current data
         from objects related to this view.

         This includes getting data from stage, players, etc. and
         checking if the game is over.
         */

    }

    public void updateView() {

        /*
         TODO:
         Use/create methods in model to extract updated data
         from objects related to this view.

         This includes getting data from stage, players, etc.
         */

    }


    /*
        TODO:
        Add a clicklistener:
            https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html

        Clicks in this view should trgger one of the following methods
        in the controller, based on the position and duration of the click:
            - controller.navigateTo("MENU") (ONLY IF GAMEOVER)
            - controller.move(Boolean left)
            - controller.buyArrow(String type)
            - controller.drawBow(Vector2 vector2)

        Additional:
            - if move() or buyArrow is called, they should be followed by
                updateView()
     */

}
