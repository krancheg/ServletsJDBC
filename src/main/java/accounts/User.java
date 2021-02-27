package accounts;

import java.sql.SQLException;

public interface User {

    void addNewUser(UserProfile userProfile) throws SQLException;

    UserProfile getUserByLogin(String login) throws SQLException;

    UserProfile getUserBySessionId(String sessionId) throws SQLException;

}
