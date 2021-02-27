package DBService.dao;

import DBService.executor.Executor;
import accounts.Session;
import accounts.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class SessionDAO implements Session {

    private final Executor executor;
    private Connection connection;

    public SessionDAO(Connection connection) {
        this.connection = connection;
        this.executor = new Executor(connection);
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) throws SQLException {
        UserDAO userDAO = new UserDAO(connection);
        Long id = userDAO.getIdByLogin(userProfile.getLogin());
        if (id == -1) {
            userDAO.addNewUser(userProfile);
            id = userDAO.getIdByLogin(userProfile.getLogin());
        }
        String query = String.format("insert into sessions (user_id) values (%d)",id);
        executor.execUpdate(query);
    }

    @Override
    public void deleteSession(String sessionId) throws SQLException {
        String query = String.format("delete from sessions where id=%d",sessionId);
        executor.execUpdate(query);
    }
}
