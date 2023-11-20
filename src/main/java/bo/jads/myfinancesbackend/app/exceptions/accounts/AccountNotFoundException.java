package bo.jads.myfinancesbackend.app.exceptions.accounts;

public class AccountNotFoundException extends AccountException {

    public AccountNotFoundException() {
        super("The Account could not be found.");
    }

}
