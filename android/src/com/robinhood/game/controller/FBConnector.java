package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FBConnector {

    private DatabaseReference mDatabase;
    private Controller controller;
    private boolean firstPlayer, sameUsername;

    FBConnector (Controller controller) {
        this.controller = controller;
    }

    // attempts to find a match
    // TODO-ola: need expandable implementation (index-based replace bool firstPlayer
    public void findPlayers(String username, int nrOfPlayers) {


        // TODO: add dummy til design finished


        firstPlayer = false;
        sameUsername = false;

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
                if (playerNames.size() == 1 && !sameUsername) {
                    firstPlayer = true;
                } else if(playerNames.size() == 2 && !sameUsername) {
                    // TODO-ola: have to compare all names and adapt
                    if (playerNames.get(0).equals(playerNames.get(1))) {
                        sameUsername = true;
                        if(!firstPlayer) {
                            controller.setUsername(playerNames.get(1) + "2");
                            mDatabase.removeValue();
                            mDatabase.push().setValue(playerNames.get(0));
                            mDatabase.push().setValue(playerNames.get(1) + "2");
                            mDatabase.push().setValue(UUID.randomUUID().toString());
                        }
                    } else {
                        if (firstPlayer) {
                            mDatabase.push().setValue(UUID.randomUUID().toString());
                        }
                    }
                } else if(playerNames.size() == 3) {
                    mDatabase.removeValue();
                    controller.initiateGame(playerNames.subList(0, 2));

                    mDatabase = FirebaseDatabase.getInstance().getReference()
                            .child("rooms").child(playerNames.get(2));
                    if(firstPlayer) {
                        mDatabase.child("move").setValue(true);
                        mDatabase.child("activeArrow").setValue("Normal");
                        mDatabase.child("drawBow").setValue("(0,0)");
                    }
                    createGameRoomListeners();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }

        });
    }

    // Cancels search for opponent
    public void cancelFindPlayer() {
        FirebaseDatabase.getInstance().getReference()
                .child("availablePlayer").removeValue();
    }

    // creates a new game room in firebase real-time database
    public void createGameRoomListeners() {
        mDatabase.child("move").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    controller.registerMove((boolean) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });

        mDatabase.child("activeArrow").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    controller.registerBuy((String) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });

        mDatabase.child("drawBow").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    String vectorStr = (String) dataSnapshot.getValue();
                    controller.registerDraw(new Vector2().fromString(vectorStr));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });
    }

    // Method to change last movement in players game room
    public void setMove(boolean left) {
        mDatabase.child("move").setValue(null);
        mDatabase.child("move").setValue(left);
    }

    // Method to change active arrow type in players game room
    public void setBuy(String type) {
        mDatabase.child("activeArrow").setValue(null);
        mDatabase.child("activeArrow").setValue(type);
    }

    // Method to change draw vector in players game room
    public void setDraw(Vector2 vector2) {
        mDatabase.child("drawBow").setValue(vector2.toString());
    }

    // Method to clean database after game is finished
    public void removeRoom() {
        mDatabase.removeValue();
    }

}
