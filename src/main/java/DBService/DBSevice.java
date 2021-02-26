package DBService;


import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSevice {
    private final Connection connection;

    public DBSevice(Connection connection) {
        this.connection = H2Connection();
    }

    private Connection H2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
