package bo.jads.myfinancesbackend.app.exceptions.useraccounts;

public class UnauthorizedUserAccountException extends UserAccountException {

    public UnauthorizedUserAccountException() {
        super("The User does not have authorization over the Account.");
    }

    public UnauthorizedUserAccountException(Throwable cause) {
        super("The User does not have authorization over the Account.", cause);
    }

}
