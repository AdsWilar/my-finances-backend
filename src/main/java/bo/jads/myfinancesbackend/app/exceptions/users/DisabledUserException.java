package bo.jads.myfinancesbackend.app.exceptions.users;

public class DisabledUserException extends UserException {

    public DisabledUserException() {
        super("Disabled user.");
    }

}
