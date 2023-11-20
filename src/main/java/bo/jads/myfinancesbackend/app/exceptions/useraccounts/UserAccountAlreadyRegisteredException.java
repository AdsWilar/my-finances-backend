package bo.jads.myfinancesbackend.app.exceptions.useraccounts;

public class UserAccountAlreadyRegisteredException extends UserAccountException {

    public UserAccountAlreadyRegisteredException() {
        super("The User is already associated to the Account.");
    }

}
