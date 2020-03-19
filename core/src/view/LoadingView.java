package view;

import controller.Controller;

public class LoadingView {

    public LoadingView(Controller controller) {
        controller.findPlayer();
    }

}
