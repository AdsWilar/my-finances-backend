package bo.jads.myfinancesbackend.app.exceptions.useraccounts;

public class AlreadyInactiveUserAccountException extends UserAccountException {

    public AlreadyInactiveUserAccountException() {
        super("The User Account is already INACTIVE.");
    }

}
