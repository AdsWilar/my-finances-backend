package bo.jads.myfinancesbackend.app.exceptions;

public class DirectoryCreationException extends Exception {

    public DirectoryCreationException() {
        super("The directory could not be created");
    }

}