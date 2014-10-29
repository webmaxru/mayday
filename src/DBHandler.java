
import java.sql.*;

public class DBHandler {


    ConnectToDB db;


    public DBHandler(String user, String password) throws SQLException {
        db = new ConnectToDB("localhost", "world", user, password);
    }



    public void dropTableIfExists(String tableName) throws SQLException {
        String query = String.format("DROP TABLE IF EXISTS %s", tableName);
            Connection connection = db.getConnection(); {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.printf("Table '%s' is dropped!%n", tableName);
        }
    }

    //Print table data in console
    public void showTable(String tableName) throws SQLException {

        //Assuming that it's quite safe to use select * statement in this case
        // because we'll anyway select all data from the table ignoring columns order

        String query = String.format("SELECT * FROM %s", tableName);
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            TableHandler tableHandler = new TableHandler(resultSet);
            tableHandler.showTable();
        }
        catch (SQLException e){
        e.printStackTrace();        }

    }

    //returns object TableHandler that contains table data and metadata
    public TableHandler getTable(String tableName) throws SQLException {

        String query = String.format("SELECT * FROM %s", tableName);
        TableHandler tableHandler = null;
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            tableHandler = new TableHandler(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableHandler;

    }



    //Create a mySQL table from a given file
    public void copyFile(String fileName, String tableName) throws SQLException {
        FileParser fileToCopy = new FileParser(fileName);

        //Try to establish connection
        try (Connection connection = db.getConnection();) {

            //Delete eventually existing table with this name
            dropTableIfExists(tableName);

            //Create table
            PreparedStatement preparedStatement = connection.prepareStatement(fileToCopy.prepareCreateTableQuery(tableName));
            preparedStatement.executeUpdate();
            System.out.println("Table " + tableName + " is created!");

            //Insert data
            preparedStatement = connection.prepareStatement(fileToCopy.prepareInsertQuery(tableName));
            preparedStatement.executeUpdate();
            System.out.println("Data inserted");


        } catch (SQLException e) {

            System.out.println(e.getMessage());


        }
    }

}

