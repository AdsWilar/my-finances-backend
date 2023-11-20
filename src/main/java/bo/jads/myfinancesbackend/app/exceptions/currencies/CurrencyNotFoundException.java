package bo.jads.myfinancesbackend.app.exceptions.currencies;

public class CurrencyNotFoundException extends CurrencyException {

    public CurrencyNotFoundException() {
        super("The Currency could not be found.");
    }

}
