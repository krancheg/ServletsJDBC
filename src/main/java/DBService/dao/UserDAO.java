package DBService.dao;

import DBService.executor.Executor;
import accounts.User;
import accounts.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO implements User {

    private Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public void addNewUser(UserProfile userProfile) throws SQLException {
        String query = String.format("insert into users (login, pass, email) values ('%s','%s','%s')",
                userProfile.getLogin(),
                userProfile.getPass(),
                userProfile.getEmail());
        executor.execUpdate(query);
    }

    @Override
    public UserProfile getUserByLogin(String login) throws SQLException {
        String query = String.format("select * from users where login='%s'",login);
        return executor.executeQuery(query,resultSet -> {
            if (resultSet.next()) {
                return new UserProfile(resultSet.getString("login"),
                        resultSet.getString("pass"), resultSet.getString("email"));
            } else return null;
        });
    }

    @Override
    public UserProfile getUserBySessionId(String sessionId) throws SQLException {
        String query = String.format("select * from sessions left join users " +
                "on sessions.user_id = users.id where sessions.id=%d",sessionId);
        return executor.executeQuery(query,resultSet -> {
            resultSet.next();
            return new UserProfile(resultSet.getString("login"),
                    resultSet.getString("pass"), resultSet.getString("email"));
        });
    }

    /*
    Return id of user by login,
    if id of user not found, return -1
     */
    protected Long getIdByLogin(String login) throws SQLException {
        String query = String.format("select * from users where login='%s'",login);
        return (Long) executor.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                resultSet.next();
                return resultSet.getLong("id");
            } else return -1;
        });
    }

}
