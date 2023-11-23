package bo.jads.myfinancesbackend.app.exceptions.entitynotfound;

public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException() {
        super("The Account could not be found.");
    }

}
