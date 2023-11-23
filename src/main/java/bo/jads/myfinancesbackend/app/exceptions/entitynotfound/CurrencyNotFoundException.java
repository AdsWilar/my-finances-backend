package bo.jads.myfinancesbackend.app.exceptions.entitynotfound;

public class CurrencyNotFoundException extends EntityNotFoundException {

    public CurrencyNotFoundException() {
        super("The Currency could not be found.");
    }

}
