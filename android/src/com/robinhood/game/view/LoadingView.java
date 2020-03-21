package com.robinhood.game.view;

import com.robinhood.game.controller.Controller;

public class LoadingView {

    public LoadingView(Controller controller) {
        controller.findPlayer();
    }

}
