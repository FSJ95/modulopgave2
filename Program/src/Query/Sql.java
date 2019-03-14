package Query;

import java.sql.*;

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

}
