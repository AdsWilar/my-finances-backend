package bo.jads.myfinancesbackend.app.exceptions.entitynotfound;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super("The User could not be found.");
    }

}
