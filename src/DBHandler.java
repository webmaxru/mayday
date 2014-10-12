
import java.sql.*;
import java.util.ArrayList;

public class DBHandler {


    ConnectToDB db;


    public DBHandler(String user, String password) throws SQLException {
        db = new ConnectToDB("localhost", "world", user, password);
    }

    public void showTable(String tableName) throws SQLException {

        String query = "SELECT * FROM " + tableName;
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {
            //ResultSetMetaData rsmd = resultSet.getMetaData();
            TableReader reader = new TableReader(resultSet.getMetaData());
            while (resultSet.next()) {
                reader.showLine(resultSet);
            }
        }
    }

    public void copyFile(String fileName, String tableName) throws SQLException {
        FileParser fileToCopy = new FileParser(fileName);
        //String sql = "CREATE TABLE " + tableName + " IF NOT EXISTS;";

        PreparedStatement preparedStatement = null;
        try (Connection connection = db.getConnection()) {
            preparedStatement = connection
                    .prepareStatement("CREATE TABLE IF NOT EXISTS" + tableName + ";
            preparedStatement = connection
                    .prepareStatement("insert into " + tableName + " values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
            // parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

        }}
*/
}
