package accounts;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UserProfile {
    private final long id;
    private final String login;
    private final String pass;
    private final String email;

    public UserProfile(long id, String login, String pass, String email) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.id = 1;
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public long getId() { return id; }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
