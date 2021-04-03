package Model;

/**
 *  Main user of the app. Can register readers, create and remove borrowment records, browse books
 */
public class Librarian {
    private String username;
    private String password;

    public Librarian(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
