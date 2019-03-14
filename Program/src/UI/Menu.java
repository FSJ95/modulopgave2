package UI;


public class Menu {

    private int chosenID;

    public void mainMenu() {

        boolean running = true;

        while (running){
            clearScreen();
            showMenu();
            switch (Input.getIntRangeFromConsole(0,1 )) {
                case 1:
                    clearScreen();
                    lookupMenu();
                    break;
                case 2:

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


    public void showMenu() {
        System.out.println(
                "\nAutomatic Marshalling Yard \n" +
                        "-------------------\n" +
                        "1. Lookup cart/train information. \n" +
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
        System.out.println("\nPlease specify "+ s +" ID:");
        System.out.println("(Type '0' to go back)\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}
