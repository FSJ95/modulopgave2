package Database;

import java.util.ArrayList;
import java.util.Random;

public class Train {

    Random rnd = new Random();

    // Array vi gemmer togets vogne i.
    private ArrayList<Cart> cartArray = new ArrayList<>();

    public Train() {

    }

    public Train(ArrayList<Cart> cartArray) {
        this.cartArray = cartArray;
    }

    // Tilføjer tilfædigt antal vogne (op til 10).
    public void generateCarts(){
        int cartCount = numberOfCarts();
        for (int i = 0; i < cartCount; i++){
            Cart cart = new Cart();
            cart.fillCart();
            cartArray.add(cart);
        }
    }

    // Tilfældigt antal vogne på toget.
    private int numberOfCarts(){

        int number = 10;

        return number;
    }

    public ArrayList<Cart> getCartArray() {
        return cartArray;
    }

    public void addToCartArray(Cart cartArray) {
        this.cartArray.add(cartArray);
    }

}
