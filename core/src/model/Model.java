package model;

public class Model {

    private Player player1, player2;
    private Stage stage;

    public void stateChange(String userInput) {
        //TODO: update all data based on data from controller
        // either from listeners, direct parameters or other
    }

    public String getData(String state) {
        if(state == "state1") {
            return "DATA FOR GRENSESNITT 1";
        } else {
            return "DATA FOR GRENSESNITT 2";
        }
    }
}
