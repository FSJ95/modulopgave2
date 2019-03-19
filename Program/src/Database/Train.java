package Database;

import java.util.ArrayList;
import java.util.Random;

public class Train {

    Random rnd = new Random();

    // Array vi gemmer togets vogne i.
    private static ArrayList<String> cartArray = new ArrayList<String>();


    // Konstruktor der tager et int og herefter opretter så mange toge.
    public Train(int numberOfTrains) {
        
    }


    // Tilfældigt antal vogne på toget.
    private int numberOfCarts{

        int randomNumber = rnd.nextInt(10)+1;

        return randomNumber;
    }

}
