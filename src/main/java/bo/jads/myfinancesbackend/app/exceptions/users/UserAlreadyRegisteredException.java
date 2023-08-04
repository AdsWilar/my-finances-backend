package bo.jads.myfinancesbackend.app.exceptions.users;

public class UserAlreadyRegisteredException extends UserException {

    public UserAlreadyRegisteredException() {
        super("The user is already registered");
    }

}
