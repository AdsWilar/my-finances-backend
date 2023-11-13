package bo.jads.myfinancesbackend.app.exceptions.users;

public class InvalidPasswordException extends UserException {

    public InvalidPasswordException(String message) {
        super(message);
    }

}