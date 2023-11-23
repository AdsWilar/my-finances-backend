package bo.jads.myfinancesbackend.app.exceptions.entitynotfound;

public class UserAccountNotFoundException extends EntityNotFoundException {

    public UserAccountNotFoundException() {
        super("The User Account could not be found.");
    }

}
