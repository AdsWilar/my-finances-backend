package bo.jads.myfinancesbackend.app.exceptions.users;

public class InvalidCredentialsException extends UserException {

    public InvalidCredentialsException() {
        super("Invalid credentials.");
    }

}
