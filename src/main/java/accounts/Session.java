package accounts;

import java.sql.SQLException;

public interface Session {

    void addSession(String sessionId, UserProfile userProfile) throws SQLException;

    void deleteSession(String sessionId) throws SQLException;

}
