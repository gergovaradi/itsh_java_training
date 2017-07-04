package registrationproject.persistence;

/**
 * Created by Training on 2017. 05. 15..
 */
public class LoginInformation {

    private String userName;
    private String password;

    public LoginInformation() {
    }

    public LoginInformation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
