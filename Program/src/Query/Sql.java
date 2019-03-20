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
            conn.close();

            System.out.println(startString + addCartString + endString);

        } catch (SQLException e) {

            e.getMessage();

        }
    }

}
