package UI;


public class Menu {


    public static void run() {

        boolean running = true;

        while (running){

            showMenu();
            switch (Input.getIntRangeFromConsole(0,1 )) {
                case 1:
                    clearScreen();
                    lookupMenu();
                    break;
                case 0:
                    running = false;


            }

        }

    }
    
    private static void lookupMenu() {
        int chosenID;
        showLookupMenu();
        switch (Input.getIntRangeFromConsole(0,2 )) {
            case 1:
                showSpecifyId("train");
                chosenID = Input.getIntFromConsole();

                break;
            case 2:
                showSpecifyId("cart");
                chosenID = Input.getIntFromConsole();

                break;
            case 0:

                break;


        }

    }
    private static void showMenu() {
        System.out.println(
                "\nAutomatic Marshalling Yard \n" +
                        "-------------------\n" +
                        "1. Lookup cart/train information. \n" +
                        "-------------------\n" +
                        "0. Exit program. \n");

    }

    private static void showLookupMenu(){
        System.out.println(
                "\nWhat information do you want? \n" +
                        "-------------------\n" +
                        "1. Train \n" +
                        "2. Cart \n" +
                        "-------------------\n" +
                        "0. Back \n");

    }

    private static void showSpecifyId(String s){
        System.out.println("\nPlease specify "+ s +" ID \n");

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}
