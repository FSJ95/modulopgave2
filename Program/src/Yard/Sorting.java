package Yard;

import Database.Cart;
import Database.Train;

import java.util.*;

public class Sorting {

    // One track for all unused trains
    private ArrayList<Train> allTrains;
    private ArrayList<Train> arrivalTracks = new ArrayList<>();
    private Train sortTrack = new Train();
    private Train scratchTrack = new Train();
    private ArrayList<Train> departingTrack = new ArrayList<>();
    private ArrayList<Train> finishedTrains = new ArrayList<>();

    private int finishedTrainCarts = 10;


    public Sorting(ArrayList<Train> arrivingCarts) {

        this.allTrains = new ArrayList<>(arrivingCarts);

        for (Train train : allTrains) {
            Train t = new Train ();
            for (Cart cart : train.getCartArray()) {
                t.addToCartArray(cart);
            }
            arrivalTracks.add(t);
        }
    }

    public ArrayList<Train> startSorting() {

        while (!arrivalTracks.isEmpty()){
            for (int i = 0; i < allTrains.size()*10; i++) {

                fillSortTrack();

                departingTrackDestination();

                fillDeparting();
            }
            if (finishedTrainCarts != 1) {
                finishedTrainCarts --;
            }

        }

        for (Train train : departingTrack) {
            finishedTrains.add(train);
        }
        return finishedTrains;

    }

    public void fillSortTrack() {
        if (sortTrack.getCartArray().isEmpty()) {
            if (!arrivalTracks.isEmpty()) {
                sortTrack = arrivalTracks.get(0);
                arrivalTracks.remove(arrivalTracks.get(0));
            }

        }
    }


    public void departingTrackDestination() {
        while (departingTrack.size() < 8) {
            Train t = new Train();
            t.addToCartArray(sortTrack.getCartArray().get(0));
            departingTrack.add(t);
            sortTrack.getCartArray().remove(0);

        }
    }

    public void fillDeparting() {
        ArrayList<Cart> usedSortTrack = new ArrayList<>();
        for (Cart cart : sortTrack.getCartArray()) {
            boolean isUsed = false;
            for (int i = 0; i < departingTrack.size(); i++) {
                if (departingTrack.get(i).getCartArray().isEmpty()) {
                    departingTrack.get(i).addToCartArray(cart);
                    usedSortTrack.add(cart);
                    isUsed = true;
                }
            }
            if (!isUsed) {
                if (cart.getDestination().equals(departingTrack.get(0).getCartArray().get(0).getDestination())) {

                    departingTrack.get(0).addToCartArray(cart);
                    checkDeparting(0);
                    usedSortTrack.add(cart);


                } else if (cart.getDestination().equals(departingTrack.get(1).getCartArray().get(0).getDestination())) {

                    departingTrack.get(1).addToCartArray(cart);
                    checkDeparting(1);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(2).getCartArray().get(0).getDestination())) {

                    departingTrack.get(2).addToCartArray(cart);
                    checkDeparting(2);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(3).getCartArray().get(0).getDestination())) {

                    departingTrack.get(3).addToCartArray(cart);
                    checkDeparting(3);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(4).getCartArray().get(0).getDestination())) {

                    departingTrack.get(4).addToCartArray(cart);
                    checkDeparting(4);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(5).getCartArray().get(0).getDestination())) {

                    departingTrack.get(5).addToCartArray(cart);
                    checkDeparting(5);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(6).getCartArray().get(0).getDestination())) {

                    departingTrack.get(6).addToCartArray(cart);
                    checkDeparting(6);
                    usedSortTrack.add(cart);

                } else if (cart.getDestination().equals(departingTrack.get(7).getCartArray().get(0).getDestination())) {

                    departingTrack.get(7).addToCartArray(cart);
                    checkDeparting(7);
                    usedSortTrack.add(cart);

                }

            }

        }
        for (int i = 0; i < 8; i++) {
            checkDeparting(i);
        }
        for (Cart cart : usedSortTrack) {
            sortTrack.getCartArray().remove(cart);
        }
        scratchTrain();
    }

    public void checkDeparting(int i) {
        if (!(departingTrack.get(i).getCartArray().size() < finishedTrainCarts)) {
            Train t = new Train();
            for (Cart c : departingTrack.get(i).getCartArray()) {
                t.addToCartArray(c);
            }
            finishedTrains.add(t);
            departingTrack.set(i, new Train());

        }

    }

    public void scratchTrain(){

        for (Cart cart : sortTrack.getCartArray()) {
            scratchTrack.getCartArray().add(cart);

            if (!(scratchTrack.getCartArray().size() < 10)) {
                Train t = new Train();
                for (Cart c : scratchTrack.getCartArray()) {
                    t.addToCartArray(c);
                }
                arrivalTracks.add(t);
                scratchTrack = new Train();
            }
        }
        sortTrack = new Train();
    }

}
