package bo.jads.myfinancesbackend.app.exceptions.users;

public class UserEmailAlreadyRegisteredException extends UserException {

    public UserEmailAlreadyRegisteredException() {
        super("The user email is already registered");
    }

}
