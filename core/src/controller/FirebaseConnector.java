package controller;

import com.badlogic.gdx.math.Vector2;

public class FirebaseConnector {

    Controller controller;

    public FirebaseConnector(Controller controller) {
        this.controller = controller;

        /*
        TODO:

        The constructor should try to setup a connection
        to another player and call the following method in
        controller on success:
            - controller.initateGame()

        */

    }

    public void move(Boolean left) {
        // TODO: creates a firebase formated message and store in the db
    }

    public void buyArrow(String type) {
        // TODO: creates a firebase formated message and store in the db
    }

    public void drawBow(Vector2 vector2) {
        // TODO: creates a firebase formated message and store in the db
    }

    /*

       TODO:
     There should be a listener to the db here, which retain data from
     fb and updates the controller.

     */

}
