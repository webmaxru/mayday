
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {


    ConnectToDB db;


    public DBHandler(String user, String password) throws SQLException {
        db = new ConnectToDB("localhost", "world", user, password);
    }



    public void showTable(String tableName) throws SQLException {

        String initialQuery = "show columns from " + tableName;
        String query = "SELECT ";
        List<String> columns = new ArrayList<>();
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(initialQuery)) {
            while (resultSet.next()) {
               columns.add(resultSet.getString(1));
            }

            for(String column: columns){
                query += column + ", ";
            }

            query = query.substring(0, query.length()-2) + " from " + tableName;
            System.out.println(query);
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement.executeQuery(query);
                while (resultSet2.next()) {
                    System.out.println(resultSet2.getString(1));
                }
        }
    }

}
