package com.robinhood.game.controller;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class responsible for communication with applications
 * Firebase real-time database.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class FBConnector {

    private Controller controller;
    private DatabaseReference mDatabase;
    private ValueEventListener actionListener;
    private boolean nameIsValid, cancelFindPlayer;
    private String removeUsername;

    FBConnector (Controller controller) {
        this.controller = controller;
    }

    public void findPlayers(final String username, final int nrOfPlayers) {


        // TODO: replace following with commented after design finished
        List<String> usernames = new ArrayList<>();
        usernames.add("Username");
        usernames.add("Username");
        controller.initiateGame(usernames);
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child("UsernameRoom");
        createGameRoomListener();


/*
        nameIsValid = false;
        cancelFindPlayer = false;

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("availablePlayer");
        mDatabase.push().setValue(username);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> playerNames = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    playerNames.add(snapshot.getValue().toString());
                }

                // Logic for removing the current player's username
                if (cancelFindPlayer) {
                    mDatabase.removeValue();
                    for (String name: playerNames) {
                        if(!name.equals(removeUsername)
                                && !name.equals("cancelFindPlayer")) {
                            mDatabase.push().setValue(name);
                        }
                    }
                    cancelFindPlayer = false;
                }

                // Ensure last username is unique
                if (!nameIsValid) {
                    boolean usernameChanged = false;
                    while (!lastUsernameIsUnique(playerNames)) {
                        usernameChanged = true;
                        playerNames.set(
                                playerNames.size()-1,
                                playerNames.get(playerNames.size()-1) + "!"
                        );
                    }
                    if (usernameChanged) {
                        controller.setMyUsername(playerNames.get(playerNames.size()-1));
                        mDatabase.removeValue();
                        for (String name: playerNames) {
                            mDatabase.push().setValue(name);
                        }
                    }
                    nameIsValid = true;
                    if (playerNames.size() == nrOfPlayers) {
                        mDatabase.push().setValue(UUID.randomUUID().toString());
                    }
                }

                if (playerNames.size() == nrOfPlayers+1
                        && !playerNames.get(playerNames.size()-1).equals("cancelFindPlayer")) {
                    mDatabase.removeValue();
                    mDatabase.removeEventListener(this);
                    controller.initiateGame(playerNames.subList(0, nrOfPlayers));
                    mDatabase = FirebaseDatabase.getInstance().getReference()
                            .child("rooms").child(playerNames.get(playerNames.size()-1));
                    createGameRoomListener();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });
*/
    }

    // Cancels search for opponent(s)
    public void cancelFindPlayer(String username) {
        cancelFindPlayer = true;
        removeUsername = username;
        mDatabase.push().setValue("cancelFindPlayer");
    }

    // Creates listener within the designated game room
    public void createGameRoomListener() {
        actionListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    controller.notifyChangeInFirebase(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        };

        mDatabase.child("action").addValueEventListener(actionListener);
    }

    // Method to change last action in players game room
    public void exportActionToFirebase(String action) {
        mDatabase.child("action").setValue(null);
        mDatabase.child("action").setValue(action);
    }

    // Method to clean database after game is finished
    public void removeRoom() {
        mDatabase.removeValue();
        mDatabase.child("action").removeEventListener(actionListener);
    }

    // Checks if last element is unique
    private boolean lastUsernameIsUnique(List<String> usernames) {
        for (int i = 0; i < (usernames.size()-1); i++) {
            if (usernames.get(usernames.size()-1).equals(usernames.get(i))) {
                return false;
            }
        }
        return true;
    }
}
