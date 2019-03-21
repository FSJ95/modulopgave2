package Database;

import java.util.*;

@SuppressWarnings("Duplicates")
public class Cart {

    Random rnd = new Random();

    // På et tidspunkt bliver de her fjernet og erstattet med nogle database kald,
    // så vi kan sikre os at hvis der f.eks. kommer flere cargo-typer i databasen,
    // så bliver de også automatisk tilføjet når der bliver oprettet et nyt cart objekt.
    String[] weightArrayPlaceholder = {"Light", "Medium", "Heavy"};
    String[] cargoArrayPlaceholder = {"Oil", "Wood", "Steel", "Gasoline", "Food", "Cars", "General Goods"};
    String[] destinationArrayPlaceholder = {"Berlin", "Duisburg", "Frankfurt", "Hannover", "Kassel", "Köln",
                                "Leipzig", "Munich", "Nürnberg", "Stuttgart"};

    // Arraylist da de er lækre at arbejde med :)
    private ArrayList<String> weightArray = new ArrayList<String>();
    private ArrayList<String> cargoArray = new ArrayList<String>();
    private ArrayList<String> destinationArray = new ArrayList<String>();


    // Vores variabler vi gemmer de valgte værdier i.
    private String weight, cargo, destination;


    public Cart() {

        fillArrays();

        this.weight = randomWeight(randomNumberGenerator(weightArray.size()));
        this.cargo = randomCargo(randomNumberGenerator(cargoArray.size()));
        this.destination = randomDestination(randomNumberGenerator(destinationArray.size()));

    }

    public Cart(String weight, String cargo, String destination) {
        this.weight = weight;
        this.cargo = cargo;
        this.destination = destination;
    }

    // Denne metode tager et tal og returnere så et tal fra 1 til det tal.
    // Den kan vi så give størrelsen af et ønsket array og derved finde et tilfældigt element deri.
    private int randomNumberGenerator(int maxNumber){

        int randomNumber = rnd.nextInt(maxNumber);

        return randomNumber;
    }


    private String randomCargo (int randomNumber){

        String chosenCargo = cargoArray.get(randomNumber);

        return chosenCargo;
    }


    private String randomDestination(int randomNumber){

        String chosenDestination = destinationArray.get(randomNumber);

        return chosenDestination;
    }


    private String randomWeight(int randomNumber){

        String chosenWeight = weightArray.get(randomNumber);

        return chosenWeight;
    }


    private void fillArrays() {
        // Dette er til når vi tilføjer database kald, så behøver vi ikke tilføje lave nye
        // database kald hver gang vi opretter en cart, da alle værdier allerede vil være i vores arrayList efter første gang.
        // Hvis ingen værdier er i vores arrayList, kaldes databsen senere her og tilføjer alle elementer.
        if (cargoArray.isEmpty()) {
            cargoArray.addAll(Arrays.asList(cargoArrayPlaceholder));
        }

        if (weightArray.isEmpty()) {
            weightArray.addAll(Arrays.asList(weightArrayPlaceholder));
        }

        if (destinationArray.isEmpty()) {
            destinationArray.addAll(Arrays.asList(destinationArrayPlaceholder));
        }

    }

    // Getters til at hente værdierne.
    public String getWeight() {
        return weight;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDestination() {
        return destination;
    }


}
