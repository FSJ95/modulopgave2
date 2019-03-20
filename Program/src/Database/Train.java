package Database;

import java.util.ArrayList;
import java.util.Random;

public class Train {

    Random rnd = new Random();


    // Array vi gemmer togets vogne i.
    private ArrayList<Cart> cartArray = new ArrayList<Cart>();


    // Konstruktor der tilføjer et tilfædigt antal vogne (op til 10).
    public Train() {
        int cartCount = numberOfCarts();
        for (int i = 0; i <= cartCount; i++){
            cartArray.add(new Cart());
        }
    }


    // Tilfældigt antal vogne på toget.
    private int numberOfCarts(){

        int randomNumber = rnd.nextInt(10)+1;

        return randomNumber;
    }

    public ArrayList<Cart> getCartArray() {
        return cartArray;
    }

}
