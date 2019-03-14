package UI;

import Database.Connection;
import Query.Sql;

public class Menu {

    private int chosenID;
    Sql sql = new Sql();
    Connection conn = new Connection();


    public void mainMenu() {

        boolean running = true;

        while (running){
            clearScreen();
            showMainMenu();
            switch (Input.getIntRangeFromConsole(0,2 )) {
                case 1:
                    clearScreen();
                    lookupMenu();
                    break;
                case 2:
                    clearScreen();
                    writeYourOwnMenu();
                    break;

                case 0:
                    running = false;
                    break;

            }
        }
    }
    
    public void lookupMenu() {

        showLookupMenu();
        switch (Input.getIntRangeFromConsole(0,2 )) {
            case 1:
                clearScreen();
                specifyIdMenu("train");
                break;
            case 2:
                clearScreen();
                specifyIdMenu("cart");
                break;
            case 0:
                clearScreen();
                break;
        }

    }

    public void specifyIdMenu(String s){

        int inputID;
        showSpecifyIdMenu(s);

        switch (inputID = Input.getIntFromConsole()) {
            case 0:
                clearScreen();
                break;

            default:
                this.chosenID = inputID;
                break;
        }

    }

    public void writeYourOwnMenu(){
        clearScreen();
        showWriteYourOwnMenu();
        System.out.println(Sql.writeYourOwn(Input.getStringFromConsole(), Connection.getConnection()));
        Input.pressToContinue();

    }

    public void showWriteYourOwnMenu(){
        System.out.println("\nPlease write wanted SQL statement:");
    }

    public void showMainMenu() {
        System.out.println(
                "\nAutomatic Marshalling Yard \n" +
                        "-------------------\n" +
                        "1. Lookup cart/train information. \n" +
                        "2. Write your own SQL search. \n" +
                        "-------------------\n" +
                        "0. Exit program. \n");

    }

    public void showLookupMenu(){
        System.out.println(
                "\nWhat information do you want? \n" +
                        "-------------------\n" +
                        "1. Train \n" +
                        "2. Cart \n" +
                        "-------------------\n" +
                        "0. Back \n");

    }

    public void showSpecifyIdMenu(String s){
        System.out.println("\nPlease specify "+ s +" ID: \n" +
                            "(Type '0' to go back)\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}
