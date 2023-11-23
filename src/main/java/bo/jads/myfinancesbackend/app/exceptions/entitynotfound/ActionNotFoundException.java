package bo.jads.myfinancesbackend.app.exceptions.entitynotfound;

public class ActionNotFoundException extends EntityNotFoundException {

    public ActionNotFoundException() {
        super("The Action could not be found.");
    }

}
