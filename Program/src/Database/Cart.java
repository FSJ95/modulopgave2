import java.util.*;

public class Cart
{

    public static void cart()
    {
        Random randomGenerator = new Random();
        int[] x = new int[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = (randomGenerator.nextInt(10) + 1);
        }
        Random r = new Random();
        String currentWeight;
        String[] weight = {"light", "medium", "heavy"};
        currentWeight = weight[r.nextInt(weight.length)];

        String currentCargo;
        String[] cargo = {"wood", "steel", "gasoline", "food", "cars", "goods"};
        currentCargo = cargo[r.nextInt(cargo.length)];



        System.out.print("Cart:" + "\t" + currentWeight + "\t"  +  currentCargo);
    }


};