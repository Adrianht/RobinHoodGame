package com.robinhood.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.badlogic.gdx.Gdx;
public class AndroidLauncher extends AndroidApplication {

	final static String TAG = "RobinYoHood";

	private DatabaseReference dataRef;



	private boolean mLeft;
	private boolean mRight;
	private boolean shoot;





	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RobinHood(), config);

		dataRef = FirebaseDatabase.getInstance().getReference();

		dataRef.child("game").setValue("left");

	}
}
