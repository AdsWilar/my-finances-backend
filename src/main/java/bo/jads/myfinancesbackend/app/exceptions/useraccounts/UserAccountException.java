package bo.jads.myfinancesbackend.app.exceptions.useraccounts;

public class UserAccountException extends Exception {

    public UserAccountException(String message) {
        super(message);
    }

    public UserAccountException(String message, Throwable cause) {
        super(message, cause);
    }

}
