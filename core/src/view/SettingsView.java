package view;

import controller.Controller;
import model.Model;

public class SettingsView {

    Controller controller;

    public SettingsView(Controller controller, Model model) {
        this.controller = controller;

        /*
         TODO:
         use/create methods in model to extract current data
         from objects related to this view
         */

    }


    /*
        TODO:
        Add a clicklistener:
            https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html

        Clicks in this view should trgger one of the following methods
        in the controller, based on the position of the click:
            - controller.navigateTo("MENU")
            - controller.changeSound()
     */

}
