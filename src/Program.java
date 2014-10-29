import java.sql.SQLException;

/**
 * Created by Ekaterina Orlova on 08/10/14.
 */
public class Program {
    public static void main(String[] args) throws SQLException {
        DBHandler dbHandler = new DBHandler("root", "");

        dbHandler.copyFile("test.txt", "test");

        DBHandler dbHandler2 = new DBHandler("root", "");

        dbHandler2.showTable("test");

        DBHandler dbHandler3 = new DBHandler("root", "");
        dbHandler3.showTable("city");

        DBHandler dbHandler4 = new DBHandler("root", "");
        dbHandler4.getTable("test");

    }
}
