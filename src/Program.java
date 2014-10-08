import java.sql.SQLException;

/**
 * Created by altavista on 08/10/14.
 */
public class Program {
    public static void main(String[] args) throws SQLException {
        DBHandler dbHandler = new DBHandler("root", "");

            dbHandler.showTable("country");

    }
}
