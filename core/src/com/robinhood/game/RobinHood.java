package com.robinhood.game;

import com.badlogic.gdx.ApplicationAdapter;

import controller.Controller;
import model.Model;
import view.View;

public class RobinHood extends ApplicationAdapter {

	private View view;
	private Controller controller;

	@Override
	public void create () {
		controller = new Controller(this);
	}

	@Override
	public void render () {
		view.render();
	}
	
	@Override
	public void dispose () {
		view.dispose();
	}

	public void setView(View view) {
		this.view = view;
	}
}
