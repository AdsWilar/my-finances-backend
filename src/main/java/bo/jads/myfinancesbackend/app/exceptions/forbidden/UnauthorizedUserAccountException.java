package bo.jads.myfinancesbackend.app.exceptions.forbidden;

public class UnauthorizedUserAccountException extends ForbiddenException {

    public UnauthorizedUserAccountException() {
        super("The User does not have authorization over the Account.");
    }

    public UnauthorizedUserAccountException(Throwable cause) {
        super("The User does not have authorization over the Account.", cause);
    }

}
