import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

/**
 * Created by altavista on 08/10/14.
 */

public class ConnectToDB implements AutoCloseable {

    MysqlDataSource ds;
    Connection connection;

    public ConnectToDB(String user, String password) {
        ds = new MysqlDataSource();
        ds.setDatabaseName("world");
        ds.setServerName("localhost");
        ds.setUser(user);
        ds.setPassword(password);
    }

    public ConnectToDB(String host, String db, String user,
                       String password) throws SQLException {
        ds = new MysqlDataSource();
        ds.setDatabaseName(db);
        ds.setServerName(host);
        ds.setUser(user);
        ds.setPassword(password);
        connection = ds.getConnection();
    }



    public Connection getConnection() throws SQLException {

            return connection;

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

