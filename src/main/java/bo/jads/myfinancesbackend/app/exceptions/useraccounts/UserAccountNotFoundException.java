package bo.jads.myfinancesbackend.app.exceptions.useraccounts;

public class UserAccountNotFoundException extends UserAccountException {

    public UserAccountNotFoundException() {
        super("The User Account could not be found.");
    }

}
