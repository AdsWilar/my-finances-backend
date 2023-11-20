package bo.jads.myfinancesbackend.app.exceptions.users;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super("The User could not be found.");
    }

}
