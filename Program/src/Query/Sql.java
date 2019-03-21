package Query;

import Database.Cart;
import Database.Train;

import java.sql.*;
import java.util.ArrayList;

public class Sql {

    public Sql() {

    }

    public static String writeYourOwn(String s, Connection conn) {
        String returnString = "\n";

        try{

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()){
                returnString += (rs.getString(1) + " - " + rs.getString(2) + "\n");
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }

        return returnString;

    }

    public static void addTrains(Train receivedTrain, Connection conn) {

        try {
            conn.setAutoCommit(false);

            ArrayList<Cart> cartArray = receivedTrain.getCartArray();

            String startString = "BEGIN; \nINSERT INTO Train (status_id) VALUE (1);\n" +
                    "SELECT LAST_INSERT_ID() INTO @trainID;\n";



            String addCartString = "";

            String endString = "COMMIT;";


            for (Cart cart : cartArray) {
                addCartString += "INSERT INTO Cart (cargo_id, weight_id, destination_id) " +
                    "VALUES ((SELECT cargo_id FROM Cargo WHERE type='" + cart.getCargo() + "'), " +
                    "(SELECT weight_id FROM Weight WHERE type='" + cart.getWeight() + "'), " +
                    "(SELECT destination_id FROM Destination WHERE name='" + cart.getDestination() + "'));\n" +
                    "INSERT INTO Train_cart_link (train_id, cart_id) " +
                    "VALUES (@trainID, LAST_INSERT_ID());\n";

            }

            PreparedStatement stmt1 = conn.prepareStatement(startString);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(addCartString);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = conn.prepareStatement(endString);
            stmt3.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException e) {

            e.getMessage();

        }
    }

    public static String getInfo(String type, int givenId, Connection conn) {

        String returnString = "Empty";
        String chosenID = Integer.toString(givenId);

        try{

            Statement stmt = conn.createStatement();

            if (type.equals("train")) {

                String trainSQL = "SELECT Train.train_id, Status.status FROM Train\n" +
                        "INNER JOIN Status on Train.status_id = Status.status_id\n" +
                        "WHERE Train.train_id = "+ chosenID +";";

                String cartSQL = "SELECT Train_cart_link.train_id, Train_cart_link.cart_id, Destination.name, Cargo.type, Weight.type FROM Train_cart_link\n" +
                        "INNER JOIN Cart on Train_cart_link.cart_id = Cart.cart_id\n" +
                        "INNER JOIN Destination on Cart.destination_id = Destination.destination_id\n" +
                        "INNER JOIN Cargo on Cart.cargo_id = Cargo.cargo_id\n" +
                        "INNER JOIN Weight on Cart.weight_id = Weight.weight_id\n" +
                        "WHERE train_id = "+ chosenID +";";

                ResultSet trs1 = stmt.executeQuery(trainSQL);

                while (trs1.next()) {
                    returnString = "================================\n" +
                            "Train [" + trs1.getInt(1) + "] - Status: [" + trs1.getString(2) + "]\n" +
                            "================================\n";
                }

                ResultSet trs2 = stmt.executeQuery(cartSQL);

                while (trs2.next()) {
                    returnString += "\nCart [" + trs2.getInt(2) + "]\n" +
                            "--------------------------------\n" +
                            "Destination:\t" + trs2.getString(3) + "\n" +
                            "Cargo:\t\t" + trs2.getString(4) + "\n" +
                            "Weight:\t\t" + trs2.getString(5) + "\n";
                }



            } else {

                String carSQL = "SELECT Cart.cart_id, Train_cart_link.train_id, Destination.name, Cargo.type, Weight.type FROM Cart\n" +
                        "INNER JOIN Train_cart_link ON Cart.cart_id = Train_cart_link.cart_id\n" +
                        "INNER JOIN Destination on Cart.destination_id = Destination.destination_id\n" +
                        "INNER JOIN Cargo on Cart.cargo_id = Cargo.cargo_id\n" +
                        "INNER JOIN Weight on Cart.weight_id = Weight.weight_id\n" +
                        "WHERE Cart.cart_id = " + chosenID +";";

                ResultSet crs = stmt.executeQuery(carSQL);

                while (crs.next()) {
                    returnString = "================================\n" +
                            "Cart [" + crs.getInt(1) + "] - Located on Train [" + crs.getInt(2) + "]\n" +
                            "================================\n" +
                            "Destination:\t" + crs.getString(3) + "\n" +
                            "Cargo:\t\t" + crs.getString(4) + "\n" +
                            "Weight:\t\t" + crs.getString(5) + "\n";
                }


            }

        }
        catch (SQLException e) {
            e.getMessage();
        }

        return returnString;

    }

    public static ArrayList<Train> getArrivingCars(Connection conn) {

        // Array vi gemmer togets vogne i.
        ArrayList<Cart> cartArray = new ArrayList<>();

        // Array vi gemmer toget i.
        ArrayList<Train> trainArray = new ArrayList<>();


        try{
            Statement stmt = conn.createStatement();

            String arrivingTrainSQL = "SELECT Train.train_id, Weight.type, Cargo.type, Destination.name FROM Cart\n" +
                    "INNER JOIN Weight on Cart.weight_id = Weight.weight_id\n" +
                    "INNER JOIN Cargo on Cart.cargo_id = Cargo.cargo_id\n" +
                    "INNER JOIN Destination on Cart.destination_id = Destination.destination_id\n" +
                    "INNER JOIN Train_cart_link ON Cart.cart_id = Train_cart_link.cart_id\n" +
                    "INNER JOIN Train ON Train_cart_link.train_id = Train.train_id\n" +
                    "WHERE Train_cart_link.train_id " +
                    "IN (SELECT train_id FROM Train WHERE status_id = 1)\n" +
                    "ORDER BY Train.train_id, Destination.name ASC;";

            ResultSet arrivingRS = stmt.executeQuery(arrivingTrainSQL);

            int currentID;
            int previousID = 0;
            cartArray.clear();
            while (arrivingRS.next()){

                currentID = arrivingRS.getInt(1);

                if (currentID == previousID || previousID == 0) {
                    Cart newCart = new Cart(arrivingRS.getString(2), arrivingRS.getString(3), arrivingRS.getString(4));
                    cartArray.add(newCart);
                } else {
                    Train newTrain = new Train(cartArray);
                    trainArray.add(newTrain);
                    cartArray.clear();
                    Cart newCart = new Cart(arrivingRS.getString(2), arrivingRS.getString(3), arrivingRS.getString(4));
                    cartArray.add(newCart);
                }
                previousID = currentID;

            }
            Train newTrain = new Train(cartArray);
            trainArray.add(newTrain);

        }
        catch (SQLException e) {
            e.getMessage();
        }

        return trainArray;





    }



}
