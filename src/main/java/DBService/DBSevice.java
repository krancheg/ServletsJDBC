package DBService;


import DBService.dao.SessionDAO;
import DBService.dao.TablesDAO;
import DBService.dao.UserDAO;
import accounts.Session;
import accounts.User;
import accounts.UserProfile;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSevice implements User, Session {
    private final Connection connection;

    public DBSevice() {
        this.connection = H2Connection();
    }

    public void addNewUser(UserProfile userProfile) {
        try {
            connection.setAutoCommit(false);
            new TablesDAO(connection).createTables();
            UserDAO userDAO = new UserDAO(this.connection);
            userDAO.addNewUser(userProfile);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public UserProfile getUserByLogin(String login) {
        try {
            return new UserDAO(connection).getUserByLogin(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public UserProfile getUserBySessionId(String sessionId) {
        try {
            return new UserDAO(connection).getUserBySessionId(sessionId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) {
        try {
            new SessionDAO(connection).addSession(sessionId,userProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteSession(String sessionId) {
        try {
            new SessionDAO(connection).deleteSession(sessionId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cleanAllTables() {
        try {
            new TablesDAO(connection).dropTables();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
