package UI;

import Database.*;
import Query.Sql;

import static Query.Sql.*;


public class Menu {

    private int chosenID;
    Sql sql = new Sql();
    Connection conn = new Connection();


    public void mainMenu() {

        boolean running = true;

        while (running){
            clearScreen();
            showMainMenu();
            switch (Input.getIntRangeFromConsole(0,4 )) {
                case 1:
                    clearScreen();
                    generateMenu();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("THIS IS MISSING");
                    Input.pressToContinue();
                    break;
                case 3:
                    clearScreen();
                    lookupMenu();
                    break;
                case 4:
                    clearScreen();
                    writeYourOwnMenu();
                    break;



                case 0:
                    running = false;
                    break;

            }
        }
    }

    public void generateMenu() {

        clearScreen();
        showGenerateMenu();

        int numberOfTrains;
        int trainCounter;


        switch (numberOfTrains = Input.getIntFromConsole()) {
            case 0:
                clearScreen();
                break;

            default:
                clearScreen();
                for (trainCounter = 0; trainCounter < numberOfTrains; trainCounter++) {
                    Sql.addTrains(new Train(),conn.getConnection());

                }
                System.out.println("Succesfully added all trains (" + trainCounter + ") and their carts.\n");
                Input.pressToContinue();
                break;
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
                clearScreen();
                System.out.println(Sql.getInfo(s, inputID, conn.getConnection()));
                Input.pressToContinue();
                break;
        }

    }

    public void writeYourOwnMenu(){
        clearScreen();
        showWriteYourOwnMenu();
        String inputString;
        switch (inputString = Input.getStringFromConsole()) {
            case "0":
                clearScreen();
                break;

            default:
                System.out.println(Sql.writeYourOwn(inputString, conn.getConnection()));
                Input.pressToContinue();
                break;
        }



    }

    public void showGenerateMenu(){
        System.out.println("How many trains do you want? \nEach train have a random number of carts!\n" +
                "(Type '0' to go back)\n");
    }

    public void showWriteYourOwnMenu(){
        System.out.println("Please write wanted SQL statement:\n" +
                "(Type '0' to go back)\n");
    }

    public void showMainMenu() {
        System.out.println(
                "Automatic Marshalling Yard \n" +
                        "-------------------\n" +
                        "1. Generate trains. \n" +
                        "2. Sort arriving trains. \n" +
                        "-------------------\n" +
                        "3. Lookup cart/train information. \n" +
                        "4. Write your own SQL search. \n" +
                        "-------------------\n" +
                        "0. Exit program. \n");

    }

    public void showLookupMenu(){
        System.out.println(
                "What information do you want? \n" +
                        "-------------------\n" +
                        "1. Train \n" +
                        "2. Cart \n" +
                        "-------------------\n" +
                        "0. Back \n");

    }

    public void showSpecifyIdMenu(String s){
        System.out.println("Please specify "+ s +" ID: \n" +
                            "(Type '0' to go back)\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}
